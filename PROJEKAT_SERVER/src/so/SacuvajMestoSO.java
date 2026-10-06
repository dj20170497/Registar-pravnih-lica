package so;

import baza.DBBroker;
import baza.Konekcija;
import domeni.Mesto;
import domeni.OpstiDomenskiObjekat;
import java.util.List;

public class SacuvajMestoSO extends OpstaSistemskaOperacija {

    private boolean uspesno = false;
    private DBBroker dbb = new DBBroker();

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {

        Mesto novoMesto = (Mesto) parametar;

        if (novoMesto.getNaziv() == null || novoMesto.getNaziv().trim().isEmpty()) {
            uspesno = false;
            return;
        }

        novoMesto.setNaziv(novoMesto.getNaziv().trim());

        List<OpstiDomenskiObjekat> lista = dbb.select(new Mesto());

        if (lista != null) {
            for (OpstiDomenskiObjekat odo : lista) {
                Mesto m = (Mesto) odo;

                if (m.getNaziv().equalsIgnoreCase(novoMesto.getNaziv())) {
                    uspesno = false;
                    return;
                }
            }
        }

        boolean sacuvano = dbb.insert(novoMesto);

        if (sacuvano) {
            Konekcija.getInstace().getConnection().commit();
            uspesno = true;
        } else {
            Konekcija.getInstace().getConnection().rollback();
            uspesno = false;
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
}