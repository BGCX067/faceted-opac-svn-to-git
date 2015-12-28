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

import java.text.SimpleDateFormat;

import java.util.Hashtable;
import java.util.Vector;
import java.util.Locale;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

/**
 * Class used to parse multi-part forms.
 *
 * <p> Implements Form-based File Uploads in HTML as documentated in <a href="http://www.ietf.org/rfc/rfc1867.txt" target="PROTOCOL">RFC 1867</a> for supporting <b>&lt;FORM ENCTYPE="multipart/form-data" ACTION="</b><i>servletalias</i><b>" METHOD="POST"&gt;</b> HTML tag.
 *
 * <h4>Notes:</h4>
 * Internally the <b>parse()</b> method parses the post data and depending on the arguments used will store the uploaded file(s) on the file system or in the <b>FileObject</b> object(s).
 * <p>The <b>parse()</b> method will throw a <b>java.lang.Exception</b> if the post data is partually or fully processesd by the Servlet engine, Servlet, JavaServer Pages (JSP) engine or JSP page.
 *
 * @author Servertec
 *
 * @since 1.0.0 04/29/2001
 */
public class MultiPartForm
{
	/* THE DEFINITIONS BELOW MUST NOT BE ALTERED OR REMOVED. */
	private static final String COPYRIGHT = "Copyright (C) 2001-2005 Servertec. All rights reserved.";
	private static final String COMPANY_NAME = "Servertec";
	private static final String PRODUCT_NAME = "File Upload";
	private static final String ALT_PRODUCT_NAME = null;
	private static final String VERSION_NUMBER = "1.1.2";
	private static final String REVISION = null;
	private static final String RELEASE_DATE = "09/04/2005";
	private static boolean logo_displayed = false;
	private static final String RELEASE = "Open Source Release";

	private static final void displayLogo()
	{
		if(!logo_displayed)
		{
			logo_displayed = true;

			System.err.print(COMPANY_NAME);
			System.err.print(" (R) ");

			if(ALT_PRODUCT_NAME == null)
			{
				System.err.print(PRODUCT_NAME);
			}
			else
			{
				System.err.print(ALT_PRODUCT_NAME);
			}

			System.err.print(" Version ");
			System.err.print(VERSION_NUMBER);

			if(REVISION != null)
			{
				System.err.print(REVISION);
			}

			System.err.print(' ');
			System.err.print(RELEASE_DATE);
			System.err.print(' ');
			System.err.println(RELEASE);

			System.err.println(COPYRIGHT);

			System.err.println();
		}
	}

	protected static final int BUF_SIZE = 8 * 1024;

	private static final String CONTENT_BOUNDARY_TAG = "boundary=";
	private static final int EOF = -1;

	/**
	 * Returns a hashtable containing the form elements using the platform's default character set.
	 *
	 * <p>Converts encoded characters using the platform's default character set.
	 *
	 * @param request the servlet request object.
	 * @param basedir the base directory to use.
	 *
	 * <p><b>null</b>, if none. If none then the file are uploaded into a buffer which can be accessed using <a href="./FileUpload.html#getBuffer()">getBuffer()</a>.
	 *
	 * @param max_content_length the maximum length of the request.
	 *
	 * <p><b>-1</b> if unlimited. Using <b>-1</b> can open the server to denial of service attacks.
	 *
	 * @return contains the form elements and uploaded files.
	 *
	 * <p>Each form element is a Vector of items containing either a String or a FileUpload object. String is used by form elements and <a href="./FileUpload.html">FileUpload</a> object is used by uploaded files.
	 *
	 * @throws Exception for invalid content type, when content length exceeds maximum content length, when content boundary is not found, when end is found while searching for content and when content header is not valid.
	 *
	 * @since 1.0.0 04/29/2001
	 */
	public static Hashtable parse(HttpServletRequest request, String basedir, int max_content_length) throws Exception
	{
		return parse(request, basedir, max_content_length, null);
	}

