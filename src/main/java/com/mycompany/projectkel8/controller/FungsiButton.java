package com.mycompany.projectkel8.controller;

import com.mycompany.projectkel8.dashboard;
import com.mycompany.projectkel8.model.KaryawanModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class FungsiButton implements ActionListener {
    
    private final dashboard view;
    private final KaryawanController dbControl;

    // Constructor menerima objek dashboard agar bisa membaca & mengubah komponen UI
    public FungsiButton(dashboard view) {
        this.view = view;
        this.dbControl = new KaryawanController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Mengarahkan ActionCommand tombol ke method eksekusi masing-masing
        switch (command) {
            case "TAMBAH":
                eksekusiTambah();
                break;
            case "EDIT":
                eksekusiEdit();
                break;
            case "HAPUS":
                eksekusiHapus();
                break;
            case "RESET":
                eksekusiReset();
                break;
            case "REFRESH":
                eksekusiRefresh();
                break;
            default:
                break;
        }
    }

    private void eksekusiTambah() {
        String id = view.getInputId().getText().trim();
        String nama = view.getInputNama().getText().trim();
        String jabatan = (String) view.getBoxJabatan().getSelectedItem();
        String departemen = (String) view.getjComboBox2().getSelectedItem();
        String gajiText = view.getInputGajiPokok().getText().trim();
        String tunjanganText = view.getInputTunjangan().getText().trim();

        // Validasi
        if (id.isEmpty() || id.equals("Input id")) {
            JOptionPane.showMessageDialog(view, "ID Karyawan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (nama.isEmpty() || nama.equals("Input nama")) {
            JOptionPane.showMessageDialog(view, "Nama Karyawan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jabatan == null || jabatan.equals("--Jabatan--")) {
            JOptionPane.showMessageDialog(view, "Pilih Jabatan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (departemen == null || departemen.equals("--Departemen--")) {
            JOptionPane.showMessageDialog(view, "Pilih Departemen terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!view.getKaryawanTetap().isSelected() && !view.getKaryawanKontak().isSelected()) {
            JOptionPane.showMessageDialog(view, "Pilih Jenis Karyawan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String jenisKaryawan = view.getKaryawanTetap().isSelected() ? "Tetap" : "Kontrak";
        double gajiPokok = 0;
        double tunjangan = 0;

        try {
            gajiPokok = gajiText.replaceAll("[^0-9]", "").isEmpty() ? 0 : Double.parseDouble(gajiText.replaceAll("[^0-9]", ""));
            if (view.getInputTunjangan().isEnabled() && !tunjanganText.replaceAll("[^0-9]", "").isEmpty()) {
                tunjangan = Double.parseDouble(tunjanganText.replaceAll("[^0-9]", ""));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Format Gaji atau Tunjangan tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        KaryawanModel karyawan = new KaryawanModel(id, nama, jabatan, departemen, jenisKaryawan, gajiPokok, tunjangan);

        try {
            if (dbControl.tambahKaryawan(karyawan)) {
                JOptionPane.showMessageDialog(view, "Data karyawan berhasil ditambahkan!", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
                view.load_table();
                eksekusiReset();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal menyimpan ke database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eksekusiEdit() {
        String id = view.getInputId().getText().trim();
        String nama = view.getInputNama().getText().trim();
        String jabatan = (String) view.getBoxJabatan().getSelectedItem();
        String departemen = (String) view.getjComboBox2().getSelectedItem();

        // VALIDASI UTAMA
        if (id.equals("Input id") || id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Silakan pilih data pada tabel yang ingin diubah terlebih dahulu!");
            return;
        }
        if (nama.isEmpty() || nama.equals("Input nama")) {
            JOptionPane.showMessageDialog(view, "Nama Karyawan tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (jabatan == null || jabatan.equals("--Jabatan--")) {
            JOptionPane.showMessageDialog(view, "Pilih Jabatan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (departemen == null || departemen.equals("--Departemen--")) {
            JOptionPane.showMessageDialog(view, "Pilih Departemen terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!view.getKaryawanTetap().isSelected() && !view.getKaryawanKontak().isSelected()) {
            JOptionPane.showMessageDialog(view, "Pilih Jenis Karyawan terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String gajiClean = view.getInputGajiPokok().getText().replaceAll("[^0-9]", "");
            String tunjanganClean = view.getInputTunjangan().getText().replaceAll("[^0-9]", "");
            
            double gaji = gajiClean.isEmpty() ? 0 : Double.parseDouble(gajiClean);
            double tunjangan = tunjanganClean.isEmpty() ? 0 : Double.parseDouble(tunjanganClean);
            String jenis = view.getKaryawanTetap().isSelected() ? "Tetap" : "Kontrak";
            
            KaryawanModel karyawan = new KaryawanModel(id, nama, jabatan, departemen, jenis, gaji, tunjangan);
            
            if (dbControl.updateKaryawan(karyawan)) {
                JOptionPane.showMessageDialog(view, "Data Karyawan Berhasil Diperbarui!");
                view.load_table(); 
                eksekusiReset(); 
            } else {
                JOptionPane.showMessageDialog(view, "Gagal memperbarui, ID Karyawan tidak ditemukan.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Gagal mengubah data: " + ex.getMessage());
        }
    }

    private void eksekusiHapus() {
        String id = view.getInputId().getText().trim();
        if (id.equals("Input id") || id.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Silakan pilih data pada tabel terlebih dahulu!");
            return;
        }
        
        int konfirmasi = JOptionPane.showConfirmDialog(view, 
                "Apakah Anda yakin ingin menghapus karyawan dengan ID " + id + "?", 
                "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
                
        if (konfirmasi == JOptionPane.YES_OPTION) {
            try {
                if (dbControl.hapusKaryawan(id)) {
                    JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus!");
                    view.load_table();
                    eksekusiReset(); 
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Gagal menghapus data: " + ex.getMessage());
            }
        }
    }

    public void eksekusiReset() {
        view.getInputId().setText("Input id");
        view.getInputId().setForeground(new java.awt.Color(204, 204, 204));
        view.getInputNama().setText("Input nama");
        view.getInputNama().setForeground(new java.awt.Color(204, 204, 204));
        view.getBoxJabatan().setSelectedIndex(0);
        view.getjComboBox2().setSelectedIndex(0);
        view.getJenisKaryawan().clearSelection();
        view.getInputGajiPokok().setText("");
        view.getInputTunjangan().setText("");
        view.getInputTunjangan().setEnabled(true);
        view.getInputTunjangan().setBackground(java.awt.Color.WHITE);
    }

    private void eksekusiRefresh() {
        view.load_table();
        JOptionPane.showMessageDialog(view, "Seluruh data (" + view.getjTable1().getRowCount() + " karyawan)", "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }
}