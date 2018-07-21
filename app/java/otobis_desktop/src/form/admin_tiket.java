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
 * @author RPL206
 */
public class admin_tiket extends javax.swing.JFrame {

    public Statement st;
    public ResultSet rs;
    public Connection cn = config.koneksi.getKoneksi();
    public DefaultTableModel tabmodel,tab_bus,tab_trayek;
    public String bus;
    fungsi aksi = new fungsi();
   
    
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
        Object[] judul ={"No.","Kode Tiket","Kode BUS","Nama BUS","No.Polisi Bus","Kode Trayek","Nama Trayek","Harga Trayek","Kode Kelas","Kelas BUS","Harga Kelas","Tanggal Berangkat","Jam Berangkat","Harga Tiket","Diskon","Jumlah Kursi","Ketersediaan Tiket"};
        tabmodel = new DefaultTableModel(null,judul);
        tbl_tiket.setModel(tabmodel);
    }
    
     public void judul_bus(){
        Object[] judul ={"No.","Kode BUS","Nama BUS","No Polisi","Kode Kelas","Nama Kelas","Harga Kelas","Status BUS"};
        tab_bus = new DefaultTableModel(null,judul);
        tbl_bus.setModel(tab_bus);
    }
     
     public void judul_trayek(){
        Object[] judul ={"No.","Kode Trayek","Kota Asal","Kota Tujuan","Harga Trayek","Nama Trayek"};
        tab_trayek = new DefaultTableModel(null,judul);
        tbl_trayek.setModel(tab_trayek);
    }
     
    public void tampildata(){
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_tiket ORDER BY tanggal_berangkat DESC");
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
                tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
         
    public void tampil_bus(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_bus.getDataVector().removeAllElements();
            tab_bus.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_bus WHERE status_bus = 'tersedia'");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_bus"),
                    rs.getString("nama"),
                    rs.getString("no_polisi"),
                    rs.getString("kd_kelas"),
                    rs.getString("nama_kelas"),
                    rs.getString("harga_kelas"),
                    rs.getString("status_bus")
                };
                tab_bus.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void tampil_trayek(){
        try {
            int no = 1;
            st = cn.createStatement();
            tab_trayek.getDataVector().removeAllElements();
            tab_trayek.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_trayek");
            while (rs.next()) {
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_trayek"),
                    rs.getString("asal"),
                    rs.getString("tujuan"),
                    rs.getInt("harga_trayek"),
                    rs.getString("nama_trayek")
                };
                tab_trayek.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public void reset(){
        tnama_bus.setText("");
        tkode_bus.setText("");
        tnopol_bus.setText("");
        tkode_kelas.setText("");
        tnama_kelas.setText("");
        tharga_kelas.setText("");
        tnama_trayek.setText("");
        tkode_trayek.setText("");
        tharga_trayek.setText("");
        tharga_tiket.setText("");
        tdiskon.setText("");
        tkursi.setText("");
        tjam_berangkat.setText("");
        ttanggal_berangkat.setText("");
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bsimpan.setVisible(true);
        breset.setVisible(true);
        tnama_bus.requestFocus();
        autokode();
    }
     
     public void autokode(){
        try {
            String tampilkode = "";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("YMMdd");
            String tgl = format.format(date);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT MAX(RIGHT(kd_tiket,3)) FROM tbl_tiket WHERE kd_tiket LIKE '%"+tgl+"%' order by kd_tiket DESC limit 0,1");
            if(rs.next()){
                int no = rs.getInt(1)+1;
                if (no < 10) {
                    tampilkode = "TKT"+tgl+"00"+String.valueOf(no);
                } else if(no > 9 && no <= 99) {
                     tampilkode = "TKT"+tgl+"0"+String.valueOf(no);
                }else{
                    tampilkode = "TKT"+tgl+String.valueOf(no);
                }
                kode_tiket.setText(tampilkode);
            }else{
                kode_tiket.setText("TKT"+tgl+"001");
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
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
     
     public void refresh(){
         tampildata();
         reset();
         autokode();
     }
    
     public void aksi2(String sql2){
        try {
            st = cn.createStatement();
            st.executeUpdate(sql2);
           tampil_bus();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates new form admin_tiket
     */
    public admin_tiket() {
        initComponents();
        judul();
        judul_bus();
        judul_trayek();
        tampildata();
        tampil_bus();
        tampil_trayek();
        autokode();
        reset();
        hari();
        waktu();
        tnama_bus.requestFocus();
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

        popup_bus = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bus = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        popup_trayek = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_trayek = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        kode_tiket = new javax.swing.JLabel();
        tkode_bus = new javax.swing.JTextField();
        btampil2 = new javax.swing.JPanel();
        btampil3 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        tnama_bus = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        tnama_kelas = new javax.swing.JTextField();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        tnopol_bus = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        ltanggal = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lwaktu = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        tharga_kelas = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        tkode_trayek = new javax.swing.JTextField();
        btampil4 = new javax.swing.JPanel();
        btampil5 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        tnama_trayek = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        tharga_trayek = new javax.swing.JTextField();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tkode_kelas = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator16 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tharga_tiket = new javax.swing.JTextField();
        jSeparator12 = new javax.swing.JSeparator();
        tcari = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        tdiskon = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        tkursi = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel30 = new javax.swing.JLabel();
        tjam_berangkat = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        bsimpan = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        bedit = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        breset = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        bhapus = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tiket = new javax.swing.JTable();
        jSeparator21 = new javax.swing.JSeparator();
        ttanggal_berangkat = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        hide = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        popup_bus.setUndecorated(true);
        popup_bus.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(74, 131, 122));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        tbl_bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_busMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_bus);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(232, 146, 61));
        jLabel2.setText("Daftar BUS Kosong");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        popup_bus.getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 430));

        popup_trayek.setUndecorated(true);
        popup_trayek.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(74, 131, 122));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_trayek.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_trayek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_trayekMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_trayek);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(232, 146, 61));
        jLabel6.setText("Daftar Trayek BUS");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        popup_trayek.getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 430));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(85, 153, 142));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kode_tiket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        kode_tiket.setForeground(new java.awt.Color(255, 255, 255));
        kode_tiket.setText("TKT20170608001");
        jPanel1.add(kode_tiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, -1, -1));

        tkode_bus.setEditable(false);
        tkode_bus.setBackground(new java.awt.Color(85, 153, 142));
        tkode_bus.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_bus.setForeground(new java.awt.Color(255, 255, 255));
        tkode_bus.setBorder(null);
        tkode_bus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkode_busMouseClicked(evt);
            }
        });
        jPanel1.add(tkode_bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 220, 30));

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

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(232, 146, 61));
        jLabel15.setText("Kelas BUS");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, -1, 20));

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
        jPanel1.add(tnama_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 370, 320, 30));
        jPanel1.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 320, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(232, 146, 61));
        jLabel11.setText("Harga Tiket BUS");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

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
        jPanel1.add(tnopol_bus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 320, 30));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 320, 10));

        ltanggal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ltanggal.setForeground(new java.awt.Color(255, 255, 255));
        ltanggal.setText("08-09-2017");
        jPanel1.add(ltanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(232, 146, 61));
        jLabel19.setText("Kode Tiket :");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(232, 146, 61));
        jLabel21.setText("Tanggal :");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));

        lwaktu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lwaktu.setForeground(new java.awt.Color(255, 255, 255));
        lwaktu.setText("        ");
        jPanel1.add(lwaktu, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 10, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(232, 146, 61));
        jLabel23.setText("Waktu :");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(232, 146, 61));
        jLabel22.setText("Harga Kelas");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, -1, 20));

        tharga_kelas.setEditable(false);
        tharga_kelas.setBackground(new java.awt.Color(85, 153, 142));
        tharga_kelas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tharga_kelas.setForeground(new java.awt.Color(255, 255, 255));
        tharga_kelas.setBorder(null);
        tharga_kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tharga_kelasKeyTyped(evt);
            }
        });
        jPanel1.add(tharga_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 320, 30));
        jPanel1.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 470, 320, 10));

        tkode_trayek.setEditable(false);
        tkode_trayek.setBackground(new java.awt.Color(85, 153, 142));
        tkode_trayek.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_trayek.setForeground(new java.awt.Color(255, 255, 255));
        tkode_trayek.setBorder(null);
        tkode_trayek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkode_trayekMouseClicked(evt);
            }
        });
        jPanel1.add(tkode_trayek, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 520, 220, 30));

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

        jPanel1.add(btampil4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 510, 90, 40));
        jPanel1.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 550, 220, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(232, 146, 61));
        jLabel5.setText("Nama Trayek");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 560, -1, -1));

        tnama_trayek.setEditable(false);
        tnama_trayek.setBackground(new java.awt.Color(85, 153, 142));
        tnama_trayek.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnama_trayek.setForeground(new java.awt.Color(255, 255, 255));
        tnama_trayek.setBorder(null);
        jPanel1.add(tnama_trayek, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 590, 320, 30));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 620, 320, 10));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(232, 146, 61));
        jLabel25.setText("Kode Trayek");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, -1, 20));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(232, 146, 61));
        jLabel26.setText("Harga Trayek");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 630, -1, -1));

        tharga_trayek.setEditable(false);
        tharga_trayek.setBackground(new java.awt.Color(85, 153, 142));
        tharga_trayek.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tharga_trayek.setForeground(new java.awt.Color(255, 255, 255));
        tharga_trayek.setBorder(null);
        tharga_trayek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tharga_trayekKeyTyped(evt);
            }
        });
        jPanel1.add(tharga_trayek, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 660, 320, 30));
        jPanel1.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 690, 320, 10));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(232, 146, 61));
        jLabel27.setText("Kode BUS");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, 20));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(232, 146, 61));
        jLabel28.setText("Kode Kelas");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, -1, 20));

        tkode_kelas.setEditable(false);
        tkode_kelas.setBackground(new java.awt.Color(85, 153, 142));
        tkode_kelas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode_kelas.setForeground(new java.awt.Color(255, 255, 255));
        tkode_kelas.setBorder(null);
        jPanel1.add(tkode_kelas, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 320, 30));
        jPanel1.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 320, 20));
        jPanel1.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1140, 20));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(232, 146, 61));
        jLabel12.setText("No. Polisi BUS");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(232, 146, 61));
        jLabel13.setText("Tanggal Pemberangakatan");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        tharga_tiket.setEditable(false);
        tharga_tiket.setBackground(new java.awt.Color(85, 153, 142));
        tharga_tiket.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tharga_tiket.setForeground(new java.awt.Color(255, 255, 255));
        tharga_tiket.setBorder(null);
        tharga_tiket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tharga_tiketKeyTyped(evt);
            }
        });
        jPanel1.add(tharga_tiket, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 320, 30));
        jPanel1.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 320, 10));

        tcari.setBackground(new java.awt.Color(85, 153, 142));
        tcari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tcari.setForeground(new java.awt.Color(255, 255, 255));
        tcari.setBorder(null);
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel1.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, 370, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(232, 146, 61));
        jLabel14.setText("Pencarian");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, -1, -1));

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

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 380, 380, 50));
        jPanel1.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 430, 370, 20));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(232, 146, 61));
        jLabel24.setText("Diskon(%)");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, -1, 20));

        tdiskon.setBackground(new java.awt.Color(85, 153, 142));
        tdiskon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tdiskon.setForeground(new java.awt.Color(255, 255, 255));
        tdiskon.setBorder(null);
        tdiskon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tdiskonKeyTyped(evt);
            }
        });
        jPanel1.add(tdiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 320, 30));
        jPanel1.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 260, 320, 10));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(232, 146, 61));
        jLabel29.setText("Jumlah Kursi");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 270, -1, 20));

        tkursi.setBackground(new java.awt.Color(85, 153, 142));
        tkursi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkursi.setForeground(new java.awt.Color(255, 255, 255));
        tkursi.setBorder(null);
        tkursi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tkursiKeyTyped(evt);
            }
        });
        jPanel1.add(tkursi, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 320, 30));
        jPanel1.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 320, 10));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(232, 146, 61));
        jLabel30.setText("Jam Pemberangkatan");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 60, -1, -1));

        tjam_berangkat.setBackground(new java.awt.Color(85, 153, 142));
        tjam_berangkat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tjam_berangkat.setForeground(new java.awt.Color(255, 255, 255));
        tjam_berangkat.setBorder(null);
        tjam_berangkat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjam_berangkatKeyTyped(evt);
            }
        });
        jPanel1.add(tjam_berangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 90, 380, 30));
        jPanel1.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 380, 10));

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

        jPanel1.add(bsimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, 380, 50));

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

        jPanel1.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, 380, -1));

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

        jPanel1.add(breset, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, 380, -1));

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

        jPanel1.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 270, 380, -1));

        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

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
        tbl_tiket.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_tiketMouseMoved(evt);
            }
        });
        tbl_tiket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_tiketMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_tiket);

        jScrollPane4.setViewportView(jScrollPane1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 450, 860, 240));
        jPanel1.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 320, 10));

        ttanggal_berangkat.setBackground(new java.awt.Color(85, 153, 142));
        ttanggal_berangkat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ttanggal_berangkat.setForeground(new java.awt.Color(255, 255, 255));
        ttanggal_berangkat.setBorder(null);
        ttanggal_berangkat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ttanggal_berangkatKeyTyped(evt);
            }
        });
        jPanel1.add(ttanggal_berangkat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 320, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 1390, 730));

        jPanel2.setBackground(new java.awt.Color(74, 131, 122));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("FORM PEMBUATAN TIKET BUS");
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

    private void tnama_kelasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnama_kelasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tnama_kelasKeyTyped

    private void tnopol_busKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnopol_busKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_tnopol_busKeyTyped

    private void hideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_hideMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void btampil3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil3MouseClicked
        // TODO add your handling code here:
        popup_bus.setVisible(true);
        popup_bus.setSize(700,420);
    }//GEN-LAST:event_btampil3MouseClicked

    private void btampil2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil2MouseClicked
        // TODO add your handling code here:
        popup_bus.setVisible(true);
        popup_bus.setSize(700,420);
    }//GEN-LAST:event_btampil2MouseClicked

    private void tbl_busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_busMouseClicked
        // TODO add your handling code here:
        tkode_bus.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 1)+"");
        tnama_bus.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 2)+"");
        tnopol_bus.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 3)+"");
        tkode_kelas.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 4)+"");
        tnama_kelas.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 5)+"");
        tharga_kelas.setText(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 6)+"");
        popup_bus.dispose();
        int a;
        if (tharga_trayek.getText().isEmpty()) {
            a = 0;
        }else{
            a = Integer.parseInt(tharga_trayek.getText());
            int b = Integer.parseInt(tab_bus.getValueAt(tbl_bus.getSelectedRow(), 6)+"");
            int c = a + b;
            tharga_tiket.setText(String.valueOf(c));
        }
        //membuat harga otomatis
