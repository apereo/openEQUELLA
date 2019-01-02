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

package com.tle.core.freetext.reindex;

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * @author Nicholas Read
 */
public class ItemMetadataFilter extends ReindexFilter
{
	private static final Logger LOGGER = Logger.getLogger(ItemMetadataFilter.class);

	private static final long serialVersionUID = 1L;

	private static final String[] NAMES = {"targetId"};

	private Object[] values;

	public ItemMetadataFilter(String targetId)
	{
		values = new Object[]{targetId};
	}

	@Override
	protected String getWhereClause()
	{
		return "where metadataSecurityTargets like :targetId";
	}

	@Override
	protected String[] getNames()
	{
		return NAMES;
	}

	@Override
	protected Object[] getValues()
	{
		// Bookend the target ID(s) with wildcards for the 'like' where clause.
		Object[] ret = Arrays.stream(values).map(s -> "%" + s + "%").toArray();
		if(LOGGER.isTraceEnabled()) {
			LOGGER.trace("Original values: " + Arrays.toString(values));
			LOGGER.trace("Wildcard values: " + Arrays.toString(ret));
		}
		return ret;
	}
}
