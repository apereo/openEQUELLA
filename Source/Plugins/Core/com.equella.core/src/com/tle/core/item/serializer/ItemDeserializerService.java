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

package com.tle.core.item.serializer;

import com.tle.beans.item.ItemIdKey;
import com.tle.web.api.item.equella.interfaces.beans.EquellaItemBean;

public interface ItemDeserializerService
{
	/**
	 * @param itemBean
	 * @param stagingUuid
	 * @param lockId
	 * @param unlock
	 * @param ensureOnIndexList
	 * @return
	 */
	ItemIdKey edit(EquellaItemBean itemBean, String stagingUuid, String lockId, boolean unlock,
		boolean ensureOnIndexList);

	/**
	 * @param equellaItemBean
	 * @param stagingUuid
	 * @param dontSubmit
	 * @param ensureOnIndexList
	 * @return
	 */
	ItemIdKey newItem(EquellaItemBean equellaItemBean, String stagingUuid, boolean dontSubmit,
		boolean ensureOnIndexList, boolean noAutoArchive);

	/**
	 * @param equellaItemBean
	 * @param stagingUuid
	 * @param dontSubmit
	 * @param ensureOnIndexList
	 * @return
	 */
	ItemIdKey importItem(EquellaItemBean equellaItemBean, String stagingUuid, boolean ensureOnIndexList);
}
