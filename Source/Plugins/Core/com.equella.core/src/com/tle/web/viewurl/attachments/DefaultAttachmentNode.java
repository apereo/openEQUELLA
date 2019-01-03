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

package com.tle.web.viewurl.attachments;

import java.util.Collections;
import java.util.List;

import com.tle.beans.item.attachments.IAttachment;

public class DefaultAttachmentNode implements AttachmentNode
{
	private IAttachment attachment;
	private List<AttachmentNode> children;

	public DefaultAttachmentNode(IAttachment attachment)
	{
		this(attachment, null);
	}

	public DefaultAttachmentNode(IAttachment attachment, List<AttachmentNode> children)
	{
		this.attachment = attachment;
		this.children = children;
	}

	@Override
	public List<AttachmentNode> getChildren()
	{
		if( children == null )
		{
			return Collections.emptyList();
		}
		return children;
	}

	@Override
	public IAttachment getAttachment()
	{
		return attachment;
	}
}
