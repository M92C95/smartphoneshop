package de.smartphoneshop.kunde;

public class Bankdaten {

    private String bank;
    private String iban;
    private String bic;

    public Bankdaten(String bank, String iban, String bic) {
	this.iban = iban;
	this.bic = bic;
	this.bank = bank;
    }

    public String getInstitut() {
	return bank;
    }

    public String getIban() {
	return iban;
    }

    public String getBic() {
	return bic;
    }

}
