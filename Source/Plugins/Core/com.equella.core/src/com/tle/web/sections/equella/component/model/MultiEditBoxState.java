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

package com.tle.web.sections.equella.component.model;

import java.util.Map;

import com.tle.web.sections.standard.model.HtmlComponentState;
import com.tle.web.sections.standard.model.HtmlValueState;

/**
 * @author Andrew Gibb
 */
public class MultiEditBoxState extends HtmlComponentState
{
	private int size;
	private Map<String, HtmlValueState> localeMap;

	public MultiEditBoxState()
	{
		super("multieditbox"); //$NON-NLS-1$
	}

	public Map<String, HtmlValueState> getLocaleMap()
	{
		return localeMap;
	}

	public void setLocaleMap(Map<String, HtmlValueState> localeMap)
	{
		this.localeMap = localeMap;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

}
