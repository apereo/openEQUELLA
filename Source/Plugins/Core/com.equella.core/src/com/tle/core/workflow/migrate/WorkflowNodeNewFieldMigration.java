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

package com.tle.core.workflow.migrate;

import com.google.inject.Singleton;
import com.tle.core.guice.Bind;
import com.tle.core.hibernate.impl.HibernateMigrationHelper;
import com.tle.core.migration.AbstractHibernateSchemaMigration;
import com.tle.core.migration.MigrationInfo;
import com.tle.core.migration.MigrationResult;
import com.tle.core.plugins.impl.PluginServiceImpl;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.AccessType;
import org.hibernate.classic.Session;

@Bind
@Singleton
@SuppressWarnings("nls")
public class WorkflowNodeNewFieldMigration extends AbstractHibernateSchemaMigration {
  private static String prefix =
      PluginServiceImpl.getMyPluginId(WorkflowNodeNewFieldMigration.class) + ".";

  @Override
  public MigrationInfo createMigrationInfo() {
    return new MigrationInfo(prefix + "script.migration.addproceednext.title");
  }

  @Override
  protected void executeDataMigration(
      HibernateMigrationHelper helper, MigrationResult result, Session session) throws Exception {
    result.incrementStatus();
  }

  @Override
  protected int countDataMigrations(HibernateMigrationHelper helper, Session session) {
    return 1;
  }

  @Override
  protected List<String> getDropModifySql(HibernateMigrationHelper helper) {
    return null;
  }

  @Override
  protected List<String> getAddSql(HibernateMigrationHelper helper) {
    return helper.getAddColumnsSQL("workflow_node", "proceed_next");
  }

  @Override
  protected Class<?>[] getDomainClasses() {
    return new Class<?>[] {FakeWorkflowNode.class};
  }

  @Entity(name = "WorkflowNode")
  @AccessType("field")
  public static class FakeWorkflowNode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    boolean proceedNext;
  }
}
