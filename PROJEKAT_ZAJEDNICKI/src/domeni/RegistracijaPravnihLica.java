package domeni;

import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegistracijaPravnihLica implements Serializable, OpstiDomenskiObjekat {

    private int idRegistracija;
    private Date datumRegistracije;
    private Zaposleni zaposleni;
    private PravnoLice pravnoLice;
    private String kriterijum;

    public RegistracijaPravnihLica() {
    }

    public RegistracijaPravnihLica(int idRegistracija, Date datumRegistracije, Zaposleni zaposleni,
                                   PravnoLice pravnoLice, String kriterijum) {
        this.idRegistracija = idRegistracija;
        this.datumRegistracije = datumRegistracije;
        this.zaposleni = zaposleni;
        this.pravnoLice = pravnoLice;
        this.kriterijum = kriterijum;
    }

    public int getIdRegistracija() {
        return idRegistracija;
    }

    public void setIdRegistracija(int idRegistracija) {
        this.idRegistracija = idRegistracija;
    }

    public Date getDatumRegistracije() {
        return datumRegistracije;
    }

    public void setDatumRegistracije(Date datumRegistracije) {
        this.datumRegistracije = datumRegistracije;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    protected String vratiDatumVrednost(Date d) {
        if (d == null) {
            return "NULL";
        }
        return "'" + d.toString() + "'";
    }

    protected String vratiZaposleniVrednost() {
        if (zaposleni == null) {
            return "NULL";
        }
        return String.valueOf(zaposleni.getIdZaposleni());
    }

    protected String vratiPravnoLiceVrednost() {
        if (pravnoLice == null) {
            return "NULL";
        }
        return String.valueOf(pravnoLice.getIdPravnoLice());
    }

    @Override
    public String vratiNazivTabele() {
        return "registracija_pravnih_lica";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "registracija_pravnih_lica.*, pl.*, z.*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "datumRegistracije, idZaposleni, idPravnoLice";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return vratiDatumVrednost(datumRegistracije) + ", " +
               vratiZaposleniVrednost() + ", " +
               vratiPravnoLiceVrednost();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRegistracija";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return String.valueOf(idRegistracija);
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "datumRegistracije = " + vratiDatumVrednost(datumRegistracije) + ", " +
               "idZaposleni = " + vratiZaposleniVrednost() + ", " +
               "idPravnoLice = " + vratiPravnoLiceVrednost();
    }

    @Override
    public String vratiUslovZaSelect() {

        if (idRegistracija > 0) {
            return " WHERE registracija_pravnih_lica.idRegistracija = " + idRegistracija;
        }

        if (kriterijum == null || kriterijum.trim().isEmpty()) {
            return "";
        }

        String k = kriterijum.trim();

        return " WHERE " +
               "registracija_pravnih_lica.idRegistracija LIKE '%" + k + "%' OR " +
               "pl.naziv LIKE '%" + k + "%' OR " +
               "pl.pib LIKE '%" + k + "%' OR " +
               "pl.maticniBroj LIKE '%" + k + "%' OR " +
               "z.ime LIKE '%" + k + "%' OR " +
               "z.prezime LIKE '%" + k + "%'";
    }

    @Override
    public String vratiUslovZaDelete() {

        if (pravnoLice != null && pravnoLice.getIdPravnoLice() > 0) {
            return " WHERE idPravnoLice = " + pravnoLice.getIdPravnoLice();
        }

        return " WHERE idRegistracija = " + idRegistracija;
    }

    @Override
    public String vratiJoin() {

        return " JOIN pravno_lice pl ON registracija_pravnih_lica.idPravnoLice = pl.idPravnoLice "
             + " JOIN zaposleni z ON registracija_pravnih_lica.idZaposleni = z.idZaposleni ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {

            RegistracijaPravnihLica r = new RegistracijaPravnihLica();

            r.setIdRegistracija(rs.getInt("idRegistracija"));
            r.setDatumRegistracije(rs.getDate("datumRegistracije"));

            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("idZaposleni"));
            z.setIme(rs.getString("ime"));
            z.setPrezime(rs.getString("prezime"));
            z.setKorisnickoIme(rs.getString("korisnickoIme"));

            try {
                z.setLozinka(rs.getString("lozinka"));
            } catch (Exception e) {
            }

            r.setZaposleni(z);

            PravnoLice pl = new PravnoLice();
            pl.setIdPravnoLice(rs.getInt("idPravnoLice"));
            pl.setNaziv(rs.getString("naziv"));
            pl.setPib(rs.getString("pib"));
            pl.setMaticniBroj(rs.getString("maticniBroj"));
            pl.setTipPravnogLica(rs.getString("tipPravnogLica"));
            pl.setOdgovornoLice(rs.getString("odgovornoLice"));

            r.setPravnoLice(pl);

            lista.add(r);
        }

        return lista;
    }

    @Override
    public String toString() {
        return "Registracija broj: " + idRegistracija;
    }
}