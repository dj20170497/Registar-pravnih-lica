/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domeni.Zaposleni;
import java.util.List;

public class OdjavaSO extends OpstaSistemskaOperacija {

    private boolean uspesno;
    private List<Zaposleni> ulogovani;

    public OdjavaSO(List<Zaposleni> ulogovani) {
        this.ulogovani = ulogovani;
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Zaposleni zaposleni = (Zaposleni) parametar;
        uspesno = ulogovani.remove(zaposleni);
    }

    public boolean isUspesno() {
        return uspesno;
    }
}
