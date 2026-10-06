package controller;

import domeni.Mesto;
import domeni.PravnoLice;
import domeni.RegistracijaPravnihLica;
import domeni.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.IzmeniPravnoLiceSO;
import so.IzmeniZaposlenogSO;
import so.KreirajPravnoLiceSO;
import so.LoginSO;
import so.ObrisiPravnoLiceSO;
import so.ObrisiZaposlenogSO;
import so.OdjavaSO;
import so.SacuvajMestoSO;
import so.SacuvajZaposlenogSO;
import so.VratiJednoPravnoLiceSO;
import so.VratiMestaSO;
import so.VratiPravnaLicaPoMestuSO;
import so.VratiPravnaLicaSO;
import so.VratiZaposleneSO;

public class Controller {

    private static Controller instance;
    private List<Zaposleni> ulogovani = new ArrayList<>();

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

   public Zaposleni login(Zaposleni zaposleni) throws Exception {

    LoginSO so = new LoginSO(ulogovani);
    so.izvrsi(zaposleni);

    return so.getUlogovaniKorisnik();
}

    public boolean kreirajPravnoLice(RegistracijaPravnihLica registracija) throws Exception {
        KreirajPravnoLiceSO so = new KreirajPravnoLiceSO();
        so.izvrsi(registracija);
        return so.isUspesno();
    }

    public List<PravnoLice> vratiPravnaLica(PravnoLice pravnoLice) {
        try {
            VratiPravnaLicaSO so = new VratiPravnaLicaSO();
            so.izvrsi(pravnoLice);
            return so.getPravnaLica();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<PravnoLice> vratiPravnaLicaPoMestu(Mesto mesto) {
        try {
            VratiPravnaLicaPoMestuSO so = new VratiPravnaLicaPoMestuSO();
            so.izvrsi(mesto);
            return so.getLista();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean izmeniPravnoLice(PravnoLice pravnoLice) throws Exception {
        IzmeniPravnoLiceSO so = new IzmeniPravnoLiceSO();
        so.izvrsi(pravnoLice);
        return so.isUspesno();
    }

    public boolean obrisiPravnoLice(PravnoLice pravnoLice) throws Exception {
        ObrisiPravnoLiceSO so = new ObrisiPravnoLiceSO();
        so.izvrsi(pravnoLice);
        return so.isUspesno();
    }

    public List<Mesto> vratiMesta() throws Exception {
        VratiMestaSO so = new VratiMestaSO();
        so.izvrsi(null);
        return so.getMesta();
    }

    public boolean odjava(Zaposleni zaposleni) {
        try {
            OdjavaSO so = new OdjavaSO(ulogovani);
            so.izvrsi(zaposleni);
            return so.isUspesno();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Zaposleni> vratiZaposlene() {
        try {
            VratiZaposleneSO so = new VratiZaposleneSO();
            so.izvrsi(null);
            return so.getZaposleni();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public boolean sacuvajZaposlenog(Zaposleni zaposleni) {
        SacuvajZaposlenogSO so = new SacuvajZaposlenogSO();

        try {
            so.izvrsi(zaposleni);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return so.isUspesno();
    }

    public boolean izmeniZaposlenog(Zaposleni zaposleni) {
        IzmeniZaposlenogSO so = new IzmeniZaposlenogSO();

        try {
            so.izvrsi(zaposleni);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return so.isUspesno();
    }

    public boolean obrisiZaposlenog(Zaposleni zaposleni) {
        ObrisiZaposlenogSO so = new ObrisiZaposlenogSO();

        try {
            so.izvrsi(zaposleni);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return so.isUspesno();
    }

    public PravnoLice vratiJednoPravnoLice(PravnoLice pl) throws Exception {
        VratiJednoPravnoLiceSO so = new VratiJednoPravnoLiceSO();
        so.izvrsi(pl);
        return so.getRezultat();
    }

    public boolean sacuvajMesto(Mesto mesto) {

        SacuvajMestoSO so = new SacuvajMestoSO();

        try {
            so.izvrsi(mesto);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

        return so.isUspesno();
    }
}