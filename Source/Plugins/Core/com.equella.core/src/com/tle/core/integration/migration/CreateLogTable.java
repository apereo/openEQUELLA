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

package com.tle.core.integration.migration;

import com.tle.beans.Institution;
import com.tle.core.guice.Bind;
import com.tle.core.hibernate.impl.HibernateCreationFilter;
import com.tle.core.hibernate.impl.HibernateMigrationHelper;
import com.tle.core.hibernate.impl.TablesOnlyFilter;
import com.tle.core.integration.beans.AuditLogLms;
import com.tle.core.migration.AbstractCreateMigration;
import com.tle.core.migration.MigrationInfo;
import com.tle.core.plugins.impl.PluginServiceImpl;
import javax.inject.Singleton;

@Bind
@Singleton
@SuppressWarnings("nls")
public class CreateLogTable extends AbstractCreateMigration {
  private static final String KEY_PFX = PluginServiceImpl.getMyPluginId(CreateLogTable.class) + ".";

  @Override
  public MigrationInfo createMigrationInfo() {
    return new MigrationInfo(KEY_PFX + "migration");
  }

  @Override
  protected HibernateCreationFilter getFilter(HibernateMigrationHelper helper) {
    return new TablesOnlyFilter("audit_log_lms");
  }

  @Override
  protected Class<?>[] getDomainClasses() {
    return new Class<?>[] {AuditLogLms.class, Institution.class};
  }
}
