package de.smartphoneshop.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProfilServlet")
public class ProfilServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfilServlet() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// Hole die Session, aber erstelle keine neue
	HttpSession session = request.getSession(false);

	// Überprüfung ob Kunde eingeloggt ist
	if (session.getAttribute("ANMELDUNG_KUNDE") == null || session.getAttribute("ANMELDUNG_KUNDE") == "") {
	    // falls nicht eingeloggt wird zum login weitergeleitet
	    request.getRequestDispatcher("/anmelden.jsp").forward(request, response);
	    // andernfalls zum profil
	} else {
	    request.getRequestDispatcher("/profil.jsp").forward(request, response);
	}
    }
}
