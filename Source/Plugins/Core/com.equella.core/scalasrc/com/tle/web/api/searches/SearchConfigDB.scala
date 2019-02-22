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

package com.tle.web.api.searches

import java.util.UUID

import cats.data.OptionT
import com.tle.core.db.DB
import com.tle.core.settings.SettingsDB

object SearchConfigDB {

  def configName(id: UUID): String         = s"searchconfig.$id"
  def pageConfigName(name: String): String = s"searchpage.$name"

  def writeConfig(id: UUID, config: SearchConfig): DB[Unit] =
    SettingsDB.setJsonProperty(configName(id), config)

  def readConfig(id: UUID): OptionT[DB, SearchConfig] =
    SettingsDB.jsonProperty(configName(id))

  def readPageConfig(page: String): OptionT[DB, SearchPageConfig] =
    SettingsDB.jsonProperty(pageConfigName(page))

  def writePageConfig(page: String, config: SearchPageConfig): DB[Unit] =
    SettingsDB.setJsonProperty(pageConfigName(page), config)
}
