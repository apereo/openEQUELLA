/*
 * Licensed to The Apereo Foundation under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * The Apereo Foundation licenses this file to you under the Apache License,
 * Version 2.0, (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tle.core.hibernate.impl;

import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Index;
import org.hibernate.mapping.Table;
import org.hibernate.mapping.UniqueKey;

public interface HibernateCreationFilter {
  boolean includeTable(Table table);

  boolean includeUniqueKey(Table table, UniqueKey uk);

  boolean includeIndex(Table table, Index index);

  boolean includeForeignKey(Table table, ForeignKey fk);

  boolean includeGenerator(PersistentIdentifierGenerator pig);

  boolean includeObject(AuxiliaryDatabaseObject object);
}
