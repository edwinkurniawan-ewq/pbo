/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.model;

import com.mycompany.projectkel8.model.Karyawan;

/**
 *
 * @author fshlyf
 */
public class KaryawanKontrak extends Karyawan {
    public Double gajiPokok;
    public Double tunjangan;

    @Override
    public double hitungGaji() {
        // Logika dasar karyawan kontrak
        return this.gajiPokok + this.tunjangan;
    }

    @Override
    public Double hitungPajak() {
        // Misal: Pajak Karyawan Kontrak lebih kecil, cuma 2% dari Gaji Pokok
        return this.gajiPokok * 0.02;
    }
}
