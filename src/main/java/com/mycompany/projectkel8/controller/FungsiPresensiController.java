package com.mycompany.projectkel8.controller;

import com.mycompany.projectkel8.Presensi;
import com.mycompany.projectkel8.model.PresensiModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class FungsiPresensiController implements ActionListener {

    private final Presensi view;
    private final PresensiController dbControl;

    // FIX #4 — id_presensi sekarang langsung dibaca dari kolom 0 tabel
    // Tidak perlu lagi query ulang getAllPresensi() untuk cari id_presensi
    private int     idPresensiEdit = -1;
    private boolean modeEdit       = false;

    public FungsiPresensiController(Presensi view) {
        this.view      = view;
        this.dbControl = new PresensiController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SIMPAN": eksekusiSimpan(); break;
            case "EDIT":   eksekusiEdit();   break;
            case "HAPUS":  eksekusiHapus();  break;
            case "CLEAR":  eksekusiClear();  break;
            case "CETAK":  eksekusiCetak();  break;
        }
    }

    // -----------------------------------------------------------
    // SIMPAN — insert baru atau update tergantung modeEdit
    // -----------------------------------------------------------
    private void eksekusiSimpan() {
        String id      = (String) view.getjComboBox1().getSelectedItem();
        String nama    = (String) view.getboxNama().getSelectedItem();
        String tanggal = view.getTanggalDipilih();

        if (id == null || id.equals("-Pilih ID Karyawan-")) {
            tampilPeringatan("Silakan pilih ID Karyawan!");
            return;
        }
        if (nama == null || nama.equals("-Pilih Nama Karyawan-")) {
            tampilPeringatan("Silakan pilih Nama Karyawan!");
            return;
        }
        if (tanggal == null || tanggal.isEmpty()) {
            tampilPeringatan("Silakan pilih Tanggal!");
            return;
        }
        if (!view.getjCheckBox1().isSelected() && !view.getjCheckBox2().isSelected()) {
            tampilPeringatan("Silakan pilih status kehadiran (Hadir / Absen)!");
            return;
        }

        // FIX #2 — status sekarang "Hadir" / "Absen", bukan "Masuk" / "Izin"
        String statusKehadiran = view.getjCheckBox1().isSelected() ? "Hadir" : "Absen";
        double durasiLembur    = 0.0; // Default 0, bisa diperluas dengan field lembur jika ada

        PresensiModel presensi = new PresensiModel(id, nama, tanggal, statusKehadiran, durasiLembur);

        try {
            boolean berhasil;
            if (modeEdit) {
                presensi.setIdPresensi(idPresensiEdit);
                berhasil = dbControl.updatePresensi(presensi);
                if (berhasil) JOptionPane.showMessageDialog(view, "Data presensi berhasil diperbarui!");
            } else {
                berhasil = dbControl.tambahPresensi(presensi);
                if (berhasil) JOptionPane.showMessageDialog(view, "Presensi berhasil disimpan!");
            }

            if (berhasil) {
                view.load_table();
                eksekusiClear();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view,
                "Gagal menyimpan presensi:\n" + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // -----------------------------------------------------------
    // EDIT — baca data dari baris tabel yang dipilih
    //
    // Urutan kolom tabel (wajib sama dengan load_table() di Presensi.java):
    //   kolom 0 = id_presensi  ← langsung ambil dari sini, tidak perlu query ulang
    //   kolom 1 = id_karyawan
    //   kolom 2 = nama_karyawan
    //   kolom 3 = tanggal
    //   kolom 4 = status_kehadiran
    //   kolom 5 = durasi_lembur
    // -----------------------------------------------------------
    private void eksekusiEdit() {
        int baris = view.getjTable1().getSelectedRow();
        if (baris < 0) {
            tampilPeringatan("Pilih baris yang ingin diedit terlebih dahulu!");
            return;
        }

        // FIX #4 — id_presensi langsung dari kolom 0, tidak ada query ulang
        idPresensiEdit = Integer.parseInt(nilaiTabel(baris, 0));
        String idKaryawan      = nilaiTabel(baris, 1);
        String namaKaryawan    = nilaiTabel(baris, 2);
        String tanggal         = nilaiTabel(baris, 3);
        String statusKehadiran = nilaiTabel(baris, 4);

        // FIX #1 — signature isiFormDariTabel sekarang pakai "statusKehadiran"
        view.isiFormDariTabel(idKaryawan, namaKaryawan, tanggal, statusKehadiran);
        modeEdit = true;

        JOptionPane.showMessageDialog(view,
            "Mode EDIT aktif. Ubah data lalu klik SIMPAN.",
            "Mode Edit", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------
    // HAPUS — konfirmasi lalu hapus baris terpilih
    // -----------------------------------------------------------
    private void eksekusiHapus() {
        int baris = view.getjTable1().getSelectedRow();
        if (baris < 0) {
            tampilPeringatan("Pilih baris yang ingin dihapus terlebih dahulu!");
            return;
        }

        // FIX #4 — id_presensi langsung dari kolom 0
        int    idPresensiHapus = Integer.parseInt(nilaiTabel(baris, 0));
        String namaKaryawan    = nilaiTabel(baris, 2);
        String tanggal         = nilaiTabel(baris, 3);

        int konfirmasi = JOptionPane.showConfirmDialog(view,
            "Hapus presensi milik " + namaKaryawan + " pada tanggal " + tanggal + "?",
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                if (dbControl.hapusPresensi(idPresensiHapus)) {
                    JOptionPane.showMessageDialog(view, "Data berhasil dihapus!");
                    view.load_table();
                    eksekusiClear();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view,
                    "Gagal menghapus data:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // -----------------------------------------------------------
    // CETAK — preview slip dari baris terpilih
    // -----------------------------------------------------------
    private void eksekusiCetak() {
        int baris = view.getjTable1().getSelectedRow();
        if (baris < 0) {
            tampilPeringatan("Pilih karyawan dari tabel terlebih dahulu untuk mencetak slip!");
            return;
        }

        String idKaryawan      = nilaiTabel(baris, 1);
        String namaKaryawan    = nilaiTabel(baris, 2);
        String tanggal         = nilaiTabel(baris, 3);
        String statusKehadiran = nilaiTabel(baris, 4);
        String durasiLembur    = nilaiTabel(baris, 5);

        String slip = "============================\n"
                    + "        SLIP PRESENSI       \n"
                    + "============================\n"
                    + "ID Karyawan  : " + idKaryawan      + "\n"
                    + "Nama         : " + namaKaryawan    + "\n"
                    + "Tanggal      : " + tanggal         + "\n"
                    + "Status       : " + statusKehadiran + "\n"
                    + "Lembur       : " + durasiLembur    + "\n"
                    + "============================\n"
                    + "Dicetak pada : " + LocalDate.now() + "\n"
                    + "============================\n";

        JOptionPane.showMessageDialog(view, slip, "Preview Slip", JOptionPane.INFORMATION_MESSAGE);
    }

    // -----------------------------------------------------------
    // CLEAR — reset semua field form
    // -----------------------------------------------------------
    public void eksekusiClear() {
        view.getjComboBox1().setSelectedIndex(0);
        view.getboxNama().setSelectedIndex(0);
        view.getjCheckBox1().setSelected(false);
        view.getjCheckBox2().setSelected(false);
        view.resetTanggal();
        modeEdit       = false;
        idPresensiEdit = -1;
    }

    // -----------------------------------------------------------
    // HELPER
    // -----------------------------------------------------------
    private String nilaiTabel(int baris, int kolom) {
        Object val = view.getjTable1().getValueAt(baris, kolom);
        return val != null ? val.toString() : "";
    }

    private void tampilPeringatan(String pesan) {
        JOptionPane.showMessageDialog(view, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}