/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapleBlockedOpcodeViewer.java
 *
 * Created on May 9, 2009, 10:27:38 PM
 */
package org.snow.maplesnowsniffer;

import java.io.FileOutputStream;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Raz
 */
public class MapleBlockedOpcodeViewer extends javax.swing.JFrame implements TableModelListener {

    private MaplePcapture capture;

    private static boolean isedit = false;
    /**
     * Creates new form MapleBlockedOpcodeViewer
     */
    public MapleBlockedOpcodeViewer(MaplePcapture capture) {
        // <editor-fold defaultstate="collapsed" desc="LookAndFeelInfo">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MapleBlockedOpcodeViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapleBlockedOpcodeViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapleBlockedOpcodeViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapleBlockedOpcodeViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        initComponents();
        opcodeTable.getModel().addTableModelListener(this);
        opcodeTable1.getModel().addTableModelListener(this);
        this.capture = capture;
        updateTable();
        if(!isedit) {
        saveButton.setEnabled(false);
        }
    }

    public void updateTable() {
        DefaultTableModel dtm = (DefaultTableModel) opcodeTable.getModel();
        DefaultTableModel dtm1 = (DefaultTableModel) opcodeTable1.getModel();
        if (dtm.getRowCount() > 0 || dtm1.getRowCount() > 0) {
            dtm.getDataVector().removeAllElements();
            dtm1.getDataVector().removeAllElements();
        }

        for (Entry<String, Boolean> rowData : capture.getBlockedOpcodes().entrySet()) {
            if (rowData.getKey().startsWith("S_")) {
                dtm.addRow(new Object[]{rowData.getKey(), rowData.getValue()});
            } else if (rowData.getKey().startsWith("R_")){
                dtm1.addRow(new Object[]{rowData.getKey(), rowData.getValue()});
            }
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        if (row > -1 && e.getType() == TableModelEvent.UPDATE) {
            TableModel model = (TableModel) e.getSource();
            String key = (String) model.getValueAt(row, 0);
            boolean value = (Boolean) model.getValueAt(row, 1);
            capture.getBlockedOpcodes().put(key, value);
            updateTable();
            isedit = true;
            saveButton.setEnabled(true);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        opcodeTable = new javax.swing.JTable();
        closeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        opcodeTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Snow's Packet Header Viewer");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        opcodeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Send Opcode", Lang.get("blockTable.blocked")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        opcodeTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        opcodeTable.setShowHorizontalLines(false);
        opcodeTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(opcodeTable);
        opcodeTable.getColumnModel().getColumn(0).setResizable(false);
        opcodeTable.getColumnModel().getColumn(1).setResizable(false);

        closeButton.setText(Lang.get("blockTable.button.close"));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        deleteButton.setText(Lang.get("blockTable.button.del"));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        saveButton.setText(Lang.get("blockTable.button.save"));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jButton2.setText(Lang.get("blockTable.button.add"));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        opcodeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Recv Opcode", Lang.get("blockTable.blocked")
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        opcodeTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        opcodeTable1.setShowHorizontalLines(false);
        opcodeTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(opcodeTable1);
        opcodeTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        opcodeTable1.getColumnModel().getColumn(0).setResizable(false);
        opcodeTable1.getColumnModel().getColumn(1).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(closeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(deleteButton)
                    .addComponent(saveButton)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
            //CLOSE
            setVisible(false);
            MaplePcaptureGUI.viewBlockedOpcodeButton.setEnabled(true);
}//GEN-LAST:event_closeButtonActionPerformed

	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
            //DELETE BLOCKED OPCODE
            int rowIndex = opcodeTable.getSelectedRow();
            int rowIndex1 = opcodeTable1.getSelectedRow();
            DefaultTableModel dtm = (DefaultTableModel) opcodeTable.getModel();
            DefaultTableModel dtm1 = (DefaultTableModel) opcodeTable1.getModel();
            if (rowIndex >= 0) {
                String key = (String) dtm.getValueAt(rowIndex, 0);
                dtm.removeRow(rowIndex);
                capture.getBlockedOpcodes().remove(key);
                updateTable();
                isedit = true;
                saveButton.setEnabled(true);
            } else if (rowIndex1 >= 0) {
                String key = (String) dtm1.getValueAt(rowIndex1, 0);
                dtm1.removeRow(rowIndex1);
                capture.getBlockedOpcodes().remove(key);
                updateTable();
                isedit = true;
                saveButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, Lang.get("blockTable.notice.del"), "Delete", JOptionPane.INFORMATION_MESSAGE);
            }
}//GEN-LAST:event_deleteButtonActionPerformed

	private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
            //SAVE OPCODE CHANGES
            Properties prop = capture.getBlockOp();
            for (int rowIndex = 0; rowIndex < opcodeTable.getRowCount(); rowIndex++) {
                String enable = Integer.toString(((Boolean) opcodeTable.getValueAt(rowIndex, 1)) ? 1 : 0);
                prop.put(opcodeTable.getValueAt(rowIndex, 0), enable);
            }
            for (int rowIndex = 0; rowIndex < opcodeTable1.getRowCount(); rowIndex++) {
                String enable = Integer.toString(((Boolean) opcodeTable1.getValueAt(rowIndex, 1)) ? 1 : 0);
                prop.put(opcodeTable1.getValueAt(rowIndex, 0), enable);
            }
            try {
                prop.store(new FileOutputStream("blockOpcode.ini"), null);
                JOptionPane.showMessageDialog(null, Lang.get("blockTable.notice.save.ok"), "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                saveButton.setEnabled(false);
                isedit = false;
            } catch (Exception e) {
                System.out.println("Error storing properties file");
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, Lang.get("blockTable.notice.save.error"), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
}//GEN-LAST:event_saveButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(null, Lang.get("notDone"), "INFO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        MaplePcaptureGUI.viewBlockedOpcodeButton.setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable opcodeTable;
    private javax.swing.JTable opcodeTable1;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}
