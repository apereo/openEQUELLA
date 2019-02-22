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

package com.tle.web.api.item.interfaces.beans;

import com.tle.common.interfaces.UuidReference;
import com.tle.web.api.interfaces.beans.AbstractExtendableBean;
import javax.xml.bind.annotation.XmlRootElement;

/** @author Aaron */
@XmlRootElement
public class NavigationTabBean extends AbstractExtendableBean {
  private String name;
  private UuidReference attachment;
  private String viewer;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UuidReference getAttachment() {
    return attachment;
  }

  public void setAttachment(UuidReference attachment) {
    this.attachment = attachment;
  }

  public String getViewer() {
    return viewer;
  }

  public void setViewer(String viewer) {
    this.viewer = viewer;
  }
}
