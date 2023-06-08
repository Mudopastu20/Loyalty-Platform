package Carta.Model;

import java.util.Scanner;
public class CommessoEsercente {
    private Piattaforma piattaforma;
    private Scanner scanner;

    public CommessoEsercente(){
        piattaforma = new Piattaforma();
        scanner = new Scanner(System.in);
    }


    /**
     * Metodo che fa salire la carta del cliente di grado
     * 1 - Bronze = 1000 punti
     * 2 - Silver = 1000 punti
     * 3 - Gold = 1000 punti
     */
    public void saliLivelloCarta(){
        String cartaUp;
    }

    /**
     * Aggiunge Punti sulla carta fedeltà,
     *      digitare 0 per uscire.
     */
    public void aggiungiPuntiaPunti() {
        int puntiDaAggiungere;

        do {
            System.out.println("Inserisci il numero di punti da aggiungere: ");
            puntiDaAggiungere = scanner.nextInt();

            if(puntiDaAggiungere > 0) {
                piattaforma.aggiungiPuntiaPunti(puntiDaAggiungere);
                System.out.println("I punti totali sulla tua carta fedeltà sono: "+ piattaforma.getPunti());
            }
        } while (puntiDaAggiungere > 0);
    }

    /**
     * Aggiunge Punti sulla carta per salire di grado (bronze = 1000 punti exp ,
     *      silver = 2000 punti exp ,
     *          gold = 3000 punti exp)
     *           digitare 0 per uscire
     */
    public void aggiungiPuntiaLivelli() {
        int puntiDaAggiungere;

        do {
            System.out.println("Inserisci il numero di punti da aggiungere per salire di grado: ");
            puntiDaAggiungere = scanner.nextInt();

            if(puntiDaAggiungere > 0) {
                piattaforma.aggiungiPuntiaLivelli(puntiDaAggiungere);
                System.out.println("L'esperienza sulla tua carta fedeltà è di: "+ piattaforma.getLivelli());
            }
        } while (puntiDaAggiungere > 0);
    }

    /**
     * Mostra i punti totali ( sia a livelli che a punti)
     */
    public void visualizzaTotalePunti() {
        System.out.println("I tuoi punti sulla carta sono: " + piattaforma.getPunti());
        System.out.println("La tua esperienza sulla carta è di: " + piattaforma.getLivelli());
    }



}
