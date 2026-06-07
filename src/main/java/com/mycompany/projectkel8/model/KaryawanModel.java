/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.model;

public class KaryawanModel {
    private String idKaryawan;
    private String namaKaryawan;
    private String jabatan;
    private String departemen;
    private String jenisKaryawan;
    private double gajiPokok;
    private double tunjangan;

    public KaryawanModel() {}

    public KaryawanModel(String id, String nama, String jabatan, String departemen, String jenis, double gaji, double tunjangan) {
        this.idKaryawan = id;
        this.namaKaryawan = nama;
        this.jabatan = jabatan;
        this.departemen = departemen;
        this.jenisKaryawan = jenis;
        this.gajiPokok = gaji;
        this.tunjangan = tunjangan;
    }

    public String getIdKaryawan() { return idKaryawan; }
    public void setIdKaryawan(String idKaryawan) { this.idKaryawan = idKaryawan; }
    
    public String getNamaKaryawan() { return namaKaryawan; }
    public void setNamaKaryawan(String namaKaryawan) { this.namaKaryawan = namaKaryawan; }
    
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
    
    public String getDepartemen() { return departemen; }
    public void setDepartemen(String departemen) { this.departemen = departemen; }
    
    public String getJenisKaryawan() { return jenisKaryawan; }
    public void setJenisKaryawan(String jenisKaryawan) { this.jenisKaryawan = jenisKaryawan; }
    
    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) { this.gajiPokok = gajiPokok; }
    
    public double getTunjangan() { return tunjangan; }
    public void setTunjangan(double tunjangan) { this.tunjangan = tunjangan; }
}
