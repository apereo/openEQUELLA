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

package com.tle.web.sections.equella

import com.tle.web.sections.SectionWriter
import com.tle.web.sections.events.PreRenderContext
import com.tle.web.sections.render.SectionRenderable

class ScalaSectionRenderable(render: SectionWriter => Unit,
                             prerender: PreRenderContext => Unit = _ => ())
    extends SectionRenderable {
  override def realRender(writer: SectionWriter): Unit = render(writer)
  override def preRender(info: PreRenderContext): Unit = prerender(info)
}
