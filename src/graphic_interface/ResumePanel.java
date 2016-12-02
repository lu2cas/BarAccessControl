package graphic_interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import business.*;
import persistence.ClientDAOMySQL;

public class ResumePanel extends JPanel {
	private JLabel lblTotalClients;
	private JLabel lblGenderDistribution;
	private JLabel lblMenPercent;
	private JLabel lblWomenPercent;
	private JLabel lblCategoryDistribution;
	private JLabel lblSilverPercent;
	private JLabel lblGoldPercent;
	private JLabel lblPlatinumPercent;
	private JLabel lblNotVipPercent;

	private JTextField textFieldTotalClients;
	private JTextField textFieldMenPercent;
	private JTextField textFieldWomenPercent;
	private JTextField textFieldSilverPercent;
	private JTextField textFieldGoldPercent;
	private JTextField textFieldPlatinumPercent;
	private JTextField textFieldNotVipPercent;

	private JButton btnClear;
	private JButton btnCheckIn;

	public ResumePanel() {}

	public void makeForm() {
		this.removeAll();

		this.setBounds(0, 0, 600, 450);
		this.setLayout(null);

		try {
			ClientDAOMySQL clientDAOMySQL = new ClientDAOMySQL();

			int total_clients = clientDAOMySQL.getAllClients(true).size();
			HashMap<String, Double> clientsRelationByGender = clientDAOMySQL.getClientsRelationByGender(true);
			HashMap<String, Double> clientsRelationByCategory = clientDAOMySQL.getClientsRelationByCategory(true);

			lblTotalClients = new JLabel("Total de clientes no bar:");
			lblTotalClients.setBounds(30, 40, 350, 20);
			this.add(lblTotalClients);
	
			textFieldTotalClients = new JTextField();
			textFieldTotalClients.setText(Integer.toString(total_clients));
			textFieldTotalClients.setEditable(false);
			textFieldTotalClients.setBounds(30, 60, 430, 20);
			this.add(textFieldTotalClients);
	
			lblGenderDistribution = new JLabel("Distribuição por gênero");
			lblGenderDistribution.setBounds(30, 120, 350, 20);
			this.add(lblGenderDistribution);
	
			lblMenPercent = new JLabel("Homens:");
			lblMenPercent.setBounds(30, 150, 170, 20);
			this.add(lblMenPercent);
	
			textFieldMenPercent = new JTextField();
			textFieldMenPercent.setBounds(30, 170, 210, 20);
			textFieldMenPercent.setText(String.format("%.2f%%", clientsRelationByGender.get(ClientGender.MALE.toString())));
			textFieldMenPercent.setEditable(false);
			this.add(textFieldMenPercent);
	
			lblWomenPercent = new JLabel("Mulheres:");
			lblWomenPercent.setBounds(250, 150, 180, 20);
			this.add(lblWomenPercent);
	
			textFieldWomenPercent = new JTextField();
			textFieldWomenPercent.setBounds(250, 170, 210, 20);
			textFieldWomenPercent.setText(String.format("%.2f%%", clientsRelationByGender.get(ClientGender.FEMALE.toString())));
			textFieldWomenPercent.setEditable(false);
			this.add(textFieldWomenPercent);
	
			lblCategoryDistribution = new JLabel("Distribuição por categoria");
			lblCategoryDistribution.setBounds(30, 230, 350, 20);
			this.add(lblCategoryDistribution);
	
			lblSilverPercent = new JLabel("Silver:");
			lblSilverPercent.setBounds(30, 260, 75, 20);
			this.add(lblSilverPercent);
	
			textFieldSilverPercent = new JTextField();
			textFieldSilverPercent.setBounds(30, 280, 100, 20);
			textFieldSilverPercent.setText(String.format("%.2f%%", clientsRelationByCategory.get(ClientCategory.SILVER.toString())));

			textFieldSilverPercent.setEditable(false);
			this.add(textFieldSilverPercent);
	
			lblGoldPercent = new JLabel("Gold:");
			lblGoldPercent.setBounds(140, 260, 75, 20);
			this.add(lblGoldPercent);
	
			textFieldGoldPercent = new JTextField();
			textFieldGoldPercent.setBounds(140, 280, 100, 20);
			textFieldGoldPercent.setText(String.format("%.2f%%", clientsRelationByCategory.get(ClientCategory.GOLD.toString())));
			textFieldGoldPercent.setEditable(false);
			this.add(textFieldGoldPercent);
	
			lblPlatinumPercent = new JLabel("Platinum:");
			lblPlatinumPercent.setBounds(250, 260, 75, 20);
			this.add(lblPlatinumPercent);
	
			textFieldPlatinumPercent = new JTextField();
			textFieldPlatinumPercent.setBounds(250, 280, 100, 20);
			textFieldPlatinumPercent.setText(String.format("%.2f%%", clientsRelationByCategory.get(ClientCategory.PLATINUM.toString())));
			textFieldPlatinumPercent.setEditable(false);
			this.add(textFieldPlatinumPercent);
	
			lblNotVipPercent = new JLabel("Não VIP:");
			lblNotVipPercent.setBounds(360, 260, 75, 20);
			this.add(lblNotVipPercent);
	
			textFieldNotVipPercent = new JTextField();
			textFieldNotVipPercent.setBounds(360, 280, 100, 20);
			textFieldNotVipPercent.setText("50%");
			textFieldNotVipPercent.setText(String.format("%.2f%%", clientsRelationByCategory.get("NOT_VIP")));
			textFieldNotVipPercent.setEditable(false);
			this.add(textFieldNotVipPercent);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

}