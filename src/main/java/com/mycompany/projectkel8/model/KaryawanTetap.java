
package com.mycompany.projectkel8.model;

import com.mycompany.projectkel8.model.Karyawan;

public class KaryawanTetap extends Karyawan {

    public Double gajiPokok;

    @Override
    public double hitungGaji() {
        return gajiPokok + tunjangan;
    }

    @Override
    public Double hitungPajak() {
        return gajiPokok * 0.05;
    }
    
    @Override
    public double getGajiPokok() {
    return gajiPokok;
}
}
