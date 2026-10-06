/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import domeni.AD;
import domeni.DOO;
import domeni.KomanditnoDrustvo;
import domeni.Mesto;
import domeni.OrtackoDrustvo;
import domeni.PravnoLice;
import domeni.RegistracijaPravnihLica;
import domeni.Zaposleni;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import domeni.Adresa;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import modeli.ModelTabeleAdrese;

public class SacuvajPravnoLiceForma extends javax.swing.JFrame {

    private PravnoLice pravnoLiceZaIzmenu;
    private Zaposleni ulogovaniZaposleni;
    private ModelTabeleAdrese modelAdrese;
    private List<Adresa> sveAdrese = new ArrayList<>();

    public SacuvajPravnoLiceForma(List<Mesto> mesta, Zaposleni ulogovaniZaposleni) {
        initComponents();
        this.ulogovaniZaposleni = ulogovaniZaposleni;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        popuniComboMesta(mesta);
        srediTabeluAdresa();
        jComboBoxMesto.addActionListener(e -> osveziTabeluAdresa());
        prikaziPoljaZaTip();

        jButtonObrisi.setVisible(false);

        jComboBoxTipPrivrednogDrustva.addActionListener(e -> prikaziPoljaZaTip());
    }

    public SacuvajPravnoLiceForma(List<Mesto> mesta, Zaposleni ulogovaniZaposleni, PravnoLice pl) {
        initComponents();
        this.ulogovaniZaposleni = ulogovaniZaposleni;
        this.pravnoLiceZaIzmenu = pl;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        popuniComboMesta(mesta);
        srediTabeluAdresa();
        jComboBoxMesto.addActionListener(e -> osveziTabeluAdresa());
        popuniFormu(pl);
        prikaziPoljaZaTip();

        jButtonObrisi.setVisible(true);

        jComboBoxTipPrivrednogDrustva.addActionListener(e -> prikaziPoljaZaTip());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelAdresa = new javax.swing.JLabel();
        jLabelNazivSubjekta = new javax.swing.JLabel();
        jLabelPib = new javax.swing.JLabel();
        jLabelMaticni = new javax.swing.JLabel();
        jLabelOdgovornoLice = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNazivSubjekta = new javax.swing.JTextField();
        jTextFieldPib = new javax.swing.JTextField();
        jTextFieldMaticniBroj = new javax.swing.JTextField();
        jTextFieldOdgovornoLice = new javax.swing.JTextField();
        jButtonObrisi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabelMesto = new javax.swing.JLabel();
        jComboBoxMesto = new javax.swing.JComboBox<>();
        jComboBoxTipPrivrednogDrustva = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldOsnovniKapital = new javax.swing.JTextField();
        jTextFieldBrojAkcija = new javax.swing.JTextField();
        jTextFieldBrojClanova = new javax.swing.JTextField();
        jTextFieldBrojKomanditora = new javax.swing.JTextField();
        jTextFieldBrojKomplementara = new javax.swing.JTextField();
        jTextFieldBrojOrtaka = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnDodajAdresu = new javax.swing.JButton();
        btnObrisiAdresu = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelAdresa.setText("Adresa");

        jLabelNazivSubjekta.setText("Naziv subjekta:");

        jLabelPib.setText("PIB:");

        jLabelMaticni.setText("Maticni broj:");

        jLabelOdgovornoLice.setText("Odgovorno lice");

        jLabel1.setText("Izaberi TIP preduzeca:");

        jButtonObrisi.setText("Obrisi");
        jButtonObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonObrisiActionPerformed(evt);
            }
        });

        jButton1.setText("Sacuvaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelMesto.setText("Mesto:");

        jComboBoxMesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMestoActionPerformed(evt);
            }
        });

        jComboBoxTipPrivrednogDrustva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "doo", "ad", "ortacko_drustvo", "komanditno_drustvo" }));

        jLabel2.setText("Osnovni kapital:");

        jLabel3.setText("Broj akcija: ");

        jLabel4.setText("Broj clanova:");

        jLabel5.setText("Broj komanditora:");

        jLabel6.setText("Broj komplementara: ");

        jLabel7.setText("Broj ortaka:");

        jLabel8.setText("RSD");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnDodajAdresu.setText("+");
        btnDodajAdresu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajAdresuActionPerformed(evt);
            }
        });

        btnObrisiAdresu.setText("-");
        btnObrisiAdresu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiAdresuActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Prikazi sve adrese pravnog lica");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNazivSubjekta)
                    .addComponent(jLabelPib, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMaticni)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelOdgovornoLice))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxTipPrivrednogDrustva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldOsnovniKapital, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnObrisiAdresu)
                            .addComponent(btnDodajAdresu)))
                    .addComponent(jTextFieldOdgovornoLice, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPib, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNazivSubjekta, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMaticniBroj, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBrojAkcija, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBrojClanova, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBrojOrtaka, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldBrojKomplementara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(jTextFieldBrojKomanditora, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTipPrivrednogDrustva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNazivSubjekta)
                    .addComponent(jTextFieldNazivSubjekta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPib)
                    .addComponent(jTextFieldPib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMaticni)
                    .addComponent(jTextFieldMaticniBroj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelAdresa)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnObrisiAdresu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDodajAdresu)))
                .addGap(2, 2, 2)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelOdgovornoLice)
                    .addComponent(jTextFieldOdgovornoLice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldOsnovniKapital, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldBrojAkcija, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldBrojClanova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldBrojKomanditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldBrojKomplementara, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldBrojOrtaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void popuniComboMesta(List<Mesto> mesta) {
        jComboBoxMesto.removeAllItems();
        if (mesta != null) {
            for (Mesto m : mesta) {
                jComboBoxMesto.addItem(m);
            }
        }
    }

    private void prikaziPoljaZaTip() {
        String tip = (String) jComboBoxTipPrivrednogDrustva.getSelectedItem();

        jLabel2.setVisible(false);
        jTextFieldOsnovniKapital.setVisible(false);
        jLabel8.setVisible(false);

        jLabel3.setVisible(false);
        jTextFieldBrojAkcija.setVisible(false);

        jLabel4.setVisible(false);
        jTextFieldBrojClanova.setVisible(false);

        jLabel5.setVisible(false);
        jTextFieldBrojKomanditora.setVisible(false);

        jLabel6.setVisible(false);
        jTextFieldBrojKomplementara.setVisible(false);

        jLabel7.setVisible(false);
        jTextFieldBrojOrtaka.setVisible(false);

        if (tip == null) {
            return;
        }

        if (tip.startsWith("doo")) {
            jLabel2.setVisible(true);
            jTextFieldOsnovniKapital.setVisible(true);
            jLabel8.setVisible(true);
            jLabel4.setVisible(true);
            jTextFieldBrojClanova.setVisible(true);
        } else if (tip.startsWith("ad")) {
            jLabel2.setVisible(true);
            jTextFieldOsnovniKapital.setVisible(true);
            jLabel8.setVisible(true);
            jLabel3.setVisible(true);
            jTextFieldBrojAkcija.setVisible(true);
        } else if (tip.startsWith("komanditno_drustvo")) {
            jLabel5.setVisible(true);
            jTextFieldBrojKomanditora.setVisible(true);
            jLabel6.setVisible(true);
            jTextFieldBrojKomplementara.setVisible(true);
        } else if (tip.startsWith("ortacko_drustvo")) {
            jLabel7.setVisible(true);
            jTextFieldBrojOrtaka.setVisible(true);
        }

        revalidate();
        repaint();
    }

    private void validirajFormu() throws Exception {
        if (jTextFieldNazivSubjekta.getText().trim().isEmpty()) {
            throw new Exception("Naziv subjekta je obavezan.");
        }
        if (jTextFieldPib.getText().trim().isEmpty()) {
            throw new Exception("PIB je obavezan.");
        }
        if (jTextFieldMaticniBroj.getText().trim().isEmpty()) {
            throw new Exception("Matični broj je obavezan.");
        }
        if (jTextFieldOdgovornoLice.getText().trim().isEmpty()) {
            throw new Exception("Odgovorno lice je obavezno.");
        }
        if (jComboBoxMesto.getSelectedItem() == null) {
            throw new Exception("Morate izabrati mesto.");
        }
        if (sveAdrese.isEmpty()) {
            throw new Exception("Morate uneti bar jednu adresu.");
        }

        boolean imaSediste = false;

        for (Adresa a : sveAdrese) {
            if (a.getUlica() == null || a.getUlica().trim().isEmpty()) {
                throw new Exception("Ulica je obavezna za svaku adresu.");
            }
            if (a.getBroj() == null || a.getBroj().trim().isEmpty()) {
                throw new Exception("Broj je obavezan za svaku adresu.");
            }
            if (a.isSediste()) {
                imaSediste = true;
            }
        }

        if (!imaSediste) {
            throw new Exception("Morate označiti sedište.");
        }

        String tip = (String) jComboBoxTipPrivrednogDrustva.getSelectedItem();

        try {
            if (tip.startsWith("doo")) {
                Double.parseDouble(jTextFieldOsnovniKapital.getText().trim());
                Integer.parseInt(jTextFieldBrojClanova.getText().trim());
            } else if (tip.startsWith("ad")) {
                Double.parseDouble(jTextFieldOsnovniKapital.getText().trim());
                Integer.parseInt(jTextFieldBrojAkcija.getText().trim());
            } else if (tip.startsWith("komanditno_drustvo")) {
                Integer.parseInt(jTextFieldBrojKomanditora.getText().trim());
                Integer.parseInt(jTextFieldBrojKomplementara.getText().trim());
            } else if (tip.startsWith("ortacko_drustvo")) {
                Integer.parseInt(jTextFieldBrojOrtaka.getText().trim());
            }
        } catch (NumberFormatException e) {
            throw new Exception("Specifična numerička polja nisu ispravno uneta.");
        }
    }

    private PravnoLice kreirajPravnoLiceIzForme() throws Exception {
        String naziv = jTextFieldNazivSubjekta.getText().trim();
        String pib = jTextFieldPib.getText().trim();
        String maticniBroj = jTextFieldMaticniBroj.getText().trim();
        String odgovornoLice = jTextFieldOdgovornoLice.getText().trim();

        String tip = (String) jComboBoxTipPrivrednogDrustva.getSelectedItem();

        if (tip.startsWith("doo")) {
            DOO d = new DOO();
            d.setNaziv(naziv);
            d.setPib(pib);
            d.setMaticniBroj(maticniBroj);
            d.setOdgovornoLice(odgovornoLice);
            d.setAdrese(sveAdrese);
            d.setOsnovniKapital(Double.parseDouble(jTextFieldOsnovniKapital.getText().trim()));
            d.setBrojClanova(Integer.parseInt(jTextFieldBrojClanova.getText().trim()));

            if (pravnoLiceZaIzmenu != null) {
                d.setIdPravnoLice(pravnoLiceZaIzmenu.getIdPravnoLice());
            }
            return d;
        }

        if (tip.startsWith("ad")) {
            AD a = new AD();
            a.setNaziv(naziv);
            a.setPib(pib);
            a.setMaticniBroj(maticniBroj);
            a.setOdgovornoLice(odgovornoLice);
            a.setAdrese(sveAdrese);
            a.setOsnovniKapital(Double.parseDouble(jTextFieldOsnovniKapital.getText().trim()));
            a.setBrojAkcija(Integer.parseInt(jTextFieldBrojAkcija.getText().trim()));

            if (pravnoLiceZaIzmenu != null) {
                a.setIdPravnoLice(pravnoLiceZaIzmenu.getIdPravnoLice());
            }
            return a;
        }

        if (tip.startsWith("komanditno_drustvo")) {
            KomanditnoDrustvo k = new KomanditnoDrustvo();
            k.setNaziv(naziv);
            k.setPib(pib);
            k.setMaticniBroj(maticniBroj);
            k.setOdgovornoLice(odgovornoLice);
            k.setAdrese(sveAdrese);
            k.setBrojKomanditora(Integer.parseInt(jTextFieldBrojKomanditora.getText().trim()));
            k.setBrojKomplementara(Integer.parseInt(jTextFieldBrojKomplementara.getText().trim()));

            if (pravnoLiceZaIzmenu != null) {
                k.setIdPravnoLice(pravnoLiceZaIzmenu.getIdPravnoLice());
            }
            return k;
        }

        if (tip.startsWith("ortacko_drustvo")) {
            OrtackoDrustvo o = new OrtackoDrustvo();
            o.setNaziv(naziv);
            o.setPib(pib);
            o.setMaticniBroj(maticniBroj);
            o.setOdgovornoLice(odgovornoLice);
            o.setAdrese(sveAdrese);
            o.setBrojOrtaka(Integer.parseInt(jTextFieldBrojOrtaka.getText().trim()));

            if (pravnoLiceZaIzmenu != null) {
                o.setIdPravnoLice(pravnoLiceZaIzmenu.getIdPravnoLice());
            }
            return o;
        }

        throw new Exception("Nepoznat tip pravnog lica.");
    }

    private RegistracijaPravnihLica kreirajRegistracijuIzForme() throws Exception {
        PravnoLice pl = kreirajPravnoLiceIzForme();

        RegistracijaPravnihLica r = new RegistracijaPravnihLica();
        r.setDatumRegistracije(new java.sql.Date(System.currentTimeMillis()));
        r.setPravnoLice(pl);
        r.setZaposleni(ulogovaniZaposleni);

        return r;
    }

    private void popuniFormu(PravnoLice pl) {
        if (pl == null) {
            return;
        }

        jTextFieldNazivSubjekta.setText(pl.getNaziv());
        jTextFieldPib.setText(pl.getPib());
        jTextFieldMaticniBroj.setText(pl.getMaticniBroj());

        jTextFieldOdgovornoLice.setText(pl.getOdgovornoLice());
        if (pl.getAdrese() != null) {
            sveAdrese = new ArrayList<>(pl.getAdrese());
            for (Adresa a : pl.getAdrese()) {
                if (a.isSediste() && a.getMesto() != null) {
                    for (int i = 0; i < jComboBoxMesto.getItemCount(); i++) {
                        Mesto m = jComboBoxMesto.getItemAt(i);

                        if (m.getIdMesto() == a.getMesto().getIdMesto()) {
                            jComboBoxMesto.setSelectedIndex(i);
                            osveziTabeluAdresa();
                            break;
                        }
                    }

                    break;
                }
            }

            String tip = pl.getTipPravnogLica();

            if ("doo".equalsIgnoreCase(tip)) {
                jComboBoxTipPrivrednogDrustva.setSelectedIndex(0);
                if (pl instanceof DOO d) {
                    jTextFieldOsnovniKapital.setText(String.valueOf(d.getOsnovniKapital()));
                    jTextFieldBrojClanova.setText(String.valueOf(d.getBrojClanova()));
                }
            } else if ("ad".equalsIgnoreCase(tip)) {
                jComboBoxTipPrivrednogDrustva.setSelectedIndex(1);
                if (pl instanceof AD a) {
                    jTextFieldOsnovniKapital.setText(String.valueOf(a.getOsnovniKapital()));
                    jTextFieldBrojAkcija.setText(String.valueOf(a.getBrojAkcija()));
                }
            } else if ("ortacko_drustvo".equalsIgnoreCase(tip)) {
                jComboBoxTipPrivrednogDrustva.setSelectedIndex(2);
                if (pl instanceof OrtackoDrustvo o) {
                    jTextFieldBrojOrtaka.setText(String.valueOf(o.getBrojOrtaka()));
                }
            } else if ("komanditno_drustvo".equalsIgnoreCase(tip)) {
                jComboBoxTipPrivrednogDrustva.setSelectedIndex(3);
                if (pl instanceof KomanditnoDrustvo k) {
                    jTextFieldBrojKomanditora.setText(String.valueOf(k.getBrojKomanditora()));
                    jTextFieldBrojKomplementara.setText(String.valueOf(k.getBrojKomplementara()));
                }
            }

            prikaziPoljaZaTip();
        }
    }

    private void ocistiFormu() {
        jTextFieldNazivSubjekta.setText("");
        jTextFieldPib.setText("");
        jTextFieldMaticniBroj.setText("");

        jTextFieldOdgovornoLice.setText("");
        jTextFieldOsnovniKapital.setText("");
        jTextFieldBrojAkcija.setText("");
        jTextFieldBrojClanova.setText("");
        jTextFieldBrojKomanditora.setText("");
        jTextFieldBrojKomplementara.setText("");
        jTextFieldBrojOrtaka.setText("");
        if (jComboBoxMesto.getItemCount() > 0) {
            jComboBoxMesto.setSelectedIndex(0);
        }
        jComboBoxTipPrivrednogDrustva.setSelectedIndex(0);

        sveAdrese.clear();
        osveziTabeluAdresa();
        prikaziPoljaZaTip();
    }

    private void jButtonObrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonObrisiActionPerformed
        try {
            if (pravnoLiceZaIzmenu == null) {
                JOptionPane.showMessageDialog(this, "Nije izabrano pravno lice za brisanje.");
                return;
            }

            int potvrda = JOptionPane.showConfirmDialog(
                    this,
                    "Da li ste sigurni da želite da obrišete pravno lice?",
                    "Potvrda brisanja",
                    JOptionPane.YES_NO_OPTION
            );

            if (potvrda != JOptionPane.YES_OPTION) {
                return;
            }

            KlijentskiZahtev kz = new KlijentskiZahtev(
                    Operacije.obrisiPravnoLice,
                    pravnoLiceZaIzmenu
            );
            Komunikacija.getInstance().posaljiZahtev(kz);

            ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();
            boolean uspesno = (boolean) so.getOdgovor();

            if (uspesno) {
                JOptionPane.showMessageDialog(this, "Sistem je obrisao pravno lice.");
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Sistem ne može da obriše pravno lice.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri brisanju pravnog lica: " + ex.getMessage());
        }
    }//GEN-LAST:event_jButtonObrisiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            validirajFormu();
            normalizujSediste();

            if (pravnoLiceZaIzmenu == null) {
                RegistracijaPravnihLica r = kreirajRegistracijuIzForme();

                KlijentskiZahtev kz = new KlijentskiZahtev(
                        Operacije.kreirajRegistracijuPravnogLica,
                        r
                );
                Komunikacija.getInstance().posaljiZahtev(kz);

                ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();
                boolean uspesno = (boolean) so.getOdgovor();

                if (uspesno) {
                    JOptionPane.showMessageDialog(this, "Sistem je zapamtio pravno lice i registraciju.");

                    int odgovor = JOptionPane.showConfirmDialog(
                            this,
                            "Da li želite da sačuvate još jedno pravno lice?",
                            "Potvrda",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (odgovor == JOptionPane.YES_OPTION) {
                        ocistiFormu();
                    } else {
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Sistem ne može da zapamti pravno lice.");
                }
            } else {
                PravnoLice pl = kreirajPravnoLiceIzForme();

                KlijentskiZahtev kz = new KlijentskiZahtev(
                        Operacije.izmeniPravnoLice,
                        pl
                );
                Komunikacija.getInstance().posaljiZahtev(kz);

                ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();
                boolean uspesno = (boolean) so.getOdgovor();

                if (uspesno) {
                    JOptionPane.showMessageDialog(this, "Sistem je izmenio pravno lice.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Sistem ne može da izmeni pravno lice.");
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnObrisiAdresuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiAdresuActionPerformed
        int red = jTable1.getSelectedRow();

        if (red == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite adresu za brisanje.");
            return;
        }

        Adresa adresaZaBrisanje = modelAdrese.getAdrese().get(red);
        sveAdrese.remove(adresaZaBrisanje);
        osveziTabeluAdresa();
    }//GEN-LAST:event_btnObrisiAdresuActionPerformed

    private void btnDodajAdresuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajAdresuActionPerformed
        Mesto mesto = (Mesto) jComboBoxMesto.getSelectedItem();

        if (mesto == null) {
            JOptionPane.showMessageDialog(this, "Izaberite mesto.");
            return;
        }

        JTextField txtUlica = new JTextField();
        JTextField txtBroj = new JTextField();
        JCheckBox cbSediste = new JCheckBox("Sedište");

        Object[] polja = {
            "Ulica:", txtUlica,
            "Broj:", txtBroj,
            cbSediste
        };

        int opcija = JOptionPane.showConfirmDialog(
                this,
                polja,
                "Dodaj adresu",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcija != JOptionPane.OK_OPTION) {
            return;
        }

        String ulica = txtUlica.getText().trim();
        String broj = txtBroj.getText().trim();

        if (ulica.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ulica je obavezna.");
            return;
        }

        if (broj.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Broj je obavezan.");
            return;
        }

        Adresa a = new Adresa();
        a.setUlica(ulica);
        a.setBroj(broj);
        a.setMesto(mesto);
        a.setSediste(cbSediste.isSelected());

        if (sveAdrese.isEmpty()) {
            a.setSediste(true);
        }

        if (a.isSediste()) {
            for (Adresa adr : sveAdrese) {
                adr.setSediste(false);
            }
        }
        sveAdrese.add(a);
        osveziTabeluAdresa();
    }//GEN-LAST:event_btnDodajAdresuActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        osveziTabeluAdresa();

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jComboBoxMestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMestoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMestoActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodajAdresu;
    private javax.swing.JButton btnObrisiAdresu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonObrisi;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<Mesto> jComboBoxMesto;
    private javax.swing.JComboBox<String> jComboBoxTipPrivrednogDrustva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAdresa;
    private javax.swing.JLabel jLabelMaticni;
    private javax.swing.JLabel jLabelMesto;
    private javax.swing.JLabel jLabelNazivSubjekta;
    private javax.swing.JLabel jLabelOdgovornoLice;
    private javax.swing.JLabel jLabelPib;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldBrojAkcija;
    private javax.swing.JTextField jTextFieldBrojClanova;
    private javax.swing.JTextField jTextFieldBrojKomanditora;
    private javax.swing.JTextField jTextFieldBrojKomplementara;
    private javax.swing.JTextField jTextFieldBrojOrtaka;
    private javax.swing.JTextField jTextFieldMaticniBroj;
    private javax.swing.JTextField jTextFieldNazivSubjekta;
    private javax.swing.JTextField jTextFieldOdgovornoLice;
    private javax.swing.JTextField jTextFieldOsnovniKapital;
    private javax.swing.JTextField jTextFieldPib;
    // End of variables declaration//GEN-END:variables

    private void srediTabeluAdresa() {
        modelAdrese = new ModelTabeleAdrese(new ArrayList<>());
        jTable1.setModel(modelAdrese);
    }

    private void osveziTabeluAdresa() {
        if (jCheckBox1.isSelected()) {
            modelAdrese.setAdrese(new ArrayList<>(sveAdrese));
            return;
        }

        Mesto izabranoMesto = (Mesto) jComboBoxMesto.getSelectedItem();

        List<Adresa> filtrirane = new ArrayList<>();

        if (izabranoMesto != null) {
            for (Adresa a : sveAdrese) {
                if (a.getMesto() != null
                        && a.getMesto().getIdMesto() == izabranoMesto.getIdMesto()) {
                    filtrirane.add(a);
                }
            }
        }

        modelAdrese.setAdrese(filtrirane);
    }

    private void normalizujSediste() {
        Adresa poslednjeSediste = null;

        for (Adresa a : sveAdrese) {
            if (a.isSediste()) {
                poslednjeSediste = a;
            }
        }

        if (poslednjeSediste == null) {
            return;
        }

        for (Adresa a : sveAdrese) {
            a.setSediste(a == poslednjeSediste);
        }

        osveziTabeluAdresa();
    }

}
