/*
 * Licensed to the Apereo Foundation under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.tle.web.remoterepo.section;

import java.util.List;

import com.tle.core.fedsearch.RemoteRepoSearchResult;
import com.tle.core.guice.Bind;
import com.tle.web.freemarker.FreemarkerFactory;
import com.tle.web.freemarker.annotations.ViewFactory;
import com.tle.web.itemlist.AbstractListSection;
import com.tle.web.remoterepo.RemoteRepoListEntry;
import com.tle.web.sections.events.RenderContext;
import com.tle.web.sections.events.RenderEventContext;
import com.tle.web.sections.render.SectionRenderable;

/**
 * @author aholland
 */
@Bind
public class RemoteRepoListSection<R extends RemoteRepoSearchResult>
	extends
		AbstractListSection<RemoteRepoListEntry<R>, AbstractListSection.Model<RemoteRepoListEntry<R>>>
{
	@ViewFactory
	private FreemarkerFactory viewFactory;

	@Override
	protected SectionRenderable getRenderable(RenderEventContext context)
	{
		return viewFactory.createResult("fedsearch-list.ftl", this); //$NON-NLS-1$
	}

	@Override
	protected List<RemoteRepoListEntry<R>> initEntries(RenderContext context)
	{
		return getModel(context).getItems();
	}
}