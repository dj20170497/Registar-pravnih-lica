package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class Komunikacija {

    private Socket s;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private static Komunikacija instance;

    private Komunikacija() {
        try {
            s = new Socket("localhost", 9000);

            oos = new ObjectOutputStream(s.getOutputStream());
            oos.flush();

            ois = new ObjectInputStream(s.getInputStream());

        } catch (IOException ex) {
            System.out.println("Nije moguće povezivanje sa serverom.");
        }
    }

    public Komunikacija(Socket s) {
        this.s = s;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.flush();

            ois = new ObjectInputStream(s.getInputStream());

        } catch (IOException ex) {
            System.out.println("Greška pri kreiranju komunikacije.");
        }
    }

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public ServerskiOdgovor primiOdgovor() {
        try {
            return (ServerskiOdgovor) ois.readObject();

        } catch (Exception ex) {
            System.out.println("Server nije poslao odgovor ili je konekcija prekinuta.");
            return null;
        }
    }

    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            oos.writeObject(kz);
            oos.flush();

        } catch (IOException ex) {
            System.out.println("Nije moguce poslati zahtev serveru.");
        }
    }

    public void zatvoriKonekciju() {
        try {
            if (ois != null) {
                ois.close();
            }
            if (oos != null) {
                oos.close();
            }
            if (s != null && !s.isClosed()) {
                s.close();
            }

            instance = null;

        } catch (IOException ex) {
            System.out.println("Greška pri zatvaranju konekcije.");
        }
    }
}