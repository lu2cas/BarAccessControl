package business;

import java.util.ArrayList;

public interface ClientDAO {
	public boolean insertClient(Client client);
	public Client getClient(String cpf);
	public ArrayList<Client> getAllClients();
	public ArrayList<Client> getClientsByGender(String gender);
	public ArrayList<Client> getClientsByCategory(String category);
}