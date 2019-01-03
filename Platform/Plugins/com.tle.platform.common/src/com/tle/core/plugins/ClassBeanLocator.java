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

package com.tle.core.plugins;

public class ClassBeanLocator<T> implements BeanLocator<T>
{
	private static final long serialVersionUID = 1L;
	private final Class<? extends T> clazz;

	public ClassBeanLocator(Class<? extends T> clazz)
	{
		this.clazz = clazz;
	}

	@SuppressWarnings({"unchecked", "nls"})
	@Override
	public T get()
	{
		PluginService pluginService = AbstractPluginService.get();
		String pluginId = pluginService.getPluginIdForObject(clazz);
		return (T) AbstractPluginService.get().getBean(pluginId, "bean:" + clazz.getName());
	}
}
