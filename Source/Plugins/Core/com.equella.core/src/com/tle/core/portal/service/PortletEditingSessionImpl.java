/*
 * Copyright 2019 Apereo
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

package com.tle.core.portal.service;

import com.tle.common.EntityPack;
import com.tle.common.portal.entity.Portlet;
import com.tle.core.entity.service.impl.EntityEditingSessionImpl;

/**
 * @author aholland
 */
public class PortletEditingSessionImpl extends EntityEditingSessionImpl<PortletEditingBean, Portlet>
	implements
		PortletEditingSession
{
	private static final long serialVersionUID = 1L;

	public PortletEditingSessionImpl(String sessionId, EntityPack<Portlet> pack, PortletEditingBean bean)
	{
		super(sessionId, pack, bean);
	}
}
