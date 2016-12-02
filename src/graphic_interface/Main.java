package graphic_interface;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Main extends JFrame {

	private JPanel contentPane;

	private CheckInPanel panelCheckIn;
	private CheckOutPanel panelCheckOut;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setTitle("Controle de acesso ao bar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Menu principal
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 494, 21);
		contentPane.add(menuBar);

		JMenu mnFile = new JMenu("Arquivo");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Sair");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnClients = new JMenu("Clientes");
		menuBar.add(mnClients);

		JMenuItem mntmCheckIn = new JMenuItem("Check in");
		mntmCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmCheckIn_actionPerformed(e);
			}
		});
		mnClients.add(mntmCheckIn);

		JMenuItem mntmCheckOut = new JMenuItem("Check out");
		mntmCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmCheckOut_actionPerformed(e);
			}
		});
		mnClients.add(mntmCheckOut);

		panelCheckIn = new CheckInPanel();
		panelCheckIn.makeForm();

		panelCheckOut = new CheckOutPanel();
		panelCheckOut.makeForm();
	}

	protected void do_mntmCheckIn_actionPerformed(ActionEvent e) {
		changePanel(panelCheckIn);
	}

	protected void do_mntmCheckOut_actionPerformed(ActionEvent e) {
		changePanel(panelCheckOut);
	}

	private void changePanel(JPanel panel) {
		if (contentPane.getComponents().length > 1) {
			contentPane.remove(contentPane.getComponent(1));
		}
		contentPane.add(panel);
		contentPane.revalidate();
		contentPane.repaint();
	}
}