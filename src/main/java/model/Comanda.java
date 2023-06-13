package model;

public class Comanda {
    private int idComanda;
    private int produs_id;
    private String nume;
    private int cient_id;
    private int cantitate;
    private float pret;
    private static int  bere=0;

    /**
     *
     * Pentru ca idComanda sa nu mai fie prezenta in constructor, am ascuns-o cu ajutorul variabilei statice
     * "bere" pe care o folosim la adaugarea sau stergerea unui produs din cos
     */

    public Comanda( int produs_id, String nume, int cient_id, int cantitate, float pret) {
        idComanda=bere;
        bere++;
        this.produs_id = produs_id;
        this.nume = nume;
        this.cient_id = cient_id;
        this.cantitate = cantitate;
        this.pret = pret;
    }



    public int getIdComanda() {
        return idComanda;
    }

    public void setIdComanda(int idComanda) {
        this.idComanda = idComanda;
    }

    public int getProdus_id() {
        return produs_id;
    }

    public void setProdus_id(int produs_id) {
        this.produs_id = produs_id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCient_id() {
        return cient_id;
    }

    public void setCient_id(int cient_id) {
        this.cient_id = cient_id;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }
}
