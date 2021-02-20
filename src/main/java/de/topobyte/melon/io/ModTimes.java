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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Collection;

public class ModTimes
{

	/**
	 * Test if 'file' has been modified after all of 'others'. Typically used in
	 * build systems where an artifact depends on a number of other files and
	 * needs to be recreated only if any of the other files has been modified
	 * after the artifact itself. This method can be used to check if an
	 * artifact is up to date. Comparison is not strict, i.e. 'after' has the
	 * meaning of >=, not > in this context.
	 */
	public static boolean isNewerThan(Path file, Path... others)
			throws IOException
	{
		if (!Files.exists(file)) {
			return false;
		}
		FileTime mod = Files.getLastModifiedTime(file);
		for (Path other : others) {
			FileTime otherMod = Files.getLastModifiedTime(other);
			if (otherMod.compareTo(mod) > 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Test if 'file' has been modified after all of 'others'. Typically used in
	 * build systems where an artifact depends on a number of other files and
	 * needs to be recreated only if any of the other files has been modified
	 * after the artifact itself. This method can be used to check if an
	 * artifact is up to date. Comparison is not strict, i.e. 'after' has the
	 * meaning of >=, not > in this context.
	 */
	public static boolean isNewerThan(Path file, Collection<Path> others)
			throws IOException
	{
		return isNewerThanIterable(file, others);
	}

	/**
	 * Test if 'file' has been modified after all of 'others'. Typically used in
	 * build systems where an artifact depends on a number of other files and
	 * needs to be recreated only if any of the other files has been modified
	 * after the artifact itself. This method can be used to check if an
	 * artifact is up to date. Comparison is not strict, i.e. 'after' has the
	 * meaning of >=, not > in this context.
	 * 
	 * Take special care when using this method because Path itself implements
	 * {@code Iterable<Path>}. Passing a Path as that argument likely has
	 * unexpected results.
	 */
	public static boolean isNewerThanIterable(Path file, Iterable<Path> others)
			throws IOException
	{
		if (!Files.exists(file)) {
			return false;
		}
		FileTime mod = Files.getLastModifiedTime(file);
		for (Path other : others) {
			FileTime otherMod = Files.getLastModifiedTime(other);
			if (otherMod.compareTo(mod) > 0) {
				return false;
			}
		}
		return true;
	}

}