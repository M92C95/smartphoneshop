package de.smartphoneshop.smartphone;

public enum Farbe {

    SCHWARZ("Schwarz"), SILBER("Silber"), WEISS("Weiss"), BLAU("Blau"), GOLD("Gold");

    String farbe;

    Farbe(String farbe) {
	this.farbe = farbe;
    }

    public String getFarbe() {
	return farbe;
    }

}
