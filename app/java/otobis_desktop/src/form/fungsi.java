/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author JamilahLKM
 */
public class fungsi {
    public Statement st;
    public ResultSet rs;
    public Connection cn = config.koneksi.getKoneksi();
    
    public void simpan(String table, String field){
        try {
            st = cn.createStatement();
            String sql = "INSERT INTO "+table+" VALUES("+field+")";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void update(String table, String field, String where){
        try {
            st = cn.createStatement();
            String sql = "UPDATE "+table+" SET "+field+" WHERE "+where+" ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hapus(String table, String where){
        try {
            st = cn.createStatement();
            String sql = "DELETE FROM "+table+" WHERE "+where+" ";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
