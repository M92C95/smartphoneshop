package de.smartphoneshop.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.smartphoneshop.controller.util.SmartphoneUtil;
import de.smartphoneshop.smartphone.Smartphone;

@WebServlet("/ProduktServlet")
public class ProduktServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final SmartphoneUtil smartphoneUtil = new SmartphoneUtil();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProduktServlet() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	try {
	    // holen alle schuhe aus der Datenbank
	    List<Smartphone> smartphoneliste = smartphoneUtil.getAlleSmartphones();
	    System.out.println(smartphoneliste.toString());
	    // schuhliste wird als Attribut gesetzt, damit in der jsp über
	    // expressionlanguage darauf zugegriffen werden kann
	    request.setAttribute("SMARTPHONELISTE", smartphoneliste);
	    // wird an index.jsp weitergeleitet
	    request.getRequestDispatcher("index.jsp").forward(request, response);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
