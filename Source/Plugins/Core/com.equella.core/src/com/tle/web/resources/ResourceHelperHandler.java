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

package com.tle.web.resources;

import com.tle.core.guice.Bind;
import com.tle.web.sections.Section;
import com.tle.web.sections.SectionTree;
import com.tle.web.sections.registry.handler.CachedScannerHandler;
import javax.inject.Singleton;

@Bind
@Singleton
public class ResourceHelperHandler extends CachedScannerHandler<AnnotatedResourceHelperScanner> {

  @Override
  protected AnnotatedResourceHelperScanner newEntry(Class<?> clazz) {
    return new AnnotatedResourceHelperScanner(clazz, this);
  }

  @Override
  public void registered(String id, SectionTree tree, Section section) {
    getForClass(section.getClass()).setup(section);
  }
}
