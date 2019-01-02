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

package com.tle.web.api.staging.interfaces.beans;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tle.web.api.interfaces.beans.RestBean;

public class MultipartBean implements RestBean
{
	private final String uploadId;

	@JsonCreator
	public MultipartBean(@JsonProperty("uploadId") String uploadId)
	{
		this.uploadId = uploadId;
	}

	public String getUploadId()
	{
		return uploadId;
	}
}