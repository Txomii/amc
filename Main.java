package Practica1B;

import InterfazGrafica.App;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Eloy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        app.setSize(1920, 960);
        
        app.setVisible(true);
        app.setLocationRelativeTo(null);
    }
}
