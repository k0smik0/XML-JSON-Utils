/*******************************************************************************
 * Copyleft (c) 2014, "Massimiliano Leone 
 * <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (FileUtils.java) is part of XML-JSON-Utils.
 *
 *     Json2Csv.java is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     Json2Csv.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.datautils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.json.CDL;
import org.json.JSONArray;

public class Json2Csv implements Transformer {

	public static void main(String[] args) {
		
		String jsonFile = args[0];
		try {
			String jsonFlattenAsString = FileUtils.readFile(jsonFile, Charset.defaultCharset());
			String csv = new Json2Csv().transform( jsonFlattenAsString );
			
			FileUtils.writeStringToNewFile(jsonFile, "json", "csv", csv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String transform(String dataAsString)  {
		
//		String jsonFlattenAsString = FileUtils.readFile(jsonFile, Charset.defaultCharset());
		
		JSONArray jsonArray = new JSONArray(dataAsString);
		String jsonArrayAsString = CDL.toString( jsonArray );
		return jsonArrayAsString;
	}

}
