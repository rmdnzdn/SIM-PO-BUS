/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zidun
 */
public class admin_bus extends javax.swing.JFrame {
    public Statement st;
    public ResultSet rs;
    public Connection cn = config.koneksi.getKoneksi();
    public DefaultTableModel tabmodel,tab_kelas;
    fungsi aksi = new fungsi();
    
    public void judul(){
        Object[] judul ={"No.","Kode BUS","Nama BUS","No.Polisi Bus","Kode Kelas","Kelas BUS","Status"};
        tabmodel = new DefaultTableModel(null,judul);
        tbl_bus.setModel(tabmodel);
    }
    
     public void judul_kelas(){
        Object[] judul ={"No.","Kode Kelas","Nama Kelas","Harga Kelas"};
        tab_kelas = new DefaultTableModel(null,judul);
        tbl_kelas.setModel(tab_kelas);
    }
     
    public void tampil_kelas(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_kelas.getDataVector().removeAllElements();
            tab_kelas.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_kelas");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getInt("harga_kelas")
                };
                tab_kelas.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tampildata(){
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_bus");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_bus"),
                    rs.getString("nama"),
                    rs.getString("no_polisi"),
                    rs.getString("kd_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getString("status_bus")
                };
                tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void reset(){
        tnama.setText("");
        tkode.setText("");
        tnopol.setText("");
        tkode_kelas.setText("");
        tnama_kelas.setText("");
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bsimpan.setVisible(true);
        breset.setVisible(true);
        tnama.requestFocus();
        autokode();
    }
     
