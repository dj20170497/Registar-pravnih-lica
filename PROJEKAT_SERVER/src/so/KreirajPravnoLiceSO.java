package so;

import baza.DBBroker;
import baza.Konekcija;
import domeni.Adresa;
import domeni.PravnoLice;
import domeni.RegistracijaPravnihLica;
import java.sql.Connection;

public class KreirajPravnoLiceSO extends OpstaSistemskaOperacija {

    private boolean uspesno;
    private DBBroker dbb;

    public KreirajPravnoLiceSO() {
        dbb = new DBBroker();
        uspesno = false;
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        RegistracijaPravnihLica registracija = (RegistracijaPravnihLica) parametar;
        PravnoLice pl = registracija.getPravnoLice();

        Connection con = Konekcija.getInstace().getConnection();

        try {
            PravnoLice osnovnoPravnoLice = new PravnoLice();
            osnovnoPravnoLice.setNaziv(pl.getNaziv());
            osnovnoPravnoLice.setPib(pl.getPib());
            osnovnoPravnoLice.setMaticniBroj(pl.getMaticniBroj());
            osnovnoPravnoLice.setTipPravnogLica(pl.getTipPravnogLica());
            osnovnoPravnoLice.setOdgovornoLice(pl.getOdgovornoLice());

            int generisaniId = dbb.insertVratiID(osnovnoPravnoLice);

            if (generisaniId <= 0) {
                throw new Exception("Sistem ne može da dobije ID pravnog lica.");
            }

            pl.setIdPravnoLice(generisaniId);

            boolean sacuvanPodtip = dbb.insert(pl);
            if (!sacuvanPodtip) {
                throw new Exception("Sistem ne može da sačuva tip pravnog lica.");
            }

            if (pl.getAdrese() != null) {
                for (Adresa adresa : pl.getAdrese()) {
                    adresa.setPravnoLice(pl);

                    boolean sacuvanaAdresa = dbb.insert(adresa);
                    if (!sacuvanaAdresa) {
                        throw new Exception("Sistem ne može da sačuva adresu pravnog lica.");
                    }
                }
            }

            registracija.setPravnoLice(pl);

            boolean sacuvanaRegistracija = dbb.insert(registracija);
            if (!sacuvanaRegistracija) {
                throw new Exception("Sistem ne može da sačuva registraciju pravnog lica.");
            }

            con.commit();
            uspesno = true;

        } catch (Exception e) {
            con.rollback();
            uspesno = false;
            throw e;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
}