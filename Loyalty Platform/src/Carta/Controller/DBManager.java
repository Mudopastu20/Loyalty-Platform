package Carta.Controller;
import java.sql.*;

import Carta.Model.Cliente;
import Carta.Model.Livello;
import Carta.Model.Premio;

import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;


public class DBManager {
    Connection connection;
    Statement owldb;

    public DBManager(String url, String user, String password) throws Exception {
        connection = DriverManager.getConnection(url, user, password);
        owldb = connection.createStatement();
        ResultSet resultset = owldb.executeQuery("select * from clienti");
        //ResultSet resultsetUno = owldb.executeQuery("select * from programmapunti");
    }

    /**
     * Recupera l'utente con le credenziali corrispondenti
     */
    public Cliente estraiCliente(String user, String password) throws SQLException{

        String query = "SELECT * FROM clienti WHERE email = '" + user + "' AND password = '" + password + "' ;";
        ResultSet resultSet = owldb.executeQuery(query);
        Cliente cliente = null;
        if (resultSet.next()) {
            Integer id = resultSet.getObject(1, Integer.class);
            String nome = resultSet.getObject(2, String.class);
            String cognome = resultSet.getObject(3, String.class);
            String mail = resultSet.getObject(4, String.class);
            String us = resultSet.getObject(5,String.class);
            String pw = resultSet.getObject(6, String.class);

            cliente = new Cliente(id, nome, cognome, mail, us, pw);
        }
        return cliente;
    }

    public void inserisciNuovoCliente(Cliente cliente) throws SQLException {
        String update = "INSERT INTO clienti (nome, cognome, email, user, password) " +
                "VALUES ('" +
                cliente.getNome() + "', '" +
                cliente.getCognome() + "', '" +
                cliente.getEmail() + "', '" +
                cliente.getUser() + "','" +
                cliente.getPassword() + "');";
        owldb.executeUpdate(update);
    }

    /**
     * @param programmaScelto true se punti false se livelli
     * @param puntiDaInserire punti da aggiungere a quelli attuali
     */
    public Integer inserisciPunti(Integer id, boolean programmaScelto, int puntiDaInserire) throws SQLException {
        String s;
        if(programmaScelto){
            s="carte_punti";
        } else {
            s="carte_livelli";
        }
        String selectPunti = "SELECT punti FROM "+s+" WHERE id_cliente="+id+";";
        ResultSet rs = owldb.executeQuery(selectPunti);
        if(rs.next()){
            Integer puntiAttuali = rs.getObject(1, Integer.class);
            puntiDaInserire+=puntiAttuali;

            String update = " UPDATE "+s+" SET punti = " + puntiDaInserire + " WHERE id_cliente = "+ id +";";
            owldb.executeUpdate(update);
            return puntiDaInserire;
        }
        return 0;
    }

    /**
     * Poniamo come precondizione che l utente sia inserito in tabella
     *
     * @param id
     * @return true se troviamo l id del cliente nella tabella carte_punti, false se è nella tabella carte_livelli
     * @throws SQLException
     */
    public boolean puntiLivelli(Integer id) throws SQLException {
        String query = "SELECT COUNT(id_cliente) FROM carte_livelli WHERE id_cliente = '" + id + "' ;";
        ResultSet resultSet = owldb.executeQuery(query);
        if(!resultSet.next()){
            String queryLivelli = "SELECT COUNT(id_cliente) FROM carte_livelli WHERE id_cliente = '" + id + "' ;";
            ResultSet resultSet2 = owldb.executeQuery(queryLivelli);
            return !resultSet2.next();
        }
        return true;
    }

    public ArrayList<Premio> estraiPremi() throws SQLException {
        String queryEstraiPremi = "SELECT * FROM premi;";
        ResultSet rs = null;
        try {
            rs = owldb.executeQuery(queryEstraiPremi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Premio> premiEstratti;
        premiEstratti = new ArrayList<>();
        while(rs.next()){
            Integer idpremio = rs.getObject(1, Integer.class);
            String nome = rs.getObject(2, String.class);
            String descrizione = rs.getObject(3, String.class);
            Integer puntiNecessari = rs.getObject(4, Integer.class);
            premiEstratti.add(new Premio(idpremio, nome, descrizione, puntiNecessari));
        }
        return premiEstratti;
    }

    public int estraiPuntiCliente(Integer id) throws SQLException {
        String queryEstraiPuntiCliente = "SELECT punti FROM carte_punti WHERE id_cliente = "+id+";";
        ResultSet rs = null;
        rs = owldb.executeQuery(queryEstraiPuntiCliente);
        Integer puntiCliente = null;
        if(rs.next()){
            puntiCliente = rs.getObject(1, Integer.class);
        }
        return puntiCliente;
    }

    public void scalaPuntiCliente(Integer idCliente, int idPremioRiscattato) throws SQLException {

        String queryEstraiPuntiNecessari = "SELECT punti_necessari FROM premi WHERE idpremio="+idPremioRiscattato+";";
        ResultSet rs = null;
        try {
            rs = owldb.executeQuery(queryEstraiPuntiNecessari);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Integer puntiNecessari = null;
        if(rs.next()){
            puntiNecessari = rs.getObject(1, Integer.class);
        }

        String queryFinale = "UPDATE carte_punti SET punti = "+(estraiPuntiCliente(idCliente)-puntiNecessari)+ " WHERE id_cliente = "+ idCliente +";";
        owldb.executeUpdate(queryFinale);
    }

    public ArrayList<Livello> estraiLivelli() throws SQLException {
        String queryEstraiPremi = "SELECT * FROM livelli;";
        ResultSet rs = null;
        try {
            rs = owldb.executeQuery(queryEstraiPremi);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Livello> livelli;
        livelli = new ArrayList<>();
        while(rs.next()){
            Integer idLivello = rs.getObject(1, Integer.class);
            String livello = rs.getObject(2, String.class);
            String descrizione = rs.getObject(3, String.class);
            Integer puntiSoglia = rs.getObject(4, Integer.class);
            livelli.add(new Livello(idLivello, livello, descrizione, puntiSoglia));
        }
        return livelli;
    }

    public Integer livelloAttualeCliente(Integer idCliente) throws SQLException {
        String query = "SELECT idlivello FROM carte_livelli WHERE id_cliente=" + idCliente + ";";
        ResultSet rs = null;
        try {
            rs = owldb.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Integer idLivello = null;
        if (rs.next()) {
            idLivello = rs.getObject(1, Integer.class);
        }
        return idLivello;
    }

    public Integer estraiLivelloCliente(Integer idCliente) throws SQLException {
        String query = "SELECT idlivello FROM carte_livelli WHERE id_cliente=" + idCliente + ";";
        ResultSet rs = null;
        try {
            rs = owldb.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rs.next()) {
            return rs.getObject(1, Integer.class);
        }
        return null;
    }

    public void aggiornaLivelloCLiente(Integer idCliente, Integer idLivello) throws SQLException {
        idLivello+=1;
        System.out.println(idLivello +"  " +  idCliente);
        String update = " UPDATE carte_livelli SET idlivello = " + idLivello + " WHERE id_cliente = "+ idCliente +";";
        try {
            owldb.executeUpdate(update);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}