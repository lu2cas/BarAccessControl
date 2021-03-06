package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import business.*;
import persistence.ClientDAOMySQL;

public class ClientByCpfPanel extends JPanel {
	private static final long serialVersionUID = 2910004492718431440L;

	private JTable clientByCpfTable;

	private JScrollPane scrollPane;

	private JLabel lblCpf;

	private JTextField textFieldCpf;

	private JButton btnClear;
	private JButton btnSearch;

	public ClientByCpfPanel() {
	}

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

		String[] column_names = {"Id", "Nome", "CPF", "Gênero", "Categoria"};

		Object[][] data = new Object[1][5];

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

				data = new String[] {
					Integer.toString(client.getId()),
					DataFormat.upperCaseWords(client.getName()),
					DataFormat.formatCpf(client.getCpf()),
					client.getFormattedGender(),
					client.getFormattedCategory()
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

}