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

package com.tle.cal.service;

import javax.inject.Inject;

import com.dytech.devlib.PropBagEx;
import com.tle.beans.item.Item;
import com.tle.core.guice.Bind;
import com.tle.core.item.operations.AbstractWorkflowOperation;

@Bind
public class CALCollectOperation extends AbstractWorkflowOperation {
  @Inject private CALMetadataCollection metadataCollection;

  @Override
  public boolean execute() {
    PropBagEx itemXml = getItemXml();
    Item item = getItem();
    metadataCollection.metadataChanged(item, itemXml);
    return false;
  }
}
