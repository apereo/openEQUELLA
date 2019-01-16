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

package com.tle.web.search.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tle.beans.item.ItemStatus;
import com.tle.web.sections.SectionResult;
import com.tle.web.sections.events.RenderEventContext;

public class FilterByItemStatusTaskSection extends FilterByItemStatusSection {
  private static List<ItemStatus> MOD_STATUSES =
      Arrays.asList(ItemStatus.LIVE, ItemStatus.MODERATING, ItemStatus.REVIEW);

  @Override
  public SectionResult renderHtml(RenderEventContext context) {
    getModel(context).setHideCheckBox(true);
    return super.renderHtml(context);
  }

  @Override
  protected List<ItemStatus> getStatusList() {
    return MOD_STATUSES;
  }
}
