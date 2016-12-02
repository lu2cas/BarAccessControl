package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import business.*;
import persistence.ClientDAOMySQL;
import persistence.DAOException;

public class ClientByCpfPanel extends JPanel {
	private JTable clientByCpfTable;

	private JScrollPane scrollPane;

	private JLabel lblCpf;

	private JTextField textFieldCpf;

	private JButton btnClear;
	private JButton btnSearch;

	public ClientByCpfPanel() {}

	public void makeForm() {
		this.removeAll();

		this.setBounds(0, 0, 600, 450);
		this.setLayout(null);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(30, 40, 350, 20);
		this.add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(30, 60, 195, 20);
		this.add(textFieldCpf);

		btnClear = new JButton("Limpar");
		btnClear.setBounds(345, 385, 100, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClear_actionPerformed(e);
			}
		});
		this.add(btnClear);

		btnSearch = new JButton("Buscar");
		btnSearch.setBounds(455, 385, 105, 23);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnSearch_actionPerformed(e);
			}
		});
		this.add(btnSearch);


		String[] column_names = {
			"Id",
			"Nome",
			"CPF",
			"Gênero",
			"Categoria"
		};

		Object [][] data = new Object [1][5];

		data[0] = new Object[] {"", "", "", "", ""};

		clientByCpfTable = new JTable(data, column_names);

		clientByCpfTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		clientByCpfTable.getColumnModel().getColumn(1).setPreferredWidth(160);
		clientByCpfTable.getColumnModel().getColumn(2).setPreferredWidth(80);
		clientByCpfTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		clientByCpfTable.getColumnModel().getColumn(4).setPreferredWidth(60);

		scrollPane = new JScrollPane(clientByCpfTable);
		scrollPane.setBounds(30, 85, 540, 39);


		clientByCpfTable.setFillsViewportHeight(true);

		this.add(scrollPane);
	}

	protected void do_btnSearch_actionPerformed(ActionEvent e) {
		String cpf_target = textFieldCpf.getText();

		try {
			if (!ClientValidator.getInstance().isValidCpf(cpf_target)) {
				throw new IllegalArgumentException("CPF inválido!");
			}

			ClientDAOMySQL clientDAOMySQL = new ClientDAOMySQL();
			Client client;
			client = clientDAOMySQL.getClient(cpf_target, true);

			if (client != null) {
	
				String[] data = new String[5];
	
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
	
				String cpf = "";
				try {
					MaskFormatter mf;
					mf = new MaskFormatter("AAA.AAA.AAA-AA");
					mf.setValueContainsLiteralCharacters(false);
					cpf = mf.valueToString(client.getCpf());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
	
				data = new String[] {
					Integer.toString(client.getId()),
					toTitleCase(client.getName()),
					cpf,
					gender,
					category
				};
	
				for (int i = 0; i < data.length; i++) {
					clientByCpfTable.getModel().setValueAt(data[i], 0, i);
				}
			} else {
				JOptionPane.showMessageDialog(null, "O cliente não está no bar atualmente!");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected void do_btnClear_actionPerformed(ActionEvent e) {
		textFieldCpf.setText("");
		for (int i = 0; i < 5; i++) {
			clientByCpfTable.getModel().setValueAt("", 0, i);
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