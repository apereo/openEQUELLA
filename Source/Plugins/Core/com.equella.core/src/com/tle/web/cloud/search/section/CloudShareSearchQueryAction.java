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

package com.tle.web.cloud.search.section;

import javax.inject.Inject;

import com.tle.web.search.actions.AbstractShareSearchQueryAction;
import com.tle.web.sections.equella.annotation.PlugKey;
import com.tle.web.sections.equella.dialog.EquellaDialog;
import com.tle.web.sections.render.Label;
import com.tle.web.sections.standard.annotations.Component;

public class CloudShareSearchQueryAction extends AbstractShareSearchQueryAction
{
	@PlugKey("actions.share")
	private static Label LABEL;

	@Inject
	@Component(name = "csd")
	private CloudShareSearchQueryDialog shareDialog;

	@Override
	public Label getLabel()
	{
		return LABEL;
	}

	@Override
	public EquellaDialog<?> getDialog()
	{
		return shareDialog;
	}
}
