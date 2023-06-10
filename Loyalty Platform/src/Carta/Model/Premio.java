package Carta.Model;

public class Premio {

    private final Integer idpremio;
    private final String nome;
    private final String descrizione;
    private final Integer puntiNecessari;   // per riscuotere il premio


    public Premio(Integer idpremio, String nome, String descrizione, Integer puntiNecessari) {
        this.idpremio = idpremio;
        this.nome = nome;
        this.descrizione = descrizione;
        this.puntiNecessari = puntiNecessari;

    }

    public Integer getIdpremio() {
        return idpremio;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Integer getPuntiNecessari() {
        return puntiNecessari;
    }

    @Override
    public String toString(){
        return (idpremio.toString() +"\t"+nome+"\t\t\t"+descrizione+"\t"+puntiNecessari.toString()+"  - punti necessari");
    }
}
