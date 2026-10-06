/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darko
 */
public class PokreniServer extends Thread {
    ServerSocket serverSoket;

    public PokreniServer() {
        try {
            serverSoket = new ServerSocket(9000);
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PokreniServer(ServerSocket serverSoket) {
        this.serverSoket = serverSoket;
    }

    @Override
    public void run() {
while(!serverSoket.isClosed()){
    System.out.println("Server pokrenut, ceka se klijent");
    try {
        Socket s = serverSoket.accept();
        System.out.println("Klijent povezan");
        ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s);
        nit.start();
    } catch (IOException ex) {
        if (!serverSoket.isClosed()) {
                Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
            } else {
                System.out.println("Server je zaustavljen.");
            }
    }
}
    }
    
    public void zaustaviServer() {
        try {
            serverSoket.close();
           
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
