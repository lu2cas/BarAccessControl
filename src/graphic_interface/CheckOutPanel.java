package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.*;

public class CheckOutPanel extends JPanel {
	private JLabel lblCpf;

	private JTextField textFieldCpf;


	private JButton btnClear;
	private JButton btnCheckOut;

	public CheckOutPanel() {}

	public void makeForm() {
		this.setBounds(0, 0, 500, 450);
		this.setLayout(null);

		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(30, 40, 350, 20);
		this.add(lblCpf);

		textFieldCpf = new JTextField();
		textFieldCpf.setBounds(30, 60, 195, 20);
		this.add(textFieldCpf);

		btnClear = new JButton("Limpar");
		btnClear.setBounds(296, 378, 84, 23);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClear_actionPerformed(e);
			}
		});
		this.add(btnClear);

		btnCheckOut = new JButton("Check out");
		btnCheckOut.setBounds(385, 378, 89, 23);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCheckOut_actionPerformed(e);
			}
		});
		this.add(btnCheckOut);
	}

	protected void do_btnCheckOut_actionPerformed(ActionEvent e) {
		String cpf = textFieldCpf.getText();
	}

	protected void do_btnClear_actionPerformed(ActionEvent e) {
		textFieldCpf.setText("");
	}
}