package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.naming.ldap.ManageReferralControl;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.junit.validator.PublicClassValidator;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.desktop.AboutHandler;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale.Category;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JOptionPane;

/**
 * This is the TeamSetupScreen class. This screen will allow players to purchase
 * their first 5 athletes so that they can start the game. Players are given an
 * option to choose 5 from 6 players and the players are generated mostly
 * randomly.
 * 
 * @author Yunu Cho
 * @author Kush Desai
 * 
 */
public class TeamSetupScreen {
	/**
	 * Frame of the GameScreen.
	 */
	private JFrame frame;
	/**
	 * The current instance of the GameManager object.
	 */
	private GameManager manager;

	/**
	 * Constructs a new TeamSetupScreen object with the given GameManager.
	 * 
	 * @param gameManager the current instance of the GameManager object
	 */
	public TeamSetupScreen(GameManager gameManager) {
		manager = gameManager;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Launch the application.
	 * @param args Command line args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamSetupScreen window = new TeamSetupScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeamSetupScreen() {
		initialize();
	}

	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * Calls closeTeamSetupScreen within the GameManager object which opens the next
	 * window.
	 */
	public void finishedWindow() {
		manager.closeTeamSetupScreen(this);
	}

	/**
	 * @return true if the team is at maximum capacity, false otherwise
	 */
	public boolean maxSize() {
		return manager.getTeam().getPlayersArray().size() == 5;
	}

	/**
	 * @return true if the team is at less than maximum capacity
	 */
	public boolean lessThanMax() {
		return manager.getTeam().getPlayersArray().size() < 5;
	}

	/**
	 * When a JToggleButton is pressed to select an Athlete, it displays the
	 * information of its corresponding Athlete in a panel and adds the Athlete to
	 * the team. If the JToggleButton is deselected, the Athlete is removed from the
	 * team.
	 * 
	 * @param btn   the toggled JToggleButton
	 * @param pos   the position of the Athlete
	 * @param name  the name of the Athlete
	 * @param ovr   the overall stat of the Athlete
	 * @param off   the offence stat of the Athlete
	 * @param def   the defence stat of the Athlete
	 * @param stam  the stamina stat of the Athlete
	 * @param agil  the agility stat of the Athlete
	 * @param index the index of the player in the Team
	 */
	public void tglButtonEvent(JToggleButton btn, JLabel pos, JLabel name, JLabel ovr, JLabel off, JLabel def,
			JLabel stam, JLabel agil, int index) {

		if (btn.isSelected()) {

			Athlete athlete = manager.getMarket().getStarterAthletes().get(index);
			if (manager.getTeam().getPlayersMap().get(athlete.getPosition()) instanceof Athlete) {
				// another player in same position already in team
				btn.setSelected(false);
				String message = "Another player is already in that position!";
				JOptionPane.showMessageDialog(new JFrame(), message, "Selection Error", JOptionPane.ERROR_MESSAGE);
			}

			else {
				manager.getTeam().addPlayer(athlete); // Add player from array to starters
				name.setText(athlete.getName()); // Set name label to athlete name
				pos.setText(String.valueOf(athlete.getPosition()));
				ovr.setText(String.valueOf(athlete.getRating()));
				off.setText(String.valueOf(athlete.getStat(Athlete.STATS.O)));
				def.setText(String.valueOf(athlete.getStat(Athlete.STATS.D)));
				stam.setText(String.valueOf(athlete.getStat(Athlete.STATS.S)));
				agil.setText(String.valueOf(athlete.getStat(Athlete.STATS.A)));
			}
		}

		else if (!btn.isSelected()) {
			manager.getTeam().removePlayer(manager.getMarket().getStarterAthletes().get(index));
		}
	}

	/**
	 * Sets the JButtons to show the information of its corresponding Athlete.
	 * 
	 * @param btns list of buttons that will be set to show Athletes' information.
	 */
	public void setAthleteButtons(ArrayList<JToggleButton> btns) {
		for (int i = 0; i < btns.size(); i++) {
			Athlete athlete = manager.getMarket().getStarterAthletes().get(i);
			btns.get(i).setText(String.valueOf(athlete.getName() + " - " + athlete.getPosition()));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 64));
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 64));

		JLabel lblErrorMsg = new JLabel("Select 5 Players");
		lblErrorMsg.setForeground(new Color(255, 255, 255));
		lblErrorMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorMsg.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnStart = new JButton("Start");

		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!maxSize()) {
					lblErrorMsg.setForeground(Color.red);
					String message = "Please select exactly 5 players from different positions!";
					JOptionPane.showMessageDialog(new JFrame(), message, "Selection Error", JOptionPane.ERROR_MESSAGE);
				} else {
					finishedWindow();
				}
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE).addGap(138)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE)
								.addGap(83)
								.addComponent(btnStart, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
				.addGap(18)));

		JLabel lblName = new JLabel("Click a player to add them into your team");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblPosition = new JLabel("and see their stats.");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblOffValue = new JLabel("Offence: ");
		lblOffValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JSeparator separator = new JSeparator();

		JLabel lblDefValue = new JLabel("Defence: ");
		lblDefValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblStamValue = new JLabel("Stamina: ");
		lblStamValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblAgilValue = new JLabel("Agility: ");
		lblAgilValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel ovrLabel = new JLabel("");
		ovrLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel offValue = new JLabel("");
		offValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel defValue = new JLabel("");
		defValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel stamValue = new JLabel("");
		stamValue.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel agilValue = new JLabel("");
		agilValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblStamValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblDefValue, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(6).addComponent(stamValue))
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblAgilValue).addGap(18)
										.addComponent(agilValue))
								.addGroup(gl_panel.createSequentialGroup().addComponent(lblOffValue).addGap(6)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(defValue)
												.addComponent(offValue))))
						.addContainerGap(173, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addGap(10).addGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE).addGap(16))
						.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel
										.createParallelGroup(Alignment.TRAILING).addComponent(lblPosition,
												Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
										.addComponent(ovrLabel))
								.addContainerGap()))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(181)
						.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup().addGap(20).addComponent(ovrLabel))
								.addGroup(gl_panel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblPosition, GroupLayout.PREFERRED_SIZE, 14,
												GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(9)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblOffValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(offValue))
						.addGap(5)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
								.createSequentialGroup()
								.addComponent(lblDefValue, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblStamValue)
										.addComponent(stamValue))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblAgilValue)
										.addComponent(agilValue)))
								.addComponent(defValue))
						.addContainerGap(18, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JToggleButton tglbtnPlayer1 = new JToggleButton("Player1");
		tglbtnPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer1, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 0);
			}
		});
		tglbtnPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JToggleButton tglbtnPlayer2 = new JToggleButton("Player2");

		tglbtnPlayer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer2, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 1);
			}
		});
		tglbtnPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JToggleButton tglbtnPlayer3 = new JToggleButton("Player3");
		tglbtnPlayer3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer3, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 2);
			}
		});
		tglbtnPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JToggleButton tglbtnPlayer4 = new JToggleButton("Player4");
		tglbtnPlayer4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer4, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 3);
			}
		});
		tglbtnPlayer4.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JToggleButton tglbtnPlayer5 = new JToggleButton("Player5");
		tglbtnPlayer5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer5, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 4);
			}
		});
		tglbtnPlayer5.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JToggleButton tglbtnPlayer6 = new JToggleButton("Player6");
		tglbtnPlayer6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tglButtonEvent(tglbtnPlayer6, lblPosition, lblName, ovrLabel, offValue, defValue, stamValue,
						agilValue, 5);
			}
		});
		tglbtnPlayer6.setFont(new Font("Tahoma", Font.PLAIN, 20));

		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(tglbtnPlayer4, GroupLayout.DEFAULT_SIZE, 232,
																Short.MAX_VALUE)
														.addGap(6))
												.addGroup(gl_panel_1.createSequentialGroup()
														.addComponent(tglbtnPlayer1, GroupLayout.DEFAULT_SIZE, 235,
																Short.MAX_VALUE)
														.addGap(3)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(tglbtnPlayer2, GroupLayout.DEFAULT_SIZE, 228,
														Short.MAX_VALUE)
												.addComponent(tglbtnPlayer5, GroupLayout.DEFAULT_SIZE, 228,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
												.addComponent(tglbtnPlayer3, GroupLayout.DEFAULT_SIZE, 230,
														Short.MAX_VALUE)
												.addComponent(tglbtnPlayer6, GroupLayout.DEFAULT_SIZE, 230,
														Short.MAX_VALUE)))
								.addGroup(gl_panel_1.createSequentialGroup().addGap(274)
										.addComponent(lblErrorMsg, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
										.addGap(265)))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap().addComponent(lblErrorMsg).addGap(39)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnPlayer1, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(tglbtnPlayer2, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnPlayer3, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
				.addGap(84)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(tglbtnPlayer4, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(tglbtnPlayer5, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
						.addComponent(tglbtnPlayer6, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
				.addGap(27)));
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);

		ArrayList<JToggleButton> tglBtns = new ArrayList<JToggleButton>();
		tglBtns.add(tglbtnPlayer1);
		tglBtns.add(tglbtnPlayer2);
		tglBtns.add(tglbtnPlayer3);
		tglBtns.add(tglbtnPlayer4);
		tglBtns.add(tglbtnPlayer5);
		tglBtns.add(tglbtnPlayer6);

		setAthleteButtons(tglBtns);
	}

}