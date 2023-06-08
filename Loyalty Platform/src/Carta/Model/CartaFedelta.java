package Carta.Model;

import Carta.Model.Cliente;
import java.util.Scanner;
import java.util.*;
import Carta.Model.Cliente;

public class CartaFedelta {
    private Piattaforma piattaforma;
    private Scanner scanner;
    private String ID;
    private int indice;
    private int punti;
    private String nomecarta;


    public CartaFedelta(String nomecarta, int punti) {
        piattaforma = new Piattaforma();
        scanner = new Scanner(System.in);
        this.nomecarta = nomecarta;
        this.punti = punti;
        this.ID = Integer.toString(creaID());
        indice = 0;
    }

    public int creaID() {
        if (indice <= 9999) {
            int nuovoIndice = indice;
            indice++;
            return nuovoIndice;
        } else {
            return -1; // valore sentinella per indicare che l'indice massimo è stato raggiunto
        }
    }

    public int getPunti() {
        return punti;
    }

    public String getNomecarta() {
        return nomecarta;
    }

    public String getID() {
        return ID;
    }
}
