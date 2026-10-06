/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package so;

import baza.DBBroker;
import domeni.Zaposleni;
import java.util.List;

public class LoginSO extends OpstaSistemskaOperacija {

    private Zaposleni ulogovaniKorisnik;
    private List<Zaposleni> listaUlogovanih;
    private DBBroker dbb = new DBBroker();

    public LoginSO(List<Zaposleni> listaUlogovanih) {
        this.listaUlogovanih = listaUlogovanih;
    }

    @Override
    protected void izvrsiOperaciju(Object parametar) throws Exception {
        Zaposleni zaposleni = (Zaposleni) parametar;

        Zaposleni novi = dbb.login(zaposleni);

        if (novi == null) {
            ulogovaniKorisnik = null;
            return;
        }

        
        for (Zaposleni z : listaUlogovanih) {
            if (z.equals(novi)) {
                ulogovaniKorisnik = new Zaposleni(-1, null, null, null, null, null);
                return;
            }
        }

        listaUlogovanih.add(novi);
        ulogovaniKorisnik = novi;
    }

    public Zaposleni getUlogovaniKorisnik() {
        return ulogovaniKorisnik;
    }
}