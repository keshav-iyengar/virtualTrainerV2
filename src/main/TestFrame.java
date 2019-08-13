package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TestFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame();
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
	public TestFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Welcome to Virtual Trainer!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		homePanel.setLayout(null);

		JPanel newWorkoutPanel = new JPanel();
		tabbedPane.addTab("New workout", null, newWorkoutPanel, null);
		GridBagLayout gbl_newWorkoutPanel = new GridBagLayout();
		gbl_newWorkoutPanel.columnWidths = new int[] { 835, 0 };
		gbl_newWorkoutPanel.rowHeights = new int[] { 14, 0, 0 };
		gbl_newWorkoutPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_newWorkoutPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		newWorkoutPanel.setLayout(gbl_newWorkoutPanel);

		JLabel lblNewLabel_1 = new JLabel(
				"Tell the trainer your fitness goals! Hit submit when you're done to see your personalized workout.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		newWorkoutPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

	}
}
