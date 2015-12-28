/*
 * Copyright (c) 2001-2005 Servertec. All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * THIS NOTICE MUST NOT BE ALTERED NOR REMOVED.
 *
 * CopyrightVersion 1.0
 */

package stec.servlet.http;

/**
 * Methods for accessing file upload object.
 *
 * @author Servertec
 *
 * @since 1.0.0 04/29/2001
 */
public class FileUpload
{
	protected String remoteFilePath;
	protected String localFilePath;
	protected String contentType;
	protected byte[] buffer;

	/**
	 * Returns the remote file path and name.
	 *
	 * @return the remote file path and name.
	 *
	 * @since 1.0.0 04/29/2001
	 */
	public String getRemoteFilePath()
	{
		return remoteFilePath;
	}

	/**
	 * Returns the local file path and name.
	 *
	 * @return the local file path and name. null if not written to disk and in memory buffer that can be retrieved using getBuffer().
	 *
	 * @since 1.0.0 04/29/2001
	 */
	public String getLocalFilePath()
	{
		return localFilePath;
	}

	/**
	 * Returns the content type.
	 *
	 * @return the content type.
	 *
	 * @since 1.0.0 04/29/2001
	 */
	public String getContentType()
	{
		return contentType;
	}

	/**
	 * Returns a byte array containing the uploaded file.
	 *
	 * @return the uploaded file.
	 *
	 * @since 1.0.0 04/29/2001
	 */
	public byte[] getBuffer()
	{
		return buffer;
	}
}
