package presentation;

import dao.AbstractDao;
import model.Client;
import model.Produs;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class ProdusOp extends JFrame {

    /**
     * Aceasta clasa ne prezinta tabelul actual cu produse si optiunea de a
     * adauga sau sterge un produs, sau optiunea Update pentru un produs deja existent
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JButton add;
    private JButton delete;
    private JButton update;
    private JTable tabel;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ProdusOp frame = new ProdusOp();
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
    public ProdusOp() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 577, 503);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /**
         * Ne trimite la fereastra care se ocupa cu adaugarea unui produs nou
         */

        add = new JButton("Add");
        add.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add.setBounds(34, 91, 150, 51);
        add.addActionListener(n->
        {
            AddProdus a2 = new AddProdus(tabel);
            a2.setVisible(true);

        });
        contentPane.add(add);
        contentPane.add(add);

        /**
         * Stergem un produs din tabel, pentru asta acesta trebuie mai intai selectat
         */

        delete = new JButton("Delete");
        delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        delete.setBounds(34, 191, 150, 51);
        delete.addActionListener(n->
        {
            int rand =  tabel.getSelectedRow();
            int id = (int) tabel.getValueAt(rand, 0);
            AbstractDao abs = new AbstractDao();
            Produs p = new Produs(null, 0, 0);
            p.setIdProdus(id);
            abs.delete(p);
            tabel.setModel(abs.buildTable("Produs"));
        });
        contentPane.add(delete);

        /**
         * Cu ajutorul acestui buton facem update unui produs existent din tabelul nostrul, el trebuie de
         * semenea selectat intai
         */


        update = new JButton("Update");
        update.setFont(new Font("Tahoma", Font.PLAIN, 18));
        update.setBounds(34, 295, 150, 51);
        update.addActionListener(n->{
        int rand = tabel.getSelectedRow();
        Long cantitate = (long) tabel.getValueAt(rand,2);
        Produs p =new Produs((String) tabel.getValueAt(rand,1),cantitate.intValue() ,(float) tabel.getValueAt(rand,3));
        p.setIdProdus((Integer) tabel.getValueAt(rand,0));
        UpdateProdus updateProdus = new UpdateProdus(tabel, p);
        updateProdus.setVisible(true);});
        contentPane.add(update);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(237, 43, 300, 385);
        contentPane.add(scrollPane);

        tabel = new JTable();
        scrollPane.setViewportView(tabel);


        AbstractDao abs = new AbstractDao();
        tabel.setModel(abs.buildTable("Produs"));


    }
}
