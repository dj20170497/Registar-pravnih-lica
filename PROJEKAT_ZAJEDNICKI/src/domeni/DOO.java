package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DOO extends PravnoLice {

    private double osnovniKapital;
    private int brojClanova;

    public DOO() {
        this.tipPravnogLica = "doo";
        this.adrese = new ArrayList<>();
    }

    public DOO(int idPravnoLice, String naziv, String pib, String maticniBroj,
               String odgovornoLice, String kriterijum, List<Adresa> adrese,
               double osnovniKapital, int brojClanova) {

        super(idPravnoLice, naziv, pib, maticniBroj, "doo", odgovornoLice, kriterijum, adrese);
        this.osnovniKapital = osnovniKapital;
        this.brojClanova = brojClanova;
    }

    public double getOsnovniKapital() {
        return osnovniKapital;
    }

    public void setOsnovniKapital(double osnovniKapital) {
        this.osnovniKapital = osnovniKapital;
    }

    public int getBrojClanova() {
        return brojClanova;
    }

    public void setBrojClanova(int brojClanova) {
        this.brojClanova = brojClanova;
    }

    @Override
    public String vratiNazivTabele() {
        return "doo";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "doo.*, pravno_lice.*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idPravnoLice, osnovniKapital, brojClanova";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return idPravnoLice + ", " + osnovniKapital + ", " + brojClanova;
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
               "brojClanova = " + brojClanova;
    }

    @Override
    public String vratiUslovZaSelect() {
        return " WHERE doo.idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiJoin() {
        return " JOIN pravno_lice ON doo.idPravnoLice = pravno_lice.idPravnoLice ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            DOO doo = new DOO();
            doo.setIdPravnoLice(rs.getInt("idPravnoLice"));
            doo.setNaziv(rs.getString("naziv"));
            doo.setPib(rs.getString("pib"));
            doo.setMaticniBroj(rs.getString("maticniBroj"));
            doo.setTipPravnogLica(rs.getString("tipPravnogLica"));
            doo.setOdgovornoLice(rs.getString("odgovornoLice"));
            doo.setOsnovniKapital(rs.getDouble("osnovniKapital"));
            doo.setBrojClanova(rs.getInt("brojClanova"));
            doo.setAdrese(new ArrayList<>());

            lista.add(doo);
        }

        return lista;
    }

    @Override
    public String toString() {
        return "DOO: " + naziv;
    }
}