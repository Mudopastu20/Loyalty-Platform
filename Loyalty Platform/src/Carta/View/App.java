package Carta.View;
import Carta.Controller.Controller;


import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception, SQLException {
        Controller controller = new Controller();
        controller.executeProgram();
    }
}

