package business;

import java.util.ArrayList;
import java.util.HashMap;

import persistence.DAOException;

public interface ClientDAO {
	public boolean insertClient(Client client) throws DAOException;
	public Client getClient(String cpf, boolean presents_only) throws DAOException;
	public ArrayList<Client> getAllClients(boolean presents_only) throws DAOException;
	public ArrayList<Client> getClientsByGender(ClientGender gender, boolean presents_only) throws DAOException;
	public ArrayList<Client> getClientsByCategory(ClientCategory category, boolean presents_only) throws DAOException;
	public HashMap<String, Double> getClientsRelationByGender(boolean presents_only) throws DAOException;
	public HashMap<String, Double> getClientsRelationByCategory(boolean presents_only) throws DAOException;
	public boolean checkIn(String cpf) throws DAOException;
	public boolean checkOut(String cpf) throws DAOException;
}