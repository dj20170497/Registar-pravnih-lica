/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import domeni.Mesto;
import domeni.PravnoLice;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import modeli.ModelTabelePravnaLica;
import operacije.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

public class PrikazPravnihLicaForma extends javax.swing.JFrame {

    public PrikazPravnihLicaForma() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ucitajPravnaLica("");
        pripremiPretragu();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonIzmeni = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextFieldPretraga = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonIzmeni.setText("DETALJNIJE");
        jButtonIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniActionPerformed(evt);
            }
        });

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

        jLabel1.setText("Unesite podatke za pretragu:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(372, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jButtonIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jTextFieldPretraga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(31, 31, 31)
                .addComponent(jButtonIzmeni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pripremiPretragu() {
        jTextFieldPretraga.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                ucitajPravnaLica(jTextFieldPretraga.getText().trim());
            }
        });
    }

    private void ucitajPravnaLica(String kriterijum) {
        try {
            PravnoLice pl = new PravnoLice();
            pl.setKriterijum(kriterijum);

            KlijentskiZahtev kz = new KlijentskiZahtev(Operacije.vratiPravnaLica, pl);
            Komunikacija.getInstance().posaljiZahtev(kz);

            ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();
            List<PravnoLice> pravnaLica = (List<PravnoLice>) so.getOdgovor();

            ModelTabelePravnaLica model = new ModelTabelePravnaLica(pravnaLica);
            jTable1.setModel(model);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju pravnih lica: " + ex.getMessage());
        }
    }

    
    private void jButtonIzmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniActionPerformed
try {
    int red = jTable1.getSelectedRow();

    if (red == -1) {
        JOptionPane.showMessageDialog(this, "Izaberite pravno lice iz tabele.");
        return;
    }

    ModelTabelePravnaLica model = (ModelTabelePravnaLica) jTable1.getModel();
    PravnoLice izTabele = model.vratiPravnoLice(red);

    // 1. povuci kompletno pravno lice sa servera
    KlijentskiZahtev kzDetalji = new KlijentskiZahtev(Operacije.vratiJednoPravnoLice, izTabele);
    Komunikacija.getInstance().posaljiZahtev(kzDetalji);

    ServerskiOdgovor soDetalji = Komunikacija.getInstance().primiOdgovor();

    if (soDetalji == null || soDetalji.getOdgovor() == null) {
        JOptionPane.showMessageDialog(this, "Ne mogu da učitam detalje pravnog lica.");
        return;
    }

    PravnoLice pl = (PravnoLice) soDetalji.getOdgovor();

    // 2. povuci mesta
    KlijentskiZahtev kzMesta = new KlijentskiZahtev(Operacije.vratiMesta, null);
    Komunikacija.getInstance().posaljiZahtev(kzMesta);

    ServerskiOdgovor soMesta = Komunikacija.getInstance().primiOdgovor();

    if (soMesta == null || soMesta.getOdgovor() == null) {
        JOptionPane.showMessageDialog(this, "Ne mogu da povučem mesta iz baze.");
        return;
    }

    List<Mesto> mesta = (List<Mesto>) soMesta.getOdgovor();

    SacuvajPravnoLiceForma forma = new SacuvajPravnoLiceForma(mesta, null, pl);
    forma.setVisible(true);

    forma.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosed(java.awt.event.WindowEvent e) {
            ucitajPravnaLica(jTextFieldPretraga.getText().trim());
        }
    });

} catch (Exception ex) {
    JOptionPane.showMessageDialog(this, "Greška pri otvaranju detalja: " + ex.getMessage());
}
    }//GEN-LAST:event_jButtonIzmeniActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrikazPravnihLicaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrikazPravnihLicaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrikazPravnihLicaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrikazPravnihLicaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrikazPravnihLicaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldPretraga;
    // End of variables declaration//GEN-END:variables
}
