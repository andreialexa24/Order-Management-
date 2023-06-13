package presentation;

import dao.AbstractDao;
import model.Client;
import model.Produs;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class UpdateClient extends JFrame {

    private JPanel contentPane;
    private JTextField nume;
    private JTextField prenume;
    private JTextField email;
    private JTextField adresa;
    private JTextField varsta;
    private JLabel adresafff;
    private JButton add;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    AddClient frame = new AddClient(null);
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
    public UpdateClient(JTable tabel,Client c) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 507, 485);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        nume = new JTextField();
        nume.setBounds(47, 67, 147, 45);
        contentPane.add(nume);
        nume.setColumns(10);

        prenume = new JTextField();
        prenume.setColumns(10);
        prenume.setBounds(239, 67, 147, 45);
        contentPane.add(prenume);

        email = new JTextField();
        email.setColumns(10);
        email.setBounds(47, 148, 253, 45);
        contentPane.add(email);

        adresa = new JTextField();
        adresa.setColumns(10);
        adresa.setBounds(47, 235, 218, 45);
        contentPane.add(adresa);

        varsta = new JTextField();
        varsta.setColumns(10);
        varsta.setBounds(47, 322, 147, 45);
        contentPane.add(varsta);

        /**
         * Pentru a face update unui client folosim butonul Update, acesta presupune inserarea noilor valori care au fost
         * extrase cu ajutorul id-ului acelui client
         *
         * Datele reintroduse vor fi actualizate si afisate dupa apasarea butonului Add Client
         */

        add = new JButton("Add Client");
        add.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add.setBounds(279, 337, 174, 61);
        add.addActionListener(n->{
                    Client c1 = new Client(nume.getText(), prenume.getText(), email.getText(), adresa.getText(), Integer.valueOf(varsta.getText()));
                    c1.setIdClient(c.getIdClient());
                    AbstractDao abs = new AbstractDao();
                    abs.update(c1);
                    tabel.setModel(abs.buildTable("Client"));
                    dispose();
                }
        );
        contentPane.add(add);

        JLabel numeddd = new JLabel("Nume");
        numeddd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        numeddd.setBounds(47, 37, 123, 26);
        contentPane.add(numeddd);

        JLabel prenumedd = new JLabel("Prenume");
        prenumedd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        prenumedd.setBounds(239, 37, 123, 26);
        contentPane.add(prenumedd);

        JLabel emailddd = new JLabel("Email");
        emailddd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        emailddd.setBounds(47, 122, 123, 26);
        contentPane.add(emailddd);

        JLabel ddd = new JLabel("Varsta");
        ddd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        ddd.setBounds(47, 290, 123, 26);
        contentPane.add(ddd);

        adresafff = new JLabel("Adresa");
        adresafff.setFont(new Font("Tahoma", Font.PLAIN, 18));
        adresafff.setBounds(47, 203, 123, 26);
        contentPane.add(adresafff);

        nume.setText(c.getNume());
        prenume.setText(c.getPrenume());
        adresa.setText(c.getAdresa());
        email.setText(c.getEmail());
        varsta.setText(String.valueOf(c.getAge()));
    }
}
