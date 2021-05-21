package salesianas.dao;

import java.sql.SQLException;

public class test {

	public static void main(String[] args) {
		try {
			DaoSocios.getInstance().listAll().forEach(System.out::println);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
