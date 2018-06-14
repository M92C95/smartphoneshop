package de.smartphoneshop.kunde;

public class Kunde extends Person {

    private int kundeId;
    private String email;
    private int alter;
    private Adresse adresse;
    private Bankdaten bankverbindung;

    public Kunde(String email, String vorname, String nachname, String nutzername, String passwort) {
	super(vorname, nachname, nutzername, passwort);
    }

    public Kunde(String vorname, String nachname, String nutzername, int alter, String passwort, String email,
	    Adresse adresse, Bankdaten bankverbindung) {
	super(vorname, nachname, nutzername, passwort);
	this.email = email;
	this.alter = alter;
	this.adresse = adresse;
	this.bankverbindung = bankverbindung;
    }

    public Kunde(String vorname, String nachname, String nutzername, String passwort, String email, int alter) {
	super(vorname, nachname, nutzername, passwort);
	this.email = email;
	this.alter = alter;

    }

    public int getKundenId() {
	return kundeId;
    }

    public String getEmail() {
	return email;
    }

    public int getAlter() {
	return alter;
    }

    public Adresse getAdresse() {
	return adresse;
    }

    public Bankdaten getBankverbindung() {
	return bankverbindung;
    }

}
