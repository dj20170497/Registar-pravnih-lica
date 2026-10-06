package modeli;

import domeni.Adresa;
import domeni.PravnoLice;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabelePravnaLica extends AbstractTableModel {

    private List<PravnoLice> pravnaLica = new ArrayList<>();

    private String[] kolone = {
        "Naziv",
        "PIB",
        "Matični broj",
        "Tip pravnog lica",
        "Odgovorno lice",
        "Mesto"
    };

    public ModelTabelePravnaLica(List<PravnoLice> pravnaLica) {
        this.pravnaLica = pravnaLica;
    }

    @Override
    public int getRowCount() {
        return pravnaLica.size();
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

        PravnoLice pl = pravnaLica.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return pl.getNaziv();

            case 1:
                return pl.getPib();

            case 2:
                return pl.getMaticniBroj();

            case 3:
                return pl.getTipPravnogLica();

            case 4:
                return pl.getOdgovornoLice();

            case 5:

                if (pl.getAdrese() != null) {

                    for (Adresa a : pl.getAdrese()) {

                        if (a.isSediste() && a.getMesto() != null) {
                            return a.getMesto().getNaziv();
                        }
                    }
                }

                return "";

            default:
                return "";
        }
    }

    public PravnoLice vratiPravnoLice(int red) {
        return pravnaLica.get(red);
    }

    public void setPravnaLica(List<PravnoLice> pravnaLica) {
        this.pravnaLica = pravnaLica;
        fireTableDataChanged();
    }
}