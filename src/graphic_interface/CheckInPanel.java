package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.*;

public class CheckInPanel extends JPanel {
	private JLabel lblName;
	private JLabel lblCpf;
	private JLabel lblAge;
	private JLabel lblGender;
	private JLabel lblCategory;

	private JTextField textFieldName;
	private JTextField textFieldCpf;
	private JTextField textFieldAge;
	private JTextField textFieldGender;
	private JTextField textFieldCategory;

	private JButton btnClear;
	private JButton btnCheckIn;

	public CheckInPanel() {}

	public void makeForm() {
		this.setBounds(0, 0, 500, 450);
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

		textFieldGender = new JTextField();
		textFieldGender.setBounds(30, 195, 195, 20);
		this.add(textFieldGender);

		lblCategory = new JLabel("Categoria");
		lblCategory.setBounds(30, 220, 350, 20);
		this.add(lblCategory);

		textFieldCategory = new JTextField();
		textFieldCategory.setBounds(30, 240, 195, 20);
		this.add(textFieldCategory);

		btnClear = new JButton("Limpar");
		btnClear.setBounds(296, 378, 84, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClear_actionPerformed(e);
			}
		});
		this.add(btnClear);

		btnCheckIn = new JButton("Check in");
		btnCheckIn.setBounds(385, 378, 89, 23);
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
		String gender = textFieldGender.getText();
		String category = textFieldCategory.getText();
	}

	protected void do_btnClear_actionPerformed(ActionEvent e) {
		textFieldName.setText("");
		textFieldCpf.setText("");
		textFieldAge.setText("");
		textFieldGender.setText("");
		textFieldCategory.setText("");
	}
}