/*******************************************************************************
 * Copyleft (c) 2014, "Massimiliano Leone 
 * <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (FileUtils.java) is part of XML-JSON-Utils.
 *
 *     XMLToCSV.java is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     XMLToCSV.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.datautils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XMLToCSV implements Transformer {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		String xmlFile = args[0];
		try {
			
			String xmlAsString = FileUtils.readFile(xmlFile, Charset.defaultCharset());
			
			String csv = new XMLToCSV().transform( xmlAsString );
			
			FileUtils.writeStringToNewFile(xmlFile, "xml", "csv", csv);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public String transform(String dataAsString) throws JSONException {		
		
//		String xmlFile = "../datasets/totale_mancante03_e_visitabili.xml";
//		String xmlFile = xml;
		
//		"tot_esclusi_03_e_visitabili.csv";
		
		
		/*String xmlAsString = "";
		try {
			xmlAsString+= FileUtils.readFile(xmlFile, Charset.defaultCharset());
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		
		
		// xmlsimple way
		/*
		Serializer serializer = new Persister();		
		try {			
			PalermoOpendata palermoOpendata = serializer.read(PalermoOpendata.class, xmlAsString);
			System.out.println( palermoOpendata.toString() );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		
		// jackson way
//		XmlMapper xmlMapper = new XmlMapper();
//		ObjectMapper xmlMapper = new ObjectMapper();
//		PalermoOpendata palermoOpendata  = null;

		// the crockford way
			
			JSONObject xmlJSONObj = XML.toJSONObject(dataAsString);
			
			JSONObject luoghiJsonObject = xmlJSONObj
					.getJSONObject("palermo-opendata")
					.getJSONObject("luoghi");
			JSONArray allLuogoJsonArray = luoghiJsonObject.getJSONArray("luogo");
			
			JSONArray flattenJsonArray = new JSONArray();
			
			int length = allLuogoJsonArray.length();
			for (int i=0; i< length; i++) {
				JSONObject luogoJsonObject = allLuogoJsonArray.getJSONObject(i);
				String luogoFlattenString = JsonFlattener.encode(luogoJsonObject);

				flattenJsonArray.put(new JSONObject(luogoFlattenString));				
			}
			
//			writeToFile(flattenJsonArray.toString(), "tot_esclusi_03_e_visitabili__flatten.json");
			
			String csv = CDL.toString(flattenJsonArray);
//			System.out.println(csv);
			
			return csv;
	}

}
