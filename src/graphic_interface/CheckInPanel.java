package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.*;
import persistence.ClientDAOMySQL;

public class CheckInPanel extends JPanel {
	private JLabel lblName;
	private JLabel lblCpf;
	private JLabel lblAge;
	private JLabel lblGender;
	private JLabel lblCategory;

	private JTextField textFieldName;
	private JTextField textFieldCpf;
	private JTextField textFieldAge;
	private JComboBox<String> comboGender;
	private JComboBox<String> comboCategory;

	private JButton btnClear;
	private JButton btnCheckIn;

	public CheckInPanel() {}

	public void makeForm() {
		this.removeAll();

		this.setBounds(0, 0, 600, 450);
		this.setLayout(null);

		lblName = new JLabel("Nome");
		lblName.setBounds(30, 40, 350, 20);
		this.add(lblName);

		textFieldName = new JTextField();
		textFieldName.setBounds(30, 60, 350, 20);
		this.add(textFieldName);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(30, 85, 350, 20);
		this.add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(30, 105, 195, 20);
		this.add(textFieldCpf);

		lblAge = new JLabel("idade");
		lblAge.setBounds(30, 130, 350, 20);
		this.add(lblAge);

		textFieldAge = new JTextField();
		textFieldAge.setBounds(30, 150, 195, 20);
		this.add(textFieldAge);

		lblGender = new JLabel("Gênero");
		lblGender.setBounds(30, 175, 350, 20);
		this.add(lblGender);

		String[] genders = new String[] {"Masculino", "Feminino"};
		comboGender = new JComboBox<String>(genders);
		comboGender.setBounds(30, 195, 195, 20);
		this.add(comboGender);

		lblCategory = new JLabel("Categoria");
		lblCategory.setBounds(30, 220, 350, 20);
		this.add(lblCategory);

		String[] categories = new String[] {"", "Silver", "Gold", "Platinum"};
		comboCategory = new JComboBox<String>(categories);
		comboCategory.setBounds(30, 240, 195, 20);
		this.add(comboCategory);

		btnClear = new JButton("Limpar");
		btnClear.setBounds(345, 385, 100, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClear_actionPerformed(e);
			}
		});
		this.add(btnClear);

		btnCheckIn = new JButton("Check in");
		btnCheckIn.setBounds(455, 385, 105, 23);
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCheckIn_actionPerformed(e);
			}
		});
		this.add(btnCheckIn);
	}

	protected void do_btnCheckIn_actionPerformed(ActionEvent e) {
		String name = textFieldName.getText();
		String cpf = textFieldCpf.getText();
		String age = textFieldAge.getText();
		String selected_gender = comboGender.getSelectedItem().toString();
		String selected_category = comboCategory.getSelectedItem().toString();

		ClientGender gender = null;
		if (selected_gender.equals("Feminino")) {
			gender = ClientGender.FEMALE;
		} else if (selected_gender.equals("Masculino")) {
			gender = ClientGender.MALE;
		}

		ClientCategory category = null;
		if (selected_category.equals("Silver")) {
			category = ClientCategory.SILVER;
		} else if (selected_category.equals("Gold")) {
			category = ClientCategory.GOLD;
		} else if (selected_category.equals("Platinum")) {
			category = ClientCategory.PLATINUM;
		}
	
		try {
			Client client = new Client(name, cpf, Integer.parseInt(age), gender, category);
			ClientDAOMySQL clientDAOMySQL = new ClientDAOMySQL();
			clientDAOMySQL.insertClient(client);
			btnClear.doClick();
			JOptionPane.showMessageDialog(null, "Check in registrado com sucesso!");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected void do_btnClear_actionPerformed(ActionEvent e) {
		textFieldName.setText("");
		textFieldCpf.setText("");
		textFieldAge.setText("");
		comboGender.setSelectedIndex(0);
		comboCategory.setSelectedIndex(0);
	}
}