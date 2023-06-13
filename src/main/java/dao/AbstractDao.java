package dao;

import connection.ConnectionFactory;
import model.Comanda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In aceasta clasa facem metodele de adaugare, stergere si update pentru tabelele noastre, pentru a le putea popula
 */
public class AbstractDao {

    /**
     * Aceasta metoda returneaza Stringul specific, depinzand de verificarile facute intr-un switch
     * ajutand la alegerea tabelului pe care vrem sa facem operatia de adaugare
     * @param tabel
     * @return
     */
    private String adauagare(String tabel){

        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(tabel);

        switch(tabel)
        {
            case "Client": {
                sb.append("(Nume,Prenume,Adresa,Email,age) ");
                sb.append("values(?, ? ,?, ?, ?);");
                break;
            }
            case "Produs": {
            sb.append("(Nume,Stoc,Pret) ");
            sb.append("values(?, ? ,?);");
            break;
        }
            case "Comanda": {
                sb.append("(idComanda,Produs_Id,Nume,Client_Id,Cantitate,Pret) ");
                sb.append("values(?, ? ,?, ?, ?, ?);");
                break;
            }

        }
        return sb.toString();

    }

    /**
     *  In aceasta metoda ne folosim de un obiect asupra caruia vrem sa operam,
     *  folosim un field pentru a putea accesa
     *  valorile din atribute(pentru ca in mod normal sunt private),
     *  iar obiectul primeste valoarea din campul respectiv
     *
     *  La final verifcam daca e de tipul cerut, iar daca e ii dam statement.
     * @param o
     */
    public void insert(Object o){
        Connection connection  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String querry = adauagare(o.getClass().getSimpleName());
        Field[] atribute = o.getClass().getDeclaredFields();
        System.out.println();
        try {
            connection = ConnectionFactory.getConnection();
            statement =  connection.prepareStatement(querry);

            int i =1;
            Object valFields;
            for(Field f : atribute){
                f.setAccessible(true);
                valFields = f.get(o);
                System.out.println(valFields.toString());
            if(!(o instanceof Comanda)){
                if(f.getName().contains("id"))
                    continue;
            }
                if(f.getName().contains("bere"))
                    continue;

                if(valFields instanceof Integer){
                    statement.setInt(i, (int)valFields);
                }
                else if(valFields instanceof String){
                    statement.setString(i, (String)valFields);
                }
                else if(valFields instanceof Float){
                    statement.setFloat(i, (float)valFields);
                }
                i++;
            }
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);


    }

    /**
     *   Aceasta metoda este o interogare pe care o sa o folosim la delete-ul dintr-un tabel
     * @param tabel
     * @return
     */

    public String stergere(String tabel){
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(tabel);
        sb.append((" where id") + tabel + " =?");

        return sb.toString();

    }

    /**
     * Aceasta metoda este destul de simpla si se rezuma la stergerea din tabel a unui rand selectat

     */

    public void delete(Object o){
        Connection connection  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String querry = stergere(o.getClass().getSimpleName());
        Field[] atribute = o.getClass().getDeclaredFields();

        try {
            connection = ConnectionFactory.getConnection();
            statement =  connection.prepareStatement(querry);

            atribute[0].setAccessible(true);
            Object val = atribute[0].get(o);
            statement.setInt(1, (int)val);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);

    }

    /**
     * Metoda care gaseste un item specific in functie daca acesta apartine tabelului
     * de Client sau Produs asupra caruia sa se poata executa Update, depinzand de id
     * @param tabel - Un String specific
     * @return
     */

    public String find(String tabel){
        StringBuilder sb = new StringBuilder();
        sb.append("update ");
        sb.append(tabel);

        switch (tabel){
            case "Client" : sb.append(" set Nume=?,Prenume = ?, Adresa = ?, Email = ?, age= ? ");
                break;
            case "Produs" : sb.append(" set Nume=?,Stoc = ?, Pret = ?");
            break;
        }
        sb.append((" where id") + tabel + " =?  ;");

        return sb.toString();

    }

    /**
     * In aceasta metoda facem lucruri asemanatoare cu ceea ce facem in adaugare, dar de data asta
     * setam id-ul ales la valoarea de dupa update
     * @param o
     */

    public void update(Object o){
        Connection connection  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String querry = find(o.getClass().getSimpleName());
        Field[] atribute = o.getClass().getDeclaredFields();
        System.out.println(querry);
        try {
            connection = ConnectionFactory.getConnection();
            statement =  connection.prepareStatement(querry);

            int i =1;
            Object valFields;
            for(Field f : atribute){
                f.setAccessible(true);
                valFields = f.get(o);
                System.out.println(valFields.toString());
                if(!(o instanceof Comanda)){
                    if(f.getName().contains("id"))
                        continue;
                }

                if(valFields instanceof Integer){
                    statement.setInt(i, (int)valFields);
                }
                else if(valFields instanceof String){
                    statement.setString(i, (String)valFields);
                }
                else if(valFields instanceof Float){
                    statement.setFloat(i, (float)valFields);
                }
                i++;
            }
            statement.setInt(i, (int)atribute[0].get(o));
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    /**
     * Aceasta metoda este folosita pentru a stoca datele pe care le obtinem prin
     * interogarile facute asupra bazei de date.
     * @param tabel
     * @return
     */
    public ResultSet afisare(String tabel){
        String querry = "Select * from " + tabel + " ;";
        Connection connection  = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(querry);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    /**
     * Aceasta metoda o folosim pentru a constui un tabel in interiorul unui JPanel, ne folosim si de fuctia ResultSet pentru a seta valorile ,
     * iar mai apoi le stocam intr-un metaData pentru a putea afisa coloanele si randurile.
     * @param Table
     * @return
     */

    public DefaultTableModel buildTable(String Table) {
        DefaultTableModel model = new DefaultTableModel();

        try {
            ResultSet resultSet = this.afisare(Table);
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                model.addColumn(metaData.getColumnName(i));
            }
            while (resultSet.next()) {
                List<Object> objects = new ArrayList<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount(); i++)
                    objects.add(resultSet.getObject(i));
                model.addRow(objects.toArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    /**
     * Cu ajutorul acestei metode realizam accesarea id-urilor clientilor cu care vrem sa facem o comanda
     * @return
     */

    public JComboBox select_client()
    {
        ResultSet resultSet=this.afisare("Client");
        JComboBox model=new JComboBox<>();
            try {
                while (resultSet.next()) {
                    model.addItem(resultSet.getObject(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return model;
    }
}
