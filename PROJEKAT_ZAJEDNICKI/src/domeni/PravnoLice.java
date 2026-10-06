package domeni;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PravnoLice implements Serializable, OpstiDomenskiObjekat {

    protected int idPravnoLice;
    protected String naziv;
    protected String pib;
    protected String maticniBroj;
    protected String tipPravnogLica;
    protected String odgovornoLice;
    protected String kriterijum;
    protected List<Adresa> adrese;

    public PravnoLice() {
        adrese = new ArrayList<>();
    }

    public PravnoLice(int idPravnoLice, String naziv, String pib, String maticniBroj,
                      String tipPravnogLica, String odgovornoLice,
                      String kriterijum, List<Adresa> adrese) {
        this.idPravnoLice = idPravnoLice;
        this.naziv = naziv;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.tipPravnogLica = tipPravnogLica;
        this.odgovornoLice = odgovornoLice;
        this.kriterijum = kriterijum;
        this.adrese = adrese;
    }

    public int getIdPravnoLice() {
        return idPravnoLice;
    }

    public void setIdPravnoLice(int idPravnoLice) {
        this.idPravnoLice = idPravnoLice;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(String maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getTipPravnogLica() {
        return tipPravnogLica;
    }

    public void setTipPravnogLica(String tipPravnogLica) {
        this.tipPravnogLica = tipPravnogLica;
    }

    public String getOdgovornoLice() {
        return odgovornoLice;
    }

    public void setOdgovornoLice(String odgovornoLice) {
        this.odgovornoLice = odgovornoLice;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    public List<Adresa> getAdrese() {
        return adrese;
    }

    public void setAdrese(List<Adresa> adrese) {
        this.adrese = adrese;
    }

    protected String vratiVrednost(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "NULL";
        }
        return "'" + s + "'";
    }

    @Override
    public String vratiNazivTabele() {
        return "pravno_lice";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv, pib, maticniBroj, tipPravnogLica, odgovornoLice";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return vratiVrednost(naziv) + ", " +
               vratiVrednost(pib) + ", " +
               vratiVrednost(maticniBroj) + ", " +
               vratiVrednost(tipPravnogLica) + ", " +
               vratiVrednost(odgovornoLice);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idPravnoLice";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return String.valueOf(idPravnoLice);
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv = " + vratiVrednost(naziv) + ", " +
               "pib = " + vratiVrednost(pib) + ", " +
               "maticniBroj = " + vratiVrednost(maticniBroj) + ", " +
               "tipPravnogLica = " + vratiVrednost(tipPravnogLica) + ", " +
               "odgovornoLice = " + vratiVrednost(odgovornoLice);
    }

    @Override
    public String vratiUslovZaSelect() {
        if (idPravnoLice > 0) {
            return " WHERE pravno_lice.idPravnoLice = " + idPravnoLice;
        }

        if (kriterijum == null || kriterijum.trim().isEmpty()) {
            return "";
        }

        String k = kriterijum.trim();

        return " WHERE " +
               "pravno_lice.naziv LIKE '%" + k + "%' OR " +
               "pravno_lice.pib LIKE '%" + k + "%' OR " +
               "pravno_lice.maticniBroj LIKE '%" + k + "%' OR " +
               "pravno_lice.tipPravnogLica LIKE '%" + k + "%' OR " +
               "pravno_lice.odgovornoLice LIKE '%" + k + "%'";
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            PravnoLice pl = new PravnoLice();
            pl.setIdPravnoLice(rs.getInt("idPravnoLice"));
            pl.setNaziv(rs.getString("naziv"));
            pl.setPib(rs.getString("pib"));
            pl.setMaticniBroj(rs.getString("maticniBroj"));
            pl.setTipPravnogLica(rs.getString("tipPravnogLica"));
            pl.setOdgovornoLice(rs.getString("odgovornoLice"));
            pl.setAdrese(new ArrayList<>());

            lista.add(pl);
        }

        return lista;
    }

    @Override
    public String toString() {
        return naziv != null ? naziv : "";
    }
}