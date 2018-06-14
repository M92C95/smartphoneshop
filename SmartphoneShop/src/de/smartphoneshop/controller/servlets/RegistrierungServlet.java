package de.smartphoneshop.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.smartphoneshop.controller.util.KundeUtil;
import de.smartphoneshop.kunde.Kunde;

//import de.smartphoneshop.controller.util.KundenUtil;
//import de.smartphoneshop.model.personen.Kunde;

//@WebServlet("/RegistrierungServlet")
public class RegistrierungServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String ERRORTEXT = "Bitte geben Sie ein Passwort ein und bestätigen sie dieses.";
    private final KundeUtil kundenUtil = new KundeUtil();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrierungServlet() {
	super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// hole alle Daten aus der JSP
	String vorname = request.getParameter("vorname");
	String nachname = request.getParameter("nachname");
	String email = request.getParameter("email");
	String nutzername = request.getParameter("nutzername");
	String passwort = request.getParameter("passwort");
	String confirm = request.getParameter("confirm");
	String alter = request.getParameter("alter");
	Integer alter2 = Integer.parseInt(alter);// parse nötig weil getParameter nur mit String funktioniert

	try {
	    // Passwort muss zweimal übereinstimmend eingegeben werden
	    if (checkPasswort(passwort, confirm)) {
		// falls Passwort korrekt, wird Kunde mit Daten aus der JSP erstellt
		Kunde kunde = new Kunde(vorname, nachname, nutzername, passwort, email, alter2);
		HttpSession session = request.getSession();
		kundenUtil.kundeHinzufuegen(kunde);
		// Kunde wird als Session gesetzt
		session.setAttribute("LOGIN_KUNDE", kunde);
		// und man wird auf das Profil weitergeleitet
		request.getRequestDispatcher("profil.jsp").forward(request, response);
	    } else {
		System.out.println("Passwort leer oder stimmt nicht überein.");
		request.setAttribute("ERRORTEXT", ERRORTEXT);
		request.getRequestDispatcher("registrieren.jsp").forward(request, response);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // prueft ob ein Passwort eingegeben wurde
    private boolean checkPasswort(String passwort, String confirm) {
	return passwort.equals(confirm) && !passwort.isEmpty() ? true : false;
    }
}
