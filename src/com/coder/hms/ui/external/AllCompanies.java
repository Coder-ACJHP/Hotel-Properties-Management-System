/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coder.hms.ui.external;

import com.coder.hms.ui.extras.CustomTableHeaderRenderer;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author dell-pc
 */
public class AllCompanies extends javax.swing.JFrame {

    /**
     * Creates new form AllCompanies
     */
    private TableRowSorter<DefaultTableModel> tableRowSorter;
    private final CustomTableHeaderRenderer THR = new CustomTableHeaderRenderer();
    final String [] columnNames = {"TITLE", "KIND", "EMAIL", "ADDRESS", "PHONE"};
    private final DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    private final String LOGOPATH = "/com/coder/hms/icons/main_logo(128X12).png";
    public AllCompanies() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonHolder = new javax.swing.JPanel();
        newBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(500, 0), new java.awt.Dimension(32767, 0));
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        tableScrooler = new javax.swing.JScrollPane();
        companiesTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Coder HPMSA - [All Companies]");
        setAlwaysOnTop(true);
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(LOGOPATH)));
        setMinimumSize(new java.awt.Dimension(1100, 750));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1312, 800));

        buttonHolder.setBackground(new java.awt.Color(6, 109, 149));
        buttonHolder.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        buttonHolder.setAutoscrolls(true);
        buttonHolder.setPreferredSize(new java.awt.Dimension(1031, 60));
        buttonHolder.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        newBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        newBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/room_posting.png"))); // NOI18N
        newBtn.setText("New");
        newBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        newBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(newBtn);

        editBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/main_edit.png"))); // NOI18N
        editBtn.setText("Edit");
        editBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(editBtn);

        deleteBtn.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 14)); // NOI18N
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/coder/hms/icons/room_checkout.png"))); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.setPreferredSize(new java.awt.Dimension(110, 40));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        buttonHolder.add(deleteBtn);
        buttonHolder.add(filler1);

        jLabel1.setFont(new java.awt.Font("Microsoft Sans Serif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Search : ");
        buttonHolder.add(jLabel1);

        searchField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchField.setPreferredSize(new java.awt.Dimension(120, 25));
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });
        buttonHolder.add(searchField);

        getContentPane().add(buttonHolder, java.awt.BorderLayout.PAGE_START);

        companiesTable.setAutoCreateRowSorter(true);
        companiesTable.setFont(new java.awt.Font("Microsoft Sans Serif", 0, 12)); // NOI18N
        companiesTable.setModel(model);
        companiesTable.setDragEnabled(true);
        tableScrooler.setViewportView(companiesTable);

        getContentPane().add(tableScrooler, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newBtnActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped
        String modifiedQuery = "(?i)" + searchField.getText();
    	tableRowSorter.setRowFilter(RowFilter.regexFilter(modifiedQuery));
    }//GEN-LAST:event_searchFieldKeyTyped

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
            java.util.logging.Logger.getLogger(AllCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllCompanies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllCompanies().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonHolder;
    private javax.swing.JTable companiesTable;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton newBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JScrollPane tableScrooler;
    // End of variables declaration//GEN-END:variables
}
