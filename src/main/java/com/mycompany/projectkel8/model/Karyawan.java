/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.model;

import com.mycompany.projectkel8.model.Absensi;

/**
 *
 * @author fshlyf
 */
public class Karyawan implements Pajak {
    public int idKaryawan;
    public String nama;
    public String posisi;
    public Absensi[] absensi; // Agregasi 1 ke banyak (array) sesuai gambar UML

    public double hitungGaji() {
        return 0.0; // Bakal di-override secara detail oleh class anak di bawah
    }

    @Override
    public Double hitungPajak() {
        return 0.0; // Bakal di-override secara detail oleh class anak di bawah
    }
}
