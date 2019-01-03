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

package com.tle.web.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileContentStream extends AbstractContentStream
{
	private File file;
	private FileInputStream inp;

	public FileContentStream(File file, String filename, String mimeType)
	{
		super(filename, mimeType);
		this.file = file;
	}

	@Override
	public boolean exists()
	{
		return file != null && file.exists() && !file.isDirectory();
	}

	@Override
	public long getContentLength()
	{
		return file.length();
	}

	@Override
	public File getDirectFile()
	{
		return file;
	}

	@Override
	public long getLastModified()
	{
		return file.lastModified();
	}

	@Override
	public InputStream getInputStream() throws IOException
	{
		if( inp == null )
		{
			inp = new FileInputStream(file);
		}
		return inp;
	}

}
