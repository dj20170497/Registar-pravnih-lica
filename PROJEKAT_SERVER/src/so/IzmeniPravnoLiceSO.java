package so;

import baza.DBBroker;
import baza.Konekcija;
import domeni.AD;
import domeni.Adresa;
import domeni.DOO;
import domeni.KomanditnoDrustvo;
import domeni.OpstiDomenskiObjekat;
import domeni.OrtackoDrustvo;
import domeni.PravnoLice;
import java.sql.Connection;
import java.util.List;

public class IzmeniPravnoLiceSO extends OpstaSistemskaOperacija {

    private boolean uspesno;
    private DBBroker dbb;

    public IzmeniPravnoLiceSO() {
        dbb = new DBBroker();
        uspesno = false;
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        PravnoLice novoPl = (PravnoLice) parametar;

        Connection con = Konekcija.getInstace().getConnection();

        try {
            PravnoLice kriterijum = new PravnoLice();
            kriterijum.setIdPravnoLice(novoPl.getIdPravnoLice());

            List<OpstiDomenskiObjekat> lista = dbb.select(kriterijum);
            if (lista == null || lista.isEmpty()) {
                throw new Exception("Pravno lice ne postoji.");
            }

            PravnoLice staroPl = (PravnoLice) lista.get(0);

            String stariTip = staroPl.getTipPravnogLica().trim().toLowerCase();
            String noviTip = novoPl.getTipPravnogLica().trim().toLowerCase();

            PravnoLice osnovnoPravnoLice = new PravnoLice();
            osnovnoPravnoLice.setIdPravnoLice(novoPl.getIdPravnoLice());
            osnovnoPravnoLice.setNaziv(novoPl.getNaziv());
            osnovnoPravnoLice.setPib(novoPl.getPib());
            osnovnoPravnoLice.setMaticniBroj(novoPl.getMaticniBroj());
            osnovnoPravnoLice.setTipPravnogLica(noviTip);
            osnovnoPravnoLice.setOdgovornoLice(novoPl.getOdgovornoLice());

            boolean izmenjenoOsnovno = dbb.update(osnovnoPravnoLice);
            if (!izmenjenoOsnovno) {
                throw new Exception("Sistem ne može da izmeni osnovne podatke pravnog lica.");
            }

            if (stariTip.equals(noviTip)) {
                boolean izmenjenPodtip = dbb.update(novoPl);
                if (!izmenjenPodtip) {
                    throw new Exception("Sistem ne može da izmeni podatke podtipa.");
                }
            } else {
                OpstiDomenskiObjekat stariPodtip = kreirajObjekatZaTip(stariTip, staroPl.getIdPravnoLice());

                boolean obrisanStariPodtip = dbb.delete(stariPodtip);
                if (!obrisanStariPodtip) {
                    throw new Exception("Sistem ne može da obriše stare podatke podtipa.");
                }

                boolean sacuvanNoviPodtip = dbb.insert(novoPl);
                if (!sacuvanNoviPodtip) {
                    throw new Exception("Sistem ne može da sačuva nove podatke podtipa.");
                }
            }

            Adresa kriterijumAdresa = new Adresa();
            PravnoLice plZaBrisanjeAdresa = new PravnoLice();
            plZaBrisanjeAdresa.setIdPravnoLice(novoPl.getIdPravnoLice());
            kriterijumAdresa.setPravnoLice(plZaBrisanjeAdresa);

            dbb.delete(kriterijumAdresa);

            if (novoPl.getAdrese() != null) {
                for (Adresa adresa : novoPl.getAdrese()) {
                    adresa.setPravnoLice(novoPl);

                    boolean sacuvanaAdresa = dbb.insert(adresa);
                    if (!sacuvanaAdresa) {
                        throw new Exception("Sistem ne može da sačuva adresu pravnog lica.");
                    }
                }
            }

            con.commit();
            uspesno = true;

        } catch (Exception e) {
            con.rollback();
            uspesno = false;
            throw e;
        }
    }

    private OpstiDomenskiObjekat kreirajObjekatZaTip(String tip, int idPravnoLice) throws Exception {
        switch (tip) {
            case "doo":
                DOO d = new DOO();
                d.setIdPravnoLice(idPravnoLice);
                return d;

            case "ad":
                AD a = new AD();
                a.setIdPravnoLice(idPravnoLice);
                return a;

            case "komanditno_drustvo":
                KomanditnoDrustvo k = new KomanditnoDrustvo();
                k.setIdPravnoLice(idPravnoLice);
                return k;

            case "ortacko_drustvo":
                OrtackoDrustvo o = new OrtackoDrustvo();
                o.setIdPravnoLice(idPravnoLice);
                return o;

            default:
                throw new Exception("Nepoznat tip pravnog lica: " + tip);
        }
    }

    public boolean isUspesno() {
        return uspesno;
    }
}