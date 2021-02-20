// Copyright 2021 Sebastian Kuerten
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestModTimes
{

	private Path file1;
	private Path file2;
	private Path file3;
	private Path file4;
	private Path file5;

	@Before
	public void setup() throws IOException
	{
		// file1 = file2 < file3 < file4
		file1 = Files.createTempFile("file", null);
		file2 = Files.createTempFile("file", null);
		file3 = Files.createTempFile("file", null);
		file4 = Files.createTempFile("file", null);
		file5 = Files.createTempFile("file", null);
		delete(file5);
		long base = 1460000000;
		Files.setLastModifiedTime(file1, FileTime.from(base, TimeUnit.SECONDS));
		Files.setLastModifiedTime(file2, FileTime.from(base, TimeUnit.SECONDS));
		Files.setLastModifiedTime(file3,
				FileTime.from(base + 60, TimeUnit.SECONDS));
		Files.setLastModifiedTime(file4,
				FileTime.from(base + 120, TimeUnit.SECONDS));
	}

	@After
	public void cleanUp()
	{
		delete(file1);
		delete(file2);
		delete(file3);
		delete(file4);
	}

	private void delete(Path path)
	{
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// ignore
		}
	}

	@Test
	public void test() throws IOException
	{
		// comparison is not strict!
		assertTrue(ModTimes.isNewerThan(file2, file1));
		assertTrue(ModTimes.isNewerThan(file1, file2));

		assertFalse(ModTimes.isNewerThan(file2, file3));
		assertFalse(ModTimes.isNewerThan(file3, file4));
		assertFalse(ModTimes.isNewerThan(file2, file4));

		assertTrue(ModTimes.isNewerThan(file3, file2));
		assertTrue(ModTimes.isNewerThan(file4, file3));
		assertTrue(ModTimes.isNewerThan(file4, file2));

		assertTrue(ModTimes.isNewerThan(file4, file2, file3));

		assertFalse(ModTimes.isNewerThan(file2, file3, file4));
		assertFalse(ModTimes.isNewerThan(file3, file2, file4));

		assertFalse(ModTimes.isNewerThan(file5, file2));
		assertFalse(ModTimes.isNewerThan(file5, file2, file3));
	}

	@Test(expected = NoSuchFileException.class)
	public void testException() throws IOException
	{
		ModTimes.isNewerThan(file2, file5);
	}

	@Test
	public void testCollection() throws IOException
	{
		// comparison is not strict!
		assertTrue(ModTimes.isNewerThan(file2, Arrays.asList(file1)));
		assertTrue(ModTimes.isNewerThan(file1, Arrays.asList(file2)));

		assertFalse(ModTimes.isNewerThan(file2, Arrays.asList(file3)));

		assertTrue(ModTimes.isNewerThan(file4, Arrays.asList(file2, file3)));
		assertFalse(ModTimes.isNewerThan(file2, Arrays.asList(file3, file4)));
	}

	@Test
	public void testIterable() throws IOException
	{
		// comparison is not strict!
		assertTrue(ModTimes.isNewerThanIterable(file2, Arrays.asList(file1)));
		assertTrue(ModTimes.isNewerThanIterable(file1, Arrays.asList(file2)));

		assertFalse(ModTimes.isNewerThanIterable(file2, Arrays.asList(file3)));

		assertTrue(ModTimes.isNewerThanIterable(file4,
				Arrays.asList(file2, file3)));
		assertFalse(ModTimes.isNewerThanIterable(file2,
				Arrays.asList(file3, file4)));
	}

}
