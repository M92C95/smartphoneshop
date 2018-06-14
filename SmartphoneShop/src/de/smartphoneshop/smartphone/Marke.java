package de.smartphoneshop.smartphone;

public enum Marke {

    IPHONE("iPhone"), SAMSUNG("Samsung"), HUAWEI("Huawei"), HTC("HTC"), LG("LG");

    String marke;

    Marke(String marke) {
	this.marke = marke;
    }

    public String getMarke() {
	return marke;
    }

}
