package Carta.View;

import Carta.Model.*;

import java.util.*;
import java.util.Scanner;
public class ConsoleTest {
    private Scanner scanner;

    public ConsoleTest(){
        scanner = new Scanner(System.in);
    }

    /**
     * Stampa la <i>Home</i>, con la possibilit&agrave; di effettuare<br>
     *
     * [1] -> Log in Cliente<br>
     * [2] -> Log in Commesso<br>
     * [3] -> Crea profilo Utente<br>
     * [0] -> Termina programma<br>
     *<br>
     * Implementazione dei casi d'uso pi&ugrave; rischiosi:<br>
     * - Creazione profilo Utente<br>
     * - Aggiunta punti <br>
     * @return scelta effettuata dall'Utente
     */
    public int stampaHome() {
        System.out.println("\nBenvenuto nell'area Carta Fedeltà!\n\n");

        System.out.println("Identificati:");
        System.out.println("[1] -> Cliente");
        System.out.println("[2] -> Commesso");
        System.out.println("[3] -> Nuovo Cliente");
        System.out.println("[0] -> Esci dall'area Carta Fedeltà");

        String s = scanner.nextLine();
        s = checkCharacters(s, "0", "1", "2", "3");
        return Integer.parseInt(s);
    }

    /**
     * Controlla che il carattere inserito dall'Utente sia uno tra i caratteri specificati dalla precedente stampa
     * @param s stringa contenente il carattere
     * @param values valori da controllare all'interno di s
     * @return stringa corretta se inserimento iniziale non andato a buon fine
     */
    private String checkCharacters(String s, String ... values) {
        while(invalidString(s, values)) {
            s = null;
            System.out.println("Carattere inserito non valido, ritenta");
            s = scanner.nextLine();
        }
        return s;
    }

    /**
     * Controlla se la stringa inserita dall'Utente non contiene uno dei caratteri ammissibili
     *
     * @param s stringa su cui effettuare il controllo
     * @return <code>false</code> se il carattere inserito &egrave; corretto, <code>true</code> se il carattere &egrave; presente
     */
    private boolean invalidString(String s, String ... values) {
        if(s.length()<1)
            return true;
        for (String x: values){
            if(s.contains(x))
                return false;
        }
        return true;
    }

    /**
     * Estrae le credenziali (user, password) dalle stringhe inserite dal cliente
     * @return Lista di <code>String</code> inserite dal cliente (user, password)
     */
    public List<String> getCredenziali() {
        List<String> credenziali = new ArrayList<>();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        if(!email.contains("@")){
            System.out.println("Email non valida...");
            return null;
        }
        credenziali.add(0, email);
        System.out.print("\nPassword: ");
        String password = scanner.nextLine();
        credenziali.add(1, password);
        return credenziali;
    }

    /**
     * Richiede all'utente tutti i dati necessari per la creazione di un Profilo Utente
     * @return <code>Utente</code> se i dati vengono confermati, <code>null</code> altrimenti
     */
    public Cliente registrazione() throws Exception {

        ArrayList<String> datiCliente = new ArrayList<>();
        String s;

        System.out.println("--- Creazione nuovo profilo Cliente --- ");
        System.out.println("Nome: ");
        s = scanner.nextLine();
        if(s == null)
            throw new Exception("Dati inseriti non validi");
        datiCliente.add(0, s);

        System.out.println("Cognome: ");
        datiCliente.add(1, scanner.nextLine());

        System.out.println("Email: ");
        s = scanner.nextLine();
        if(!s.contains("@") || s.isBlank()) {
            throw new IllegalArgumentException("Email non valida");
        }
        datiCliente.add(2, s);

        System.out.println("User: ");
        datiCliente.add(3, scanner.nextLine());

        System.out.println("Password: ");
        s = scanner.nextLine();
        if(s.isBlank())
            throw new IllegalArgumentException("Email non valida");
        datiCliente.add(4, s);


        System.out.println("Confermare questi dati? [Y] per confermare, qualsiasi altro tasto per annullare...");
        s = scanner.nextLine();
        s = checkCharacters(s, "Y");
        if(s.equals("Y") || s.equals("y")){
            return new Cliente(datiCliente.get(0),datiCliente.get(1),
                    datiCliente.get(2), datiCliente.get(3), datiCliente.get(4));
        }
        System.out.println("Creazione profilo Cliente annullata.");
        return null;
    }

    public void chiudiScanner() {
        scanner.close();
    }

    public void stampaArrivederci() {
        System.out.println("\nAlla prossima!");
    }

    /**
     * Messaggio di errore quando <code>Utente</code> fornisce credenziali non valide
     */
    public void stampaErroriCredenziali() {
        System.out.println("\nEmail o password errati ...\nCliente non trovato... Riprovare\n");
    }

    public int denaroSpeso() {
        System.out.println("A quanto ammonta il totale speso?");
        String s = scanner.nextLine();
        return Integer.parseInt(s);
    }

    public int selezioneCliente() {

        // todo se l'id del cliente sta in carte_punti fai vedere premi ecc..
        // todo se l'id del cliente in carte_livelli fai vedere livello attuale e livelli ottenibili

        // if punti
        System.out.println("Cosa si desidera effettuare?");
        System.out.println("[1] -> Visualizza lista dei premi");
        System.out.println("[2] -> Visualizza premi riscattabili");
        System.out.println("[3] -> Rinnovo carta");
        System.out.println("[3] -> Cancellazine profilo Cliente");
        System.out.println("[0] -> Esci dall'area Carta Fedeltà");

        String s = scanner.nextLine();
        s = checkCharacters(s, "0", "1", "2", "3");
        return Integer.parseInt(s);
    }

    public boolean puntiLivelli() {
        System.out.println("Programma a punti(1) o a livelli(2)? ");
        return scanner.nextInt() == 1;
    }

    public void stampaPremi(ArrayList<Premio> premi) {
        if(premi==null) return;
        System.out.println("\t--- Premi della nostra collezione riscattabili con il programma a punti ---\t\n");
        for (Premio p: premi) {
            System.out.println(p.toString());
        }
        System.out.println("\n\n");
    }

    public int stampaPremiRiscattabili(ArrayList<Premio> premiRiscattabili, Integer puntiCliente) {
        if(premiRiscattabili.isEmpty())
        {
            System.out.println("Nessun premio riscattabile con "+ puntiCliente+" punti :(\n");
            return 0;
        }
        System.out.println("Premi riscattabili con "+ puntiCliente+" punti:\n");
        for (Premio p: premiRiscattabili) {
            System.out.println(p.toString());
        }
        System.out.println("Se lo si desidera, inserire l'id del premio da riscattare, oppure 0 se non si vuole " +
                "riscattare alcun premio: \n");
        return scanner.nextInt();
    }

    public void stampaLivelli(Integer id, ArrayList<Livello> livelli) {
        System.out.println("Livelli disponibili:\n ");
        for (Livello l: livelli) {
            System.out.println(l.toString());
        }

        System.out.println("\n livello attuale: "+id.toString());

    }
}

