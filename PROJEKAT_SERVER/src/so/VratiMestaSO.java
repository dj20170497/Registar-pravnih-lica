/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import baza.DBBroker;
import domeni.Mesto;
import domeni.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;

public class VratiMestaSO extends OpstaSistemskaOperacija {

    private List<Mesto> mesta;
    private DBBroker dbb = new DBBroker();

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        List<OpstiDomenskiObjekat> lista = dbb.select(new Mesto());
        mesta = new ArrayList<>();

        if (lista != null) {
            for (OpstiDomenskiObjekat odo : lista) {
                mesta.add((Mesto) odo);
            }
        }
    }

    public List<Mesto> getMesta() {
        return mesta;
    }
}
