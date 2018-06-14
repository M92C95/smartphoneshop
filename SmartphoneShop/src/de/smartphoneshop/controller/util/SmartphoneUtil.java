package de.smartphoneshop.controller.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.smartphoneshop.database.DatabaseConnection;
import de.smartphoneshop.smartphone.Smartphone;

public class SmartphoneUtil {
    private static final String SELECT_ALL = "SELECT * FROM smartphone";
    private static final String MARKE = "marke";
    private static final String FARBE = "farbe";
    private static final String PREIS = "preis";

    public List<Smartphone> getAlleSmartphones() throws Exception {
	List<Smartphone> smartphoneliste = new ArrayList<>();

	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;

	try {
	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // einen SQL Statement erstellen
	    myStmt = myConn.createStatement();

	    // Query ausführen
	    myRs = myStmt.executeQuery(SELECT_ALL);

	    // Gehe durch die Datenbank
	    while (myRs.next()) {

		// dann die Daten von der Datenbank entnehmen
		int smartphoneId = myRs.getInt("smartphoneId");
		String marke = myRs.getString(MARKE);
		String farbe = myRs.getString(FARBE);
		double preis = myRs.getDouble(PREIS);
		Smartphone smartphone = new Smartphone(smartphoneId, marke, farbe, preis);
		smartphoneliste.add(smartphone);

	    }

	    // Schuh Array zurückgeben
	    return smartphoneliste;

	} finally {

	    // Alle erstellten JDBC objekte löschen (Methodenaufruf)
	    close(myConn, myStmt, myRs);
	}
    }

    public void loescheProdukt(String id) throws Exception {// id wird übergeben weil sie eindeutig ist

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {

	    int idProdukt = Integer.parseInt(id);
	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement fürs löschen erstellen
	    String sql = "DELETE FROM smartphone WHERE smartphoneId=?";

	    // prepare statement
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setInt(1, idProdukt);

	    // SQL Statement ausführen
	    myStmt.execute();

	} finally {

	    // Erstellte JDBC Objekte löschen
	    close(myConn, myStmt, null);
	}
    }

    public Smartphone getSmartphone(String id) throws Exception {// id wird übergeben weil id eindeutig ist

	Smartphone smartphone = null;

	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	int smartphoneIdX;//

	try {
	    // userId in einen Integer umwandeln
	    smartphoneIdX = Integer.parseInt(id);

	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement für aussuchen ersetllen
	    String sql = "SELECT * FROM smartphone WHERE smartphoneId = ?";

	    // Prepared Statement erstellen
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setInt(1, smartphoneIdX);//

	    // SQL Statement ausführen
	    myRs = myStmt.executeQuery();

	    // Daten von der Datenbank entnehmen (rows)
	    while (myRs.next()) {

		int smartphoneid = myRs.getInt("smartphoneId");
		String marke = myRs.getString("marke");
		String farbe = myRs.getString("farbe");
		double preis = myRs.getDouble("preis");
		smartphone = new Smartphone(smartphoneid, marke, farbe, preis);

	    }

	    return smartphone;

	} finally {
	    // Erstellte JDBC Objekte löschen
	    close(myConn, myStmt, myRs);
	}
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
