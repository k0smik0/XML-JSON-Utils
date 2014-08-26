/*******************************************************************************
 * Copyleft (c) 2014, "Massimiliano Leone 
 * <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (FileUtils.java) is part of XML-JSON-Utils.
 * 
 *     JSON2XML.java is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     JSON2XML.java is distributed in the hope that it will be useful,
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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class JSON2XML implements Transformer {
	
	public static void main(String[] args) {
		
		try {
			String jsonFile = args[0];
			String jsonFlattenAsString = FileUtils.readFile(jsonFile, Charset.defaultCharset());
			String xml = new JSON2XML().transform( jsonFlattenAsString );
			
			FileUtils.writeStringToNewFile(jsonFile, "json", "xml", xml);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String transform(String fileNameToTransform) {
//		JSONObject jsonObject = new JSONObject(fileNameToTransform);
		JSONArray jsonArray = new JSONArray(fileNameToTransform);
		String xml = "<items>";
		int length = jsonArray.length();
		for (int i=0;i<length;i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String xmlNode = XML.toString(jsonObject);
			xml += "<item>"+xmlNode+"</item>";
		}
		xml += "</items>";
		return xml;
	}
	
	

}
