package so;

import baza.DBBroker;
import domeni.Adresa;
import domeni.OpstiDomenskiObjekat;
import domeni.PravnoLice;
import java.util.ArrayList;
import java.util.List;

public class VratiPravnaLicaSO extends OpstaSistemskaOperacija {

    private List<PravnoLice> pravnaLica;
    private DBBroker dbb;

    public VratiPravnaLicaSO() {
        dbb = new DBBroker();
        pravnaLica = new ArrayList<>();
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        PravnoLice kriterijum = (PravnoLice) parametar;

        List<OpstiDomenskiObjekat> lista = dbb.select(kriterijum);

        pravnaLica = new ArrayList<>();

        if (lista != null) {
            for (OpstiDomenskiObjekat odo : lista) {
                PravnoLice pl = (PravnoLice) odo;

                Adresa kriterijumAdresa = new Adresa();

                PravnoLice plZaAdresu = new PravnoLice();
                plZaAdresu.setIdPravnoLice(pl.getIdPravnoLice());

                kriterijumAdresa.setPravnoLice(plZaAdresu);

                List<OpstiDomenskiObjekat> listaAdresa = dbb.select(kriterijumAdresa);

                List<Adresa> adrese = new ArrayList<>();

                if (listaAdresa != null) {
                    for (OpstiDomenskiObjekat odoAdresa : listaAdresa) {
                        adrese.add((Adresa) odoAdresa);
                    }
                }

                pl.setAdrese(adrese);

                pravnaLica.add(pl);
            }
        }
    }

    public List<PravnoLice> getPravnaLica() {
        return pravnaLica;
    }
}