package Carta.Model;

import java.util.ArrayList;

public class Piattaforma {
    private int punti;
    private ArrayList<Livello> livelli;
    private String premio;
    private String saliLivello;
    private ArrayList<Cliente> clienti;

    public Piattaforma(){
        this.clienti = new ArrayList<>();
    }

    /**
     *  se cliente esiste return false
     *  altrimenti true
     */
    public boolean aggiungiProfiloCliente(Cliente cliente) {
        if (this.clienti.contains(cliente))
            return false;
        this.clienti.add(cliente); // si garantisce che l'utente, nel costruttore, abbia dei campi validi
        return true;
    }

    public void saliLivelloCarta(String cartaUP){
        saliLivello = cartaUP;
    }
    public void scalaPunti(int puntiDaScalare) {
        if (punti >= puntiDaScalare) {
            punti -= puntiDaScalare;
        }
    }


    public int getPunti() {
        return punti;
    }

    public ArrayList<Livello> getLivelli() {
        return livelli;
    }

    /**
     * Calcola i punti ottenibili dal cliente in base al programma selezionato
     *
     * @param programmaScelto <code>true</code> a punti, <code>false</code> a livelli
     * @param denaroSpeso per calcolo punteggio
     * @return punteggio ottenuto
     */
    public int calcolaPunti(boolean programmaScelto, int denaroSpeso) {
        if (programmaScelto){
            return denaroSpeso/10;  // Programma a punti: regali acquistabili sbloccando premi con i punti
        }else {
            return denaroSpeso;     // Programma a livelli: piu' alto è il punteggio piu' si sale di livello
        }
    }

    public ArrayList<Premio> selezionaPremiRiscattabili(ArrayList<Premio> premi, int puntiCliente) {
        ArrayList<Premio> premiRiscattabili = new ArrayList<>();
        for (Premio p: premi) {
            if(p.getPuntiNecessari()<=puntiCliente)
                premiRiscattabili.add(p);
        }
        return premiRiscattabili;
    }

    public void inserisciLivelli(ArrayList<Livello> livelli) {
        this.livelli=livelli;
    }

    public boolean sogliaLivelloRaggiunta(Livello livelloCliente, Integer puntiAttualiCiente) {
        return puntiAttualiCiente > livelloCliente.getSogliaPunti();
    }

    public Livello getLivello(Integer livelloCliente) {
        for (Livello l: this.livelli) {
            if(livelloCliente == l.getIdLivello())
                return l;
        }
        return null;
    }
}
