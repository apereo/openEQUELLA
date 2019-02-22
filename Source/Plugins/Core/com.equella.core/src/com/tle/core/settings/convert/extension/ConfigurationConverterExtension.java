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

package com.tle.core.settings.convert.extension;

import com.tle.common.settings.ConfigurationProperties;
import com.tle.core.settings.service.ConfigurationService;
import java.util.Map;
import javax.inject.Inject;

/** @author Aaron */
public abstract class ConfigurationConverterExtension<T extends ConfigurationProperties> {
  @Inject protected ConfigurationService configurationService;

  public abstract T construct();

  public void run(Map<Long, Long> old2new) {
    T t = construct();
    t = configurationService.getProperties(t);
    clone(t, old2new);
    configurationService.setProperties(t);
  }

  public abstract void clone(T t, Map<Long, Long> old2new);
}