//        int kelas = Integer.parseInt(tharga_kelas.getText());
//        int trayek = Integer.parseInt(tharga_trayek.getText());
//        int tiket = kelas+trayek;
//        String harga_tiket = String.valueOf(tiket);
////        tharga_tiket.setText(harga_tiket);
    }//GEN-LAST:event_tbl_busMouseClicked

    private void tharga_kelasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tharga_kelasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tharga_kelasKeyTyped

    private void btampil5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil5MouseClicked
        // TODO add your handling code here:
         popup_trayek.setVisible(true);
        popup_trayek.setSize(700,420);
    }//GEN-LAST:event_btampil5MouseClicked

    private void btampil4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btampil4MouseClicked
        // TODO add your handling code here:
        popup_trayek.setVisible(true);
        popup_trayek.setSize(700,420);
    }//GEN-LAST:event_btampil4MouseClicked

    private void tharga_trayekKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tharga_trayekKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tharga_trayekKeyTyped

    private void tharga_tiketKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tharga_tiketKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tharga_tiketKeyTyped

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
        try {
            int no = 1;
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_tiket WHERE kd_tiket LIKE '%"+tcari.getText()+"%'  OR nama LIKE '%"+tcari.getText()+"%' OR  kd_bus LIKE '%"+tcari.getText()+"%' OR no_polisi LIKE '%"+tcari.getText()+"%' OR kd_trayek LIKE '%"+tcari.getText()+"%' OR asal LIKE '%"+tcari.getText()+"%' OR tujuan LIKE '%"+tcari.getText()+"%' OR harga_trayek LIKE '%"+tcari.getText()+"%' OR kd_kelas LIKE '%"+tcari.getText()+"%' OR nama_kelas LIKE '%"+tcari.getText()+"%' OR harga_kelas LIKE '%"+tcari.getText()+"%' OR tanggal_berangkat LIKE '%"+tcari.getText()+"%' OR jam_berangkat LIKE '%"+tcari.getText()+"%' OR harga LIKE '%"+tcari.getText()+"%' OR diskon LIKE '%"+tcari.getText()+"%' OR jumlah_kursi LIKE '%"+tcari.getText()+"%' OR status LIKE '%"+tcari.getText()+"%'");
            while (rs.next()) {
                String namatrayek = rs.getString("asal")+" - "+rs.getString("tujuan");
                int b = no++;
                Object[] data = {
                    b,
                    rs.getString("kd_tiket"),
                    rs.getString("kd_bus"),
                    rs.getString("nama"),
                    rs.getString("no_polisi"),
                    rs.getString("kd_trayek"),
                    namatrayek,
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
                tabmodel.addRow(data);
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

    private void tbl_tiketMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tiketMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_tiketMouseMoved

    private void tbl_tiketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_tiketMouseClicked
        // TODO add your handling code here:
        String asal = tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 6)+"";
        String tujuan = tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 7)+"";
        kode_tiket.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 1)+"");
        tkode_bus.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 2)+"");
        tnama_bus.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 3)+"");
        tnopol_bus.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 4)+"");
        tkode_trayek.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 5)+"");
        tnama_trayek.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 6)+"");
        tharga_trayek.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 7)+"");
        tkode_kelas.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 8)+"");
        tnama_kelas.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 9)+"");
        tharga_kelas.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 10)+"");
        ttanggal_berangkat.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 11)+"");
