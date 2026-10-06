package domeni;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class KomanditnoDrustvo extends PravnoLice {

    private int brojKomanditora;
    private int brojKomplementara;

    public KomanditnoDrustvo() {
        this.tipPravnogLica = "komanditno_drustvo";
        this.adrese = new ArrayList<>();
    }

    public KomanditnoDrustvo(int idPravnoLice, String naziv, String pib, String maticniBroj,
                             String odgovornoLice, String kriterijum, List<Adresa> adrese,
                             int brojKomanditora, int brojKomplementara) {

        super(idPravnoLice, naziv, pib, maticniBroj,
                "komanditno_drustvo", odgovornoLice, kriterijum, adrese);

        this.brojKomanditora = brojKomanditora;
        this.brojKomplementara = brojKomplementara;
    }

    public int getBrojKomanditora() {
        return brojKomanditora;
    }

    public void setBrojKomanditora(int brojKomanditora) {
        this.brojKomanditora = brojKomanditora;
    }

    public int getBrojKomplementara() {
        return brojKomplementara;
    }

    public void setBrojKomplementara(int brojKomplementara) {
        this.brojKomplementara = brojKomplementara;
    }

    @Override
    public String vratiNazivTabele() {
        return "komanditno_drustvo";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "komanditno_drustvo.*, pravno_lice.*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "idPravnoLice, brojKomanditora, brojKomplementara";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return idPravnoLice + ", " + brojKomanditora + ", " + brojKomplementara;
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
        return "brojKomanditora = " + brojKomanditora + ", " +
               "brojKomplementara = " + brojKomplementara;
    }

    @Override
    public String vratiUslovZaSelect() {
        return " WHERE komanditno_drustvo.idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idPravnoLice = " + idPravnoLice;
    }

    @Override
    public String vratiJoin() {
        return " JOIN pravno_lice ON komanditno_drustvo.idPravnoLice = pravno_lice.idPravnoLice ";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            KomanditnoDrustvo kd = new KomanditnoDrustvo();

            kd.setIdPravnoLice(rs.getInt("idPravnoLice"));
            kd.setNaziv(rs.getString("naziv"));
            kd.setPib(rs.getString("pib"));
            kd.setMaticniBroj(rs.getString("maticniBroj"));
            kd.setTipPravnogLica(rs.getString("tipPravnogLica"));
            kd.setOdgovornoLice(rs.getString("odgovornoLice"));

            kd.setBrojKomanditora(rs.getInt("brojKomanditora"));
            kd.setBrojKomplementara(rs.getInt("brojKomplementara"));

            kd.setAdrese(new ArrayList<>());

            lista.add(kd);
        }

        return lista;
    }

    @Override
    public String toString() {
        return "Komanditno: " + naziv;
    }
}