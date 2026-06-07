/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.model;

/**
 *
 * @author fshlyf
 */
public class Departemen {
    public String idDepartemen;
    public String namaDepartemen;

    public void tambahKaryawan(Karyawan kKaryawan) {
        // Logika internal kelompok jika dibutuhkan, 
        // minimal method ini ada agar sesuai struktur UML
        System.out.println("Karyawan " + kKaryawan.nama + " dimasukkan ke departemen " + namaDepartemen);
    }
}