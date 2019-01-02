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

package com.tle.core.filesystem.staging.users.impl;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.tle.core.events.UserSessionLogoutEvent;
import com.tle.core.events.listeners.UserSessionLogoutListener;
import com.tle.core.filesystem.staging.service.StagingService;
import com.tle.core.filesystem.staging.users.StagingCleanupService;
import com.tle.core.guice.Bind;

@Bind(StagingCleanupService.class)
@Singleton
public class StagingCleanupServiceImpl implements StagingCleanupService, UserSessionLogoutListener
{
	@Inject
	private StagingService stagingService;

	@Override
	public void userSessionDestroyedEvent(final UserSessionLogoutEvent event)
	{
		stagingService.removeAllStagingAreas(event.getSessionId());
	}
}
