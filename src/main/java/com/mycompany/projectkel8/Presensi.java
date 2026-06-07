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

        buttonKembaliDB.addActionListener(e -> {
            new dashboard().setVisible(true);
            this.dispose();
        });

        load_table();
    }

    // =========================================================
    // INIT COMPONENTS
    // =========================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonKembaliDB = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        boxNama = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        buttonKembaliDB1 = new javax.swing.JButton();
        buttonKembaliDB2 = new javax.swing.JButton();
        buttonKembaliDB3 = new javax.swing.JButton();
        buttonKembaliDB4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presensi Harian Karyawan");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        buttonKembaliDB.setBackground(new java.awt.Color(153, 153, 255));
        buttonKembaliDB.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembaliDB.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB.setText("Kembali");
        buttonKembaliDB.addActionListener(this::buttonKembaliDBActionPerformed);

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Simpan");
        jButton2.addActionListener(this::jButton2ActionPerformed);

        jButton3.setBackground(new java.awt.Color(255, 153, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cetak Slip Gaji");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("PENCATATAN PRESENSI HARIAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanggal :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID Karyawan :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("REKAPITULASI ABSEN");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("(otomatis)");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nama :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Status Kehadiran :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Jabatan :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Presensi", "ID Karyawan", "Nama Karyawan", "Tanggal", "Status Kehadiran", "Durasi Lembur"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih ID Karyawan-" }));
        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);

        boxNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Nama Karyawan-" }));
        boxNama.addActionListener(this::boxNamaActionPerformed);

        jTextField3.setEditable(false);

        jCheckBox1.setText("Hadir");
        jCheckBox1.addActionListener(this::jCheckBox1ActionPerformed);

        jCheckBox2.setText("Absen");
        jCheckBox2.addActionListener(this::jCheckBox2ActionPerformed);

        buttonKembaliDB1.setBackground(new java.awt.Color(51, 204, 51));
        buttonKembaliDB1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembaliDB1.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB1.setText("Tambah");
        buttonKembaliDB1.addActionListener((evt) -> {
            buttonKembaliDB1ActionPerformed(evt);
            tesTambah(evt);
        });

        buttonKembaliDB2.setBackground(new java.awt.Color(255, 204, 0));
        buttonKembaliDB2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembaliDB2.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB2.setText("Edit");
        buttonKembaliDB2.addActionListener(this::buttonKembaliDB2ActionPerformed);

        buttonKembaliDB3.setBackground(new java.awt.Color(153, 153, 153));
        buttonKembaliDB3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembaliDB3.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB3.setText("Clear");
        buttonKembaliDB3.addActionListener(this::buttonKembaliDB3ActionPerformed);

        buttonKembaliDB4.setBackground(new java.awt.Color(255, 51, 51));
        buttonKembaliDB4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonKembaliDB4.setForeground(new java.awt.Color(255, 255, 255));
        buttonKembaliDB4.setText("Hapus");
        buttonKembaliDB4.addActionListener(this::buttonKembaliDB4ActionPerformed);

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
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel2)
                            .addComponent(jLabel8))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(boxNama, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel5))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addGap(20, 20, 20)
                                .addComponent(jCheckBox2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonKembaliDB1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(buttonKembaliDB3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(buttonKembaliDB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tesTambah(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tesTambah
        // TODO add your handling code here:
    }//GEN-LAST:event_tesTambah


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
    private javax.swing.JButton buttonKembaliDB1;
    private javax.swing.JButton buttonKembaliDB2;
    private javax.swing.JButton buttonKembaliDB3;
    private javax.swing.JButton buttonKembaliDB4;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
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
