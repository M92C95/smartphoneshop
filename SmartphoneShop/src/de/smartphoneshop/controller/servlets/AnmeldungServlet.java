package de.smartphoneshop.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.smartphoneshop.controller.auth.Authentifikation;
import de.smartphoneshop.kunde.Kunde;

@WebServlet("/AnmeldungServlet")
public class AnmeldungServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnmeldungServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// Abmelden
	// alle Sessions werden geholt und komplett geschlossen
	HttpSession session = request.getSession();
	session.invalidate();

	request.getRequestDispatcher("/anmelden.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// Parameter aus der Form (jsp) holen
	String mail = request.getParameter("email");
	String pw = request.getParameter("passwort");

	Authentifikation authentifizierung = new Authentifikation();
	Kunde kunde = authentifizierung.authentifizierung(mail, pw);

	// Session wird erstellt von LOGIN_KUNDE - DARF NICHT FALSE SEIN!!!
	HttpSession session = request.getSession();

	session.removeAttribute("LOGIN_REGISTER");
	session.setAttribute("LOGIN_KUNDE", kunde);
	session.setAttribute("ABMELDEN", "Abmelden");
	request.getRequestDispatcher("/profil.jsp").forward(request, response);
    }

}
