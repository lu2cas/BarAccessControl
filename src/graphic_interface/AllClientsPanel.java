package graphic_interface;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import business.*;
import persistence.ClientDAOMySQL;

public class AllClientsPanel extends JPanel {
	private static final long serialVersionUID = 6804975934529384642L;

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

				data[i] = new Object[] {
					Integer.toString(client.getId()),
					DataFormat.upperCaseWords(client.getName()),
					String.format("xxx.xxx.xxx-xx", client.getCpf()),
					client.getFormattedGender(),
					client.getFormattedCategory()
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

}