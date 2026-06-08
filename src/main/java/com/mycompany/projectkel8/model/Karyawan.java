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

    public String idKaryawan;
    public String nama;
    public String posisi;

    public double getGajiPokok() {
    return 0;
}
    public Double tunjangan;

    public Absensi[] absensi;

    public double hitungGaji() {
        return 0.0;
    }

    @Override
    public Double hitungPajak() {
        return 0.0;
    }
}