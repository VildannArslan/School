import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JFrame {
    private JButton dersButton;
    private JButton ogrenciButton;
    private JButton ogretimGorevlisiButton;

    public MenuForm() {
        setTitle("Ana Menü");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        dersButton = new JButton("Ders Formu");
        ogrenciButton = new JButton("Öğrenci Formu");
        ogretimGorevlisiButton = new JButton("Öğretim Görevlisi Formu");

        add(dersButton);
        add(ogrenciButton);
        add(ogretimGorevlisiButton);

        dersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersForm dersForm = new DersForm();
                dersForm.setVisible(true);
            }
        });

        ogrenciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciForm ogrenciForm = new OgrenciForm();
                ogrenciForm.setVisible(true);
            }
        });

        ogretimGorevlisiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgretimGorevlisiForm ogretimGorevlisiForm = new OgretimGorevlisiForm();
                ogretimGorevlisiForm.setVisible(true);
            }
        });
    }

}
