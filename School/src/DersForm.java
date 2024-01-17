import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONObject;

class DersForm extends JFrame {
    private JTextField dersKoduField, dersAdiField, dersDonemiField, ogretimGorevlisiField, aramaField;
    private JButton kaydetButton, aramaButton;
    private DefaultTableModel tableModel;
    private JTable derslerTable;

    public DersForm() {
        setTitle("Ders Formu");
        setSize(400, 300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        dersKoduField = new JTextField(20);
        dersAdiField = new JTextField(20);
        dersDonemiField = new JTextField(20);
        ogretimGorevlisiField = new JTextField(20);
        kaydetButton = new JButton("Kaydet");

        add(new JLabel("Ders Kodu:"));
        add(dersKoduField);
        add(new JLabel("Ders Adı:"));
        add(dersAdiField);
        add(new JLabel("Ders Dönemi:"));
        add(dersDonemiField);
        add(new JLabel("Öğretim Görevlisi:"));
        add(ogretimGorevlisiField);
        add(kaydetButton);


        // Arama alanı
        JPanel aramaPanel = new JPanel();
        aramaField = new JTextField(20);
        aramaButton = new JButton("Ara");
        aramaPanel.add(aramaField);
        aramaPanel.add(aramaButton);

        // Tablo
        String[] columnNames = {"Ders Kodu", "Ders Adı", "Ders Dönemi", "Öğretim Görevlisi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        derslerTable = new JTable(tableModel);

        add(aramaPanel, BorderLayout.NORTH);
        add(new JScrollPane(derslerTable), BorderLayout.CENTER);

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDersData();
            }
        });

        aramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchDersData();
            }
        });

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDersData();
            }
        });
    }

    private void saveDersData() {
        String dersKodu = dersKoduField.getText();
        String dersAdi = dersAdiField.getText();
        String dersDonemi = dersDonemiField.getText();
        String ogretimGorevlisi = ogretimGorevlisiField.getText();

        JSONObject dersData = new JSONObject();
        dersData.put("dersKodu", dersKodu);
        dersData.put("dersAdi", dersAdi);
        dersData.put("dersDonemi", dersDonemi);
        dersData.put("ogretimGorevlisi", ogretimGorevlisi);

        try (FileWriter file = new FileWriter(dersKodu + ".json")) {
            file.write(dersData.toString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JOptionPane.showMessageDialog(this, "Ders Kaydedildi: " + dersKodu);
        tableModel.addRow(new Object[]{dersKodu, dersAdi, dersDonemi, ogretimGorevlisi});
        SharedData.dersListesi.add(dersKodu + " - " + dersAdi);
        clearForm();
    }

    private void searchDersData() {
        String searchText = aramaField.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        derslerTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter(searchText));
    }

    private void clearForm() {
        dersKoduField.setText("");
        dersAdiField.setText("");
        dersDonemiField.setText("");
        ogretimGorevlisiField.setText("");
    }
}

