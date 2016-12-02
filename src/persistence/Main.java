package persistence;

import java.util.Date;

import business.*;

public class Main {

	public static void main(String[] args) {
		try {

			ClientDAOMySQL clientDAOMySQL= new ClientDAOMySQL();

			/*Client client = new Client("Salomão Silveira", "65465465465", 56, ClientGender.MALE, ClientCategory.GOLD, new Date());
			System.out.println(clientDAOMySQL.insertClient(client));*/

			//System.out.println(clientDAOMySQL.getClient("45645645645"));

			for (Client client : clientDAOMySQL.getAllClients(true)) {
				System.out.println(client);
			}

			/*for (Client client : clientDAOMySQL.getClientsByGender(ClientGender.MALE, true)) {
				System.out.println(client);
			}*/

			/*for (Client client : clientDAOMySQL.getClientsByCategory(ClientCategory.GOLD, true)) {
				System.out.println(client);
			}*/

			/*Client client = new Client("Matheus Silveira", "78978978978", 21, ClientGender.MALE, null, new Date());
			System.out.println(clientDAOMySQL.insertClient(client));*/

			//System.out.println(clientDAOMySQL.getClientsRelationByGender(true));

			//System.out.println(clientDAOMySQL.getClientsRelationByCategory(false));

			//System.out.println(clientDAOMySQL.checkOut("12312312312"));
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}