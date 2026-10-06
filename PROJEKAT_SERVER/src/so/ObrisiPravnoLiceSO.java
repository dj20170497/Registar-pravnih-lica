package so;

import baza.DBBroker;
import baza.Konekcija;
import domeni.PravnoLice;
import domeni.RegistracijaPravnihLica;
import java.sql.Connection;

public class ObrisiPravnoLiceSO extends OpstaSistemskaOperacija {

    private boolean uspesno;
    private DBBroker dbb;

    public ObrisiPravnoLiceSO() {
        dbb = new DBBroker();
        uspesno = false;
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        PravnoLice pl = (PravnoLice) parametar;
        Connection con = Konekcija.getInstace().getConnection();

        try {
            RegistracijaPravnihLica registracija = new RegistracijaPravnihLica();
            registracija.setPravnoLice(pl);

            boolean obrisanaRegistracija = dbb.delete(registracija);
            if (!obrisanaRegistracija) {
                throw new Exception("Sistem ne može da obriše registraciju pravnog lica.");
            }

            boolean obrisanPodtip = dbb.delete(pl);
            if (!obrisanPodtip) {
                throw new Exception("Sistem ne može da obriše tip pravnog lica.");
            }

            PravnoLice osnovnoPravnoLice = new PravnoLice();
            osnovnoPravnoLice.setIdPravnoLice(pl.getIdPravnoLice());

            boolean obrisanoOsnovno = dbb.delete(osnovnoPravnoLice);
            if (!obrisanoOsnovno) {
                throw new Exception("Sistem ne može da obriše osnovne podatke pravnog lica.");
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