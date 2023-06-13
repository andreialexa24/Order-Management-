package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;

public class Select extends JFrame {



    private JPanel contentPane;
    private JButton client;
    private JButton produs;
    private JButton comanda;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Select frame = new Select();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Select() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 289, 445);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        /**
         * Selectam butonul Client, care ne trimite mai departe la posibilitatea editarea tabelului Client
         */
        client = new JButton("Client");
        client.setFont(new Font("Tahoma", Font.PLAIN, 18));
        client.setBounds(45, 87, 183, 62);
        client.addActionListener(n->
        {
            ClientOp a1 = new ClientOp();
            a1.setVisible(true);
        });
        contentPane.add(client);

        /**
         * Selectam butonul Produs, care ne trimite mai departe la posibilitatea editarea tabelului Produs
         */

        produs = new JButton("Produs");
        produs.setFont(new Font("Tahoma", Font.PLAIN, 18));
        produs.setBounds(45, 189, 183, 62);
        produs.addActionListener(n->
        {
            ProdusOp a2 = new ProdusOp();
            a2.setVisible(true);
        });
        contentPane.add(produs);

        /**
         * Selectam butonul Comanda, care ne trimite mai departe la posibilitatea de a plasa o comanda
         */

        comanda = new JButton("Comanda");
        comanda.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comanda.setBounds(45, 287, 183, 62);
        comanda.addActionListener(n->
        {
            Comanda_frame c1 = new Comanda_frame();
            c1.setVisible(true);
        });
        contentPane.add(comanda);
    }

}
