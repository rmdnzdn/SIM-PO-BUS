/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JamilahLKM
 */
public class admin_perjalanan extends javax.swing.JFrame {
    public Statement st;
    public ResultSet rs;
    public Connection cn = config.koneksi.getKoneksi();
    public DefaultTableModel tabmodel,tab_tiket,tab_sopir1,tab_sopir2;
    public String bus;
   
    
    public void hari(){
        Date date = new Date();
        SimpleDateFormat hari = new SimpleDateFormat("dd MMMMMM YYYY", Locale.getDefault());
        String ini = hari.format(date);
        ltanggal.setText(ini);
    }
    
    public final void waktu(){
        ActionListener taskPerformer = new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "",nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if(nilai_jam <= 9) nol_jam= "0";
                if(nilai_menit <= 9) nol_menit= "0";
                if(nilai_detik <= 9) nol_detik= "0";

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                lwaktu.setText(jam+":"+menit+":"+detik+"");
             }
        };
        new Timer(1000, taskPerformer).start();
    }
    
    public void judul(){
        Object[] judul = {"No.","Kode Perjalanan","Kode Tiket","kode Bus","Sopir Pertama","Sopir Kedua","Jumlah Penumpang","Total Pendapatan","Tanggal Berangkat","Status Perjalan"};
        tabmodel = new DefaultTableModel(null,judul);
        tbl_perjalanan.setModel(tabmodel);
    }
    
    public void tampildata(){
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_perjalanan");
            while (rs.next()) {       
                int a = no++;
                Object[] data={
                    a,
                    rs.getString("kd_perjalanan"),
                    rs.getString("kd_tiket"),
                    rs.getString("kd_bus"),
                    rs.getString("sopir_1"),
                    rs.getString("sopir_2"),
                    rs.getInt("jumlah_penumpang"),
                    rs.getInt("pendapatan"),
                    rs.getDate("tanggal_perjalanan"),
                    rs.getString("status")
                };tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void judul_tiket(){
        Object[] judul ={"No.","Kode Tiket","Kode BUS","Nama BUS","No.Polisi Bus","Kode Trayek","Nama Trayek","Harga Trayek","Kode Kelas","Kelas BUS","Harga Kelas","Tanggal Berangkat","Jam Berangkat","Harga Tiket","Diskon","Jumlah Kursi","Ketersediaan Tiket"};
        tab_tiket = new DefaultTableModel(null,judul);
        tbl_tiket.setModel(tab_tiket);
    }
     
    public void tampil_tiket(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_tiket.getDataVector().removeAllElements();
            tab_tiket.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_tiket WHERE status ='tersedia' ORDER BY tanggal_berangkat DESC");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_tiket"),
                    rs.getString("kd_bus"),
                    rs.getString("nama"),
                    rs.getString("no_polisi"),
                    rs.getString("kd_trayek"),
                    rs.getString("nama_trayek"),
                    rs.getInt("harga_trayek"),
                    rs.getString("kd_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getInt("harga_kelas"),
                    rs.getDate("tanggal_berangkat"),
                    rs.getString("jam_berangkat"),
                    rs.getInt("harga"),
                    rs.getInt("diskon"),
                    rs.getInt("jumlah_kursi"),
                    rs.getString("status")
                };
                tab_tiket.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
     public void judul_sopir1(){
        Object[] judul ={"No.","Kode Sopir","Nama","Alamat","No.HP","No.SIM"};
        tab_sopir1 = new DefaultTableModel(null,judul);
        tbl_sopir1.setModel(tab_sopir1);
    }
    
    
    public void tampil_sopir1(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_sopir1.getDataVector().removeAllElements();
            tab_sopir1.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_sopir WHERE kd_sopir != '"+tsopir2.getText()+"' AND status_sopir = 'tersedia'");
            while (rs.next()) {
               int b = no++;
                    Object[] data = {
                        b,
                        rs.getString("kd_sopir"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("no_hp"),
                        rs.getString("no_sim")
                    };
                    tab_sopir1.addRow(data);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void judul_sopir2(){
        Object[] judul ={"No.","Kode Sopir","Nama","Alamat","No.HP","No.SIM"};
        tab_sopir2 = new DefaultTableModel(null,judul);
        tbl_sopir2.setModel(tab_sopir2);
    }
    
    
    public void tampil_sopir2(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_sopir2.getDataVector().removeAllElements();
            tab_sopir2.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_sopir WHERE kd_sopir != '"+tsopir1.getText()+"' AND status_sopir = 'tersedia'");
            while (rs.next()) {
               int b = no++;
                    Object[] data = {
                        b,
                        rs.getString("kd_sopir"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("no_hp"),
                        rs.getString("no_sim")
                    };
                    tab_sopir2.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reset(){
        tkode_tiket.setText("");
        tnama_bus.setText("");
        tnopol_bus.setText("");
        ttanggal_berangkat.setText("");
        tjam_berangkat.setText("");
        tsopir1.setText("");
        tsopir2.setText("");
        tjumlah_penumpang.setText("");
        tpendapatan.setText("");
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bsimpan.setVisible(true);
        breset.setVisible(true);
        tkode_tiket.requestFocus();
    }
    
    public void aksi(String sql,String pesan){
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, pesan);
            tampildata();
            tampil_sopir1();
            tampil_sopir2();
            tampil_tiket();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public void aksi2(String sql2){
        try {
            st = cn.createStatement();
            st.executeUpdate(sql2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates new form admin_perjalanan
     */
    public admin_perjalanan() {
        initComponents();
        judul();
        judul_tiket();
        judul_sopir1();
        judul_sopir2();
        tampildata();
        tampil_tiket();
        tampil_sopir1();
        tampil_sopir2();
        hari();
        waktu();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popuup_tiket = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_tiket = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        popup_sopir1 = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_sopir1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        popup_sopir2 = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_sopir2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tkode_tiket = new javax.swing.JTextField();
        btampil2 = new javax.swing.JPanel();
        btampil3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tnama_bus = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        tnopol_bus = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        ltanggal = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lwaktu = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tsopir1 = new javax.swing.JTextField();
        btampil4 = new javax.swing.JPanel();
        btampil5 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tjam_berangkat = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        tcari = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        tjumlah_penumpang = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        tpendapatan = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        bsimpan = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        bedit = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        breset = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        bhapus = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        tsopir2 = new javax.swing.JTextField();
        btampil8 = new javax.swing.JPanel();
        btampil9 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_perjalanan = new javax.swing.JTable();
        ttanggal_berangkat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tkode_bus = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        hide = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        popuup_tiket.setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(74, 131, 122));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_tiket.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_tiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tiketMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_tiket);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 930, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(232, 146, 61));
        jLabel2.setText("Daftar Tiket BUS");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, -1, -1));

        popuup_tiket.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        popup_sopir1.setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(74, 131, 122));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_sopir1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sopir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sopir1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_sopir1);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(232, 146, 61));
        jLabel5.setText("Daftar Sopir");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        popup_sopir1.getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        popup_sopir2.setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(74, 131, 122));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_sopir2.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_sopir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sopir2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_sopir2);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(232, 146, 61));
        jLabel6.setText("Daftar Sopir");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        popup_sopir2.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(85, 153, 142));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tkode_tiket.setEditable(false);
        tkode_tiket.setBackground(new java.awt.Color(85, 153, 142));
        tkode_tiket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_tiket.setForeground(new java.awt.Color(255, 255, 255));
        tkode_tiket.setBorder(null);
        tkode_tiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkode_tiketMouseClicked(evt);
            }
        });
        jPanel1.add(tkode_tiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 220, 30));

        btampil2.setBackground(new java.awt.Color(74, 131, 122));
        btampil2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil2MouseClicked(evt);
            }
        });
        btampil2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btampil3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btampil3.setForeground(new java.awt.Color(255, 255, 255));
        btampil3.setText("TAMPIL");
        btampil3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil3MouseClicked(evt);
            }
        });
        btampil2.add(btampil3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(btampil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 90, 40));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 220, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(232, 146, 61));
        jLabel4.setText("Nama BUS");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        tnama_bus.setEditable(false);
        tnama_bus.setBackground(new java.awt.Color(85, 153, 142));
        tnama_bus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnama_bus.setForeground(new java.awt.Color(255, 255, 255));
        tnama_bus.setBorder(null);
        jPanel1.add(tnama_bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 320, 30));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 320, 10));

        tnopol_bus.setEditable(false);
        tnopol_bus.setBackground(new java.awt.Color(85, 153, 142));
        tnopol_bus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnopol_bus.setForeground(new java.awt.Color(255, 255, 255));
        tnopol_bus.setBorder(null);
        tnopol_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tnopol_busKeyTyped(evt);
            }
        });
        jPanel1.add(tnopol_bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 140, 30));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 140, 10));

        ltanggal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ltanggal.setForeground(new java.awt.Color(255, 255, 255));
        ltanggal.setText("08-09-2017");
        jPanel1.add(ltanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(232, 146, 61));
        jLabel21.setText("Tanggal :");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, -1, -1));

        lwaktu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lwaktu.setForeground(new java.awt.Color(255, 255, 255));
        lwaktu.setText("        ");
        jPanel1.add(lwaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(232, 146, 61));
        jLabel23.setText("Waktu :");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        tsopir1.setEditable(false);
        tsopir1.setBackground(new java.awt.Color(85, 153, 142));
        tsopir1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tsopir1.setForeground(new java.awt.Color(255, 255, 255));
        tsopir1.setBorder(null);
        tsopir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tsopir1MouseClicked(evt);
            }
        });
        jPanel1.add(tsopir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 220, 30));

        btampil4.setBackground(new java.awt.Color(74, 131, 122));
        btampil4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil4MouseClicked(evt);
            }
        });
        btampil4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btampil5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btampil5.setForeground(new java.awt.Color(255, 255, 255));
        btampil5.setText("TAMPIL");
        btampil5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil5MouseClicked(evt);
            }
        });
        btampil4.add(btampil5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(btampil4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 90, 40));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 220, 20));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(232, 146, 61));
        jLabel25.setText("Sopir Pertama");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, 20));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(232, 146, 61));
        jLabel27.setText("Kode Tiket");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(232, 146, 61));
        jLabel28.setText("Waktu Pemberangkatan");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 20));

        tjam_berangkat.setEditable(false);
        tjam_berangkat.setBackground(new java.awt.Color(85, 153, 142));
        tjam_berangkat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tjam_berangkat.setForeground(new java.awt.Color(255, 255, 255));
        tjam_berangkat.setBorder(null);
        jPanel1.add(tjam_berangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 300, 130, 30));
        jPanel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 330, 20));
        jPanel1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1140, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(232, 146, 61));
        jLabel12.setText("Kode BUS");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));

        tcari.setBackground(new java.awt.Color(85, 153, 142));
        tcari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tcari.setForeground(new java.awt.Color(255, 255, 255));
        tcari.setBorder(null);
        tcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tcariActionPerformed(evt);
            }
        });
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 360, 700, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(232, 146, 61));
        jLabel14.setText("Pencarian");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, -1));

        jPanel6.setBackground(new java.awt.Color(74, 131, 122));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("REFRESH");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 50));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 350, 380, 50));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 700, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(232, 146, 61));
        jLabel24.setText("Jumlah Penumpang");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, 20));

        tjumlah_penumpang.setEditable(false);
        tjumlah_penumpang.setBackground(new java.awt.Color(85, 153, 142));
        tjumlah_penumpang.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tjumlah_penumpang.setForeground(new java.awt.Color(255, 255, 255));
        tjumlah_penumpang.setBorder(null);
        tjumlah_penumpang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlah_penumpangKeyTyped(evt);
            }
        });
        jPanel1.add(tjumlah_penumpang, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 320, 30));
        jPanel1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 320, 10));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(232, 146, 61));
        jLabel29.setText("Total Pendapatan");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, 20));

        tpendapatan.setEditable(false);
        tpendapatan.setBackground(new java.awt.Color(85, 153, 142));
        tpendapatan.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tpendapatan.setForeground(new java.awt.Color(255, 255, 255));
        tpendapatan.setBorder(null);
        tpendapatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tpendapatanKeyTyped(evt);
            }
        });
        jPanel1.add(tpendapatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 320, 30));
        jPanel1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 320, 10));

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
        bsimpan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, -1, 50));

        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 80, 380, 50));

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
        bedit.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, 50));

        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 380, -1));

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
        breset.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, 50));

        jPanel1.add(breset, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, 380, -1));

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
        bhapus.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, 50));

        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 380, -1));

        tsopir2.setEditable(false);
        tsopir2.setBackground(new java.awt.Color(85, 153, 142));
        tsopir2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tsopir2.setForeground(new java.awt.Color(255, 255, 255));
        tsopir2.setBorder(null);
        tsopir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tsopir2MouseClicked(evt);
            }
        });
        jPanel1.add(tsopir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 220, 30));

        btampil8.setBackground(new java.awt.Color(74, 131, 122));
        btampil8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil8MouseClicked(evt);
            }
        });
        btampil8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btampil9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btampil9.setForeground(new java.awt.Color(255, 255, 255));
        btampil9.setText("TAMPIL");
        btampil9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btampil9MouseClicked(evt);
            }
        });
        btampil8.add(btampil9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(btampil8, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 90, 40));
        jPanel1.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 220, 20));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(232, 146, 61));
        jLabel32.setText("Sopir Pertama");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, 20));

        tbl_perjalanan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_perjalanan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_perjalananMouseMoved(evt);
            }
        });
        tbl_perjalanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_perjalananMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_perjalanan);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 400, 1140, 290));

        ttanggal_berangkat.setEditable(false);
        ttanggal_berangkat.setBackground(new java.awt.Color(85, 153, 142));
        ttanggal_berangkat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ttanggal_berangkat.setForeground(new java.awt.Color(255, 255, 255));
        ttanggal_berangkat.setBorder(null);
        jPanel1.add(ttanggal_berangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 190, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(232, 146, 61));
        jLabel13.setText("No. Polisi BUS");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        tkode_bus.setEditable(false);
        tkode_bus.setBackground(new java.awt.Color(85, 153, 142));
        tkode_bus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_bus.setForeground(new java.awt.Color(255, 255, 255));
        tkode_bus.setBorder(null);
        tkode_bus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tkode_busKeyTyped(evt);
            }
        });
        jPanel1.add(tkode_bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 170, 30));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 260, 170, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 1390, 730));

        jPanel2.setBackground(new java.awt.Color(74, 131, 122));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FORM PERJALANAN BUS");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, -1, -1));

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

    private void tkode_tiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkode_tiketMouseClicked
        // TODO add your handling code here:
       popuup_tiket.setVisible(true);
       popuup_tiket.setSize(1000,420);
    }//GEN-LAST:event_tkode_tiketMouseClicked

    private void btampil3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil3MouseClicked
        // TODO add your handling code here:
        popuup_tiket.setVisible(true);
        popuup_tiket.setSize(1000,420);
    }//GEN-LAST:event_btampil3MouseClicked

    private void btampil2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil2MouseClicked
        // TODO add your handling code here:
        popuup_tiket.setVisible(true);
        popuup_tiket.setSize(1000,420);
    }//GEN-LAST:event_btampil2MouseClicked

    private void tnopol_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnopol_busKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tnopol_busKeyTyped

    private void tsopir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tsopir1MouseClicked
        // TODO add your handling code here:
        popup_sopir1.setVisible(true);
        popup_sopir1.setSize(700,420);
    }//GEN-LAST:event_tsopir1MouseClicked

    private void btampil5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil5MouseClicked
        // TODO add your handling code here:
        popup_sopir1.setVisible(true);
        popup_sopir1.setSize(700,420);
    }//GEN-LAST:event_btampil5MouseClicked

    private void btampil4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil4MouseClicked
        // TODO add your handling code here:
        popup_sopir1.setVisible(true);
        popup_sopir1.setSize(700,420);
    }//GEN-LAST:event_btampil4MouseClicked

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_perjalanan WHERE kd_perjalanan LIKE '%"+tcari.getText()+"%' OR kd_tiket LIKE '%"+tcari.getText()+"%' OR kd_bus LIKE '%"+tcari.getText()+"%' OR sopir_1 LIKE '%"+tcari.getText()+"%' OR sopir_2 LIKE '%"+tcari.getText()+"%' OR jumlah_penumpang LIKE '%"+tcari.getText()+"%' OR pendapatan LIKE '%"+tcari.getText()+"%' OR tanggal_perjalanan LIKE '%"+tcari.getText()+"%' OR status LIKE '%"+tcari.getText()+"%' ");
            while (rs.next()) {       
                int a = no++;
                Object[] data={
                    a,
                    rs.getString("kd_perjalanan"),
                    rs.getString("kd_tiket"),
                    rs.getString("kd_bus"),
                    rs.getString("sopir_1"),
                    rs.getString("sopir_2"),
                    rs.getInt("jumlah_penumpang"),
                    rs.getInt("pendapatan"),
                    rs.getDate("tanggal_perjalanan"),
                    rs.getString("status")
                };tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcariKeyReleased

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        // TODO add your handling code here:
        tcariKeyReleased(null);
        tcari.setText("");
        tampildata();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void tjumlah_penumpangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlah_penumpangKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tjumlah_penumpangKeyTyped

    private void tpendapatanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tpendapatanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tpendapatanKeyTyped

    private void bsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bsimpanMouseClicked
        // TODO add your handling code here:
        if (tjumlah_penumpang.getText().equals(0) || tkode_tiket.getText().isEmpty() || tsopir1.getText().isEmpty() || tsopir2.getText().isEmpty() || ttanggal_berangkat.getText().isEmpty() || tjam_berangkat.getText().isEmpty() || tjumlah_penumpang.getText().isEmpty() || tpendapatan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!!!");
        }else{
           String kode = ttanggal_berangkat.getText()+tjam_berangkat.getText()+tnopol_bus.getText();
           String sql = "INSERT INTO tbl_perjalanan VALUES('"+kode+"','"+tkode_tiket.getText()+"','"+tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 2)+"','"+tsopir1.getText()+"','"+tsopir2.getText()+"','"+tjumlah_penumpang.getText()+"','"+tpendapatan.getText()+"','"+ttanggal_berangkat.getText()+"','berangkat')";
           String sql2 = "UPDATE tbl_tiket SET status = 'perjalanan' WHERE kd_tiket = '"+tkode_tiket.getText()+"'";
           String sql3 = "UPDATE tbl_sopir SET status_sopir = 'perjalanan' WHERE kd_sopir = '"+tsopir1.getText()+"' OR kd_sopir = '"+tsopir2.getText()+"'";
           String sql4 = "INSERT INTO tbl_perjalanan_detail VALUES(null,'"+kode+"','pemberangkatan awal',null)";
           aksi2(sql4);
           aksi2(sql3);
           aksi2(sql2);
           aksi(sql,"Data Berhasil Disimpan");
        }

    }//GEN-LAST:event_bsimpanMouseClicked

    private void beditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beditMouseClicked