//        ttanggal_berangkat.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 2)+"");
        tjam_berangkat.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 12)+"");
        tharga_tiket.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 13)+"");
        tdiskon.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 14)+"");
        tkursi.setText(tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 15)+"");

        bhapus.setVisible(true);
        bedit.setVisible(true);
        bsimpan.setVisible(false);
        breset.setVisible(true);
    }//GEN-LAST:event_tbl_tiketMouseClicked

    private void tdiskonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdiskonKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tdiskonKeyTyped

    private void tkursiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tkursiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tkursiKeyTyped

    private void tjam_berangkatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjam_berangkatKeyTyped
        // TODO add your handling code here:
//        char c = evt.getKeyChar();
//        if (! ( (c == KeyEvent.VK_COLON) || (Character.isDigit(c) || (c == KeyEvent.VK_DELETE) ||  (c == KeyEvent.VK_TAB) ))){
//            evt.consume();
//        }
//        
    }//GEN-LAST:event_tjam_berangkatKeyTyped

    private void tbl_trayekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_trayekMouseClicked
        // TODO add your handling code here:
        tkode_trayek.setText(tab_trayek.getValueAt(tbl_trayek.getSelectedRow(), 1)+"");
        tnama_trayek.setText(tab_trayek.getValueAt(tbl_trayek.getSelectedRow(), 5)+"");
        tharga_trayek.setText(tab_trayek.getValueAt(tbl_trayek.getSelectedRow(), 4)+"");
        //membuat harga otomatis
        int kelas = Integer.parseInt(tharga_kelas.getText());
        int trayek = Integer.parseInt(tharga_trayek.getText());
        int tiket = kelas+trayek;
        String harga_tiket = String.valueOf(tiket);
        tharga_tiket.setText(harga_tiket);
        popup_trayek.dispose();
    }//GEN-LAST:event_tbl_trayekMouseClicked

    private void tkode_busMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkode_busMouseClicked
        // TODO add your handling code here:
        popup_bus.setVisible(true);
        popup_bus.setSize(700,420);
    }//GEN-LAST:event_tkode_busMouseClicked

    private void tkode_trayekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkode_trayekMouseClicked
        // TODO add your handling code here:
        popup_trayek.setVisible(true);
        popup_trayek.setSize(700,420);
    }//GEN-LAST:event_tkode_trayekMouseClicked

    private void bsimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bsimpanMouseClicked
        // TODO add your handling code here:
        if (tkode_bus.getText().isEmpty() || tkode_kelas.getText().isEmpty() || tkode_trayek.getText().isEmpty() || ttanggal_berangkat.getText().isEmpty() || tjam_berangkat.getText().isEmpty() || tharga_tiket.getText().isEmpty() || tdiskon.getText().isEmpty() || tkursi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!!!");
        }else{
           String sql2 = "UPDATE tbl_bus SET status_bus = 'tiket' WHERE kd_bus = '"+tkode_bus.getText()+"'"; 
//           String sql = "INSERT INTO tbl_tiket VALUES('"+kode_tiket.getText()+"','"+tkode_bus.getText()+"','"+tkode_trayek.getText()+"','"+tkode_kelas.getText()+"','"+ttanggal_berangkat.getText()+"','"+tjam_berangkat.getText()+"','"+tharga_tiket.getText()+"','"+tdiskon.getText()+"','"+tkursi.getText()+"','tersedia')";
           aksi2(sql2);
           aksi.simpan("tbl_tiket", "'"+kode_tiket.getText()+"','"+tkode_bus.getText()+"','"+tkode_trayek.getText()+"','"+tkode_kelas.getText()+"','"+ttanggal_berangkat.getText()+"','"+tjam_berangkat.getText()+"','"+tharga_tiket.getText()+"','"+tdiskon.getText()+"','"+tkursi.getText()+"','tersedia'");
           refresh();
//           aksi(sql,"Data Berhasil Disimpan");
        }
        
      
    }//GEN-LAST:event_bsimpanMouseClicked

    private void beditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beditMouseClicked
        // TODO add your handling code here:
         if (tkode_bus.getText().isEmpty() || tkode_kelas.getText().isEmpty() || tkode_trayek.getText().isEmpty() || ttanggal_berangkat.getText().isEmpty() || tjam_berangkat.getText().isEmpty() || tharga_tiket.getText().isEmpty() || tdiskon.getText().isEmpty() || tkursi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Lengkapi Data!!!");
        }else{
           String sql2 = "UPDATE tbl_bus SET status_bus = 'tersedia' WHERE kd_bus = '"+tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 2)+"' ";
