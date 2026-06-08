/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectkel8.model;
import java.util.Date;

public class SlipGaji {

    public String idSlip;
    public Date tanggalCetak;

    public Double totalPotongan;
    public Double totalBonus;

    public Karyawan karyawan;

    public Double hitungTakeHomePay() {

        return karyawan.hitungGaji()
                + totalBonus
                - totalPotongan;
    }

    public void cetakSlip() {

        System.out.println(
            "Slip Gaji "
            + idSlip
            + " berhasil dicetak"
        );
    }
}