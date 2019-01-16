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

package com.dytech.edge.wizard.beans.control;

import java.io.Serializable;

import com.tle.beans.entity.LanguageBundle;

public class WizardControlItem implements Serializable {
  private static final long serialVersionUID = 1;

  private LanguageBundle name;
  private String value;
  private String defaultValue;

  public WizardControlItem() {
    this(null, "", "");
  }

  public WizardControlItem(LanguageBundle name, String value) {
    this(name, value, "");
  }

  public WizardControlItem(LanguageBundle name, String value, String defaultValue) {
    this.name = name;
    this.value = value;
    this.defaultValue = defaultValue;
  }

  public String getDefault() {
    return defaultValue;
  }

  public void setDefault(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public LanguageBundle getName() {
    return name;
  }

  public void setName(LanguageBundle name) {
    this.name = name;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public boolean isDefaultOption() {
    return Boolean.parseBoolean(defaultValue);
  }

  public void setDefaultOption(boolean b) {
    defaultValue = Boolean.toString(b);
  }
}