//           String sql = "UPDATE tbl_tiket SET kd_bus = '"+tkode_bus.getText()+"', kd_trayek ='"+tkode_trayek.getText()+"', kd_kelas = '"+tkode_kelas.getText()+"', tanggal_berangkat = '"+ttanggal_berangkat.getText()+"', jam_berangkat = '"+tjam_berangkat.getText()+"', harga = '"+tharga_tiket.getText()+"', diskon = '"+tdiskon.getText()+"', jumlah_kursi = '"+tkursi.getText()+"' WHERE kd_tiket = '"+kode_tiket.getText()+"'";
           aksi2(sql2); 
           aksi2("UPDATE tbl_bus SET status_bus = 'tiket' WHERE kd_bus = '"+tkode_bus.getText()+"'");
           aksi.update("tbl_tiket", "kd_bus = '"+tkode_bus.getText()+"', kd_trayek ='"+tkode_trayek.getText()+"', kd_kelas = '"+tkode_kelas.getText()+"', tanggal_berangkat = '"+ttanggal_berangkat.getText()+"', jam_berangkat = '"+tjam_berangkat.getText()+"', harga = '"+tharga_tiket.getText()+"', diskon = '"+tdiskon.getText()+"', jumlah_kursi = '"+tkursi.getText()+"'", "kd_tiket = '"+kode_tiket.getText()+"'");
           refresh();
