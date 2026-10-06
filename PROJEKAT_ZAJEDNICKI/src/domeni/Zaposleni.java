package domeni;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Zaposleni implements Serializable, OpstiDomenskiObjekat {

    private int idZaposleni;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private String kriterijum;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposleni, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public Zaposleni(int idZaposleni, String ime, String prezime, String korisnickoIme, String lozinka, String kriterijum) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.kriterijum = kriterijum;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getKriterijum() {
        return kriterijum;
    }

    public void setKriterijum(String kriterijum) {
        this.kriterijum = kriterijum;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idZaposleni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Zaposleni other = (Zaposleni) obj;
        return idZaposleni == other.idZaposleni;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    private String vratiVrednost(String s) {
        if (s == null || s.trim().isEmpty()) {
            return "NULL";
        }
        return "'" + s + "'";
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public String vratiKoloneZaSelect() {
        return "*";
    }

    @Override
    public String vratiKoloneZaInsert() {
        return "ime, prezime, korisnickoIme, lozinka";
    }

    @Override
    public String vratiVrednostiZaInsert() {
        return vratiVrednost(ime) + ", " +
               vratiVrednost(prezime) + ", " +
               vratiVrednost(korisnickoIme) + ", " +
               vratiVrednost(lozinka);
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idZaposleni";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return String.valueOf(idZaposleni);
    }

    @Override
    public String vratiVrednostiZaUpdate() {
        return "ime = " + vratiVrednost(ime) + ", " +
               "prezime = " + vratiVrednost(prezime) + ", " +
               "korisnickoIme = " + vratiVrednost(korisnickoIme) + ", " +
               "lozinka = " + vratiVrednost(lozinka);
    }

    @Override
    public String vratiUslovZaSelect() {
        if (idZaposleni > 0) {
            return " WHERE idZaposleni = " + idZaposleni;
        }

        if (kriterijum == null || kriterijum.trim().isEmpty()) {
            return "";
        }

        String k = kriterijum.trim();

        return " WHERE " +
               "zaposleni.ime LIKE '%" + k + "%' OR " +
               "zaposleni.prezime LIKE '%" + k + "%' OR " +
               "zaposleni.korisnickoIme LIKE '%" + k + "%'";
    }

    @Override
    public String vratiUslovZaDelete() {
        return " WHERE idZaposleni = " + idZaposleni;
    }

    @Override
    public String vratiJoin() {
        return "";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();

        while (rs.next()) {
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("idZaposleni"));
            z.setIme(rs.getString("ime"));
            z.setPrezime(rs.getString("prezime"));
            z.setKorisnickoIme(rs.getString("korisnickoIme"));
            z.setLozinka(rs.getString("lozinka"));
            lista.add(z);
        }

        return lista;
    }
}