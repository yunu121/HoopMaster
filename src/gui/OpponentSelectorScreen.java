package gui;

import main.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * This is the OpponentSelectorScreen. This screen provides 3 opponent teams to
 * choose from. When a team is clicked, the players in that team and their stats
 * are shown to the player.
 * 
 * @author Yunu Cho
 * @author Kush Desai
 * 
 */
public class OpponentSelectorScreen {
	/**
	 * Frame of the MainScreen.
	 */
	private JFrame frame;
	/**
	 * The current instance of the GameManager object.
	 */
	private GameManager manager;
	/**
	 * The opposing Team.
	 */
	private Team opposingTeam;

	/**
	 * Constructs a new OpponentSelectorScreen object with the given GameManager.
	 * 
	 * @param gameManager the current instance of the GameManager object
	 */
	public OpponentSelectorScreen(GameManager gameManager) {
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
					OpponentSelectorScreen window = new OpponentSelectorScreen();
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
	public OpponentSelectorScreen() {
		initialize();
	}

	/**
	 * Closes the window.
	 */
	public void closeWindow() {
		frame.dispose();
	}

	/**
	 * Calls closeGameScreen within the GameManager object which opens the next
	 * window.
	 */
	public void finishedWindow() {
		manager.closeOpponentSelectorScreen(this);
	}

	/**
	 * Sets the JButtons showing information based on the generated opponent Teams
	 * for the current week.
	 * 
	 * @param btns list of JButtons that will be changed
	 */
	public void setTeamButtons(ArrayList<JButton> btns) {
		for (int i = 0; i < btns.size(); i++) {
			btns.get(i).setText(manager.getWeeklyTeams().get(i).getTeamName());
		}
	}

	/**
	 * Sets the Label text when a JButton is pressed, displaying the information of
	 * an Athlete that is linked to its corresponding JButton.
	 * 
	 * @param teamName the name of the opposing Team
	 * @param player1  label that will be set to display the information of the
	 *                 opposing Athlete of the Point Guard position
	 * @param player2  label that will be set to display the information of the
	 *                 opposing Athlete of the Shooting Guard position
	 * @param player3  label that will be set to display the information of the
	 *                 opposing Athlete of the Small Forward position
	 * @param player4  label that will be set to display the information of the
	 *                 opposing Athlete of the Power Forward position
	 * @param player5  label that will be set to display the information of the
	 *                 opposing Athlete of the Center position
	 * @param index    index of the opposing Team that has been selected
	 */
	public void btnEvent(JLabel teamName, JLabel player1, JLabel player2, JLabel player3, JLabel player4,
			JLabel player5, int index) {
		teamName.setText(manager.getWeeklyTeams().get(index).getTeamName());
		player1.setText(manager.getWeeklyTeams().get(index).getPlayersMap().get(Athlete.POSITION.PG).toString());
		player2.setText(manager.getWeeklyTeams().get(index).getPlayersMap().get(Athlete.POSITION.SG).toString());
		player3.setText(manager.getWeeklyTeams().get(index).getPlayersMap().get(Athlete.POSITION.SF).toString());
		player4.setText(manager.getWeeklyTeams().get(index).getPlayersMap().get(Athlete.POSITION.PF).toString());
		player5.setText(manager.getWeeklyTeams().get(index).getPlayersMap().get(Athlete.POSITION.C).toString());
		opposingTeam = manager.getWeeklyTeams().get(index);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 64));
		frame.setBounds(100, 100, 1200, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.launchStadiumScreen();
				finishedWindow();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!manager.getTeam().canPlay()) {
					String message = "Not all starters are ready to play!";
					JOptionPane.showMessageDialog(new JFrame(), message, "Team Error", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						manager.setOpposingTeam(opposingTeam);
						manager.launchGameScreen();
						finishedWindow();
					}

					catch (NullPointerException error) {
						String message = "Please select an Opponent!";
						JOptionPane.showMessageDialog(new JFrame(), message, "Opponent Selection Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));

		JLabel lblTitle = new JLabel("Choose a team to play against");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JSeparator separator = new JSeparator();

		JLabel lblTeamName = new JLabel("");
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JLabel lblPlayer1 = new JLabel("");
		lblPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlayer2 = new JLabel("");
		lblPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlayer3 = new JLabel("");
		lblPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlayer4 = new JLabel("");
		lblPlayer4.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLabel lblPlayer5 = new JLabel("");
		lblPlayer5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(1).addComponent(separator, GroupLayout.DEFAULT_SIZE,
						435, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblTeamName, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE).addGap(244))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup().addContainerGap().addGroup(gl_panel
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPlayer4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(lblPlayer3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(lblPlayer2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(lblPlayer1, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)).addGap(14))
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(lblPlayer5, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(79)
						.addComponent(lblTeamName, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE).addGap(18)
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblPlayer1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblPlayer2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblPlayer3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblPlayer4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(lblPlayer5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(158)));
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(btnPlay,
								GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(10)
								.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addGap(62)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 529,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
												.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 522,
														GroupLayout.PREFERRED_SIZE)
												.addGap(161).addComponent(panel, GroupLayout.PREFERRED_SIZE, 342,
														Short.MAX_VALUE)))))
						.addGap(42)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(10)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(btnBack)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 0, Short.MAX_VALUE)))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(btnPlay, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE).addGap(29)));

		JButton btnTeam1 = new JButton("Team 1");
		btnTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEvent(lblTeamName, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5, 0);
			}
		});

		JButton btnTeam2 = new JButton("Team 2");
		btnTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEvent(lblTeamName, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5, 1);
			}
		});

		JButton btnTeam3 = new JButton("Team 3");
		btnTeam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEvent(lblTeamName, lblPlayer1, lblPlayer2, lblPlayer3, lblPlayer4, lblPlayer5, 2);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addComponent(btnTeam1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE).addGap(26)
						.addComponent(btnTeam2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
						.addComponent(btnTeam3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(127)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTeam1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnTeam2, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnTeam3, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(128, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);

		ArrayList<JButton> btns = new ArrayList<JButton>();
		btns.add(btnTeam1);
		btns.add(btnTeam2);
		btns.add(btnTeam3);
		setTeamButtons(btns);
	}
}
