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

package com.tle.web.sections.js.generic.expression;

import com.tle.annotation.NonNullByDefault;
import com.tle.web.sections.events.PreRenderContext;
import com.tle.web.sections.events.RenderContext;

/**
 * @author aholland
 */
@NonNullByDefault
public class ParentFrameElementExpression extends AbstractExpression implements JSElementExpression
{
	private final JSElementExpression parentExpression;

	public ParentFrameElementExpression(JSElementExpression parentExpression)
	{
		this.parentExpression = parentExpression;
	}

	@Override
	public String getExpression(RenderContext info)
	{
		return "self.parent." + parentExpression.getExpression(info); //$NON-NLS-1$
	}

	@Override
	public void preRender(PreRenderContext info)
	{
		// nada
	}
}
