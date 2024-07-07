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

import com.kurtulussahin.java.ibtechtasks.task1.bag.Bag;
import com.kurtulussahin.java.ibtechtasks.task1.bag.BagKey;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XMLParse xmlParse = new XMLParse();
		try {
//			Bag bag = xmlParse.XMLParseFileAndCommandRun("customer");
			Bag bag = xmlParse.XMLParseAndCommandRun(request.getReader().lines().collect(Collectors.joining()));
			PrintWriter writer = response.getWriter();
			writer.append("<EXT>");
			//writer.append("<id>" + bag.getValue(BagKey.ID).toString() + "</id>");
			writer.append("<name>" + bag.getValue(BagKey.NAME).toString() + "</name>");
			writer.append("<surname>" + bag.getValue(BagKey.SURNAME).toString() + "</surname>");
			writer.append("</EXT>");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//doGet(request, response);
		//doGet(request, response);
	}

}
