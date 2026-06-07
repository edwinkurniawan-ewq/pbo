
package com.mycompany.projectkel8.model;
import java.util.Date;

public class Absensi {
    public Date tanggal;
    public Boolean statusHadir;
    public Boolean lembur;

    public Boolean[] catatHadir(Boolean statusHadir, Boolean lembur) {
        this.statusHadir = statusHadir;
        this.lembur = lembur;
        // Mengembalikan array boolean sesuai tipe data di UML kamu
        return new Boolean[]{this.statusHadir, this.lembur};
    }
}
