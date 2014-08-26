/*******************************************************************************
 * Copyleft (c) 2014, "Massimiliano Leone 
 * <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (FileUtils.java) is part of XML-JSON-Utils.
 * 
 *     FileUtils.java is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     FileUtils.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.datautils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return encoding.decode(ByteBuffer.wrap(encoded)).toString();
	}

	public static void writeToFile(String stringToWrite, String fileName) throws IOException {
		FileWriter fw = new FileWriter(fileName);
		fw.write(stringToWrite);
		fw.close();
	}
	
	public static void writeStringToNewFile(String originalFile, CharSequence originalExtension, CharSequence newExtension, String transformedString) throws IOException {
		String outputFile = originalFile.replace(originalExtension, newExtension);
		FileUtils.writeToFile(transformedString, outputFile );			
		System.out.println(outputFile+" written");		
	}

}
