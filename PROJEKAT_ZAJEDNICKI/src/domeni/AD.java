package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AD extends PravnoLice {

    private double osnovniKapital;
    private int brojAkcija;

    public AD() {
        this.tipPravnogLica = "ad";
        this.adrese = new ArrayList<>();
    }

    public AD(int idPravnoLice, String naziv, String pib, String maticniBroj,
              String odgovornoLice, String kriterijum, List<Adresa> adrese,
              double osnovniKapital, int brojAkcija) {

        super(idPravnoLice, naziv, pib, maticniBroj, "ad", odgovornoLice, kriterijum, adrese);
        this.osnovniKapital = osnovniKapital;
        this.brojAkcija = brojAkcija;
    }

    public double getOsnovniKapital() {
        return osnovniKapital;
    }

    public void setOsnovniKapital(double osnovniKapital) {
        this.osnovniKapital = osnovniKapital;
    }

    public int getBrojAkcija() {
        return brojAkcija;
    }

    public void setBrojAkcija(int brojAkcija) {
        this.brojAkcija = brojAkcija;
    }

    @Override
    public String vratiNazivTabele() {
        return "ad";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "ad.*, pravno_lice.*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idPravnoLice, osnovniKapital, brojAkcija";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return idPravnoLice + ", " + osnovniKapital + ", " + brojAkcija;
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
        return "osnovniKapital = " + osnovniKapital + ", " +
               "brojAkcija = " + brojAkcija;
    }

    @Override
    public String vratiUslovZaSelect() {
        return " WHERE ad.idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiJoin() {
        return " JOIN pravno_lice ON ad.idPravnoLice = pravno_lice.idPravnoLice ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            AD ad = new AD();
            ad.setIdPravnoLice(rs.getInt("idPravnoLice"));
            ad.setNaziv(rs.getString("naziv"));
            ad.setPib(rs.getString("pib"));
            ad.setMaticniBroj(rs.getString("maticniBroj"));
            ad.setTipPravnogLica(rs.getString("tipPravnogLica"));
            ad.setOdgovornoLice(rs.getString("odgovornoLice"));
            ad.setOsnovniKapital(rs.getDouble("osnovniKapital"));
            ad.setBrojAkcija(rs.getInt("brojAkcija"));
            ad.setAdrese(new ArrayList<>());

            lista.add(ad);
        }

        return lista;
    }

    @Override
    public String toString() {
        return "AD: " + naziv;
    }
}