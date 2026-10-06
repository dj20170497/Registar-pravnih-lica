package so;

import baza.DBBroker;
import domeni.AD;
import domeni.Adresa;
import domeni.DOO;
import domeni.KomanditnoDrustvo;
import domeni.OpstiDomenskiObjekat;
import domeni.OrtackoDrustvo;
import domeni.PravnoLice;
import java.util.ArrayList;
import java.util.List;

public class VratiJednoPravnoLiceSO extends OpstaSistemskaOperacija {

    private PravnoLice rezultat;
    private DBBroker dbb = new DBBroker();

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {

        PravnoLice pl = (PravnoLice) parametar;

        List<OpstiDomenskiObjekat> lista = dbb.select(pl);

        if (lista == null || lista.isEmpty()) {
            throw new Exception("Pravno lice ne postoji.");
        }

        PravnoLice osnovno = (PravnoLice) lista.get(0);

        String tip = osnovno.getTipPravnogLica();
        if (tip != null) {
            tip = tip.trim().toLowerCase();
        }

        if ("doo".equals(tip)) {
            DOO d = new DOO();
            d.setIdPravnoLice(osnovno.getIdPravnoLice());

            List<OpstiDomenskiObjekat> listaDoo = dbb.select(d);
            if (listaDoo == null || listaDoo.isEmpty()) {
                throw new Exception("DOO podaci ne postoje za idPravnoLice = " + osnovno.getIdPravnoLice());
            }

            rezultat = (PravnoLice) listaDoo.get(0);

        } else if ("ad".equals(tip)) {
            AD a = new AD();
            a.setIdPravnoLice(osnovno.getIdPravnoLice());

            List<OpstiDomenskiObjekat> listaAd = dbb.select(a);
            if (listaAd == null || listaAd.isEmpty()) {
                throw new Exception("AD podaci ne postoje za idPravnoLice = " + osnovno.getIdPravnoLice());
            }

            rezultat = (PravnoLice) listaAd.get(0);

        } else if ("komanditno_drustvo".equals(tip)) {
            KomanditnoDrustvo k = new KomanditnoDrustvo();
            k.setIdPravnoLice(osnovno.getIdPravnoLice());

            List<OpstiDomenskiObjekat> listaK = dbb.select(k);
            if (listaK == null || listaK.isEmpty()) {
                throw new Exception("Komanditno društvo podaci ne postoje za idPravnoLice = " + osnovno.getIdPravnoLice());
            }

            rezultat = (PravnoLice) listaK.get(0);

        } else if ("ortacko_drustvo".equals(tip)) {
            OrtackoDrustvo o = new OrtackoDrustvo();
            o.setIdPravnoLice(osnovno.getIdPravnoLice());

            List<OpstiDomenskiObjekat> listaO = dbb.select(o);
            if (listaO == null || listaO.isEmpty()) {
                throw new Exception("Ortačko društvo podaci ne postoje za idPravnoLice = " + osnovno.getIdPravnoLice());
            }

            rezultat = (PravnoLice) listaO.get(0);

        } else {
            throw new Exception("Nepoznat tip pravnog lica: " + osnovno.getTipPravnogLica());
        }

        Adresa kriterijumAdresa = new Adresa();

        PravnoLice plZaAdresu = new PravnoLice();
        plZaAdresu.setIdPravnoLice(rezultat.getIdPravnoLice());

        kriterijumAdresa.setPravnoLice(plZaAdresu);

        List<OpstiDomenskiObjekat> listaAdresa = dbb.select(kriterijumAdresa);

        List<Adresa> adrese = new ArrayList<>();

        if (listaAdresa != null) {
            for (OpstiDomenskiObjekat odoAdresa : listaAdresa) {
                adrese.add((Adresa) odoAdresa);
            }
        }

        rezultat.setAdrese(adrese);
    }

    public PravnoLice getRezultat() {
        return rezultat;
    }
}