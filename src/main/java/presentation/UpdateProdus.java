package presentation;

import dao.AbstractDao;
import model.Client;
import model.Produs;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class UpdateProdus extends JFrame {

    private JPanel contentPane;
    private JTextField nume;
    private JTextField stoc;
    private JTextField pret;
    private JButton add;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    AddProdus frame = new AddProdus(null);
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public UpdateProdus(JTable tabel, Produs c) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 519, 449);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        nume = new JTextField();
        nume.setBounds(53, 96, 164, 45);
        contentPane.add(nume);
        nume.setColumns(10);

        stoc = new JTextField();
        stoc.setColumns(10);
        stoc.setBounds(53, 197, 164, 45);
        contentPane.add(stoc);

        pret = new JTextField();
        pret.setColumns(10);
        pret.setBounds(53, 306, 164, 45);
        contentPane.add(pret);

        JLabel lblNewLabel = new JLabel("Nume ");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(53, 60, 127, 26);
        contentPane.add(lblNewLabel);

        JLabel lblStoc = new JLabel("Stoc");
        lblStoc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblStoc.setBounds(53, 161, 127, 26);
        contentPane.add(lblStoc);

        JLabel pretl = new JLabel("Pret");
        pretl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        pretl.setBounds(53, 270, 127, 26);
        contentPane.add(pretl);


        /**
         * Pentru a face update unui produs folosim butonul Update, acesta presupune inserarea noilor valori care au fost
         * extrase cu ajutorul id-ului acelui produs
         *
         * Datele reintroduse vor fi actualizate si afisate dupa apasarea butonului Add Produs
         */

        add = new JButton("Adauga Produs");
        add.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add.setBounds(272, 166, 185, 69);
        add.addActionListener(n->{
            Produs c1 = new Produs(nume.getText(), Integer.valueOf(stoc.getText()), Float.valueOf(pret.getText()));
            c1.setIdProdus(c.getIdProdus());
            AbstractDao abs = new AbstractDao();
            abs.update(c1);
            tabel.setModel(abs.buildTable("Produs"));
            dispose();
                }
        );
        contentPane.add(add);


        nume.setText(c.getNume());
        stoc.setText(String.valueOf(c.getStoc()));
        pret.setText(String.valueOf(c.getPret()));
    }


}
