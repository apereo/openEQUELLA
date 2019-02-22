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

package com.tle.core.activation.service;

import com.tle.beans.activation.ActivateRequest;
import com.tle.beans.item.Item;
import java.util.List;

/** @author Aaron */
public interface ActivationImplementation {
  List<ActivateRequest> getAllRequests(Item item);

  String getActivationDescription(ActivateRequest request);

  void validateItem(Item item, boolean ignoreOverrides, boolean skipPercentage);
}
