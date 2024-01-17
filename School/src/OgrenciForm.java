import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

class OgrenciForm extends JFrame {
    private JTextField ogrenciNoField, ogrenciAdiField, ogrenciSoyadiField, ogrenciBolumuField;
    private JComboBox<String> derslerComboBox; 
    private JButton kaydetButton;
    private JTextField aramaField;
    private JButton aramaButton;
    private DefaultTableModel tableModel;
    private JTable ogrencilerTable;


    public OgrenciForm() {
        setTitle("Öğrenci Formu");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        ogrenciNoField = new JTextField(20);
        ogrenciAdiField = new JTextField(20);
        ogrenciSoyadiField = new JTextField(20);
        ogrenciBolumuField = new JTextField(20);
        derslerComboBox = new JComboBox<>();
        kaydetButton = new JButton("Kaydet");

        add(new JLabel("Öğrenci No:"));
        add(ogrenciNoField);
        add(new JLabel("Öğrenci Adı:"));
        add(ogrenciAdiField);
        add(new JLabel("Öğrenci Soyadı:"));
        add(ogrenciSoyadiField);
        add(new JLabel("Bölümü:"));
        add(ogrenciBolumuField);
        add(new JLabel("Dersler:"));
        add(derslerComboBox);
        add(kaydetButton);

        // Arama alanı
        JPanel aramaPanel = new JPanel();
        aramaField = new JTextField(20);
        aramaButton = new JButton("Ara");
        aramaPanel.add(aramaField);
        aramaPanel.add(aramaButton);

        // Tablo
        String[] columnNames = {"Öğrenci No", "Öğrenci Adı", "Öğrenci Soyadı", "Bölümü", "Dersler"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ogrencilerTable = new JTable(tableModel);

        add(aramaPanel, BorderLayout.NORTH);
        add(new JScrollPane(ogrencilerTable), BorderLayout.CENTER);

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOgrenciData();
            }
        });

        aramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOgrenciData();
            }
        });

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOgrenciData();
            }
        });

        // Form odaklandığında ders listesini güncelle
        addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                derslerComboBox.removeAllItems();
                for (String ders : SharedData.dersListesi) {
                    derslerComboBox.addItem(ders);
                }
            }
        });

        // Derslerin ComboBox'a eklenmesi için. Bu veriler bir veritabanından alınabilir
        derslerComboBox.addItem("Matematik");
        derslerComboBox.addItem("Programlama");
        derslerComboBox.addItem("Ekonomi");
        derslerComboBox.addItem("Veri Yapıları");
        derslerComboBox.addItem("Dalga Teorisi");
        derslerComboBox.addItem("Akışkanlar Mekaniği");
    }

    private void saveOgrenciData() {
        String ogrenciNo = ogrenciNoField.getText();
        String ogrenciAdi = ogrenciAdiField.getText();
        String ogrenciSoyadi = ogrenciSoyadiField.getText();
        String ogrenciBolumu = ogrenciBolumuField.getText();
        String secilenDers = (String) derslerComboBox.getSelectedItem();

        JSONObject ogrenciData = new JSONObject();
        ogrenciData.put("ogrenciNo", ogrenciNo);
        ogrenciData.put("ogrenciAdi", ogrenciAdi);
        ogrenciData.put("ogrenciSoyadi", ogrenciSoyadi);
        ogrenciData.put("ogrenciBolumu", ogrenciBolumu);
        ogrenciData.put("secilenDers", secilenDers);

        try (FileWriter file = new FileWriter(ogrenciNo + ".json")) {
            file.write(ogrenciData.toString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Öğrenci Kaydedildi: " + ogrenciNo);
        tableModel.addRow(new Object[]{ogrenciNo, ogrenciAdi, ogrenciSoyadi, ogrenciBolumu, secilenDers});
        clearForm();
    }

    private void searchOgrenciData() {
        String searchText = aramaField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        ogrencilerTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchText));
    }

    private void clearForm() {
        ogrenciNoField.setText("");
        ogrenciAdiField.setText("");
        ogrenciSoyadiField.setText("");
        ogrenciBolumuField.setText("");
        derslerComboBox.setSelectedIndex(0); // İlk elemanı seç
    }
}