//           aksi(sql,"Data Berhasil Diubah");
        }
        
    }//GEN-LAST:event_beditMouseClicked

    private void bresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bresetMouseClicked
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_bresetMouseClicked

    private void bhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bhapusMouseClicked
        // TODO add your handling code here:
        int jawab = 0;
        if((jawab = JOptionPane.showConfirmDialog(null, "Anda yakin kan menghapus data ini ?","Konfirmasi", JOptionPane.YES_NO_OPTION))==0){
            String sql2 = "UPDATE tbl_bus SET status_bus = 'tersedia' WHERE kd_bus = '"+tabmodel.getValueAt(tbl_tiket.getSelectedRow(), 2)+"'";
            String sql = "DELETE FROM tbl_tiket WHERE kd_tiket = '"+kode_tiket.getText()+"'";
            aksi2(sql2);
            aksi.hapus("tbl_tiket", "kd_tiket = '"+kode_tiket.getText()+"'");
            refresh();
//            aksi(sql,"Data Berhasil Dihapus");
        }
    }//GEN-LAST:event_bhapusMouseClicked

    private void ttanggal_berangkatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttanggal_berangkatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_ttanggal_berangkatKeyTyped

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
            java.util.logging.Logger.getLogger(admin_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_tiket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin_tiket().setVisible(true);
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel kode_tiket;
    private javax.swing.JLabel ltanggal;
    private javax.swing.JLabel lwaktu;
    private javax.swing.JDialog popup_bus;
    private javax.swing.JDialog popup_trayek;
    private javax.swing.JTable tbl_bus;
    private javax.swing.JTable tbl_tiket;
    private javax.swing.JTable tbl_trayek;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tdiskon;
    private javax.swing.JTextField tharga_kelas;
    private javax.swing.JTextField tharga_tiket;
    private javax.swing.JTextField tharga_trayek;
    private javax.swing.JTextField tjam_berangkat;
    private javax.swing.JTextField tkode_bus;
    private javax.swing.JTextField tkode_kelas;
    private javax.swing.JTextField tkode_trayek;
    private javax.swing.JTextField tkursi;
    private javax.swing.JTextField tnama_bus;
    private javax.swing.JTextField tnama_kelas;
    private javax.swing.JTextField tnama_trayek;
    private javax.swing.JTextField tnopol_bus;
    private javax.swing.JTextField ttanggal_berangkat;
    // End of variables declaration//GEN-END:variables
}
