package de.smartphoneshop.controller.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.smartphoneshop.database.DatabaseConnection;
import de.smartphoneshop.kunde.Warenkorb;
import de.smartphoneshop.smartphone.Smartphone;

public class WarenkorbUtil {

    public Warenkorb getWarenkorb(String id) throws Exception {

	Warenkorb warenkorb = null;

	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	int kundeId;//

	try {
	    // userId in einen Integer umwandeln
	    kundeId = Integer.parseInt(id);

	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement für aussuchen ersetllen
	    String sql = "SELECT * FROM warenkorb WHERE kundeId = ?";

	    // Prepared Statement erstellen
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setInt(1, kundeId);//

	    // SQL Statement ausführen
	    myRs = myStmt.executeQuery();

	    // Daten von der Datenbank entnehmen (rows)
	    while (myRs.next()) {

		int warenkorbid = myRs.getInt("warenkorbId");
		int kundeid = myRs.getInt("kundeId");

		// Person wird erstellt
		warenkorb = new Warenkorb(warenkorbid, kundeid);
	    }

	    return warenkorb;

	} finally {
	    // Erstellte JDBC Objekte löschen
	    close(myConn, myStmt, myRs);
	}
    }

    public void produktWarenkorbHinzufuegen(Smartphone smartphone, Warenkorb warenkorb) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // einen SQL Statement erstellen
	    String sql = "INSERT INTO warenkorb_hat_smartphone (smartphoneId, warenkorbId) VALUES (?, ?)";

	    myStmt = myConn.prepareStatement(sql);

	    // Die Parameter für den neuen Benutzer erstellen - SQL
	    myStmt.setInt(1, smartphone.getSmartphoneId());
	    myStmt.setInt(2, warenkorb.getWarenkorbId());

	    // SQL Statement ausführen
	    myStmt.execute();

	} finally {

	    // Alle erstellten JDBC objekte löschen
	    close(myConn, myStmt, null);
	}
    }

    public List<Smartphone> getProduktImWarenkorb(String id) throws Exception {

	List<Smartphone> warenkorb = new ArrayList<>();

	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	int warenkorbId;//

	try {
	    // userId in einen Integer umwandeln
	    warenkorbId = Integer.parseInt(id);

	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement für aussuchen ersetllen
	    String sql = "SELECT * FROM warenkorb_hat_smartphone NATURAL JOIN smartphone WHERE warenkorbId = ?";

	    // Prepared Statement erstellen
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setInt(1, warenkorbId);//

	    // SQL Statement ausführen
	    myRs = myStmt.executeQuery();

	    // Daten von der Datenbank entnehmen (rows)
	    while (myRs.next()) {

		// dann die Daten von der Datenbank entnehmen
		int smartphoneId = myRs.getInt("smartphoneId");
		String marke = myRs.getString("marke");
		String farbe = myRs.getString("farbe");
		double preis = myRs.getDouble("preis");

		// Einen neuen Artikel erstellen mit den Parametern (Daten)

		Smartphone smartphone = new Smartphone(smartphoneId, marke, farbe, preis);

		// Artikel dem Array hinzufügen
		warenkorb.add(smartphone);
	    }

	    return warenkorb;

	} finally {
	    // Erstellte JDBC Objekte löschen
	    close(myConn, myStmt, myRs);
	}
    }

    public void loescheProduktAusWarenkorb(String smartphoneId, String warenkorbId) throws Exception {

	Connection myConn = null;
	PreparedStatement myStmt = null;

	int idsmartphone = Integer.parseInt(smartphoneId);
	int idwarenkorb = Integer.parseInt(warenkorbId);

	try {

	    // Datenbankverbindung aufbauen
	    myConn = DatabaseConnection.getConnection();

	    // SQL Statement fürs löschen erstellen
	    String sql = "DELETE FROM warenkorb_hat_smartphone WHERE smartphoneId=? AND warenkorbId=?";

	    // prepare statement
	    myStmt = myConn.prepareStatement(sql);

	    // Parameter setzen
	    myStmt.setInt(1, idsmartphone);
	    myStmt.setInt(2, idwarenkorb);

	    // SQL Statement ausführen
	    myStmt.execute();

	} finally {

	    // Erstellte JDBC Objekte löschen
	    close(myConn, myStmt, null);
	}

    }

    public double getSumme(String idWarenkorb) throws Exception {

	double summe = 0;
	List<Smartphone> warenkorbliste = getProduktImWarenkorb(idWarenkorb);

	for (int i = 0; i < warenkorbliste.size(); i++) {
	    summe += warenkorbliste.get(i).getPreis();
	}

	return summe;
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
