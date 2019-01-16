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

package com.tle.admin.security.tree.model;

import java.util.ArrayList;
import java.util.List;

import com.tle.beans.entity.itemdef.ItemDefinition;
import com.tle.beans.item.ItemStatus;
import com.tle.common.i18n.CurrentLocale;

/** @author Nicholas Read */
public class ItemStatusParentNode extends AbstractLazyNode {
  private final ItemDefinition itemDefinition;

  public ItemStatusParentNode(ItemDefinition itemDefinition) {
    super(
        CurrentLocale.get(
            "com.tle.admin.security.tree.model.itemstatusparentnode.name"), //$NON-NLS-1$
        null);
    this.itemDefinition = itemDefinition;
  }

  /*
   * (non-Javadoc)
   * @see com.tle.admin.security.tree.model.SecurityTreeNode#getTargetObject()
   */
  @Override
  public Object getTargetObject() {
    return null;
  }

  @Override
  protected List<SecurityTreeNode> getChildren() {
    List<SecurityTreeNode> results = new ArrayList<SecurityTreeNode>();
    for (ItemStatus status : ItemStatus.values()) {
      results.add(new ItemStatusLeafNode(itemDefinition, status));
    }
    return results;
  }
}
