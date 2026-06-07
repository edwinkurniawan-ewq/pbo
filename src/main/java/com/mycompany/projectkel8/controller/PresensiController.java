package com.mycompany.projectkel8.controller;

import com.mycompany.projectkel8.model.PresensiModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PresensiController {

    // =========================================================
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/SistemGajiDanPresensi"
                                        + "?useSSL=false&serverTimezone=Asia/Makassar";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL tidak ditemukan. "
                + "Pastikan mysql-connector-j sudah ada di classpath.", e);
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }
    public List<PresensiModel> getAllPresensi() throws SQLException {
        List<PresensiModel> list = new ArrayList<>();

        String sql = "SELECT id_presensi, id_karyawan, nama_karyawan, "
                   + "       tanggal, status_kehadiran, durasi_lembur "
                   + "FROM   presensi "
                   + "ORDER  BY tanggal DESC";

        try (Connection      conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet         rs = ps.executeQuery()) {

            while (rs.next()) {
                PresensiModel p = new PresensiModel(
                    rs.getInt   ("id_presensi"),
                    rs.getString("id_karyawan"),
                    rs.getString("nama_karyawan"),
                    rs.getString("tanggal"),
                    rs.getString("status_kehadiran"),
                    rs.getDouble("durasi_lembur")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            System.err.println("[PresensiController] getAllPresensi() GAGAL: " + e.getMessage());
            throw e;
        }

        return list;
    }

    public boolean tambahPresensi(PresensiModel p) throws SQLException {
        // Cek duplikat terlebih dahulu
        if (sudahPresensiHariIni(p.getIdKaryawan(), p.getTanggal())) {
            throw new SQLException(
                "Karyawan dengan ID \"" + p.getIdKaryawan() + "\" sudah melakukan "
                + "presensi pada tanggal " + p.getTanggal() + ".");
        }

        String sql = "INSERT INTO presensi "
                   + "    (id_karyawan, nama_karyawan, tanggal, status_kehadiran, durasi_lembur) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection        conn = getConnection();
             PreparedStatement ps   = conn.prepareStatement(sql)) {

            ps.setString(1, p.getIdKaryawan());
            ps.setString(2, p.getNamaKaryawan());
            ps.setString(3, p.getTanggal());
            ps.setString(4, p.getStatusKehadiran());
            ps.setDouble(5, p.getDurasiLembur());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[PresensiController] tambahPresensi() GAGAL: " + e.getMessage());
            throw e;
        }
    }

    public boolean updatePresensi(PresensiModel p) throws SQLException {
        String sql = "UPDATE presensi "
                   + "SET    id_karyawan      = ?, "
                   + "       nama_karyawan    = ?, "
                   + "       tanggal          = ?, "
                   + "       status_kehadiran = ?, "
                   + "       durasi_lembur    = ? "
                   + "WHERE  id_presensi      = ?";

        try (Connection        conn = getConnection();
             PreparedStatement ps   = conn.prepareStatement(sql)) {

            ps.setString(1, p.getIdKaryawan());
            ps.setString(2, p.getNamaKaryawan());
            ps.setString(3, p.getTanggal());
            ps.setString(4, p.getStatusKehadiran());
            ps.setDouble(5, p.getDurasiLembur());
            ps.setInt   (6, p.getIdPresensi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[PresensiController] updatePresensi() GAGAL: " + e.getMessage());
            throw e;
        }
    }

    boolean hapusPresensi(int idPresensi) throws SQLException {
        String sql = "DELETE FROM presensi WHERE id_presensi = ?";

        try (Connection        conn = getConnection();
             PreparedStatement ps   = conn.prepareStatement(sql)) {

            ps.setInt(1, idPresensi);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("[PresensiController] hapusPresensi() GAGAL: " + e.getMessage());
            throw e;
        }
    }


    private boolean sudahPresensiHariIni(String idKaryawan, String tanggal) throws SQLException {
        String sql = "SELECT COUNT(*) FROM presensi "
                   + "WHERE id_karyawan = ? AND tanggal = ?";

        try (Connection        conn = getConnection();
             PreparedStatement ps   = conn.prepareStatement(sql)) {

            ps.setString(1, idKaryawan);
            ps.setString(2, tanggal);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
