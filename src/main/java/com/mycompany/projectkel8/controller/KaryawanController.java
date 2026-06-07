/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.controller;

import com.mycompany.projectkel8.model.KaryawanModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KaryawanController {
    
    private Connection configDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/SistemGajiDanPresensi"; 
            String user = "root"; 
            String pass = "";     
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL tidak ditemukan!", e);
        }
    }

    public List<KaryawanModel> getAllKaryawan() throws SQLException {
        List<KaryawanModel> list = new ArrayList<>();
        String sql = "SELECT * FROM karyawan";
        
        try (Connection conn = configDB();
             Statement stm = conn.createStatement();
             ResultSet res = stm.executeQuery(sql)) {
            
            while(res.next()){
                KaryawanModel k = new KaryawanModel(
                    res.getString("id_karyawan"),
                    res.getString("nama_karyawan"),
                    res.getString("jabatan"),
                    res.getString("departemen"),
                    res.getString("jenis_karyawan"),
                    res.getDouble("gaji_pokok"),
                    res.getDouble("tunjangan")
                );
                list.add(k);
            }
        }
        return list;
    }

    public boolean tambahKaryawan(KaryawanModel karyawan) throws SQLException {
        String sql = "INSERT INTO karyawan (id_karyawan, nama_karyawan, jabatan, departemen, jenis_karyawan, gaji_pokok, tunjangan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = configDB(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, karyawan.getIdKaryawan());
            pst.setString(2, karyawan.getNamaKaryawan());
            pst.setString(3, karyawan.getJabatan());
            pst.setString(4, karyawan.getDepartemen());
            pst.setString(5, karyawan.getJenisKaryawan());
            pst.setDouble(6, karyawan.getGajiPokok());
            pst.setDouble(7, karyawan.getTunjangan());
            
            return pst.executeUpdate() > 0;
        }
    }

    public boolean updateKaryawan(KaryawanModel karyawan) throws SQLException {
        String sql = "UPDATE karyawan SET nama_karyawan = ?, jabatan = ?, departemen = ?, jenis_karyawan = ?, gaji_pokok = ?, tunjangan = ? WHERE id_karyawan = ?";
        try (Connection conn = configDB(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, karyawan.getNamaKaryawan());
            pst.setString(2, karyawan.getJabatan());
            pst.setString(3, karyawan.getDepartemen());
            pst.setString(4, karyawan.getJenisKaryawan());
            pst.setDouble(5, karyawan.getGajiPokok());
            pst.setDouble(6, karyawan.getTunjangan());
            pst.setString(7, karyawan.getIdKaryawan());
            
            return pst.executeUpdate() > 0;
        }
    }

    public boolean hapusKaryawan(String id) throws SQLException {
        String sql = "DELETE FROM karyawan WHERE id_karyawan = ?";
        try (Connection conn = configDB(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        }
    }
}