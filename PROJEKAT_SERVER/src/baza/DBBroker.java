package baza;

import domeni.OpstiDomenskiObjekat;
import domeni.Zaposleni;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBroker {

    public Zaposleni login(Zaposleni zaposleni) throws Exception {

    String upit = "SELECT * FROM zaposleni WHERE korisnickoIme = ? AND lozinka = ?";

    try {
        PreparedStatement ps = Konekcija.getInstace()
                .getConnection()
                .prepareStatement(upit);

        ps.setString(1, zaposleni.getKorisnickoIme());
        ps.setString(2, zaposleni.getLozinka());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Zaposleni novi = new Zaposleni();

            novi.setIdZaposleni(rs.getInt("idZaposleni"));
            novi.setIme(rs.getString("ime"));
            novi.setPrezime(rs.getString("prezime"));
            novi.setKorisnickoIme(rs.getString("korisnickoIme"));
            novi.setLozinka(rs.getString("lozinka"));

            return novi;
        }

        return null;

    } catch (Exception ex) {
        throw new Exception("Greška pri povezivanju sa bazom ili izvršavanju login upita.", ex);
    }
}

    public boolean insert(OpstiDomenskiObjekat odo) {
        try {
            String upit = "INSERT INTO " + odo.vratiNazivTabele()
                    + " (" + odo.vratiKoloneZaInsert() + ") VALUES ("
                    + odo.vratiVrednostiZaInsert() + ")";

            PreparedStatement ps = Konekcija.getInstace().getConnection().prepareStatement(upit);
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int insertVratiID(OpstiDomenskiObjekat odo) throws Exception {
        String upit = "INSERT INTO " + odo.vratiNazivTabele()
                + " (" + odo.vratiKoloneZaInsert() + ") VALUES ("
                + odo.vratiVrednostiZaInsert() + ")";

        PreparedStatement ps = Konekcija.getInstace().getConnection()
                .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

        int brojRedova = ps.executeUpdate();

        if (brojRedova == 0) {
            throw new Exception("Insert nije uspeo.");
        }

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }

        throw new Exception("Sistem ne može da vrati generisani ID.");
    }

    public boolean update(OpstiDomenskiObjekat odo) {
        try {
            String upit = "UPDATE " + odo.vratiNazivTabele()
                    + " SET " + odo.vratiVrednostiZaUpdate()
                    + " WHERE " + odo.vratiPrimarniKljuc() + " = " + odo.vratiVrednostPrimarnogKljuca();

            PreparedStatement ps = Konekcija.getInstace().getConnection().prepareStatement(upit);
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(OpstiDomenskiObjekat odo) {
        try {
            String upit = "DELETE FROM " + odo.vratiNazivTabele() + odo.vratiUslovZaDelete();

            PreparedStatement ps = Konekcija.getInstace().getConnection().prepareStatement(upit);
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OpstiDomenskiObjekat> select(OpstiDomenskiObjekat odo) {
        try {
            String upit = "SELECT " + odo.vratiKoloneZaSelect() + " FROM " + odo.vratiNazivTabele();

            if (odo.vratiJoin() != null && !odo.vratiJoin().isEmpty()) {
                upit += " " + odo.vratiJoin();
            }

            if (odo.vratiUslovZaSelect() != null && !odo.vratiUslovZaSelect().isEmpty()) {
                upit += " " + odo.vratiUslovZaSelect();
            }

            PreparedStatement ps = Konekcija.getInstace().getConnection().prepareStatement(upit);
            ResultSet rs = ps.executeQuery();

            return odo.vratiListu(rs);

        } catch (Exception ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}