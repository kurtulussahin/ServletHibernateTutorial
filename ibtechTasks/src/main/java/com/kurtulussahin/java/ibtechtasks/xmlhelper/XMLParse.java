package com.kurtulussahin.java.ibtechtasks.xmlhelper;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.tasks.cmd.CommandExecuter;
import com.kurtulussahin.java.ibtechtasks.tasks.model.Customer;

public class XMLParse {

	public Bag XMLParseAndCommandRun(String body) throws Exception {

		Document document = createDocumentFromBody(body);

		Customer customer = createCustomerObjectFromDocument(document);
		Bag bag = createBagFromCustomer(customer);
		Bag resultBag = executeCommandName(document, bag);

		return resultBag;
	}

	private Bag executeCommandName(Document document, Bag bag) throws Exception {
		String commandName = document.getDocumentElement().getElementsByTagName("commandName").item(0).getTextContent();
		Bag resultBag = CommandExecuter.execute(commandName, bag);
		return resultBag;
	}

	private Bag createBagFromCustomer(Customer customer) {
		Bag bag = new Bag();
		bag.put(BagKey.NAME, customer.getName());
		bag.put(BagKey.SURNAME, customer.getSurname());
		return bag;
	}

	private Customer createCustomerObjectFromDocument(Document document) {
		String customerName = document.getDocumentElement().getElementsByTagName("customerName").item(0)
				.getTextContent();
		String customerSurname = document.getDocumentElement().getElementsByTagName("customerSurname").item(0)
				.getTextContent();

		Customer customer = new Customer(customerName, customerSurname);
		System.out.println("-----> " + customer.getName());
		System.out.println("-----> " + customer.getSurname());
		return customer;
	}

	private Document createDocumentFromBody(String body)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(body)));
		document.getDocumentElement().normalize();
		return document;
	}
}

/*
 * <EXT> <command> <commandName>customer_add</commandName>
 * <customerName>name1</customerName>
 * <customerSurname>surname1</customerSurname> </command> </EXT>
 */
