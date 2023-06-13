package presentation;

import dao.AbstractDao;
import model.Client;
import model.Produs;

import java.awt.EventQueue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ClientOp extends JFrame {

    /**
     * Aceasta clasa ne prezinta tabelul actual cu clienti si optiunea de a
     * adauga sau sterge un client, sau optiunea Update pentru un client deja existent
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
//                    ClientOp frame = new ClientOp();
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
    public ClientOp() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 577, 503);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /**
         * Ne trimite la fereastra care se ocupa cu adaugarea unui client nou
         */

        add = new JButton("Add");
        add.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add.setBounds(34, 91, 150, 51);
        add.addActionListener(n->
        {
            AddClient a2 = new AddClient(tabel);
            a2.setVisible(true);
        });
        contentPane.add(add);

        /**
         * Stergem un client din tabel, pentru asta acesta trebuie mai intai selectat
         */

        delete = new JButton("Delete");
        delete.setFont(new Font("Tahoma", Font.PLAIN, 18));
        delete.setBounds(34, 191, 150, 51);
        delete.addActionListener(n->
        {
            int rand =  tabel.getSelectedRow();
            int id = (int) tabel.getValueAt(rand, 0);
            AbstractDao abs = new AbstractDao();
            Client p = new Client(null, null, null,null, 0);
            p.setIdClient(id);
            abs.delete(p);
            tabel.setModel(abs.buildTable("Client"));
        });
        contentPane.add(delete);

        /**
         * Cu ajutorul acestui buton facem update unui client existent din tabelul nostrul, el trebuie de
         * semenea selectat intai
         */

        update = new JButton("Update");
        update.setFont(new Font("Tahoma", Font.PLAIN, 18));
        update.setBounds(34, 295, 150, 51);
        update.addActionListener(n->
        {
            int rand = tabel.getSelectedRow();
            Client c =new Client((String) tabel.getValueAt(rand,1), (String) tabel.getValueAt(rand,2), (String) tabel.getValueAt(rand,3), (String) tabel.getValueAt(rand,4), (Integer) tabel.getValueAt(rand,5));
            c.setIdClient((Integer) tabel.getValueAt(rand,0));
            UpdateClient updateClient = new UpdateClient(tabel, c);
            updateClient.setVisible(true);
        });
        contentPane.add(update);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(237, 43, 300, 385);
        contentPane.add(scrollPane);

        tabel = new JTable();
        scrollPane.setViewportView(tabel);

        AbstractDao abs = new AbstractDao();
        tabel.setModel(abs.buildTable("Client"));

    }
}
