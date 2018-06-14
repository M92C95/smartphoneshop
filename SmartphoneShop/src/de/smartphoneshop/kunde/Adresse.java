package de.smartphoneshop.kunde;

public class Adresse {

    private String strasse;
    private int hausnummer;
    private int plz;
    private String stadt;

    public Adresse(String strasse, int hausnummer, int plz, String stadt, String land) {
	this.strasse = strasse;
	this.hausnummer = hausnummer;
	this.plz = plz;
	this.stadt = stadt;
    }

    public String getStrasse() {
	return strasse;
    }

    public int getHausnummer() {
	return hausnummer;
    }

    public int getPlz() {
	return plz;
    }

    public String getStadt() {
	return stadt;
    }

}
