package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrtackoDrustvo extends PravnoLice {

    private int brojOrtaka;

    public OrtackoDrustvo() {
        this.tipPravnogLica = "ortacko_drustvo";
        this.adrese = new ArrayList<>();
    }

    public OrtackoDrustvo(int idPravnoLice, String naziv, String pib, String maticniBroj,
                          String odgovornoLice, String kriterijum, List<Adresa> adrese,
                          int brojOrtaka) {

        super(idPravnoLice, naziv, pib, maticniBroj, "ortacko_drustvo", odgovornoLice, kriterijum, adrese);
        this.brojOrtaka = brojOrtaka;
    }

    public int getBrojOrtaka() {
        return brojOrtaka;
    }

    public void setBrojOrtaka(int brojOrtaka) {
        this.brojOrtaka = brojOrtaka;
    }

    @Override
    public String vratiNazivTabele() {
        return "ortacko_drustvo";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "ortacko_drustvo.*, pravno_lice.*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idPravnoLice, brojOrtaka";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return idPravnoLice + ", " + brojOrtaka;
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
        return "brojOrtaka = " + brojOrtaka;
    }

    @Override
    public String vratiUslovZaSelect() {
        return " WHERE ortacko_drustvo.idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiJoin() {
        return " JOIN pravno_lice ON ortacko_drustvo.idPravnoLice = pravno_lice.idPravnoLice ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            OrtackoDrustvo od = new OrtackoDrustvo();
            od.setIdPravnoLice(rs.getInt("idPravnoLice"));
            od.setNaziv(rs.getString("naziv"));
            od.setPib(rs.getString("pib"));
            od.setMaticniBroj(rs.getString("maticniBroj"));
            od.setTipPravnogLica(rs.getString("tipPravnogLica"));
            od.setOdgovornoLice(rs.getString("odgovornoLice"));
            od.setBrojOrtaka(rs.getInt("brojOrtaka"));
            od.setAdrese(new ArrayList<>());

            lista.add(od);
        }

        return lista;
    }

    @Override
    public String toString() {
        return "Ortačko: " + naziv;
    }
}