package de.smartphoneshop.controller.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import de.smartphoneshop.database.DatabaseConnection;
import de.smartphoneshop.kunde.Kunde;

public class Authentifikation {

    public Kunde authentifizierung(String mail, String pw) {

	Kunde kunde = null;

	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;

	try {

	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement für aussuchen ersetllen
	    String sql = "SELECT * FROM kunden WHERE email=? AND passwort=?";// Kombination aus pw und email muss
									     // existieren

	    // Prepared Statement erstellen
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setString(1, mail);
	    myStmt.setString(2, pw);

	    // SQL Statement ausführen
	    myRs = myStmt.executeQuery();

	    // Daten von der Datenbank entnehmen (rows)
	    if (myRs.next()) {

		String vorname = myRs.getString("vorname");
		String nachname = myRs.getString("nachname");
		String benutzername = myRs.getString("benutzername");
		String passwort = myRs.getString("passwort");
		String email = myRs.getString("email");
		int alter = myRs.getInt("alter");

		// Adminbereich
		// request.getRequestDispatcher("/AdminServlet").forward(request, response);

		// Kunde wird mit den Parametern erstellt
		kunde = new Kunde(vorname, nachname, benutzername, passwort, email, alter);

		return kunde;
	    }
	} catch (

	Exception e) {
	    e.printStackTrace();

	} finally {
	    // Erstellte JDBC Objekte schließen
	    close(myConn, myStmt, myRs);
	}
	return kunde;
    }

    public Kunde getLoginKunde(String vorname, String nachname, String benutzername, String passwort, String email,
	    int alter) {

	Kunde kunde = new Kunde(vorname, nachname, benutzername, passwort, email, alter);

	return kunde;
    }

    // Hauptmethode zum schliessen aller JDBC Objekte
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

	try {

	    if (myRs != null) {
		myRs.close();
	    }

	    if (myStmt != null) {
		myStmt.close();
	    }

	    if (myConn != null) { // wird nicht richtig geschlossen..., sondern
		myConn.close(); // geht nur wieder in den "connection pool"
	    }

	} catch (Exception exc) {
	    exc.printStackTrace();
	}

    }
}
