package Carta.Model;

public class UtenteNonIdentificato {
    private final String nome;
    private final String cognome;

    public UtenteNonIdentificato(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCognome() {
        return this.cognome;
    }
}
