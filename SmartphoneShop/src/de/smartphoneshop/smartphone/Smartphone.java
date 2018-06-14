package de.smartphoneshop.smartphone;

public class Smartphone {

    private int smartphoneId;

    private String marke;

    private String farbe;

    private double preis;

    public Smartphone(int smartphoneId, String marke, String farbe, double preis) {
	this.smartphoneId = smartphoneId;
	this.marke = marke;
	this.farbe = farbe;
	this.preis = preis;
    }

    public int getSmartphoneId() {
	return smartphoneId;
    }

    public String getMarke() {
	return marke;
    }

    public String getFarbe() {
	return farbe;
    }

    public double getPreis() {
	return preis;
    }

    @Override
    public String toString() {
	return "Schuh [marke=" + marke + ", farbe=" + farbe + ", preis=" + preis + "]";
    }

}
