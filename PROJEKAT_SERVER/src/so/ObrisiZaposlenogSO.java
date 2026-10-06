package so;

import baza.DBBroker;
import baza.Konekcija;
import domeni.Zaposleni;
import java.sql.Connection;

public class ObrisiZaposlenogSO extends OpstaSistemskaOperacija {

    private boolean uspesno;
    private DBBroker dbb = new DBBroker();

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Zaposleni z = (Zaposleni) parametar;
        Connection con = Konekcija.getInstace().getConnection();

        try {
            uspesno = dbb.delete(z);

            if (!uspesno) {
                throw new Exception("Sistem ne može da obriše zaposlenog.");
            }

            con.commit();

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