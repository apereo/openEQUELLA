/*
 * Copyright 2017 Apereo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.web.api

import cats.data.{NonEmptyChain, OptionT, Validated}
import com.tle.core.db.{DB, RunWithDB}
import fs2.Stream
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.{ResponseBuilder, Status}

object ApiHelper {
  def runAndBuild(db: DB[ResponseBuilder]): Response =
    RunWithDB.execute(db.map(_.build()))

  def entityOrNotFound[A](o: Option[A]): ResponseBuilder =
    o.fold(Response.status(Status.NOT_FOUND))(Response.ok(_))

  def entityOrNotFoundDB[A](db: OptionT[DB, A]): DB[ResponseBuilder] =
    db.value.map(entityOrNotFound)

  def allEntities[A](stream: Stream[DB, A]): DB[EntityPaging[A]] =
    stream.compile.toVector.map(EntityPaging.allResults)

  def validationOrOk[A](validated: Validated[NonEmptyChain[A], Boolean]): ResponseBuilder =
    validationOr(validated.map(ok => if (ok) Response.ok() else Response.status(Status.NOT_FOUND)))

  def validationOr[A](validated: Validated[NonEmptyChain[A], ResponseBuilder]): ResponseBuilder =
    validated.valueOr(
      errs => Response.status(Response.Status.BAD_REQUEST).entity(errs.toNonEmptyVector.toVector)
    )

  def validationOrEntity[A, B](validated: Validated[NonEmptyChain[A], B]): ResponseBuilder =
    validationOr(validated.map(Response.ok))
}