//           String sql0 = "DELETE FROM tbl_perjalanan WHERE kd_perjalanan = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 1)+"'";
           String sql02="UPDATE tbl_tiket SET status ='tersedia' WHERE kd_tiket='"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 2)+"'";
           String sql03 = "UPDATE tbl_sopir SET status_sopir = 'tersedia' WHERE kd_sopir = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 4)+"' OR kd_sopir = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 5)+"'";
    
           String kode = ttanggal_berangkat.getText()+tjam_berangkat.getText()+tnopol_bus.getText();
           String sql = "UPDATE tbl_perjalanan SET kd_perjalanan = '"+kode+"', kd_tiket = '"+tkode_tiket.getText()+"', kd_bus = '"+tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 2)+"', sopir_1 = '"+tsopir1.getText()+"', sopir_2 = '"+tsopir2.getText()+"', jumlah_penumpang = '"+tjumlah_penumpang.getText()+"', pendapatan = '"+tpendapatan.getText()+"', tanggal_perjalanan ='"+ttanggal_berangkat.getText()+"', status = 'berangkat' WHERE kd_perjalanan = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 1)+"'";
           String sql2 = "UPDATE tbl_tiket SET status = 'perjalanan' WHERE kd_tiket = '"+tkode_tiket.getText()+"'";
           String sql3 = "UPDATE tbl_sopir SET status_sopir = 'perjalanan' WHERE kd_sopir = '"+tsopir1.getText()+"' OR kd_sopir = '"+tsopir2.getText()+"'";
           String sql4 = "UPDATE tbl_perjalanan_detail SET kd_perjalanan = '"+kode+"' WHERE kd_perjalanan = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 1)+"'";
           aksi2(sql4);
           
           
           aksi2(sql03);
           aksi2(sql02);
