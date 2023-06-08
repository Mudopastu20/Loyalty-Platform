package Carta.Model;

public class Livello {

    private final Integer idLivello;

    private final String livello;

    public Integer getIdLivello() {
        return idLivello;
    }

    public String getLivello() {
        return livello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getSogliaPunti() {
        return sogliaPunti;
    }

    private final String descrizione;

    private final Integer sogliaPunti;

    public Livello(Integer idLivello, String livello, String descrizione, Integer sogliaPunti) {
        this.idLivello = idLivello;
        this.livello = livello;
        this.descrizione = descrizione;
        this.sogliaPunti = sogliaPunti;
    }

    @Override
    public String toString() {
        return (idLivello.toString() + "\t" + livello + "\t\t\t" + descrizione + "\t" + sogliaPunti.toString() + "  - soglia avanzamento livello");
    }
}
