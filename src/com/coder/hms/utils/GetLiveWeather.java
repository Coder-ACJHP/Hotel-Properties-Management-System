package com.coder.hms.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetLiveWeather {

	private static LoggingEngine loggingEngine;
	
	public GetLiveWeather() {
		loggingEngine = LoggingEngine.getInstance();
	}
	
	public String getCurrentLocationWeather(String city) {
		final String query = "https://query.yahooapis.com/v1/public/yql?q=select%20item.condition%20from%20weather."
				+ "forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text=%27"+city+"%27)%20and%20u=%27c%27";
		final String weather =city +" "+ getXMLParser(query) + " °C"; 
		return weather;
	}
	
	private String getXMLParser(String query) {
		String results = "";
		URL queryToUrl = null;
			try {
				queryToUrl = new URL(query);
				DocumentBuilderFactory bdFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = bdFactory.newDocumentBuilder();
				Document doc = builder.parse(queryToUrl.openStream());
				
				doc.getDocumentElement().normalize();
				
				NodeList nList = doc.getElementsByTagName("yweather:condition");
				if (nList.getLength() > 0) {
	
					Element nodo = (Element) nList.item(0);
					results = nodo.getAttribute("temp");

				}
				
			} catch (MalformedURLException e) {
				loggingEngine.setMessage(e.getMessage());
			} catch (ParserConfigurationException e) {
				loggingEngine.setMessage(e.getMessage());
			} catch (SAXException e) {
				loggingEngine.setMessage(e.getMessage());
			} catch (IOException e) {
				loggingEngine.setMessage(e.getMessage());
				results = "OUT OF SERVICE";
			}
			return results;
	}

}
