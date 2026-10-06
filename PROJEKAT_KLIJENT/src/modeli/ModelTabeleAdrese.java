package modeli;

import domeni.Adresa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleAdrese extends AbstractTableModel {

    private List<Adresa> adrese = new ArrayList<>();
    private String[] kolone = {"Ulica", "Broj", "Sedište"};

    public ModelTabeleAdrese(List<Adresa> adrese) {
        this.adrese = adrese;
    }

    @Override
    public int getRowCount() {
        return adrese.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Adresa a = adrese.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return a.getUlica();
            case 1:
                return a.getBroj();
            case 2:
                return a.isSediste();
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (columnIndex == 2) {
            boolean novoSediste = (boolean) value;

            if (novoSediste) {
                for (Adresa a : adrese) {
                    a.setSediste(false);
                }

                adrese.get(rowIndex).setSediste(true);
            } else {
                adrese.get(rowIndex).setSediste(false);
            }

            fireTableDataChanged();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return Boolean.class;
        }
        return String.class;
    }

    public void dodajAdresu(Adresa adresa) {
        if (adresa.isSediste()) {
            for (Adresa a : adrese) {
                a.setSediste(false);
            }
        }

        adrese.add(adresa);
        fireTableDataChanged();
    }

    public void obrisiAdresu(int red) {
        if (red >= 0 && red < adrese.size()) {
            adrese.remove(red);
            fireTableDataChanged();
        }
    }

    public List<Adresa> getAdrese() {
        return adrese;
    }

    public void setAdrese(List<Adresa> adrese) {
        this.adrese = adrese;
        fireTableDataChanged();
    }
}