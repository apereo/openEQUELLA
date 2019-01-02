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

package com.tle.web.api.item.equella.interfaces.beans;

public class HtmlPageAttachmentBean extends AbstractFileAttachmentBean
{
	private static final String TYPE = "html";

	private String parentFolder;

	public String getParentFolder()
	{
		return parentFolder;
	}

	public void setParentFolder(String parentFolder)
	{
		this.parentFolder = parentFolder;
	}

	@Override
	public String getRawAttachmentType()
	{
		return TYPE;
	}
}
