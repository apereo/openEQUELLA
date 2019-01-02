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

package com.tle.beans;

import com.tle.common.Pair;

import java.util.Objects;

/**
 * @author Nicholas Read
 */
public class NameId extends Pair<String, Long>
{
	private static final long serialVersionUID = 1;

	public NameId()
	{
		super();
	}

	public NameId(String name, long id)
	{
		super(name, id);
	}

	public String getName()
	{
		return getFirst();
	}

	public void setName(String name)
	{
		setFirst(name);
	}

	public long getId()
	{
		return getSecond();
	}

	public void setId(long id)
	{
		setSecond(id);
	}

	@Override
	public boolean checkFields(Pair<String, Long> rhs)
	{
		// Only check the value of this object type
		return Objects.equals(rhs.getSecond(), getSecond());
	}
}
