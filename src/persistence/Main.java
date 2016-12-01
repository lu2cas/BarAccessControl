package persistence;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		try {
			DataSource d = DataSource.getInstance();
			System.out.println("HELL YEAH");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}