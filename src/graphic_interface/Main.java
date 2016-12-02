package graphic_interface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class Main extends JFrame {
	private static final long serialVersionUID = 4802513481396152139L;

	private JPanel contentPane;

	private CheckInPanel panelCheckIn;
	private CheckOutPanel panelCheckOut;
	private AllClientsPanel panelAllClients;
	private ClientByCpfPanel panelClientByCpf;
	private ResumePanel panelResume;

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

	public Main() {

		setResizable(false);
		setTitle("Controle de acesso ao bar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height/2 - this.getSize().height / 2);

		// Menu principal
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 600, 21);
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

		JMenu mnQueries = new JMenu("Consultas");
		menuBar.add(mnQueries);

		JMenuItem mntmAllClients = new JMenuItem("Todos os clientes");
		mntmAllClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmAllClients_actionPerformed(e);
			}
		});
		mnQueries.add(mntmAllClients);

		JMenuItem mntmClientByCpf = new JMenuItem("Cliente por CPF");
		mntmClientByCpf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmClientByCpf_actionPerformed(e);
			}
		});
		mnQueries.add(mntmClientByCpf);

		JMenuItem mntmResume = new JMenuItem("Resumo");
		mntmResume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmResume_actionPerformed(e);
			}
		});
		mnQueries.add(mntmResume);

		panelCheckIn = new CheckInPanel();

		panelCheckOut = new CheckOutPanel();

		panelAllClients = new AllClientsPanel();
	
		panelClientByCpf = new ClientByCpfPanel();

		panelResume = new ResumePanel();
	}

	protected void do_mntmCheckIn_actionPerformed(ActionEvent e) {
		panelCheckIn.makeForm();
		changePanel(panelCheckIn);
	}

	protected void do_mntmCheckOut_actionPerformed(ActionEvent e) {
		panelCheckOut.makeForm();
		changePanel(panelCheckOut);
	}

	protected void do_mntmAllClients_actionPerformed(ActionEvent e) {
		panelAllClients.makeForm();
		changePanel(panelAllClients);
	}

	protected void do_mntmClientByCpf_actionPerformed(ActionEvent e) {
		panelClientByCpf.makeForm();
		changePanel(panelClientByCpf);
	}

	protected void do_mntmResume_actionPerformed(ActionEvent e) {
		panelResume.makeForm();
		changePanel(panelResume);
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