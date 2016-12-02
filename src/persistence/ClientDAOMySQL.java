package persistence;

import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import business.*;

public class ClientDAOMySQL implements ClientDAO {

	private Connection connection;

	private DateFormat dateFormat;

	private String checkInFilePath = "files" + File.separator + "check_in_file.txt";

	private String checkOutFilePath = "files" + File.separator + "check_out_file.txt";

	public ClientDAOMySQL() {
		try {
			this.connection = DataSource.getInstance().getConnection();
			this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} catch(DataSourceException e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean insertClient(Client client) throws DAOException {
		Client client_check = this.getClient(client.getCpf(), true);
		if (client_check != null && client_check.getCheckOut() == null) {
			throw new DAOException("Já existe um cliente com este CPF atualmente no bar!");
		} 

		boolean result_insert = false;

		try {
			String sql = "INSERT INTO clients(name, cpf, age, gender, category, check_in) VALUES(?, ?, ?, ?, ?, ?)";
			PreparedStatement command = this.connection.prepareStatement(sql);

			String category = null;
			if (client.getCategory() != null) {
				category = client.getCategory().toString();
			}
			command.setString(1, client.getName());
			command.setString(2, client.getCpf());
			command.setInt(3, client.getAge());
			command.setString(4, client.getGender().toString());
			command.setString(5, category);
			command.setString(6, this.dateFormat.format(client.getCheckIn()));

			result_insert = command.executeUpdate() == 1;
		} catch (Exception e) {
			throw new DAOException("Falha na inserção de cliente!");
		}

		boolean result_record = this.checkIn(client.getCpf());

		return result_insert && result_record;
	}


	public Client getClient(String cpf, boolean presents_only) throws DAOException {
		Client client = null;

		try {
			String sql = "SELECT id, name, cpf, age, gender, category, check_in, check_out FROM clients WHERE cpf = ?";
			if (presents_only) {
				sql += " AND check_out IS NULL";
			}
			sql += " ORDER BY check_in DESC";
			sql += " LIMIT 1";

			PreparedStatement command = this.connection.prepareStatement(sql);

			command.setString(1, cpf);

			ResultSet result = command.executeQuery();

			if (result.next()) {
				Date check_out = null;
				if (result.getString("check_out") != null) {
					check_out = this.dateFormat.parse(result.getString("check_out"));
				}

				ClientCategory category = null;
				if (result.getString("category") != null) {
					category = ClientCategory.valueOf(result.getString("category"));
				}

				client = new Client(
					result.getInt("id"),
					result.getString("name"),
					result.getString("cpf"),
					result.getInt("age"),
					ClientGender.valueOf(result.getString("gender")),
					category,
					this.dateFormat.parse(result.getString("check_in")),
					check_out
				);
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca de cliente por CPF!");
		}

		return client;
	}

	public ArrayList<Client> getAllClients(boolean presents_only) throws DAOException{
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			String sql = "SELECT id, name, cpf, age, gender, category, check_in, check_out FROM clients WHERE 1 = 1";
			if (presents_only) {
				sql += " AND check_out IS NULL";
			}

			PreparedStatement command = this.connection.prepareStatement(sql);

			ResultSet result = command.executeQuery();

			Date check_out;
			while (result.next()) {
				check_out = null;
				if (result.getString("check_out") != null) {
					check_out = this.dateFormat.parse(result.getString("check_out"));
				}

				ClientCategory category = null;
				if (result.getString("category") != null) {
					category = ClientCategory.valueOf(result.getString("category"));
				}

				clients.add(
					new Client(
						result.getInt("id"),
						result.getString("name"),
						result.getString("cpf"),
						result.getInt("age"),
						ClientGender.valueOf(result.getString("gender")),
						category,
						this.dateFormat.parse(result.getString("check_in")),
						check_out
					)
				);
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca por todos os clientes!");
		}

		return clients;
	}

	public ArrayList<Client> getClientsByGender(ClientGender gender, boolean presents_only) throws DAOException {
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			String sql = "SELECT id, name, cpf, age, gender, category, check_in, check_out FROM clients WHERE gender = ?";
			if (presents_only) {
				sql += " AND check_out IS NULL";
			}

			PreparedStatement command = this.connection.prepareStatement(sql);

			command.setString(1, gender.toString());

			ResultSet result = command.executeQuery();

			Date check_out;
			while (result.next()) {
				check_out = null;
				if (result.getString("check_out") != null) {
					check_out = this.dateFormat.parse(result.getString("check_out"));
				}

				ClientCategory category = null;
				if (result.getString("category") != null) {
					category = ClientCategory.valueOf(result.getString("category"));
				}

				clients.add(
					new Client(
						result.getInt("id"),
						result.getString("name"),
						result.getString("cpf"),
						result.getInt("age"),
						ClientGender.valueOf(result.getString("gender")),
						category,
						this.dateFormat.parse(result.getString("check_in")),
						check_out
					)
				);
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca de clientes por gênero!");
		}

		return clients;
	}

	public ArrayList<Client> getClientsByCategory(ClientCategory category, boolean presents_only) throws DAOException {
		ArrayList<Client> clients = new ArrayList<Client>();

		try {
			String sql = "SELECT id, name, cpf, age, gender, category, check_in, check_out FROM clients WHERE category = ?";
			if (presents_only) {
				sql += " AND check_out IS NULL";
			}

			PreparedStatement command = this.connection.prepareStatement(sql);

			command.setString(1, category.toString());

			ResultSet result = command.executeQuery();

			Date check_out;
			while (result.next()) {
				check_out = null;
				if (result.getString("check_out") != null) {
					check_out = this.dateFormat.parse(result.getString("check_out"));
				}

				clients.add(
					new Client(
						result.getInt("id"),
						result.getString("name"),
						result.getString("cpf"),
						result.getInt("age"),
						ClientGender.valueOf(result.getString("gender")),
						ClientCategory.valueOf(result.getString("category")),
						this.dateFormat.parse(result.getString("check_in")),
						check_out
					)
				);
			}
		} catch (Exception e) {
			throw new DAOException("Falha na busca de clientes por categoria!");
		}

		return clients;
	}

	public HashMap<String, Double> getClientsRelationByGender(boolean presents_only) throws DAOException {
		HashMap<String, Double> clientsRelationByGender = new HashMap<String, Double>();

		try {
			double total_clients = this.getAllClients(presents_only).size();
			double total_male = this.getClientsByGender(ClientGender.MALE, presents_only).size();
			double total_female = this.getClientsByGender(ClientGender.FEMALE, presents_only).size();
	
			double male_percent = (total_male * 100) / total_clients;
			double female_percent = (total_female * 100) / total_clients;
	
			clientsRelationByGender.put(ClientGender.MALE.toString(), male_percent);
			clientsRelationByGender.put(ClientGender.FEMALE.toString(), female_percent);
		} catch (Exception e) {
			throw new DAOException("Falha na busca de relação de clientes por categoria!");
		}

		return clientsRelationByGender;
	}

	public HashMap<String, Double> getClientsRelationByCategory(boolean presents_only) throws DAOException {
		HashMap<String, Double> clientsRelationByCategory = new HashMap<String, Double>();

		try {
			double total_clients = this.getAllClients(presents_only).size();
			double total_silver = this.getClientsByCategory(ClientCategory.SILVER, presents_only).size();
			double total_gold = this.getClientsByCategory(ClientCategory.GOLD, presents_only).size();
			double total_platinum = this.getClientsByCategory(ClientCategory.PLATINUM, presents_only).size();
			double total_not_vip = total_clients - (total_silver + total_gold + total_platinum);
	
			double silver_percent = (total_silver * 100) / total_clients;
			double gold_percent = (total_gold * 100) / total_clients;
			double platinum_percent = (total_platinum * 100) / total_clients;
			double not_vip_percent = (total_not_vip * 100) / total_clients;
	
			clientsRelationByCategory.put(ClientCategory.SILVER.toString(), silver_percent);
			clientsRelationByCategory.put(ClientCategory.GOLD.toString(), gold_percent);
			clientsRelationByCategory.put(ClientCategory.PLATINUM.toString(), platinum_percent);
			clientsRelationByCategory.put("NOT_VIP", not_vip_percent);
		} catch (Exception e) {
			throw new DAOException("Falha na busca de relação de clientes por categoria!");
		}

		return clientsRelationByCategory;
	}

	public boolean checkIn(String cpf) throws DAOException {
		boolean result = false;

		Client client = this.getClient(cpf, false);

		if (client == null || client.getCheckOut() != null) {
			throw new DAOException("O cliente não está no bar!");
		}

		try {
			this.writeFile(this.checkInFilePath, client);
	
			result = true;
		} catch (Exception e) {
			throw new DAOException("Falha ao gravar arquivo de check in!");
		}

		return result;
	}

	public boolean checkOut(String cpf) throws DAOException {
		boolean update_result = false;

		Client client = this.getClient(cpf, true);

		if (client == null) {
			throw new DAOException("O cliente não está no bar!");
		}

		try {
			String sql = "UPDATE clients SET check_out = NOW() WHERE cpf = ? AND check_out IS NULL";
			PreparedStatement command = this.connection.prepareStatement(sql);

			command.setString(1, cpf);

			update_result = command.executeUpdate() == 1;
		} catch (Exception e) {
			throw new DAOException("Falha no check out do cliente!");
		}

		boolean record_result = false;
		try {
			this.writeFile(this.checkOutFilePath, this.getClient(cpf, false));
	
			record_result = true;
		} catch (Exception e) {
			throw new DAOException("Falha ao gravar arquivo de check out!");
		}

		return update_result && record_result;
	}

	private void writeFile(String file_path, Client client) throws Exception {
		File file = new File(file_path);
		if (!file.exists()) {
			file.getParentFile().mkdirs(); 
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file_path, true);

		fw.append(client.toString());
		fw.append("\n");
		fw.append("-------------------------------------------------------------------------------");
		fw.append("\n");

		fw.close();
	}
}