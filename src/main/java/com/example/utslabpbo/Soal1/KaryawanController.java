package com.example.utslabpbo.Soal1;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class KaryawanController {

    // Menghubungkan komponen ui dengan controller
    @FXML private TableView<Karyawan> tableKaryawan;
    @FXML private TableColumn<Karyawan, String> colNama;
    @FXML private TableColumn<Karyawan, String> colPosisi;
    @FXML private TableColumn<Karyawan, Double> colGaji;
    @FXML private TableColumn<Karyawan, Void> colAksi;
    @FXML private  TableColumn<Karyawan, String> colJK;
    @FXML private  TableColumn<Karyawan, String> colKelas;
    @FXML private TableColumn<Karyawan, Integer> colNo;
    @FXML private TextField tfCari;

    // List untuk menyimpan objek data karyawan
    private final ObservableList<Karyawan> dataKaryawan = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupKolomTabel(); // Siapkan kolom agar sesuai dengan properti Karyawan
        isiTabel();        // Masukkan data awal ke tabel
        tfCari.setOnKeyReleased(e -> cariKaryawan(tfCari.getText())); // Fitur cari otomatis
    }

    private void setupKolomTabel() {
        // Menampilkan nomor urut
        colNo.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getTableView().getItems().indexOf(cellData.getValue()) + 1));

        // Menghubungkan kolom dengan properti dari objek Obat
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colPosisi.setCellValueFactory(new PropertyValueFactory<>("posisi"));
        colGaji.setCellValueFactory(new PropertyValueFactory<>("gaji"));
        colJK.setCellValueFactory(new PropertyValueFactory<>("jenisKelamin"));
        colKelas.setCellValueFactory(new PropertyValueFactory<>("kelasPerusahaan"));

        // Kolom aksi berisi tombol Edit dan Hapus
        colAksi.setCellFactory(param -> new TableCell<>() {
            private final Button btnEdit = new Button("Edit");
            private final Button btnHapus = new Button("Hapus");

            {
                btnEdit.setOnAction(e -> {
                    Karyawan karyawan = getTableView().getItems().get(getIndex());
                    tampilkanDialogEdit(karyawan); // Panggil dialog edit
                });

                btnHapus.setOnAction(e -> {
                    Karyawan karyawan = getTableView().getItems().get(getIndex());
                    dataKaryawan.remove(karyawan); // Hapus dari list
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox box = new HBox(5, btnEdit, btnHapus);
                    setGraphic(box); // Tampilkan dua tombol dalam satu sel
                }
            }
        });

    }

    private void isiTabel() {
        tableKaryawan.setItems(dataKaryawan); // Hubungkan list ke tabel
    }

    @FXML
    private void tambahKaryawan() {
        // Buat dialog input karyawan baru
        Dialog<Karyawan> dialog = new Dialog<>();
        dialog.setTitle("Tambah Karyawan");
        dialog.setHeaderText("Masukkan data karyawan baru:");

        // Tombol OK dan Batal
        ButtonType simpanButtonType = new ButtonType("Simpan", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(simpanButtonType, ButtonType.CANCEL);

        // Buat input field
        TextField tfId = new TextField();
        tfId.setPromptText("ID");

        TextField tfNama = new TextField();
        tfNama.setPromptText("Nama");

        TextField tfPosisi = new TextField();
        tfPosisi.setPromptText("Posisi");

        TextField tfGaji = new TextField();
        tfGaji.setPromptText("Gaji");

        ComboBox<String> cbKelasPerusahaan = new ComboBox<>();
        cbKelasPerusahaan.getItems().addAll("A", "B", "C");
        cbKelasPerusahaan.setPromptText("Kelas Perusahaan");

        ComboBox<String> cbJenisKelamin = new ComboBox<>();
        cbJenisKelamin.getItems().addAll("Laki-laki", "Perempuan");
        cbJenisKelamin.setPromptText("Jenis Kelamin");

        // Tata letak input
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("ID:"), 0, 0);
        grid.add(tfId, 1, 0);
        grid.add(new Label("Nama:"), 0, 1);
        grid.add(tfNama, 1, 1);
        grid.add(new Label("Posisi:"), 0, 2);
        grid.add(tfPosisi, 1, 2);
        grid.add(new Label("Gaji:"), 0, 3);
        grid.add(tfGaji, 1, 3);
        grid.add(new Label("Kelas Perusahaan:"), 0, 4);
        grid.add(cbKelasPerusahaan, 1, 4);
        grid.add(new Label("Jenis Kelamin:"), 0, 5);
        grid.add(cbJenisKelamin, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // Proses saat tombol Simpan ditekan
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == simpanButtonType) {
                String id = tfId.getText();
                String nama = tfNama.getText();
                String posisi = tfPosisi.getText();
                String gajiStr = tfGaji.getText();
                String kelas = cbKelasPerusahaan.getValue();
                String jk = cbJenisKelamin.getValue();
                double gaji;

                try{
                    gaji = Double.parseDouble(gajiStr);
                }catch (NumberFormatException e){
                    showAlert("Gaji harus angka!");
                    return null;
                }

                // Validasi input tidak kosong
                if (id.isEmpty() || nama.isEmpty() || posisi.isEmpty() ||
                        gajiStr.isEmpty() || kelas == null || jk == null) {
                    showAlert("Semua field wajib diisi!");
                    return null;
                }

                // Validasi gaji tidak boleh negatif
                if (gaji < 0){
                    showAlert("Gaji tidak boleh negatif!");
                    return null;
                }

                // Validasi ID tidak boleh duplikat
                for (Karyawan k : dataKaryawan) {
                    if (k.getId().equals(id)) {
                        showAlert("ID karyawan tidak boleh duplikat!");
                        return null;
                    }
                }

                try {
                    return new Karyawan(id, nama, posisi, gaji, kelas, jk);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        // Tambahkan ke list jika hasil tidak null
        dialog.showAndWait().ifPresent(karyawanBaru -> {
            dataKaryawan.add(karyawanBaru);
        });
    }

    // Edti posisi dan gaji karyawan
    private void tampilkanDialogEdit(Karyawan karyawan) {
        TextInputDialog dialog = new TextInputDialog(karyawan.getPosisi());
        dialog.setTitle("Edit Posisi");
        dialog.setHeaderText("Ubah posisi karyawan: " + karyawan.getNama());

        dialog.showAndWait().ifPresent(posisiBaru -> {
            if (!posisiBaru.trim().isEmpty()) {
                karyawan.setPosisi(posisiBaru);
            }
        });

        TextInputDialog dialogGaji = new TextInputDialog(String.valueOf(karyawan.getGaji()));
        dialogGaji.setTitle("Edit Gaji");
        dialogGaji.setHeaderText("Ubah gaji karyawan: " + karyawan.getNama());

        dialogGaji.showAndWait().ifPresent(gajiBaruStr -> {
            try {
                double gajiBaru = Double.parseDouble(gajiBaruStr);
                karyawan.setGaji(gajiBaru); // Update gaji
                tableKaryawan.refresh();    // Refresh tampilan tabel
            } catch (NumberFormatException e) {
                showAlert("Gaji harus berupa angka");
            }
        });
    }

    // Tampilkan alert kalau ada error atau data tidak valid
    private void showAlert(String pesan) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(pesan);
        alert.show();
    }

    private void cariKaryawan(String keyword) {
        if (keyword.isEmpty()) {
            tableKaryawan.setItems(dataKaryawan); // Tampilkan semua jika kosong
            return;
        }

        ObservableList<Karyawan> hasilCari = FXCollections.observableArrayList();

        // Cari nama karyawan di list dari string yang diinputkan pengguna di text field
        for (Karyawan k : dataKaryawan) {
            if (k.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                hasilCari.add(k);
            }
        }

        tableKaryawan.setItems(hasilCari); // Tampilkan hasil pencarian
    }
}
