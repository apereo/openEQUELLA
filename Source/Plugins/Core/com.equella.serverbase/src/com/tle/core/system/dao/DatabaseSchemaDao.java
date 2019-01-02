/*
 * Copyright 2019 Apereo
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

package com.tle.core.system.dao;

import java.util.Collection;

import com.tle.beans.DatabaseSchema;
import com.tle.core.hibernate.dao.GenericDao;

public interface DatabaseSchemaDao extends GenericDao<DatabaseSchema, Long>
{
	// Nothing yet

	Collection<DatabaseSchema> enumerate();

	DatabaseSchema setOnline(long schemaId, boolean online);

	DatabaseSchema get(long schemaId);

	long add(DatabaseSchema ds);

	void edit(DatabaseSchema ds);

	boolean deleteSchema(long schemaId);
}
