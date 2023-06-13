package model;

public class Produs {
    private int idProdus;
    private String nume;
    private int stoc;
    private float pret;


    public Produs( String nume, int stoc, float pret) {

        this.nume = nume;
        this.stoc = stoc;
        this.pret = pret;
    }

    public int getIdProdus() {
        return idProdus;
    }

    public void setIdProdus(int idProdus) {
        this.idProdus = idProdus;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
