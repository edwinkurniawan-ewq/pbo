package com.mycompany.projectkel8;

import com.mycompany.projectkel8.controller.FungsiPresensiController;
import com.mycompany.projectkel8.controller.KaryawanController;
import com.mycompany.projectkel8.controller.PresensiController;
import com.mycompany.projectkel8.model.KaryawanModel;
import com.mycompany.projectkel8.model.PresensiModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Presensi extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(Presensi.class.getName());

    private List<KaryawanModel> listKaryawanGlobal;
    private boolean isUpdating      = false;
    private String  jabatanTerpilih = "";

    // =========================================================
    // KONSTRUKTOR
    // =========================================================
    public Presensi() {
        initComponents();

        jTextField3.setEditable(false);
        ambilDataKaryawanKeComboBox();

        FungsiPresensiController aksi = new FungsiPresensiController(this);

        // Sinkron dengan FungsiPresensiController.actionPerformed():
        // buttonKembaliDB1 = TAMBAH/SIMPAN, buttonKembaliDB2 = EDIT,
        // buttonKembaliDB4 = HAPUS,          buttonKembaliDB3 = CLEAR
        jButton2.setActionCommand("SIMPAN");          // tombol "Simpan" utama (header)
        buttonKembaliDB1.setActionCommand("SIMPAN");  // tombol "Tambah" di baris aksi
        buttonKembaliDB2.setActionCommand("EDIT");
        buttonKembaliDB4.setActionCommand("HAPUS");
        buttonKembaliDB3.setActionCommand("CLEAR");
        jButton3.setActionCommand("CETAK");

        jButton2.addActionListener(aksi);
        buttonKembaliDB1.addActionListener(aksi);
        buttonKembaliDB2.addActionListener(aksi);
        buttonKembaliDB4.addActionListener(aksi);
        buttonKembaliDB3.addActionListener(aksi);
        jButton3.addActionListener(aksi);

        buttonKembaliDB.addActionListener(e -> this.dispose());

        load_table();
    }

    // =========================================================
    // INIT COMPONENTS
    // =========================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // --- komponen yang muncul di panel (visual) ---
        jPanel1          = new javax.swing.JPanel();
        buttonKembaliDB  = new javax.swing.JButton();
        jButton2         = new javax.swing.JButton();
        jButton3         = new javax.swing.JButton();
        jLabel1          = new javax.swing.JLabel();
        jLabel2          = new javax.swing.JLabel();
        jLabel4          = new javax.swing.JLabel();
        jScrollPane1     = new javax.swing.JScrollPane();
        jTable1          = new javax.swing.JTable();
        jLabel7          = new javax.swing.JLabel();
        jLabel8          = new javax.swing.JLabel();
        jLabel3          = new javax.swing.JLabel();
        jLabel9          = new javax.swing.JLabel();
        buttonKembaliDB1 = new javax.swing.JButton();   // Tambah
        buttonKembaliDB2 = new javax.swing.JButton();   // Edit
        buttonKembaliDB3 = new javax.swing.JButton();   // Clear
        buttonKembaliDB4 = new javax.swing.JButton();   // Delete/Hapus
        jComboBox1       = new javax.swing.JComboBox<>();
        jTextField3      = new javax.swing.JTextField();
        jDateChooser1    = new com.toedter.calendar.JDateChooser();
        boxNama          = new javax.swing.JComboBox<>();
        jLabel5          = new javax.swing.JLabel();
        // CheckBox status kehadiran — ditampilkan di dalam panel
        jCheckBox1       = new javax.swing.JCheckBox();
        jCheckBox2       = new javax.swing.JCheckBox();

        // --- teks & perilaku checkbox ---
        jCheckBox1.setText("Hadir");
        jCheckBox2.setText("Absen");
        // mutual exclusive: pilih satu otomatis membatalkan yang lain
        jCheckBox1.addActionListener(e -> { if (jCheckBox1.isSelected()) jCheckBox2.setSelected(false); });
        jCheckBox2.addActionListener(e -> { if (jCheckBox2.isSelected()) jCheckBox1.setSelected(false); });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Presensi Harian Karyawan");

        // --- panel utama ---
        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        // --- tombol header ---
        buttonKembaliDB.setBackground(new java.awt.Color(153, 153, 255));
        buttonKembaliDB.setFont(new java.awt.Font("Segoe UI", 1, 14));
        buttonKembaliDB.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB.setText("Kembali");

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Simpan");

        jButton3.setBackground(new java.awt.Color(255, 153, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cetak Slip Gaji");

        // --- label ---
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PENCATATAN PRESENSI HARIAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REKAPITULASI ABSEN");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status Kehadiran :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Karyawan :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jabatan :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("(otomatis)");

        // --- tombol aksi (baris bawah form) ---
        // buttonKembaliDB1 = Tambah
        buttonKembaliDB1.setBackground(new java.awt.Color(51, 204, 51));
        buttonKembaliDB1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        buttonKembaliDB1.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB1.setText("Tambah");

        // buttonKembaliDB2 = Edit
        buttonKembaliDB2.setBackground(new java.awt.Color(255, 204, 0));
        buttonKembaliDB2.setFont(new java.awt.Font("Segoe UI", 1, 14));
        buttonKembaliDB2.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB2.setText("Edit");

        // buttonKembaliDB3 = Clear
        buttonKembaliDB3.setBackground(new java.awt.Color(153, 153, 153));
        buttonKembaliDB3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        buttonKembaliDB3.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB3.setText("Clear");

        // buttonKembaliDB4 = Hapus/Delete
        buttonKembaliDB4.setBackground(new java.awt.Color(255, 51, 51));
        buttonKembaliDB4.setFont(new java.awt.Font("Segoe UI", 1, 14));
        buttonKembaliDB4.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB4.setText("Hapus");

        // --- tabel ---
        jTable1.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID Presensi", "ID Karyawan", "Nama Karyawan", "Tanggal", "Status Kehadiran", "Durasi Lembur"}
        ));
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        // Sembunyikan kolom 0 (id_presensi) dari tampilan — tetap ada untuk referensi
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
        jScrollPane1.setViewportView(jTable1);

        // --- listener combo box ---
        boxNama.addActionListener(this::boxNamaActionPerformed);
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        // =========================================================
        // LAYOUT
        // =========================================================
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3,  javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jLabel7,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8,  javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1,    0, 200, Short.MAX_VALUE)
                            .addComponent(boxNama,       0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, 0, 150, Short.MAX_VALUE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel5))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(20, 20, 20)
                                .addComponent(jCheckBox2))
                        )
                    )
                    // Baris tombol aksi: Tambah | Edit | Hapus | Clear | Cetak | Kembali
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonKembaliDB1,  90,  90,  90)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB2,  80,  80,  80)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB4,  80,  80,  80)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB3,  80,  80,  80)
                        .addGap(10, 10, 10)
                        .addComponent(jButton3,         150, 150, 150)
                        .addGap(20, 20, 20)
                        .addComponent(buttonKembaliDB,  100, 100, 100)
                    )
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, 750, 750, 750)
                )
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(boxNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonKembaliDB1)
                    .addComponent(buttonKembaliDB2)
                    .addComponent(buttonKembaliDB4)
                    .addComponent(buttonKembaliDB3)
                    .addComponent(jButton3)
                    .addComponent(buttonKembaliDB))
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, 200, 250, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents


    // =========================================================
    // HANDLER COMBO BOX
    // =========================================================
    private void boxNamaActionPerformed(java.awt.event.ActionEvent evt) {
        if (isUpdating) return;
        String namaTerpilih = (String) boxNama.getSelectedItem();
        if (namaTerpilih == null || namaTerpilih.equals("-Pilih Nama Karyawan-")) {
            isUpdating = true;
            jComboBox1.setSelectedIndex(0);
            jTextField3.setText("");
            jabatanTerpilih = "";
            isUpdating = false;
            return;
        }
        for (KaryawanModel k : listKaryawanGlobal) {
            if (k.getNamaKaryawan().equals(namaTerpilih)) {
                isUpdating = true;
                jComboBox1.setSelectedItem(k.getIdKaryawan());
                jTextField3.setText(k.getJabatan());
                jabatanTerpilih = k.getJabatan();
                isUpdating = false;
                break;
            }
        }
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (isUpdating) return;
        String idTerpilih = (String) jComboBox1.getSelectedItem();
        if (idTerpilih == null || idTerpilih.equals("-Pilih ID Karyawan-")) {
            isUpdating = true;
            boxNama.setSelectedIndex(0);
            jTextField3.setText("");
            jabatanTerpilih = "";
            isUpdating = false;
            return;
        }
        for (KaryawanModel k : listKaryawanGlobal) {
            if (k.getIdKaryawan().equals(idTerpilih)) {
                isUpdating = true;
                boxNama.setSelectedItem(k.getNamaKaryawan());
                jTextField3.setText(k.getJabatan());
                jabatanTerpilih = k.getJabatan();
                isUpdating = false;
                break;
            }
        }
    }

    // =========================================================
    // LOAD TABLE
    // Urutan kolom WAJIB sesuai FungsiPresensiController:
    //   0=id_presensi (hidden), 1=id_karyawan, 2=nama_karyawan,
    //   3=tanggal, 4=status_kehadiran, 5=durasi_lembur
    // =========================================================
    public void load_table() {
        DefaultTableModel model = new DefaultTableModel(new Object[]{}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        model.addColumn("ID Presensi");       // kolom 0 — hidden
        model.addColumn("ID Karyawan");       // kolom 1
        model.addColumn("Nama Karyawan");     // kolom 2
        model.addColumn("Tanggal");           // kolom 3
        model.addColumn("Status Kehadiran");  // kolom 4
        model.addColumn("Durasi Lembur");     // kolom 5

        try {
            PresensiController ctrl = new PresensiController();
            List<PresensiModel> list = ctrl.getAllPresensi();
            for (PresensiModel p : list) {
                model.addRow(new Object[]{
                    p.getIdPresensi(),
                    p.getIdKaryawan(),
                    p.getNamaKaryawan(),
                    p.getTanggal(),
                    p.getStatusKehadiran(),
                    p.getDurasiLembur() + " jam"
                });
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Gagal memuat data dari database:\n" + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        jTable1.setModel(model);
        // Sembunyikan kolom id_presensi dari tampilan
        jTable1.getColumnModel().getColumn(0).setMinWidth(0);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable1.getColumnModel().getColumn(0).setWidth(0);
    }

    // =========================================================
    // ISI FORM DARI TABEL (dipanggil oleh FungsiPresensiController.eksekusiEdit)
    // =========================================================
    public void isiFormDariTabel(String idKaryawan, String nama,
                                  String tanggal, String statusKehadiran) {
        isUpdating = true;
        jComboBox1.setSelectedItem(idKaryawan);
        boxNama.setSelectedItem(nama);
        isUpdating = false;

        // Isi jabatan sesuai karyawan yang dipilih
        jComboBox1ActionPerformed(null);

        // Isi tanggal
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jDateChooser1.setDate(sdf.parse(tanggal));
        } catch (Exception ex) {
            jDateChooser1.setDate(null);
        }

        // Set status kehadiran — "Hadir" atau "Absen"
        jCheckBox1.setSelected(statusKehadiran.equalsIgnoreCase("Hadir"));
        jCheckBox2.setSelected(statusKehadiran.equalsIgnoreCase("Absen"));
    }

    public void resetTanggal() {
        jDateChooser1.setDate(null);
    }

    public String getTanggalDipilih() {
        Date date = jDateChooser1.getDate();
        if (date == null) return null;
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public String getJabatanTerpilih() {
        return jabatanTerpilih;
    }

    // =========================================================
    // GETTER KOMPONEN (digunakan oleh FungsiPresensiController)
    // =========================================================
    public javax.swing.JComboBox<String> getjComboBox1() { return jComboBox1; }
    public javax.swing.JComboBox<String> getboxNama()    { return boxNama; }
    public javax.swing.JCheckBox getjCheckBox1()         { return jCheckBox1; }
    public javax.swing.JCheckBox getjCheckBox2()         { return jCheckBox2; }
    public javax.swing.JTable    getjTable1()            { return jTable1; }

    // =========================================================
    // PRIVATE HELPER
    // =========================================================
    private void ambilDataKaryawanKeComboBox() {
        isUpdating = true;
        try {
            jComboBox1.removeAllItems();
            boxNama.removeAllItems();
            jComboBox1.addItem("-Pilih ID Karyawan-");
            boxNama.addItem("-Pilih Nama Karyawan-");

            KaryawanController karyawanCtrl = new KaryawanController();
            listKaryawanGlobal = karyawanCtrl.getAllKaryawan();
            for (KaryawanModel k : listKaryawanGlobal) {
                jComboBox1.addItem(k.getIdKaryawan());
                boxNama.addItem(k.getNamaKaryawan());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Gagal memuat data karyawan:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            isUpdating = false;
        }
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Presensi().setVisible(true));
    }
        private void buttonKembaliDBActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void buttonKembaliDB1ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void buttonKembaliDB2ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void buttonKembaliDB3ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void buttonKembaliDB4ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        }
        
        

    // =========================================================
    // DEKLARASI VARIABEL
    // =========================================================
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxNama;
    private javax.swing.JButton buttonKembaliDB;
    private javax.swing.JButton buttonKembaliDB1;  // Tambah
    private javax.swing.JButton buttonKembaliDB2;  // Edit
    private javax.swing.JButton buttonKembaliDB3;  // Clear
    private javax.swing.JButton buttonKembaliDB4;  // Hapus
    private javax.swing.JButton jButton2;          // Simpan (alternatif)
    private javax.swing.JButton jButton3;          // Cetak Slip Gaji
    private javax.swing.JCheckBox jCheckBox1;      // Hadir
    private javax.swing.JCheckBox jCheckBox2;      // Absen
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