     public void autokode(){
        try {
            String tampilkode = "";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("YMM");
            String tgl = format.format(date);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT MAX(RIGHT(kd_bus,3)) FROM tbl_bus WHERE kd_bus LIKE '%"+tgl+"%' order by kd_bus DESC limit 0,1");
            if(rs.next()){
                int no = rs.getInt(1)+1;
                if (no < 10) {
                    tampilkode = "BUS"+tgl+"00"+String.valueOf(no);
                } else if(no > 9 && no <= 99) {
                     tampilkode = "BUS"+tgl+"0"+String.valueOf(no);
                }else{
                    tampilkode = "BUS"+tgl+String.valueOf(no);
                }
                tkode.setText(tampilkode);
            }else{
                tkode.setText("BUS"+tgl+"001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public void aksi(String sql, String pesan){
//        try {
//            st = cn.createStatement();
//            st.executeUpdate(sql);
//            JOptionPane.showMessageDialog(null, pesan);
//            tampildata();
//            reset();
//            autokode();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    public void refresh(){
        tampildata();
        reset();
        autokode();
    }
    /**
     * Creates new form admin_bus
     */
    public admin_bus() {
        initComponents();
        judul();
        tampildata();
        judul_kelas();
        tampil_kelas();
        autokode();
        reset();
        tnama.requestFocus();
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bsimpan.setVisible(true);
        breset.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup_kelas = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_kelas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        tkode = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tnama_kelas = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        tnopol = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        tcari = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_bus = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        bhapus = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        breset = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        bsimpan = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        bedit = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        btampil1 = new javax.swing.JPanel();
        btampil = new javax.swing.JLabel();
        tkode_kelas = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        hide = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        popup_kelas.setUndecorated(true);
        popup_kelas.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(74, 131, 122));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_kelas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_kelasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_kelas);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(232, 146, 61));
        jLabel2.setText("Daftar Kelas BUS");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, -1));

        popup_kelas.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 430));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(85, 153, 142));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 710, 610, 20));

        jLabel6.setFont(new java.awt.Font("Harlow Solid Italic", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Rmdnzdn");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 690, -1, 40));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 710, 610, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(232, 146, 61));
        jLabel5.setText("Kode BUS");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        tkode.setEditable(false);
        tkode.setBackground(new java.awt.Color(85, 153, 142));
        tkode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode.setForeground(new java.awt.Color(255, 255, 255));
        tkode.setBorder(null);
        jPanel1.add(tkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 320, 30));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 320, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(232, 146, 61));
        jLabel4.setText("Nama BUS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        tnama.setBackground(new java.awt.Color(85, 153, 142));
        tnama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnama.setForeground(new java.awt.Color(255, 255, 255));
        tnama.setBorder(null);
        jPanel1.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 320, 30));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 320, 10));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(232, 146, 61));
        jLabel15.setText("Kelas BUS");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, -1, -1));

        tnama_kelas.setEditable(false);
        tnama_kelas.setBackground(new java.awt.Color(85, 153, 142));
        tnama_kelas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnama_kelas.setForeground(new java.awt.Color(255, 255, 255));
        tnama_kelas.setBorder(null);
        tnama_kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnama_kelasKeyTyped(evt);
            }
        });
        jPanel1.add(tnama_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 320, 30));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 320, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(232, 146, 61));
        jLabel11.setText("No. Polisi BUS");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, -1, -1));

        tnopol.setBackground(new java.awt.Color(85, 153, 142));
        tnopol.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnopol.setForeground(new java.awt.Color(255, 255, 255));
        tnopol.setBorder(null);
        tnopol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnopolKeyTyped(evt);
            }
        });
        jPanel1.add(tnopol, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 320, 30));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 320, 10));

        tcari.setBackground(new java.awt.Color(85, 153, 142));
        tcari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tcari.setForeground(new java.awt.Color(255, 255, 255));
        tcari.setBorder(null);
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 560, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(232, 146, 61));
        jLabel12.setText("Pencarian");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 80, -1, -1));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 560, 20));

        tbl_bus.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_bus.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_busMouseMoved(evt);
            }
        });
        tbl_bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_busMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_bus);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, 690, 470));

        jPanel6.setBackground(new java.awt.Color(74, 131, 122));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("REFRESH");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 100, 120, 40));

        bhapus.setBackground(new java.awt.Color(74, 131, 122));
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.setEnabled(false);
        bhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bhapusMouseClicked(evt);
            }
        });
        bhapus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("HAPUS");
        bhapus.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 50));

        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 570, 320, -1));

        breset.setBackground(new java.awt.Color(74, 131, 122));
        breset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        breset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bresetMouseClicked(evt);
            }
        });
        breset.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("RESET");
        breset.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 50));

        jPanel1.add(breset, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 510, 320, -1));

        bsimpan.setBackground(new java.awt.Color(74, 131, 122));
        bsimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bsimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bsimpanMouseClicked(evt);
            }
        });
        bsimpan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("SIMPAN");
        bsimpan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 50));

        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 320, 50));

        bedit.setBackground(new java.awt.Color(74, 131, 122));
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.setEnabled(false);
        bedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beditMouseClicked(evt);
            }
        });
        bedit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("UBAH");
        bedit.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 50));

        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 320, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(232, 146, 61));
        jLabel14.setText("Kode Kelas");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 230, 10));

        btampil1.setBackground(new java.awt.Color(74, 131, 122));
        btampil1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil1MouseClicked(evt);
            }
        });
        btampil1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btampil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btampil.setForeground(new java.awt.Color(255, 255, 255));
        btampil.setText("TAMPIL");
        btampil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampilMouseClicked(evt);
            }
        });
        btampil1.add(btampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(btampil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 320, 90, 40));

        tkode_kelas.setEditable(false);
        tkode_kelas.setBackground(new java.awt.Color(85, 153, 142));
        tkode_kelas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_kelas.setForeground(new java.awt.Color(255, 255, 255));
        tkode_kelas.setBorder(null);
        tkode_kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkode_kelasMouseClicked(evt);
            }
        });
        tkode_kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkode_kelasActionPerformed(evt);
            }
        });
        jPanel1.add(tkode_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 230, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 1390, 730));

        jPanel2.setBackground(new java.awt.Color(74, 131, 122));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FORM INPUT BUS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 40));

        hide.setBackground(new java.awt.Color(74, 131, 122));
        hide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideMouseClicked(evt);
            }
        });
        hide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kembali");
        hide.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 40));

        getContentPane().add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 0, 130, 40));

        close.setBackground(new java.awt.Color(74, 131, 122));
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        close.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("X");
        close.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        getContentPane().add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 50, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tnopolKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnopolKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tnopolKeyTyped

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_bus WHERE nama LIKE '%"+tcari.getText()+"%' OR no_polisi LIKE '%"+tcari.getText()+"%' OR kd_bus LIKE '%"+tcari.getText()+"%'OR nama_kelas LIKE '%"+tcari.getText()+"%' OR status_bus = '%"+tcari.getText()+"%'");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_bus"),
                    rs.getString("nama"),
                    rs.getString("no_polisi"),
                    rs.getString("kd_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getString("status_bus")
                };
                tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcariKeyReleased

    private void tbl_busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_busMouseClicked
        // TODO add your handling code here:
        tkode.setText(tabmodel.getValueAt(tbl_bus.getSelectedRow(), 1)+"");
        tnama.setText(tabmodel.getValueAt(tbl_bus.getSelectedRow(), 2)+"");
        tnopol.setText(tabmodel.getValueAt(tbl_bus.getSelectedRow(), 3)+"");
        tkode_kelas.setText(tabmodel.getValueAt(tbl_bus.getSelectedRow(), 4)+"");
        tnama_kelas.setText(tabmodel.getValueAt(tbl_bus.getSelectedRow(), 5)+"");
        tnama.requestFocus();
        bhapus.setVisible(true);
        bedit.setVisible(true);
        bsimpan.setVisible(false);
        breset.setVisible(true);
    }//GEN-LAST:event_tbl_busMouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        tcariKeyReleased(null);
        tcari.setText("");
        tampildata();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void bhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bhapusMouseClicked
        // TODO add your handling code here:
        int jawab = 0;
        if((jawab = JOptionPane.showConfirmDialog(null, "Anda yakin kan menghapus data ini ?","Konfirmasi", JOptionPane.YES_NO_OPTION))==0){
            aksi.hapus("tbl_bus", "kd_bus = '"+tkode.getText()+"'");
            refresh();
//            String sql = "DELETE FROM tbl_bus WHERE kd_bus = '"+tkode.getText()+"'";
//            aksi(sql,"Data Berhasil Dihapus");
        }
    }//GEN-LAST:event_bhapusMouseClicked

    private void bresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bresetMouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bresetMouseClicked

    private void bsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bsimpanMouseClicked
        // TODO add your handling code here:
        if (tkode.getText().isEmpty() || tnama.getText().isEmpty() || tnopol.getText().isEmpty() || tkode_kelas.getText().isEmpty() || tnama_kelas.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data Terleih Dahulu !");
        } else {
            aksi.simpan("tbl_bus", "'"+tkode.getText()+"','"+tnama.getText()+"','"+tnopol.getText()+"','"+tkode_kelas.getText()+"','tersedia'");
            refresh();
//            String sql = "INSERT INTO tbl_bus VALUES('"+tkode.getText()+"','"+tnama.getText()+"','"+tnopol.getText()+"','"+tkode_kelas.getText()+"','tersedia')";
//            aksi(sql,"Data Berhasil Disimpan");
        }
    }//GEN-LAST:event_bsimpanMouseClicked

    private void beditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beditMouseClicked
        // TODO add your handling code here:
        if(tkode.getText().isEmpty() || tnama.getText().isEmpty() || tnopol.getText().isEmpty() || tnopol.getText().equals(0)){
            JOptionPane.showMessageDialog(null, "Lengkapi Data Terlebih Dahulu");
        }else{
            aksi.update("tbl_bus", " nama = '"+tnama.getText()+"',  no_polisi ='"+tnopol.getText()+"', kd_kelas = '"+tkode_kelas.getText()+"'","kd_bus = '"+tkode.getText()+"'");
            refresh();
//            String sql = "UPDATE tbl_bus SET nama = '"+tnama.getText()+"',  no_polisi ='"+tnopol.getText()+"', kd_kelas = '"+tkode_kelas.getText()+"' WHERE kd_bus = '"+tkode.getText()+"'";
//            aksi(sql, "Data Berhasil Diubah");
        }
    }//GEN-LAST:event_beditMouseClicked

    private void hideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_hideMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void btampilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampilMouseClicked
        // TODO add your handling code here:
        popup_kelas.setVisible(true);
        popup_kelas.setSize(700,420);
    }//GEN-LAST:event_btampilMouseClicked

    private void btampil1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil1MouseClicked
        // TODO add your handling code here:
        popup_kelas.setVisible(true);
        popup_kelas.setSize(700,420);
    }//GEN-LAST:event_btampil1MouseClicked

    private void tkode_kelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkode_kelasMouseClicked
        // TODO add your handling code here:
      popup_kelas.setVisible(true);
        popup_kelas.setSize(700,420);
    }//GEN-LAST:event_tkode_kelasMouseClicked

    private void tkode_kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkode_kelasActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tkode_kelasActionPerformed

    private void tnama_kelasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnama_kelasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tnama_kelasKeyTyped

    private void tbl_kelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_kelasMouseClicked
        // TODO add your handling code here:
        tkode_kelas.setText(tab_kelas.getValueAt(tbl_kelas.getSelectedRow(), 1)+"");
        tnama_kelas.setText(tab_kelas.getValueAt(tbl_kelas.getSelectedRow(), 2)+"");
        popup_kelas.dispose();
    }//GEN-LAST:event_tbl_kelasMouseClicked

    private void tbl_busMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_busMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_busMouseMoved

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
            java.util.logging.Logger.getLogger(admin_bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_bus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_bus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bedit;
    private javax.swing.JPanel bhapus;
    private javax.swing.JPanel breset;
    private javax.swing.JPanel bsimpan;
    private javax.swing.JLabel btampil;
    private javax.swing.JPanel btampil1;
    private javax.swing.JPanel close;
    private javax.swing.JPanel hide;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JDialog popup_kelas;
    private javax.swing.JTable tbl_bus;
    private javax.swing.JTable tbl_kelas;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tkode;
    private javax.swing.JTextField tkode_kelas;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField tnama_kelas;
    private javax.swing.JTextField tnopol;
    // End of variables declaration//GEN-END:variables
}
