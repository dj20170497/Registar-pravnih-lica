package domeni;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Mesto implements Serializable, OpstiDomenskiObjekat {

    private int idMesto;
    private String naziv;

    public Mesto() {
    }

    public Mesto(int idMesto, String naziv) {
        this.idMesto = idMesto;
        this.naziv = naziv;
    }

    public int getIdMesto() {
        return idMesto;
    }

    public void setIdMesto(int idMesto) {
        this.idMesto = idMesto;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "naziv";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return "'" + naziv + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idMesta";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return String.valueOf(idMesto);
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "naziv = '" + naziv + "'";
    }

    @Override
    public String vratiUslovZaSelect() {
        if (idMesto > 0) {
            return " WHERE idMesta = " + idMesto;
        }
        return "";
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idMesta = " + idMesto;
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Mesto m = new Mesto();
            m.setIdMesto(rs.getInt("idMesta"));
            m.setNaziv(rs.getString("naziv"));
            lista.add(m);
        }

        return lista;
    }

    @Override
    public String toString() {
        return naziv;
    }
}