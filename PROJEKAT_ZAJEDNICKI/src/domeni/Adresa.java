package domeni;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Adresa implements Serializable, OpstiDomenskiObjekat {

    private int idAdresa;
    private String ulica;
    private String broj;
    private boolean sediste;
    private PravnoLice pravnoLice;
    private Mesto mesto;

    public Adresa() {
    }

    public Adresa(int idAdresa, String ulica, String broj, boolean sediste, PravnoLice pravnoLice, Mesto mesto) {
        this.idAdresa = idAdresa;
        this.ulica = ulica;
        this.broj = broj;
        this.sediste = sediste;
        this.pravnoLice = pravnoLice;
        this.mesto = mesto;
    }

    public int getIdAdresa() {
        return idAdresa;
    }

    public void setIdAdresa(int idAdresa) {
        this.idAdresa = idAdresa;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public boolean isSediste() {
        return sediste;
    }

    public void setSediste(boolean sediste) {
        this.sediste = sediste;
    }

    public PravnoLice getPravnoLice() {
        return pravnoLice;
    }

    public void setPravnoLice(PravnoLice pravnoLice) {
        this.pravnoLice = pravnoLice;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    private String vratiVrednost(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "NULL";
        }
        return "'" + s + "'";
    }

    @Override
    public String vratiNazivTabele() {
        return "adresa";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "adresa.*, m.naziv AS nazivMesta";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "ulica, broj, sediste, idPravnoLice, idMesta";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return vratiVrednost(ulica) + ", " +
               vratiVrednost(broj) + ", " +
               sediste + ", " +
               pravnoLice.getIdPravnoLice() + ", " +
               mesto.getIdMesto();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idAdresa";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return String.valueOf(idAdresa);
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ulica = " + vratiVrednost(ulica) + ", " +
               "broj = " + vratiVrednost(broj) + ", " +
               "sediste = " + sediste + ", " +
               "idPravnoLice = " + pravnoLice.getIdPravnoLice() + ", " +
               "idMesta = " + mesto.getIdMesto();
    }

    @Override
    public String vratiUslovZaSelect() {
        if (pravnoLice != null && pravnoLice.getIdPravnoLice() > 0) {
            return " WHERE adresa.idPravnoLice = " + pravnoLice.getIdPravnoLice();
        }
        return "";
    }

    @Override
    public String vratiUslovZaDelete() {
        if (idAdresa > 0) {
            return " WHERE idAdresa = " + idAdresa;
        }

        if (pravnoLice != null && pravnoLice.getIdPravnoLice() > 0) {
            return " WHERE idPravnoLice = " + pravnoLice.getIdPravnoLice();
        }

        return "";
    }

    @Override
    public String vratiJoin() {
        return " LEFT JOIN mesto m ON adresa.idMesta = m.idMesta ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Adresa a = new Adresa();
            a.setIdAdresa(rs.getInt("idAdresa"));
            a.setUlica(rs.getString("ulica"));
            a.setBroj(rs.getString("broj"));
            a.setSediste(rs.getBoolean("sediste"));

            Mesto m = new Mesto();
            m.setIdMesto(rs.getInt("idMesta"));
            m.setNaziv(rs.getString("nazivMesta"));
            a.setMesto(m);

            PravnoLice pl = new PravnoLice();
            pl.setIdPravnoLice(rs.getInt("idPravnoLice"));
            a.setPravnoLice(pl);

            lista.add(a);
        }

        return lista;
    }
}