package de.smartphoneshop.kunde;

public class Warenkorb {

    // Attribute
    private int warenkorbId;
    private int kundeId;

    // leerer Konstruktor
    public Warenkorb() {
    }

    // Konstruktor getKunde
    public Warenkorb(int warenkorbId, int kundeId) {
	this.warenkorbId = warenkorbId;
	this.kundeId = kundeId;
    }

    // Getter
    public int getWarenkorbId() {
	return warenkorbId;
    }

    public int getKundeId() {
	return kundeId;
    }

}