	/**
	 * Returns a hashtable containing the form elements using the specified character set.
	 *
	 * <p>Converts encoded characters using the specified character set.
	 *
	 * @param request the servlet request object.
	 * @param basedir the base directory to use.
	 *
	 * <p><b>null</b>, if none. If none then the file is uploaded into a buffer which can be accessed using <a href="./FileUpload.html#getBuffer()">getBuffer()</a>.
	 *
	 * @param max_content_length the maximum length of the request.
	 *
	 * <p><b>-1</b> if unlimited. Using <b>-1</b> can open the server to denial of service attacks.
	 *
	 * @param charset the name of a supported character set. <b>null</b> if no character set is to be used.
	 *
	 * @return contains the form elements and uploaded files.
	 *
	 * <p>Each form element is a Vector of items containing either a String or a FileUpload object. String is used by form elements and <a href="./FileUpload.html">FileUpload</a> object is used by uploaded files.
	 *
	 * @throws Exception for invalid content type, when content length exceeds maximum content length, when content boundary is not found, when end is found while searching for content and when content header is not valid.
	 *
	 * @since 1.1.0 12/17/2001
	 */
	public static Hashtable parse(HttpServletRequest request, String basedir, int max_content_length, String charset) throws Exception
	{
		displayLogo();

		String method = request.getMethod();
		if(!method.equalsIgnoreCase("POST"))
		{
			throw new Exception("Unsupported method: " + method);
		}

		String contentType = request.getContentType();
		if(contentType == null)
		{
			throw new Exception("Content-Type was not found.");
		}
		else if(!contentType.toLowerCase().startsWith("multipart/form-data;"))
		{
			throw new Exception("Unsupported Content-Type: " + contentType);
		}

		if(max_content_length != -1)
		{
			int contentLength = request.getContentLength();
			if(contentLength > max_content_length)
			{
				throw new Exception("Content length exceeds maximum content length: " + contentLength + " > " + max_content_length);
			}
		}

		int offset = contentType.indexOf(CONTENT_BOUNDARY_TAG);
		if(offset == -1)
		{
			throw new Exception("Content boundary was not found: " + CONTENT_BOUNDARY_TAG);
		}

		String sdelimiter = contentType.substring(offset + CONTENT_BOUNDARY_TAG.length());
		if(sdelimiter == null)
		{
			throw new Exception("Content boundary was not found: " + CONTENT_BOUNDARY_TAG);
		}
		else if(sdelimiter.length() == 0)
		{
			throw new Exception("Content boundary was not found: " + CONTENT_BOUNDARY_TAG);
		}

		sdelimiter = "--" + sdelimiter;

		String edelimiter = sdelimiter + "--";

		int sOffset;
		int eOffset;
		String line;
		String lcLine;
		String fieldName;
		String remoteFilePath;
		String localFilePath;
		String value;
		FileUpload fileUpload;
		boolean contentFound = false;
		boolean labelFound;
		OutputStream os;
		Object obj;
		Vector v;
		ServletInputStream in = request.getInputStream();
		byte[] buffer = new byte[BUF_SIZE];
		byte[] tbuffer = new byte[BUF_SIZE];
		int bytesRead;
		int tbytesRead;
		ByteArrayOutputStream content = new ByteArrayOutputStream();
		Hashtable ht = new Hashtable();
		byte[] bytes;

		if((bytesRead = in.readLine(buffer, 0, buffer.length)) == EOF)
		{
			throw new Exception("Unexpected end of input stream while searching for content.");
		}

		bytesRead = removeEOS(buffer, bytesRead);

		if(!(new String(buffer, 0, bytesRead)).startsWith(sdelimiter))
		{
			throw new Exception("Content boundary was not found: " + sdelimiter);
		}

		while((bytesRead = in.readLine(buffer, 0, buffer.length)) != EOF)
		{
			labelFound = false;

			do
			{
				bytesRead = removeEOS(buffer, bytesRead);

				line = new String(buffer, 0, bytesRead);
				lcLine = line.toLowerCase();
				if(lcLine.startsWith("content-disposition"))
				{
					labelFound = true;
					break;
				}
			}
			while((bytesRead = in.readLine(buffer, 0, buffer.length)) != EOF);

			if(!labelFound)
			{
				throw new Exception("Unexpected end of input stream while searching for content.");
			}

			if(!lcLine.startsWith("content-disposition"))
			{
				throw new Exception("Invalid content header: " + line);
			}

			offset = lcLine.indexOf(';');
			if(offset == -1)
			{
				throw new Exception("Invalid content header: " + line);
			}

			sOffset = lcLine.indexOf("name=\"", offset);
			eOffset = lcLine.indexOf("\"", sOffset + 6);
			if(sOffset == -1 || eOffset == -1)
			{
				throw new Exception("Invalid content header: " + line);
			}

			fieldName = line.substring(sOffset + 6, eOffset);

			if(charset != null)
			{
				bytes = fieldName.getBytes();
				fieldName = new String(bytes, 0, bytes.length, charset);
			}

			sOffset = lcLine.indexOf("filename=\"", eOffset);
			eOffset = lcLine.indexOf("\"", sOffset + 10);
			if(sOffset == -1 || eOffset == -1)
			{
				remoteFilePath = null;
			}
			else
			{
				remoteFilePath = line.substring(sOffset + 10, eOffset);

				if(charset != null)
				{
					bytes = remoteFilePath.getBytes();
					remoteFilePath = new String(bytes, 0, bytes.length, charset);
				}
			}

			bytesRead = in.readLine(buffer, 0, buffer.length);
			if(bytesRead == EOF)
			{
				throw new Exception("Unexpected end of input stream while searching for content.");
			}

			bytesRead = removeEOS(buffer, bytesRead);

			line = new String(buffer, 0, bytesRead);
			if(line.toLowerCase().startsWith("content-type"))
			{
				contentType = line.substring(13).trim();

				bytesRead = in.readLine(buffer, 0, buffer.length);
				if(bytesRead == EOF)
				{
					throw new Exception("Unexpected end of input stream while searching for content.");
				}

				bytesRead = removeEOS(buffer, bytesRead);

				line = new String(buffer, 0, bytesRead);
			}
			else
			{
				contentType = "application/octet-stream";
			}

			if(line.length() != 0)
			{
				throw new Exception("Invalid content header: " + line);
			}

			if(remoteFilePath == null)
			{
				content.reset();

				if((tbytesRead = in.readLine(tbuffer, 0, tbuffer.length)) != EOF)
				{
					while((bytesRead = in.readLine(buffer, 0, buffer.length)) != EOF)
					{
						if((new String(buffer, 0, bytesRead)).startsWith(sdelimiter))
						{
							break;
						}

						content.write(tbuffer, 0, tbytesRead);

						System.arraycopy(buffer, 0, tbuffer, 0, bytesRead);
						tbytesRead = bytesRead;
					}

					tbytesRead = removeEOS(tbuffer, tbytesRead);

					content.write(tbuffer, 0, tbytesRead);
				}

				if(charset == null)
				{
					obj = content.toString();
				}
				else
				{
					bytes = content.toByteArray();
					obj = new String(bytes, 0, bytes.length, charset);
				}
			}
			else
			{
				os = null;

				try
				{
					if(remoteFilePath.length() == 0)
					{
						obj = null;

						while((bytesRead = in.readLine(buffer, 0, buffer.length)) != EOF)
						{
							if((new String(buffer, 0, bytesRead)).startsWith(sdelimiter))
							{
								break;
							}
						}
					}
					else
					{
						localFilePath = null;

						fileUpload = new FileUpload();
						fileUpload.contentType = contentType;

						fileUpload.remoteFilePath = remoteFilePath;

						if(basedir == null)
						{
							os = new ByteArrayOutputStream();
						}
						else
						{
							localFilePath = getLocalFilePath(basedir, remoteFilePath);
							os = new BufferedOutputStream(new FileOutputStream(localFilePath));
						}

						if((tbytesRead = in.readLine(tbuffer, 0, tbuffer.length)) != EOF)
						{
							while((bytesRead = in.readLine(buffer, 0, buffer.length)) != EOF)
							{
								if((new String(buffer, 0, bytesRead)).startsWith(sdelimiter))
								{
									break;
								}

								os.write(tbuffer, 0, tbytesRead);

								System.arraycopy(buffer, 0, tbuffer, 0, bytesRead);
								tbytesRead = bytesRead;
							}

							tbytesRead = removeEOS(tbuffer, tbytesRead);

							os.write(tbuffer, 0, tbytesRead);
						}

						if(basedir == null)
						{
							fileUpload.buffer = ((ByteArrayOutputStream)os).toByteArray();
						}
						else
						{
							fileUpload.localFilePath = localFilePath;
						}

						obj = fileUpload;
					}
				}
				finally
				{
					if(basedir != null && os != null)
					{
						os.close();
					}
				}
			}

			if(obj != null)
			{
				if(ht.containsKey(fieldName))
				{
					v = (Vector)ht.get(fieldName);
					v.addElement(obj);
				}
				else
				{
					v = new Vector();
					v.addElement(obj);
				}

				ht.put(fieldName, v);
			}

			if(bytesRead == -1)
			{
				throw new Exception("Unexpected end of input stream while searching for content.");
			}

			if((new String(buffer, 0, bytesRead)).startsWith(edelimiter))
			{
				return ht;
			}
		}

		return ht;
	}

