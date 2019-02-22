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

package com.dytech.edge.admin.script.options;

import com.tle.common.NameValue;
import java.util.List;

public class DefaultScriptOptions implements ScriptOptions {
  @Override
  public boolean hasWorkflow() {
    return false;
  }

  @Override
  public boolean hasItemStatus() {
    return true;
  }

  @Override
  public boolean restrictItemStatusForModeration() {
    return false;
  }

  @Override
  public boolean hasUserIsModerator() {
    return true;
  }

  @Override
  public List<NameValue> getWorkflowSteps() {
    throw new RuntimeException("Operation not supported");
  }

  @Override
  public String getWorkflowStepName(String value) {
    throw new RuntimeException("Operation not supported");
  }
}
