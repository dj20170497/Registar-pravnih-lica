package so;

import baza.Konekcija;
import domeni.PravnoLice;
import domeni.Mesto;
import domeni.Adresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VratiPravnaLicaPoMestuSO extends OpstaSistemskaOperacija {

    private List<PravnoLice> lista;

    public VratiPravnaLicaPoMestuSO() {
        lista = new ArrayList<>();
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {

        Mesto mesto = (Mesto) parametar;

        String upit =
                "SELECT DISTINCT pl.* " +
                "FROM pravno_lice pl " +
                "JOIN adresa a ON pl.idPravnoLice = a.idPravnoLice " +
                "WHERE a.idMesta = ?";

        Connection con = Konekcija.getInstace().getConnection();

        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, mesto.getIdMesto());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            PravnoLice pl = new PravnoLice();

            pl.setIdPravnoLice(rs.getInt("idPravnoLice"));
            pl.setNaziv(rs.getString("naziv"));
            pl.setPib(rs.getString("pib"));
            pl.setMaticniBroj(rs.getString("maticniBroj"));
            pl.setTipPravnogLica(rs.getString("tipPravnogLica"));
            pl.setOdgovornoLice(rs.getString("odgovornoLice"));

            List<Adresa> adrese = vratiAdreseZaPravnoLice(pl);
            pl.setAdrese(adrese);

            lista.add(pl);
        }

        rs.close();
        ps.close();
    }

    private List<Adresa> vratiAdreseZaPravnoLice(PravnoLice pl) throws Exception {

        List<Adresa> adrese = new ArrayList<>();

        String upit =
                "SELECT a.*, m.naziv AS nazivMesta " +
                "FROM adresa a " +
                "JOIN mesto m ON a.idMesta = m.idMesta " +
                "WHERE a.idPravnoLice = ?";

        Connection con = Konekcija.getInstace().getConnection();

        PreparedStatement ps = con.prepareStatement(upit);
        ps.setInt(1, pl.getIdPravnoLice());

        ResultSet rs = ps.executeQuery();

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
            a.setPravnoLice(pl);

            adrese.add(a);
        }

        rs.close();
        ps.close();

        return adrese;
    }

    public List<PravnoLice> getLista() {
        return lista;
    }
}