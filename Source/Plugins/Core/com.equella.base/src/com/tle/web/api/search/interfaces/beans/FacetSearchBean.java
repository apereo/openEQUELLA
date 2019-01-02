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

package com.tle.web.api.search.interfaces.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.tle.web.api.interfaces.beans.RestBean;

/**
 * @author Dustin<br>
 * <br>
 *         A bit pointless right now, might add to it later though
 */
@XmlRootElement
public class FacetSearchBean implements RestBean
{
	private List<FacetBean> results;

	public List<FacetBean> getResults()
	{
		return results;
	}

	public FacetSearchBean setResults(List<FacetBean> results)
	{
		this.results = results;
		return this;
	}
}
