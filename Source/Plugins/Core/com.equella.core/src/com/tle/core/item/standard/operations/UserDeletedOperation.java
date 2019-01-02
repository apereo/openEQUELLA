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

package com.tle.core.item.standard.operations;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;

/**
 * @author jmaginnis
 */
// Sonar maintains that 'Class cannot be instantiated and does not provide any
// static methods or fields', but methinks thats bunkum
public class UserDeletedOperation extends AbstractStandardWorkflowOperation // NOSONAR
{
	private final String user;

	@AssistedInject
	private UserDeletedOperation(@Assisted String user)
	{
		this.user = user;
	}

	@Override
	public boolean execute()
	{
		if( isOwner(user) )
		{
			setOwner(""); //$NON-NLS-1$
		}
		getItem().getCollaborators().remove(user);
		return true;
	}
}
