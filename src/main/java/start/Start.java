package start;

import dao.AbstractDao;
import model.Client;
import model.Comanda;
import model.Produs;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 */
public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {
//		Client c = new Client("Marian", "Bahoi", "str vangog", "aahbbdba@yahoo.com",23);
//		c.setIdClient(6);
//		AbstractDao abs = new AbstractDao();
//		abs.insert(c);
//
//		Produs p = new Produs("cola", 2, (float) 3.99);
//		abs.insert(p);
//
//		Comanda c1 =new Comanda(1, 1, "comanda1", 1, 2,(float)0);
//		abs.insert(c1);

		//abs.delete(c);
//		abs.update(c);
//		ResultSet rs = abs.afisare("Client");
//		ResultSetMetaData rsmd = rs.getMetaData();
//
//		int columnsNumber = rsmd.getColumnCount();
//		while (rs.next()) {
//			for (int i = 1; i <= columnsNumber; i++) {
//				if (i > 1) System.out.print(",  ");
//				String columnValue = rs.getString(i);
//				System.out.print( rsmd.getColumnName(i) + " " +  columnValue);
//			}
//			System.out.println("");
//		}

	}
}
