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

package com.tle.core.util.archive;

/**
 * @author aholland
 */
public abstract class ArchiveProgress
{
	private final long entryCount;

	public ArchiveProgress(final long entryCount)
	{
		this.entryCount = entryCount;
	}

	public long getEntryCount()
	{
		return entryCount;
	}

	public abstract void nextEntry(String entryPath);

	public void setCallbackMessageValue(String msg)
	{
		// Override where appropriate
	}

	public void incrementWarningCount()
	{
		// implement where appropriate
	}
}
