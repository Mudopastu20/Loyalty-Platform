package Carta.Model;

public class UtenteNonIdentificato {
    private String nome;
    private String cognome;

    public UtenteNonIdentificato(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public UtenteNonIdentificato() {

    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }
}
