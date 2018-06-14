package de.smartphoneshop.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.smartphoneshop.controller.util.SmartphoneUtil;
import de.smartphoneshop.controller.util.WarenkorbUtil;
import de.smartphoneshop.kunde.Warenkorb;
import de.smartphoneshop.smartphone.Smartphone;

@WebServlet("/WarenkorbServlet")
public class WarenkorbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SmartphoneUtil smartphoneUtil;
    private WarenkorbUtil warenkorbUtil;

    @Override
    public void init() throws ServletException {
	super.init();
	smartphoneUtil = new SmartphoneUtil();
	warenkorbUtil = new WarenkorbUtil();
    }

    public WarenkorbServlet() {
	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// "command" wird aus jsp geholt und mittels Switch Case ausgeführt
	String befehl = request.getParameter("command");

	try {
	    // befehl kann 3 Fälle haben
	    switch (befehl) {

	    case "HINZUFUEGEN_IN_WARENKORB":
		System.out.println(befehl);
		System.out.println("TESTTESTTESTTESTTEST");
		hinzufuegenInWarenkorb(request, response);
		break;

	    case "WARENKORB_AUSGEBEN":
		System.out.println(befehl);
		warenkorbAusgeben(request, response);
		break;

	    case "LOESCHEN_AUS_WARENKORB":
		System.out.println(befehl);
		loescheAusWarenkorb(request, response);
		warenkorbAusgeben(request, response);
		break;

	    default:
		// wenn keiner der 3 cases ausgeführt wird wird fehlermeldung ausgegeben und auf
		// die index.jsp weitergeleitet
		System.out.println("Error im Default von WarenkorbServlet!");
		request.getRequestDispatcher("index.jsp").forward(request, response);
		break;
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private void hinzufuegenInWarenkorb(HttpServletRequest request, HttpServletResponse response) throws Exception {

	HttpSession session = request.getSession(false);

	if (session.getAttribute("ANMELDUNG_KUNDE") == null || session.getAttribute("ANMELDUNG_KUNDE") == "") {
	    request.setAttribute("CHECKOUT_ANMELDUNG", "Bitte melde an oder registriere Dich vorher!");
	    request.getRequestDispatcher("login.jsp").forward(request, response);

	} else {

	    try {
		// hole daten aus der jsp
		String warenkorbid = request.getParameter("warenkorbId");
		String smartphoneid = request.getParameter("smartphoneId");
		String kundeid = request.getParameter("kundeId");
		// schuh Schuh und warenkorb Warenkorb anlegen und ids übergeben
		Smartphone smartphone = smartphoneUtil.getSmartphone(smartphoneid);
		Warenkorb warenkorb = warenkorbUtil.getWarenkorb(kundeid);

		// Artikel wird dem Warenkorb hinzugefügt
		warenkorbUtil.produktWarenkorbHinzufuegen(smartphone, warenkorb);

		// fuer die Berechnung der Summe
		List<Smartphone> warenkorbliste = warenkorbUtil.getProduktImWarenkorb(warenkorbid);

		session.setAttribute("ANZAHL_IM_WARENKORB", warenkorbliste.size());
		request.setAttribute("SUMME", warenkorbUtil.getSumme(warenkorbid));
		request.setAttribute("ADD",
			"<strong><div class='alert alert-success' role='alert'> Der Schuh</strong> wurde dem <strong>Warenkorb</strong> hinzugefügt!</div><hr>");
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	    } catch (Exception e) {

		request.setAttribute("BEREITS_IM_WARENKORB",
			"<strong><div class='alert alert-danger' role='alert'>Der Schuh</strong> ist bereits im <strong>Warenkorb!</strong></div>");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	    }
	}

    }

    private void warenkorbAusgeben(HttpServletRequest request, HttpServletResponse response) throws Exception {

	// Hole die Session, aber es wird keine neue erstellt
	HttpSession session = request.getSession(false);

	// Überprüfung ob ein Kunde bereits eingeloggt ist
	if (session.getAttribute("ANMELDUNG_KUNDE") == null || session.getAttribute("ANMELDUNG_KUNDE") == "") {
	    request.setAttribute("CHECKOUT_ANMELDUNG",
		    "Bitte logge dich vorher ein oder registriere einen neuen Benutzer!");
	    request.getRequestDispatcher("/login.jsp").forward(request, response);
	} else {

	    try {

		String warenkorbid = request.getParameter("warenkorbId");
		System.out.println("Warenkorb ausgeben mit der WarenkorbID: " + warenkorbid);

		List<Smartphone> warenkorbliste = warenkorbUtil.getProduktImWarenkorb(warenkorbid);

		session.setAttribute("ANZAHL_IM_WARENKORB", warenkorbliste.size());
		request.setAttribute("SUMME", warenkorbUtil.getSumme(warenkorbid));
		request.setAttribute("WARENKORB", warenkorbUtil.getProduktImWarenkorb(warenkorbid));
		request.getRequestDispatcher("/warenkorb.jsp").forward(request, response);

	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void loescheAusWarenkorb(HttpServletRequest request, HttpServletResponse response) throws Exception {

	String smartphoneid = request.getParameter("smartphoneId");
	String warenkorbid = request.getParameter("warenkorbId");

	warenkorbUtil.loescheProduktAusWarenkorb(smartphoneid, warenkorbid);
	request.setAttribute("DELETE",
		"<strong><div class='alert alert-danger' role='alert'>Der Schuh</strong> wurde aus dem <strong>Warenkorb</strong> entfernt!</div>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }
}
