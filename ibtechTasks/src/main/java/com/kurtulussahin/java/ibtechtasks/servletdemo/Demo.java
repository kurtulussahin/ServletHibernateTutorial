package com.kurtulussahin.java.ibtechtasks.servletdemo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.kurtulussahin.java.ibtechtasks.tasks.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.tasks.bag.BagKey;
import com.kurtulussahin.java.ibtechtasks.xmlhelper.XMLParse;

/**
 * Servlet implementation class Demo
 */
public class Demo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Demo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XMLParse xmlParse = new XMLParse();
		try {
			
			String body = convertHttpServletRequestToStringBody(request);
			Bag bag = xmlParse.XMLParseAndCommandRun(body);
			writeResponse(response, bag);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void writeResponse(HttpServletResponse response, Bag bag) throws IOException {
		PrintWriter writer = response.getWriter();
		writer.append("<EXT>");
		writer.append("<name>" + bag.getValue(BagKey.NAME).toString() + "</name>");
		writer.append("<surname>" + bag.getValue(BagKey.SURNAME).toString() + "</surname>");
		writer.append("</EXT>");
	}

	private String convertHttpServletRequestToStringBody(HttpServletRequest request) throws IOException {
		String body=request.getReader().lines().collect(Collectors.joining());
		return body;
	}

}
