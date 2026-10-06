/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domeni;

import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Darko
 */
public interface OpstiDomenskiObjekat {

    String vratiNazivTabele();

    String vratiKoloneZaInsert();

    String vratiVrednostiZaInsert();

    String vratiPrimarniKljuc();

    String vratiVrednostPrimarnogKljuca();

    String vratiVrednostiZaUpdate();

    String vratiUslovZaSelect();

    String vratiUslovZaDelete();

    String vratiJoin();
    
    String vratiKoloneZaSelect();

    List<OpstiDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;
}
