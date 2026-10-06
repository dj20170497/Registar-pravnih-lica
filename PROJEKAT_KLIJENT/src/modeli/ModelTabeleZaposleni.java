/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domeni.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Darko
 */
public class ModelTabeleZaposleni extends AbstractTableModel {
List<Zaposleni> zaposleni = new ArrayList<>();
String[] kolone = {"ime", "prezime", "korisnicko ime"};
    
    public ModelTabeleZaposleni(List<Zaposleni> zaposleni){
        this.zaposleni = zaposleni;
    }
    @Override
    public int getRowCount() {
        return zaposleni.size();
    }

    @Override
    public int getColumnCount() {
return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
Zaposleni z = zaposleni.get(rowIndex);
switch(columnIndex){
    case 0:
        return z.getIme();
    case 1:
        return z.getPrezime();
    case 2:
        return z.getKorisnickoIme();
    default:
        return "NA";
}
    }

    @Override
    public String getColumnName(int column) {
return kolone[column];
    }
    
     public void setLista(List<Zaposleni> zaposleni) {
        this.zaposleni = zaposleni;
        fireTableDataChanged();
    }
     public Zaposleni vratiZaposlenog(int red) {
    return zaposleni.get(red);
}
}
