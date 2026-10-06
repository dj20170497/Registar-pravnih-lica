/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import baza.DBBroker;
import domeni.OpstiDomenskiObjekat;
import domeni.Zaposleni;
import java.util.ArrayList;
import java.util.List;

public class VratiZaposleneSO extends OpstaSistemskaOperacija {

    private List<Zaposleni> zaposleni;
    private DBBroker dbb = new DBBroker();

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {

        List<OpstiDomenskiObjekat> lista = dbb.select(new Zaposleni());
        zaposleni = new ArrayList<>();

        if (lista != null) {
            for (OpstiDomenskiObjekat odo : lista) {
                zaposleni.add((Zaposleni) odo);
            }
        }
    }

    public List<Zaposleni> getZaposleni() {
        return zaposleni;
    }
}