	private static String getLocalFilePath(String _basedir, String _filePath) throws IOException
	{
		String filePath;

		int offset = _filePath.replace('\\', '/').lastIndexOf('/');
		if(offset == -1)
		{
			filePath = concatPaths(_basedir, _filePath);
		}
		else
		{
			filePath = concatPaths(_basedir, _filePath.substring(offset + 1));
		}

		return filePath.replace('/', File.separatorChar).replace('\\', File.separatorChar);
	}

	private final static String concatPaths(String base, String ext)
	{
		return concat(base, ext, File.separatorChar);
	}

	private final static String concat(String dir, String path, char delimiter)
	{
		if(dir.length() == 0)
		{
			return path;
		}
		else if(path.length() == 0)
		{
			return dir;
		}

		StringBuffer sb = new StringBuffer(dir);

		char chr = path.charAt(0);

		if(dir.endsWith("/") || dir.endsWith("\\"))
		{
			if(chr == '/' || chr == '\\')
			{
				sb.append(path.substring(1));
			}
			else
			{
				sb.append(path);

			}
		}
		else
		{
			if(chr == '/' || chr == '\\')
			{
				sb.append(path);
			}
			else
			{
				sb.append(delimiter);
				sb.append(path);
			}
		}

		return sb.toString();
	}

	private static int removeEOS(byte[] buffer, int bytes_read)
	{
		if(buffer[bytes_read - 1] == '\n')
		{
			bytes_read--;

			if(buffer[bytes_read - 1] == '\r')
			{
				bytes_read--;
			}
		}

		return bytes_read;
	}
}
