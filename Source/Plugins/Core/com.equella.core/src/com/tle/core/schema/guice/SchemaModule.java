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

package com.tle.core.schema.guice;

import com.tle.core.guice.PluginTrackerModule;
import com.tle.core.schema.SchemaReferences;
import com.tle.core.schema.extension.SchemaSaveExtension;

@SuppressWarnings("nls")
public class SchemaModule extends PluginTrackerModule
{
	@Override
	protected String getPluginId()
	{
		return "com.tle.core.schema";
	}

	@Override
	protected void configure()
	{
		bindTracker(SchemaReferences.class, "schemaRefs", "bean");
		bindTracker(SchemaSaveExtension.class, "schemaSave", "bean");
	}

}
