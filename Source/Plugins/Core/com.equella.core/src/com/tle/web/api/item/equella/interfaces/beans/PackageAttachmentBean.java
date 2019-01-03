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

package com.tle.web.api.item.equella.interfaces.beans;

@SuppressWarnings("nls")
public class PackageAttachmentBean extends EquellaAttachmentBean
{
	private long size;
	private String packageFile;
	private boolean expand;

	public long getSize()
	{
		return size;
	}

	public void setSize(long size)
	{
		this.size = size;
	}

	public String getPackageFile()
	{
		return packageFile;
	}

	public void setPackageFile(String packageFile)
	{
		this.packageFile = packageFile;
	}

	@Override
	public String getRawAttachmentType()
	{
		return "ims";
	}

	public boolean isExpand()
	{
		return expand;
	}

	public void setExpand(boolean expand)
	{
		this.expand = expand;
	}
}
