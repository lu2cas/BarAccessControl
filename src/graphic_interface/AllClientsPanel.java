package graphic_interface;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.MaskFormatter;

import business.*;
import persistence.ClientDAOMySQL;

public class AllClientsPanel extends JPanel {
	private JTable allClientsTable;

	private JScrollPane scrollPane;

	public AllClientsPanel() {}

	public void makeForm() {
		this.removeAll();

		this.setBounds(0, 0, 600, 450);
		this.setLayout(null);

		try {	
			ClientDAOMySQL clientDAOMySQL = new ClientDAOMySQL();

			ArrayList<Client> clients = clientDAOMySQL.getAllClients(true);

			String[] column_names = {
				"Id",
				"Nome",
				"CPF",
				"Gênero",
				"Categoria"
			};

			Object [][] data = new Object [clients.size()][5];

			Client client;
			for (int i = 0; i < clients.size(); i++) {
				client = clients.get(i);

				String category = "";
				if (client.getCategory() != null) {
					category = toTitleCase(client.getCategory().toString().toLowerCase());
				}

				String gender = "";
				if (client.getGender() == ClientGender.FEMALE) {
					gender = "Feminino";
				} else if (client.getGender() == ClientGender.MALE) {
					gender = "Masculino";
				}

				MaskFormatter mf = new MaskFormatter("AAA.AAA.AAA-AA");
				mf.setValueContainsLiteralCharacters(false);
				String cpf = mf.valueToString(client.getCpf());

				data[i] = new Object[] {
					Integer.toString(client.getId()),
					toTitleCase(client.getName()),
					cpf,
					gender,
					category
				};
			}

			allClientsTable = new JTable(data, column_names);

			allClientsTable.getColumnModel().getColumn(0).setPreferredWidth(10);
			allClientsTable.getColumnModel().getColumn(1).setPreferredWidth(160);
			allClientsTable.getColumnModel().getColumn(2).setPreferredWidth(80);
			allClientsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
			allClientsTable.getColumnModel().getColumn(4).setPreferredWidth(60);

			scrollPane = new JScrollPane(allClientsTable);
			scrollPane.setBounds(10, 30, 575, 385);

			allClientsTable.setFillsViewportHeight(true);

			this.add(scrollPane);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public static String toTitleCase(String givenString) {
		String[] arr = givenString.split(" ");
		StringBuffer sb = new StringBuffer();
	
		for (int i = 0; i < arr.length; i++) {
			sb.append(Character.toUpperCase(arr[i].charAt(0)))
			.append(arr[i].substring(1)).append(" ");
		}
		return sb.toString().trim();
	} 
}