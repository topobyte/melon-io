// Copyright 2016 Sebastian Kuerten
//
// This file is part of melon-io.
//
// melon-io is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// melon-io is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with melon-io. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.melon.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamUtil
{

	/**
	 * Open a new {@link FileInputStream} and wrap it in a
	 * {@link BufferedInputStream}.
	 * 
	 * @param file
	 *            the file to open.
	 * @return the created input stream.
	 */
	public static InputStream bufferedInputStream(File file)
			throws FileNotFoundException
	{
		InputStream in = new FileInputStream(file);
		return new BufferedInputStream(in);
	}

	/**
	 * Open a new {@link FileInputStream} and wrap it in a
	 * {@link BufferedInputStream}.
	 * 
	 * @param path
	 *            the path of the file to open.
	 * @return the created input stream.
	 */
	public static InputStream bufferedInputStream(String path)
			throws FileNotFoundException
	{
		InputStream in = new FileInputStream(path);
		return new BufferedInputStream(in);
	}

	/**
	 * Open a new {@link FileInputStream} and wrap it in a
	 * {@link BufferedInputStream}.
	 * 
	 * @param path
	 *            the path of the file to open.
	 * @return the created input stream.
	 */
	public static InputStream bufferedInputStream(Path path) throws IOException
	{
		InputStream in = Files.newInputStream(path);
		return new BufferedInputStream(in);
	}

	/**
	 * Open a new {@link FileOutputStream} and wrap it in a
	 * {@link BufferedOutputStream}.
	 * 
	 * @param file
	 *            the file to open.
	 * @return the created output stream.
	 */
	public static OutputStream bufferedOutputStream(File file)
			throws FileNotFoundException
	{
		OutputStream out = new FileOutputStream(file);
		return new BufferedOutputStream(out);
	}

	/**
	 * Open a new {@link FileOutputStream} and wrap it in a
	 * {@link BufferedOutputStream}.
	 * 
	 * @param file
	 *            the path of the file to open.
	 * @return the created output stream.
	 */
	public static OutputStream bufferedOutputStream(String path)
			throws FileNotFoundException
	{
		OutputStream out = new FileOutputStream(path);
		return new BufferedOutputStream(out);
	}

	/**
	 * Open a new {@link FileOutputStream} and wrap it in a
	 * {@link BufferedOutputStream}.
	 * 
	 * @param file
	 *            the path of the file to open.
	 * @return the created output stream.
	 */
	public static OutputStream bufferedOutputStream(Path path)
			throws IOException
	{
		OutputStream out = Files.newOutputStream(path);
		return new BufferedOutputStream(out);
	}

}
