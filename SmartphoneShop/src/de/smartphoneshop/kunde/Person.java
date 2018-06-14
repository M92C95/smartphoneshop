package de.smartphoneshop.kunde;

public abstract class Person {

    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;

    public Person(String vorname, String nachname, String benutzername, String passwort) {
	this.vorname = vorname;
	this.nachname = nachname;
	this.benutzername = benutzername;
	this.passwort = passwort;
    }

    public String getVorname() {
	return vorname;
    }

    public String getNachname() {
	return nachname;
    }

    public String getNutzername() {
	return benutzername;
    }

    public String getPasswort() {
	return passwort;
    }
}
