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

/**
 * @author Nicholas Read
 */
public class InstitutionFilter extends ReindexFilter
{
	private static final long serialVersionUID = 1L;

	private static final String[] NAMES = {};

	private Object[] values;

	public InstitutionFilter()
	{
		values = new Object[]{};
	}

	@Override
	protected String getWhereClause()
	{
		return "where 1 = 1"; //$NON-NLS-1$
	}

	@Override
	protected String[] getNames()
	{
		return NAMES;
	}

	@Override
	protected Object[] getValues()
	{
		return values;
	}
}
