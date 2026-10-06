package server;

import controller.Controller;
import domeni.Mesto;
import domeni.PravnoLice;
import domeni.RegistracijaPravnihLica;
import domeni.Zaposleni;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static operacije.Operacije.kreirajRegistracijuPravnogLica;
import static operacije.Operacije.login;
import static operacije.Operacije.obrisiPravnoLice;
import static operacije.Operacije.obrisiZaposlenog;
import static operacije.Operacije.odjava;
import static operacije.Operacije.izmeniPravnoLice;
import static operacije.Operacije.izmeniZaposlenog;
import static operacije.Operacije.sacuvajMesto;
import static operacije.Operacije.sacuvajZaposlenog;
import static operacije.Operacije.vratiJednoPravnoLice;
import static operacije.Operacije.vratiMesta;
import static operacije.Operacije.vratiPravnaLica;
import static operacije.Operacije.vratiPravnaLicaPoMestu;
import static operacije.Operacije.vratiZaposlene;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ObradaKlijentskihZahteva() {
    }

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (s != null && !s.isClosed()) {
            try {
                KlijentskiZahtev kz = primiZahtev();
                if (kz == null) {
                    break;
                }

                ServerskiOdgovor so = new ServerskiOdgovor();

                switch (kz.getOperacije()) {

                    case login:
                        Zaposleni z = Controller.getInstance().login((Zaposleni) kz.getParametar());
                        so.setOdgovor(z);
                        break;

                    case kreirajRegistracijuPravnogLica:
                        boolean uspesnoPL = Controller.getInstance()
                                .kreirajPravnoLice((RegistracijaPravnihLica) kz.getParametar());
                        so.setOdgovor(uspesnoPL);
                        break;

                    case vratiPravnaLica:
                        List<PravnoLice> pravnaLica = Controller.getInstance()
                                .vratiPravnaLica((PravnoLice) kz.getParametar());
                        so.setOdgovor(pravnaLica);
                        break;

                    case vratiPravnaLicaPoMestu:
                        List<PravnoLice> pravnaLicaPoMestu = Controller.getInstance()
                                .vratiPravnaLicaPoMestu((Mesto) kz.getParametar());
                        so.setOdgovor(pravnaLicaPoMestu);
                        break;

                    case izmeniPravnoLice:
                        boolean izmenjenoPL = Controller.getInstance()
                                .izmeniPravnoLice((PravnoLice) kz.getParametar());
                        so.setOdgovor(izmenjenoPL);
                        break;

                    case obrisiPravnoLice:
                        boolean obrisanoPL = Controller.getInstance()
                                .obrisiPravnoLice((PravnoLice) kz.getParametar());
                        so.setOdgovor(obrisanoPL);
                        break;

                    case vratiMesta:
                        List<Mesto> mesta = Controller.getInstance().vratiMesta();
                        so.setOdgovor(mesta);
                        break;

                    case odjava:
                        boolean odjavljen = Controller.getInstance().odjava((Zaposleni) kz.getParametar());
                        so.setOdgovor(odjavljen);
                        posaljiOdgovor(so);

                        try {
                            s.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return;

                    case vratiZaposlene:
                        List<Zaposleni> zaposleni = Controller.getInstance().vratiZaposlene();
                        so.setOdgovor(zaposleni);
                        break;

                    case sacuvajZaposlenog:
                        boolean sacuvan = Controller.getInstance()
                                .sacuvajZaposlenog((Zaposleni) kz.getParametar());
                        so.setOdgovor(sacuvan);
                        break;

                    case izmeniZaposlenog:
                        boolean izmenjenZaposleni = Controller.getInstance()
                                .izmeniZaposlenog((Zaposleni) kz.getParametar());
                        so.setOdgovor(izmenjenZaposleni);
                        break;

                    case obrisiZaposlenog:
                        boolean obrisanZaposleni = Controller.getInstance()
                                .obrisiZaposlenog((Zaposleni) kz.getParametar());
                        so.setOdgovor(obrisanZaposleni);
                        break;

                    case vratiJednoPravnoLice:
                        PravnoLice pl = Controller.getInstance()
                                .vratiJednoPravnoLice((PravnoLice) kz.getParametar());
                        so.setOdgovor(pl);
                        break;

                    case sacuvajMesto:
                        boolean sacuvanoMesto = Controller.getInstance()
                                .sacuvajMesto((Mesto) kz.getParametar());
                        so.setOdgovor(sacuvanoMesto);
                        break;

                    default:
                        so.setOdgovor(null);
                        break;
                }

                posaljiOdgovor(so);

            } catch (SocketException | EOFException ex) {
                break;
            } catch (Exception ex) {
                Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }

        zatvoriResurse();
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            return (KlijentskiZahtev) ois.readObject();
        } catch (SocketException | EOFException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void zatvoriResurse() {
        try {
            if (ois != null) {
                ois.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (oos != null) {
                oos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            if (s != null && !s.isClosed()) {
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}