//           aksi2(sql0);
           
           aksi2(sql3);
           aksi2(sql2);
           aksi(sql,"Data Berhasil Diubah");

    }//GEN-LAST:event_beditMouseClicked

    private void bresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bresetMouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bresetMouseClicked

    private void bhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bhapusMouseClicked
        // TODO add your handling code here:
        int jawab = 0;
            if (tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 9) == "selesai") {
                JOptionPane.showMessageDialog(null, "DATA TIDAK BISA DIHAPUS");
                tampildata();
                reset();
            }else{
                if((jawab = JOptionPane.showConfirmDialog(null, "Anda yakin kan menghapus data ini ?","Konfirmasi", JOptionPane.YES_NO_OPTION))==0){
                    String sql = "DELETE FROM tbl_perjalanan WHERE kd_perjalanan = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 1)+"'";
                    String sql2="UPDATE tbl_tiket SET status ='tersedia' WHERE kd_tiket='"+tkode_tiket.getText()+"'";
                    String sql3 = "UPDATE tbl_sopir SET status_sopir = 'tersedia' WHERE kd_sopir = '"+tsopir1.getText()+"' OR kd_sopir = '"+tsopir2.getText()+"'";
                    String sql4 = "DELETE FROM tbl_perjalanan_detail WHERE kd_perjalanan = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 1)+"'";
                    aksi2(sql4);
                    aksi2(sql3);
                    aksi2(sql2);
                    aksi(sql,"Data Berhasil Dihapus");
                }
            }

    }//GEN-LAST:event_bhapusMouseClicked

    private void tbl_perjalananMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_perjalananMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_perjalananMouseMoved

    private void tbl_perjalananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_perjalananMouseClicked
        // TODO add your handling code here:
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM tbl_bus WHERE kd_bus = '"+tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 3)+"'");
            while (rs.next()) {                
                tnama_bus.setText(rs.getString("nama"));
                tnopol_bus.setText(rs.getString("no_polisi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tkode_tiket.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 2)+"");
        ttanggal_berangkat.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 8)+"");
        tsopir1.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 4)+"");
        tsopir2.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 5)+"");
        tjumlah_penumpang.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 6)+"");
        tpendapatan.setText(tabmodel.getValueAt(tbl_perjalanan.getSelectedRow(), 7)+"");
        tampil_sopir1();
        tampil_sopir2();
        bhapus.setVisible(true);
        bedit.setVisible(true);
        bsimpan.setVisible(false);
        breset.setVisible(true);
    }//GEN-LAST:event_tbl_perjalananMouseClicked

    private void hideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_hideMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void tsopir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tsopir2MouseClicked
        // TODO add your handling code here:
        popup_sopir2.setVisible(true);
        popup_sopir2.setSize(700,420);
    }//GEN-LAST:event_tsopir2MouseClicked

    private void btampil9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil9MouseClicked
        // TODO add your handling code here:
        popup_sopir2.setVisible(true);
        popup_sopir2.setSize(700,420);
    }//GEN-LAST:event_btampil9MouseClicked

    private void btampil8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil8MouseClicked
        // TODO add your handling code here:
        popup_sopir2.setVisible(true);
        popup_sopir2.setSize(700,420);
    }//GEN-LAST:event_btampil8MouseClicked

    private void tbl_tiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tiketMouseClicked
        // TODO add your handling code here:
        tkode_tiket.setText(tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 1)+"");
        tnama_bus.setText(tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 3)+"");
        tnopol_bus.setText(tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 4)+"");
        ttanggal_berangkat.setText(tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 11)+"");
        tjam_berangkat.setText(tab_tiket.getValueAt(tbl_tiket.getSelectedRow(), 12)+"");
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT SUM(jumlah_beli) as penumpang FROM tbl_pembelian WHERE kd_tiket = '"+tkode_tiket.getText()+"'");
            while (rs.next()) {
                int a = rs.getInt(1);
               tjumlah_penumpang.setText(String.valueOf(a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT SUM(total_harga) as pendapat FROM tbl_pembelian WHERE kd_tiket = '"+tkode_tiket.getText()+"'");
            while (rs.next()) {
                int b = rs.getInt(1);
               tpendapatan.setText(String.valueOf(b));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        popuup_tiket.setVisible(false);
       
        //membuat harga otomatis
        //        int kelas = Integer.parseInt(tharga_kelas.getText());
        //        int trayek = Integer.parseInt(tharga_trayek.getText());
        //        int tiket = kelas+trayek;
        //        String harga_tiket = String.valueOf(tiket);
        ////        tharga_tiket.setText(harga_tiket);
    }//GEN-LAST:event_tbl_tiketMouseClicked

    private void tbl_sopir1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sopir1MouseClicked
        // TODO add your handling code here:
        tsopir1.setText(tab_sopir1.getValueAt(tbl_sopir1.getSelectedRow(), 1)+"");
        tampil_sopir1();
        tampil_sopir2();
        popup_sopir1.setVisible(false);
    }//GEN-LAST:event_tbl_sopir1MouseClicked

    private void tbl_sopir2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sopir2MouseClicked
        // TODO add your handling code here:
        tsopir2.setText(tab_sopir2.getValueAt(tbl_sopir2.getSelectedRow(), 1)+"");
        tampil_sopir1();
        tampil_sopir2();
        popup_sopir2.setVisible(false);
        
    }//GEN-LAST:event_tbl_sopir2MouseClicked

    private void tkode_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkode_busKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tkode_busKeyTyped

    private void tcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tcariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tcariActionPerformed

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
            java.util.logging.Logger.getLogger(admin_perjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_perjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_perjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_perjalanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_perjalanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bedit;
    private javax.swing.JPanel bhapus;
    private javax.swing.JPanel breset;
    private javax.swing.JPanel bsimpan;
    private javax.swing.JPanel btampil2;
    private javax.swing.JLabel btampil3;
    private javax.swing.JPanel btampil4;
    private javax.swing.JLabel btampil5;
    private javax.swing.JPanel btampil8;
    private javax.swing.JLabel btampil9;
    private javax.swing.JPanel close;
    private javax.swing.JPanel hide;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel ltanggal;
    private javax.swing.JLabel lwaktu;
    private javax.swing.JDialog popup_sopir1;
    private javax.swing.JDialog popup_sopir2;
    private javax.swing.JDialog popuup_tiket;
    private javax.swing.JTable tbl_perjalanan;
    private javax.swing.JTable tbl_sopir1;
    private javax.swing.JTable tbl_sopir2;
    private javax.swing.JTable tbl_tiket;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tjam_berangkat;
    private javax.swing.JTextField tjumlah_penumpang;
    private javax.swing.JTextField tkode_bus;
    private javax.swing.JTextField tkode_tiket;
    private javax.swing.JTextField tnama_bus;
    private javax.swing.JTextField tnopol_bus;
    private javax.swing.JTextField tpendapatan;
    private javax.swing.JTextField tsopir1;
    private javax.swing.JTextField tsopir2;
    private javax.swing.JTextField ttanggal_berangkat;
    // End of variables declaration//GEN-END:variables
}
