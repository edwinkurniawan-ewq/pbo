package com.mycompany.projectkel8.model;

public class PresensiModel {
    private int    idPresensi;
    private String idKaryawan;
    private String namaKaryawan;
    private String tanggal;          // Format: YYYY-MM-DD
    private String statusKehadiran;  // "Hadir" atau "Absen"
    private double durasiLembur;     // Dalam jam, boleh 0

    public PresensiModel() {}

    public PresensiModel(int idPresensi, String idKaryawan, String namaKaryawan,
                         String tanggal, String statusKehadiran, double durasiLembur) {
        this.idPresensi      = idPresensi;
        this.idKaryawan      = idKaryawan;
        this.namaKaryawan    = namaKaryawan;
        this.tanggal         = tanggal;
        this.statusKehadiran = statusKehadiran;
        this.durasiLembur    = durasiLembur;
    }

    // Constructor tanpa idPresensi (untuk INSERT baru)
    public PresensiModel(String idKaryawan, String namaKaryawan,
                         String tanggal, String statusKehadiran, double durasiLembur) {
        this.idKaryawan      = idKaryawan;
        this.namaKaryawan    = namaKaryawan;
        this.tanggal         = tanggal;
        this.statusKehadiran = statusKehadiran;
        this.durasiLembur    = durasiLembur;
    }

    public int    getIdPresensi()              { return idPresensi; }
    public void   setIdPresensi(int v)         { this.idPresensi = v; }

    public String getIdKaryawan()              { return idKaryawan; }
    public void   setIdKaryawan(String v)      { this.idKaryawan = v; }

    public String getNamaKaryawan()            { return namaKaryawan; }
    public void   setNamaKaryawan(String v)    { this.namaKaryawan = v; }

    public String getTanggal()                 { return tanggal; }
    public void   setTanggal(String v)         { this.tanggal = v; }

    public String getStatusKehadiran()         { return statusKehadiran; }
    public void   setStatusKehadiran(String v) { this.statusKehadiran = v; }

    public double getDurasiLembur()            { return durasiLembur; }
    public void   setDurasiLembur(double v)    { this.durasiLembur = v; }
}
