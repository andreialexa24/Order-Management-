package presentation;

import dao.AbstractDao;
import model.Comanda;
import model.Produs;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import static javax.swing.JOptionPane.showMessageDialog;

public class Comanda_frame extends JFrame {

    private JPanel contentPane;
    private JTextField cantitate;
    private JButton adauga;
    private JButton scoate;
    private JButton comanda;
    private JComboBox jCombo;
    private JTable tabel;
    private JTable tabel2;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    Comanda_frame frame = new Comanda_frame();
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
    public Comanda_frame() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 675, 469);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JScrollPane scr1 = new JScrollPane();
        scr1.setBounds(10, 29, 229, 393);
        contentPane.add(scr1);

        tabel = new JTable();
        AbstractDao abs = new AbstractDao();
        tabel.setModel(abs.buildTable("Produs"));
        scr1.setViewportView(tabel);

        JScrollPane scr2 = new JScrollPane();
        scr2.setBounds(426, 20, 229, 393);
        contentPane.add(scr2);

        tabel2 = new JTable();
        tabel2.setModel(abs.buildTable("Comanda"));
        scr2.setViewportView(tabel2);

        /**
         * acest buton il folosim pentru a adauga un produs din lista de produse afisata, in cosul nostru
         * Ca acest lucru sa poata fi realizabil cantitatea aleasa trebuie sa fie mai mica decat stocul actual, in caz contrar
         * se va return un mesaj de eroare
         */

        adauga = new JButton("Adauga");
        adauga.setFont(new Font("Tahoma", Font.PLAIN, 16));
        adauga.setBounds(273, 50, 117, 36);
        adauga.addActionListener(n->
        {
            int rand = tabel.getSelectedRow();
            int idProdus = (int) tabel.getValueAt(rand, 0);
            String nume = (String) tabel.getValueAt(rand, 1);
            Long stoc = (long) tabel.getValueAt(rand, 2);
            int cant = Integer.parseInt(cantitate.getText());
            float pret = (float) tabel.getValueAt(rand, 3)* cant;
            int IdClient= (int) jCombo.getSelectedItem();
            if (cant> stoc)
                showMessageDialog(this, "Cantitate prea mare","Eroare", JOptionPane.WARNING_MESSAGE);
            else
            {
                Comanda c=new Comanda(idProdus,nume,IdClient,cant,pret);
                abs.insert(c);
                Produs p=new Produs((String) tabel.getValueAt(rand,1), (int) (stoc-cant), (Float) tabel.getValueAt(rand,3));
                p.setIdProdus((Integer) tabel.getValueAt(rand,0));
                abs.update(p);
                tabel.setModel(abs.buildTable("Produs"));
                tabel2.setModel(abs.buildTable("Comanda"));
            }
        });
        contentPane.add(adauga);

        /**
         * In caz ca ne razgandim asupra unui produs acesta poate sa fie sters din cosul de cumparaturi cu ajutorul
         * butonului Sterge
         */

        scoate = new JButton("Sterge");
        scoate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        scoate.setBounds(273, 96, 117, 36);
        scoate.addActionListener(n->
        {
            Comanda c=new Comanda(0,null,0,0,0);
            c.setIdComanda((Integer) tabel2.getValueAt(tabel2.getSelectedRow(),0));
            abs.delete(c);
            tabel.setModel(abs.buildTable("Produs"));
            tabel2.setModel(abs.buildTable("Comanda"));
        });
        contentPane.add(scoate);

        comanda = new JButton("Plaseaza comanda");
        comanda.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comanda.setBounds(245, 256, 171, 65);
        contentPane.add(comanda);

        cantitate = new JTextField();
        cantitate.setColumns(10);
        cantitate.setBounds(273, 164, 117, 38);
        contentPane.add(cantitate);

        JLabel lblNewLabel = new JLabel("Cantitate");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(296, 142, 72, 19);
        contentPane.add(lblNewLabel);

        jCombo = abs.select_client();
        jCombo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        jCombo.setBounds(245, 373, 171, 40);
        contentPane.add(jCombo);

        JLabel lblAlegeClient = new JLabel("Alege Client");
        lblAlegeClient.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAlegeClient.setBounds(285, 344, 117, 19);
        contentPane.add(lblAlegeClient);


    }
}
