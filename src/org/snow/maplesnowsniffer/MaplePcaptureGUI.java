package org.snow.maplesnowsniffer;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.snow.odinms.ByteArrayByteStream;
import org.snow.odinms.GenericSeekableLittleEndianAccessor;
import org.snow.odinms.HexTool;
import org.snow.odinms.SaveFileFilter;
import org.snow.odinms.SeekableLittleEndianAccessor;
import org.snow.odinms.StringUtil;

/**
 *
 * @author Raz
 */
public class MaplePcaptureGUI extends javax.swing.JFrame {

    private static final long serialVersionUID = -722111285770372532L;
    private boolean autoScroll = false;
    private int packetTotal = 0;
    private MaplePacketStructureViewer structureViewer;
    private MaplePcapture capture;
    private Lang msg;

    /**
     * Creates new form MaplePcaptureGUI
     */
    public MaplePcaptureGUI() {
        // <editor-fold defaultstate="collapsed" desc="LookAndFeelInfo">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MaplePcaptureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaplePcaptureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaplePcaptureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaplePcaptureGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        initComponents();
        initSelectionListener();
        removePacketTree();
    }

    public void initSelectionListener() {
        SelectionListener listener = new SelectionListener(packetTable);
        packetTable.getSelectionModel().addListSelectionListener(listener);
        packetTable.getColumnModel().getSelectionModel().addListSelectionListener(listener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        packetTable = new javax.swing.JTable();
        autoScrollButton = new javax.swing.JToggleButton();
        viewPacketSturctureButton = new javax.swing.JButton();
        viewPacketHeaderButton = new javax.swing.JButton();
        copyPacketDataButton = new javax.swing.JButton();
        packetCountLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        packetDataTable = new javax.swing.JTable();
        viewPacketTreeButton = new javax.swing.JButton();
        RIVLabel = new javax.swing.JLabel();
        SIVLabel = new javax.swing.JLabel();
        viewBlockedOpcodeButton = new javax.swing.JButton();
        blockPacketButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        packetTree = new javax.swing.JTree();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        closeMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        resetPacketCountMenuItem = new javax.swing.JMenuItem();
        viewSettingsMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        aboutMenuItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        lng_EN = new javax.swing.JMenuItem();
        lng_VI = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Snow's Packet Sniffer [Upgrade Version]");
        setResizable(false);

        packetTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.", "Time", "Direction", "Opcode", "Opcode Name", "Length"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        packetTable.setToolTipText("Table view of recorded packets");
        packetTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        packetTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(packetTable);
        packetTable.getColumnModel().getColumn(0).setMinWidth(40);
        packetTable.getColumnModel().getColumn(0).setMaxWidth(40);
        packetTable.getColumnModel().getColumn(1).setMinWidth(110);
        packetTable.getColumnModel().getColumn(1).setMaxWidth(110);
        packetTable.getColumnModel().getColumn(2).setMinWidth(120);
        packetTable.getColumnModel().getColumn(2).setMaxWidth(120);
        packetTable.getColumnModel().getColumn(3).setMinWidth(80);
        packetTable.getColumnModel().getColumn(3).setMaxWidth(80);
        packetTable.getColumnModel().getColumn(5).setMinWidth(50);
        packetTable.getColumnModel().getColumn(5).setMaxWidth(50);

        autoScrollButton.setText(Lang.get("main.button.autoScroll"));
        autoScrollButton.setToolTipText("Scroll down to the newest packet everytime a new packet is received");
        autoScrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoScrollButtonActionPerformed(evt);
            }
        });

        viewPacketSturctureButton.setText("New Function (Comming Soon)");
        viewPacketSturctureButton.setToolTipText("New Function (Comming Soon)");
        viewPacketSturctureButton.setEnabled(false);
        viewPacketSturctureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPacketSturctureButtonActionPerformed(evt);
            }
        });

        viewPacketHeaderButton.setText(Lang.get("main.button.showHeader"));
        viewPacketHeaderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewPacketHeaderButtonActionPerformed(evt);
            }
        });

        copyPacketDataButton.setText(Lang.get("main.button.copyPacket"));//"Copy Dữ Liệu Packet");
    copyPacketDataButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            copyPacketDataButtonActionPerformed(evt);
        }
    });

    packetCountLabel.setText(Lang.get("main.bar.packetCount"));
    packetCountLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    statusLabel.setText(Lang.get("main.bar.status"));
    statusLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    packetDataTable.setFont(new java.awt.Font("Lucida Console", 0, 13)); // NOI18N
    packetDataTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "Offset", "Hex", "Ascii"
        }
    ) {
        boolean[] canEdit = new boolean [] {
            false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    packetDataTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    packetDataTable.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(packetDataTable);
    packetDataTable.getColumnModel().getColumn(0).setMinWidth(80);
    packetDataTable.getColumnModel().getColumn(0).setMaxWidth(80);
    packetDataTable.getColumnModel().getColumn(2).setMinWidth(200);
    packetDataTable.getColumnModel().getColumn(2).setMaxWidth(200);

    viewPacketTreeButton.setText("New Function (Comming Soon)");
    viewPacketTreeButton.setToolTipText("New Function (Comming Soon)");
    viewPacketTreeButton.setEnabled(false);
    viewPacketTreeButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewPacketTreeButtonActionPerformed(evt);
        }
    });

    RIVLabel.setText("RIV: 00 00 00 00");
    RIVLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    SIVLabel.setText("SIV: 00 00 00 00");
    SIVLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    viewBlockedOpcodeButton.setText(Lang.get("main.button.showBlock"));
    viewBlockedOpcodeButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewBlockedOpcodeButtonActionPerformed(evt);
        }
    });

    blockPacketButton.setText(Lang.get("main.button.lockPacket"));
    blockPacketButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            blockPacketButtonActionPerformed(evt);
        }
    });

    jTextArea1.setEditable(false);
    jTextArea1.setBackground(new java.awt.Color(240, 240, 240));
    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Packet Info"));
    jScrollPane5.setViewportView(jTextArea1);

    packetTree.setBackground(new java.awt.Color(240, 240, 240));
    packetTree.setBorder(javax.swing.BorderFactory.createTitledBorder("Packet Tree"));
    jScrollPane3.setViewportView(packetTree);

    jMenu1.setText("File");

    saveMenuItem.setText(Lang.get("main.menu.save"));
    saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveMenuItemActionPerformed(evt);
        }
    });
    jMenu1.add(saveMenuItem);

    loadMenuItem.setText(Lang.get("main.menu.load"));
    loadMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            loadMenuItemActionPerformed(evt);
        }
    });
    jMenu1.add(loadMenuItem);

    closeMenuItem.setText(Lang.get("main.menu.close"));
    closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            closeMenuItemActionPerformed(evt);
        }
    });
    jMenu1.add(closeMenuItem);

    jMenuBar1.add(jMenu1);

    jMenu3.setText("Tools");

    resetPacketCountMenuItem.setText(Lang.get("main.menu.resetCount"));
    resetPacketCountMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            resetPacketCountMenuItemActionPerformed(evt);
        }
    });
    jMenu3.add(resetPacketCountMenuItem);

    viewSettingsMenuItem.setText(Lang.get("main.menu.viewSetting"));
    viewSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            viewSettingsMenuItemActionPerformed(evt);
        }
    });
    jMenu3.add(viewSettingsMenuItem);

    jMenuBar1.add(jMenu3);

    jMenu2.setText("Help");
    jMenu2.add(jSeparator1);

    aboutMenuItem.setText(Lang.get("main.menu.about"));
    aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            aboutMenuItemActionPerformed(evt);
        }
    });
    jMenu2.add(aboutMenuItem);

    jMenu4.setText(Lang.get("main.menu.lang"));

    lng_EN.setText(Lang.get("main.menu.lang.eng"));
    lng_EN.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            lng_ENActionPerformed(evt);
        }
    });
    jMenu4.add(lng_EN);

    lng_VI.setText(Lang.get("main.menu.lang.viet"));
    lng_VI.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            lng_VIActionPerformed(evt);
        }
    });
    jMenu4.add(lng_VI);

    jMenu2.add(jMenu4);

    jMenuBar1.add(jMenu2);

    setJMenuBar(jMenuBar1);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(RIVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(SIVLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addComponent(packetCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(viewPacketSturctureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(viewPacketTreeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(copyPacketDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(107, 107, 107)
                                    .addComponent(autoScrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(blockPacketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(viewPacketHeaderButton, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addComponent(viewBlockedOpcodeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(8, 8, 8))))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewPacketSturctureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(copyPacketDataButton))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewPacketHeaderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(autoScrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addComponent(jScrollPane5))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewPacketTreeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(blockPacketButton))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(viewBlockedOpcodeButton)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(packetCountLabel)
                .addComponent(statusLabel)
                .addComponent(RIVLabel)
                .addComponent(SIVLabel)))
    );

    viewPacketSturctureButton.getAccessibleContext().setAccessibleName("");

    pack();
    }// </editor-fold>//GEN-END:initComponents
	private void closeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeMenuItemActionPerformed
            //CLOSE
            setVisible(false);
            dispose();
            System.exit(0);
}//GEN-LAST:event_closeMenuItemActionPerformed

	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
            //ABOUT
            JOptionPane.showMessageDialog(null, Lang.get("main.menu.about.text"), "About", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_aboutMenuItemActionPerformed

	private void viewSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewSettingsMenuItemActionPerformed
            //SNIFFER SETTINGS
            String snifferSettings = "Capture Type: " + capture.getCapType().name() + "\r\n";
            snifferSettings += "Packet Filter: " + capture.getPacketFilter() + "\r\n";
            snifferSettings += "Server Output Type: " + capture.getServerOutputType().name() + "\r\n";
            snifferSettings += "Logging: " + (capture.isLogging() ? "ON" : "OFF") + "\r\n";
            snifferSettings += "Default Packet: " + (capture.isBlockDefault() ? "BLOCKED" : "UNBLOCKED") + "\r\n";
            snifferSettings += "Language: " + capture.getLang();
            JOptionPane.showMessageDialog(null, snifferSettings, "Sniffer Settings", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_viewSettingsMenuItemActionPerformed

	private void autoScrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoScrollButtonActionPerformed
            //AUTOSCROLL
            autoScroll = autoScrollButton.getModel().isSelected();
}//GEN-LAST:event_autoScrollButtonActionPerformed

	private void viewPacketSturctureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPacketSturctureButtonActionPerformed
            //VIEW PACKET STRUCTURE
            //Function Disabled
            /*int selectedRow = packetTable.getSelectedRow();
            if (selectedRow == -1) {
                return;
            }
            if (structureViewer != null) {
                structureViewer.setVisible(false);
                structureViewer = null;
            }
            structureViewer = new MaplePacketStructureViewer();
            MaplePacketRecord record = MaplePacketRecord.getById(selectedRow);
            structureViewer.setPacketRecord(record);
            structureViewer.setSlea(new GenericSeekableLittleEndianAccessor(new ByteArrayByteStream(record.getPacketData())));
            structureViewer.setSend(record.isSend());
            structureViewer.setPacketLabelText("Packet: " + record.getHeader());
            structureViewer.setVisible(true);
            structureViewer.anazlyePacket();*/

}//GEN-LAST:event_viewPacketSturctureButtonActionPerformed

	private void viewPacketHeaderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPacketHeaderButtonActionPerformed
            //VIEW MAPLEOPCODE
            new MapleOpcodeView().setVisible(true);
            viewPacketHeaderButton.setEnabled(false);
}//GEN-LAST:event_viewPacketHeaderButtonActionPerformed

	private void copyPacketDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyPacketDataButtonActionPerformed
            //COPY PACKET DATA
            int rowIndex = packetTable.getSelectedRow();
            if (rowIndex > -1) {
                StringSelection stringSelection = new StringSelection(HexTool.toString(MaplePacketRecord.getById(rowIndex).getPacketData()));
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, stringSelection);
                setStatus(Lang.get("main.bar.status.copiedPacket"));
            } else {
                JOptionPane.showMessageDialog(null, Lang.get("main.notice.copyPacket"), "INFO", JOptionPane.INFORMATION_MESSAGE);
            }

}//GEN-LAST:event_copyPacketDataButtonActionPerformed

	private void resetPacketCountMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetPacketCountMenuItemActionPerformed
            //RESET PACKET TOTAL
            this.packetTotal = -1;
            updateAndIncreasePacketTotal();
            setStatus(Lang.get("main.bar.status.resetCount"));

}//GEN-LAST:event_resetPacketCountMenuItemActionPerformed

	private void viewPacketTreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewPacketTreeButtonActionPerformed
            //VIEW PACKET DATA TREE
            //Function Disabled
            /*int rowIndex = packetTable.getSelectedRow();
            if (rowIndex >=0){
            MaplePacketRecord record = MaplePacketRecord.getById(rowIndex);
            if (record != null && record.isDataRecord()) {
                PacketDataTree tree = new PacketDataTree();

                tree.getJTree1().setModel(record.getTreeModel());
                tree.setVisible(true);
                tree.getJTree1().setRootVisible(false);
            }
            } else {
                JOptionPane.showMessageDialog(null, "Chọn một dòng để xem thông tin.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }*/
}//GEN-LAST:event_viewPacketTreeButtonActionPerformed

	private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
            //SAVE PACKETS TO FILE
            File file = null;
            JFileChooser fileChooser = new JFileChooser(file);
            try {
                file = new File(new File("maple.dat").getCanonicalPath());
                fileChooser.setSelectedFile(file);
            } catch (IOException ex) {
            }
            SaveFileFilter fileFilter = new SaveFileFilter("DATA File(*.dat)");
            fileChooser.setFileFilter(fileFilter);
            if (fileChooser.showSaveDialog(MaplePcaptureGUI.this) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                return;
            }
            try {
                getCapture().dumpToFile(file.getPath(),packetTable);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, Lang.get("main.notice.savedPacket"), "INFO", JOptionPane.INFORMATION_MESSAGE);
}//GEN-LAST:event_saveMenuItemActionPerformed

	private void viewBlockedOpcodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBlockedOpcodeButtonActionPerformed
            //VIEW BLOCKED OPCODES
            new MapleBlockedOpcodeViewer(capture).setVisible(true);
            viewBlockedOpcodeButton.setEnabled(false);
}//GEN-LAST:event_viewBlockedOpcodeButtonActionPerformed

	private void blockPacketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockPacketButtonActionPerformed
            //BLOCK PACKET OPCODE
            int rowIndex = packetTable.getSelectedRow();
            if (rowIndex > -1) {
                MaplePacketRecord record = MaplePacketRecord.getById(rowIndex);
                if (record.isDataRecord()) {
                    String blockString = (record.isSend() ? "S" : "R") + "_" + (record.getHeader().equals("UNKNOWN") ? record.getOpcodeHex(false) : record.getHeader());
                    capture.getBlockedOpcodes().put(blockString, Boolean.TRUE);
                    setStatus(Lang.get("main.bar.status.blockedPacket") + " (" + record.getHeader() + ") [" + record.getOpcodeHex(false) + "]");
                }
            } else {
            JOptionPane.showMessageDialog(null, Lang.get("main.notice.blockPacket"), "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
	}//GEN-LAST:event_blockPacketButtonActionPerformed

    private void loadMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMenuItemActionPerformed
        // LOAD PACKET
        File file = null;
            JFileChooser fileChooser = new JFileChooser(file);
            try {
                file = new File(new File("maple.dat").getCanonicalPath());
                fileChooser.setSelectedFile(file);
            } catch (IOException ex) {
            }
            SaveFileFilter fileFilter = new SaveFileFilter("DATA File(*.dat)");
            fileChooser.setFileFilter(fileFilter);
            if (fileChooser.showOpenDialog(MaplePcaptureGUI.this) == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                return;
            }
            try {
                getCapture().loadFromFile(file.getPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_loadMenuItemActionPerformed

    private void lng_ENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lng_ENActionPerformed
        capture.changeLang("EN");
        JOptionPane.showMessageDialog(null, Lang.get("main.menu.lang.selected"), "INFO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lng_ENActionPerformed

    private void lng_VIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lng_VIActionPerformed
        capture.changeLang("VI");
        JOptionPane.showMessageDialog(null, Lang.get("main.menu.lang.selected"), "INFO", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lng_VIActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MaplePcaptureGUI().setVisible(true);
            }
        });
    }

    public void addRow(MaplePacketRecord record) {
        addRow(record.getRowData());
    }

    public void addRow(Object[] rowData) {
        int row = packetTable.getRowCount();
        ((DefaultTableModel) packetTable.getModel()).addRow(rowData);
        if (autoScroll) {
            packetTable.changeSelection(row, 0, false, false);
            packetTable.scrollRectToVisible(packetTable.getCellRect(row, 0, true));
        }
    }
    
    public void removeAllRow() {
            ((DefaultTableModel) packetTable.getModel()).getDataVector().clear();
    }

    public void showPacketData(MaplePacketRecord record) {
        if (record == null) {
            return;
        }
        if (record.isDataRecord()) {
            SeekableLittleEndianAccessor slea = new GenericSeekableLittleEndianAccessor(new ByteArrayByteStream(record.getPacketData()));
            ((DefaultTableModel) packetDataTable.getModel()).getDataVector().clear();

            while (true) {
                int len = Math.min((int) slea.available(), 16);
                Object[] rowData = new Object[3];
                rowData[0] = StringUtil.getLeftPaddedStr(Integer.toHexString((int) slea.getPosition()), '0', 8).toUpperCase();
                byte[] data = slea.read(len);
                if (data.length == 0) {
                    break;
                }
                rowData[1] = HexTool.toString(data);
                rowData[2] = HexTool.toStringFromAscii(data);
                ((DefaultTableModel) packetDataTable.getModel()).addRow(rowData);
                if (len < 16) {
                    break;
                }
            }
        }
    }
    
    public void showPacketInfo(MaplePacketRecord record) {
        structureViewer = new MaplePacketStructureViewer();
            structureViewer.setPacketRecord(record);
            structureViewer.setSlea(new GenericSeekableLittleEndianAccessor(new ByteArrayByteStream(record.getPacketData())));
            structureViewer.setSend(record.isSend());
            structureViewer.setPacketLabelText("Packet: " + record.getHeader());
            structureViewer.anazlyePacket();
    }
    
    public void showPacketTree(MaplePacketRecord record) {
        if (record != null && record.isDataRecord()) {
                packetTree.setModel(record.getTreeModel());
                packetTree.setRootVisible(false);
            }
    }
    
    public void removePacketTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        packetTree.setModel(new DefaultTreeModel(root));
        packetTree.setRootVisible(false);
    }

    public void setPacketCountLabelText(String s) {
        packetCountLabel.setText(s);
    }

    public void updateAndIncreasePacketTotal() {
        packetTotal++;
        setPacketCountLabelText(Lang.get("main.bar.packetCount.set") + packetTotal);
    }

    public void loadPacketTotal(int packetcount) {
        packetTotal = packetcount;
        setPacketCountLabelText(Lang.get("main.bar.packetCount.set") + packetTotal);
    }
    
    public MaplePcapture getCapture() {
        return capture;
    }

    public void setCapture(MaplePcapture capture) {
        this.capture = capture;
    }

    public void setStatusText(String status) {
        statusLabel.setText(status);
    }

    public void setRIVStr(String riv) {
        RIVLabel.setText(riv);
    }

    public void setSIVStr(String siv) {
        SIVLabel.setText(siv);
    }

    public void setStatus(String text) {
        String status = Lang.get("main.bar.status.setStatus") + text;
        statusLabel.setText(status);
        capture.outputWithLogging(status);
    }
    
    public void setPacketStructure(JTextArea structure){
        this.jTextArea1 = structure;
    }

    public class SelectionListener implements ListSelectionListener {

        JTable table;

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener(JTable table) {
            this.table = table;
        }

        public void valueChanged(ListSelectionEvent e) {
            //VIEW PACKET DETAILS
            int rowIndex = packetTable.getSelectedRow();
            if (rowIndex > -1) {
                showPacketData(MaplePacketRecord.getById(rowIndex));
                showPacketInfo(MaplePacketRecord.getById(rowIndex));
                showPacketTree(MaplePacketRecord.getById(rowIndex));
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RIVLabel;
    private javax.swing.JLabel SIVLabel;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JToggleButton autoScrollButton;
    private javax.swing.JButton blockPacketButton;
    private javax.swing.JMenuItem closeMenuItem;
    private javax.swing.JButton copyPacketDataButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem lng_EN;
    private javax.swing.JMenuItem lng_VI;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JLabel packetCountLabel;
    private javax.swing.JTable packetDataTable;
    private javax.swing.JTable packetTable;
    private javax.swing.JTree packetTree;
    private javax.swing.JMenuItem resetPacketCountMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    public javax.swing.JLabel statusLabel;
    public static javax.swing.JButton viewBlockedOpcodeButton;
    public static javax.swing.JButton viewPacketHeaderButton;
    private javax.swing.JButton viewPacketSturctureButton;
    private javax.swing.JButton viewPacketTreeButton;
    private javax.swing.JMenuItem viewSettingsMenuItem;
    // End of variables declaration//GEN-END:variables
}