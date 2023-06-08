package Carta.Controller;
import Carta.Model.*;
import Carta.View.ConsoleTest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class Controller {
    Piattaforma piattaforma;
    DBManager dbManager;
    ConsoleTest consoleTest = new ConsoleTest();


    public Controller(){
        this.piattaforma = new Piattaforma();
    }

    /**
     * Esegue il programma.
     *
     * @throws Exception se viene scelta un'opzione non contemplata.
     */
    public void executeProgram() throws Exception {
        // effettua la connessione al DB
        dbManager = new DBManager("jdbc:mysql://localhost:3306/owldb", "root", "Andrea99");
        inizializzaPiattaforma();
        int i;

        do {

            i = consoleTest.stampaHome();

            switch (i) {

                // login Utente ( UC17 )
                case 1 -> {
                    // acquisisco le credenziali
                    List<String> credenziali = consoleTest.getCredenziali();
                    if (credenziali == null) {
                        consoleTest.stampaErroriCredenziali();
                        break;
                    }
                    // autentico il cliente
                    Cliente cliente = dbManager.estraiCliente(credenziali.get(0), credenziali.get(1));
                    if (cliente == null) {
                        consoleTest.stampaErroriCredenziali();
                        break;
                    }

                    boolean punti = consoleTest.puntiLivelli(); // dbManager.puntiLivelli() todo in futuro
                    if(punti) {
                        ArrayList<Premio> premi = dbManager.estraiPremi();
                        consoleTest.stampaPremi(premi);

                        int puntiCliente = dbManager.estraiPuntiCliente(cliente.getId());
                        // 2 - vede premi riscattabili
                        int idPremioRiscattato = consoleTest.stampaPremiRiscattabili(piattaforma.selezionaPremiRiscattabili(premi, puntiCliente), puntiCliente);
                        if(idPremioRiscattato==0)
                            break;
                        else
                            dbManager.scalaPuntiCliente(cliente.getId(), idPremioRiscattato);
                    } else {
                        ArrayList<Livello> livelli = dbManager.estraiLivelli();

                        consoleTest.stampaLivelli(dbManager.livelloAttualeCliente(cliente.getId()), livelli );
                    }
                }


                //Inserisci punti a carta fedelta'
                case 2 -> {

                    // acquisisco le credenziali
                    List<String> credenziali = consoleTest.getCredenziali();
                    // autentico il cliente
                    Cliente cliente = dbManager.estraiCliente(credenziali.get(0), credenziali.get(1));
                    if (cliente == null) {
                        consoleTest.stampaErroriCredenziali();
                        break;
                    }

                    int denaroSpeso = consoleTest.denaroSpeso();

                    // true punti   false livelli
                    // boolean programmaScelto = dbManager.puntiLivelli(cliente.getId());

                    boolean programmaScelto = consoleTest.puntiLivelli();

                    int puntiDaInserire = piattaforma.calcolaPunti(programmaScelto, denaroSpeso);
                    dbManager.inserisciPunti(cliente.getId(), programmaScelto, puntiDaInserire);

                    // todo controlla se aumenta livello - prendi punti soglia e confronta, in caso aumentare
                }

                // UC1 - Creazione profilo cliente
                case 3 -> {
                    Cliente cliente;
                    cliente = consoleTest.registrazione();
                    // todo controllo se email non esista sul DB
                    if (cliente != null) {
                        piattaforma.aggiungiProfiloCliente(cliente);
                        dbManager.inserisciNuovoCliente(cliente); // cliente appena creato potra' fare il login
                    }
                }


                // termina programma
                case 0 -> {
                    consoleTest.stampaArrivederci();
                    consoleTest.chiudiScanner();
                    System.exit(0);
                }
                default -> throw new IllegalStateException("Carattere inserito non valido: " + i + "\n\n");
            }
        } while (true);
    }

    private void inizializzaPiattaforma() throws SQLException {

    }


}
