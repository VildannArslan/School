import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

class OgretimGorevlisiForm extends JFrame {
    private JTextField ogretmenNoField, adiField, soyadiField, aramaField;
    private JComboBox<String> bolumComboBox;
    private JList<String> verdigiDerslerList;
    private JButton kaydetButton, aramaButton;
    private DefaultListModel<String> dersListModel;
    private JTable ogretimGorevlileriTable;
    private DefaultTableModel tableModel;

    public OgretimGorevlisiForm() {
        setTitle("Öğretim Görevlisi Formu");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        // Arama alanı
        JPanel aramaPanel = new JPanel();
        aramaField = new JTextField(20);
        aramaButton = new JButton("Ara");
        aramaPanel.add(aramaField);
        aramaPanel.add(aramaButton);

        // Form alanları
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 5, 5));
        ogretmenNoField = new JTextField();
        adiField = new JTextField();
        soyadiField = new JTextField();
        bolumComboBox = new JComboBox<>(new String[] {"Bilgisayar Mühendisliği", "Elektrik-Elektronik Mühendisliği"});
        dersListModel = new DefaultListModel<>();
        verdigiDerslerList = new JList<>(dersListModel);
        kaydetButton = new JButton("Kaydet");

        // Örnek dersler
        dersListModel.addElement("Programlama");
        dersListModel.addElement("Veri Yapıları");
        dersListModel.addElement("Matematik");
        dersListModel.addElement("Ekonomi");
        dersListModel.addElement("Dalga Teorisi");
        dersListModel.addElement("Akışkanlar Mekaniği");

        verdigiDerslerList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        formPanel.add(new JLabel("Öğretmen No:"));
        formPanel.add(ogretmenNoField);
        formPanel.add(new JLabel("Adı:"));
        formPanel.add(adiField);
        formPanel.add(new JLabel("Soyadı:"));
        formPanel.add(soyadiField);
        formPanel.add(new JLabel("Bölümü:"));
        formPanel.add(bolumComboBox);
        formPanel.add(new JLabel("Verdiği Dersler:"));
        formPanel.add(new JScrollPane(verdigiDerslerList));
        formPanel.add(kaydetButton);

        // Tablo
        String[] columnNames = {"Öğretmen No", "Adı", "Soyadı", "Bölümü", "Verdiği Dersler"};
        tableModel = new DefaultTableModel(columnNames, 0);
        ogretimGorevlileriTable = new JTable(tableModel);

        add(aramaPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.WEST);
        add(new JScrollPane(ogretimGorevlileriTable), BorderLayout.CENTER);

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOgretimGorevlisiData();
            }
        });

        aramaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchOgretimGorevlisi();
            }
        });
    }

    private void saveOgretimGorevlisiData() {
        String ogretmenNo = ogretmenNoField.getText();
        String adi = adiField.getText();
        String soyadi = soyadiField.getText();
        String bolum = (String) bolumComboBox.getSelectedItem();

        JSONArray verdigiDersler = new JSONArray();
        for (String ders : verdigiDerslerList.getSelectedValuesList()) {
            verdigiDersler.put(ders);
        }

        JSONObject ogretimGorevlisiData = new JSONObject();
        ogretimGorevlisiData.put("ogretmenNo", ogretmenNo);
        ogretimGorevlisiData.put("adi", adi);
        ogretimGorevlisiData.put("soyadi", soyadi);
        ogretimGorevlisiData.put("bolum", bolum);
        ogretimGorevlisiData.put("verdigiDersler", verdigiDersler);

        try (FileWriter file = new FileWriter(ogretmenNo + ".json")) {
            file.write(ogretimGorevlisiData.toString());
            file.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Tabloya veri ekleme
        Vector<String> row = new Vector<>();
        row.add(ogretmenNo);
        row.add(adi);
        row.add(soyadi);
        row.add(bolum);
        row.add(verdigiDersler.toString());
        tableModel.addRow(row);

        JOptionPane.showMessageDialog(this, "Öğretim Görevlisi Kaydedildi: " + ogretmenNo);
        clearForm();
    }

    private void searchOgretimGorevlisi() {
        String searchText = aramaField.getText().toLowerCase();
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Arama metni boş olamaz.");
            return;
        }
    
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            boolean matchFound = false;
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                String cellValue = tableModel.getValueAt(i, j).toString().toLowerCase();
                if (cellValue.contains(searchText)) {
                    matchFound = true;
                    break;
                }
            }
    
            if (!matchFound) {
                // Eşleşme bulunamadıysa bu satırı gizle
                ((DefaultTableModel) ogretimGorevlileriTable.getModel()).removeRow(i);
                i--;
            }
        }
    
        if (ogretimGorevlileriTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Arama sonucu bulunamadı.");
        }
    }
    

    private void clearForm() {
        ogretmenNoField.setText("");
        adiField.setText("");
        soyadiField.setText("");
        bolumComboBox.setSelectedIndex(0);
        verdigiDerslerList.clearSelection();
    }
}
