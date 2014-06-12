package com.stock.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.stock.model.CompanyModel;

@Service("companyParser")
public class CompanyParser {
	private SAXParserFactory factory;
	private SAXParser saxParser;
	private DefaultHandler handler;
	private List<CompanyModel> companyList;

	public CompanyParser() {
		companyList = new ArrayList<CompanyModel>();
		factory = SAXParserFactory.newInstance();
		try {
			File file = new File(
					"E:/devprogs/eclipse-workspace/spring_recipes/stock-suite/resources/companyList.xml");
			saxParser = factory.newSAXParser();
			handler = new DefaultHandler() {

				//
				boolean industry;
				boolean company;

				public void startElement(String uri, String localName,
						String qName, Attributes attributes)
						throws SAXException {

					if (qName.equalsIgnoreCase("industry"))
						industry = true;
					else if (qName.equalsIgnoreCase("company")) {
						company = true;
						CompanyModel cm = new CompanyModel();
						cm.setName(attributes.getValue(0));
						cm.setSymbol(attributes.getValue(1));
						if (!companyList.contains(cm))
							companyList.add(cm);
					}
				}

				public void endElement(String uri, String localName,
						String qName) throws SAXException {

				}

				public void characters(char ch[], int start, int length)
						throws SAXException {
					if (industry) {
						industry = false;
					}
					if (company)
						company = false;
				}
			};
			saxParser.parse(file, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<CompanyModel> getCompanyList() {
		return companyList;
	}

}
