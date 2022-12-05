
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

/**  TetraGameSimulation
 * 
 * This is the main display screen which displays the grids and characters.
 * Also provides option to choose test cases and also buttons to set screen 
 * and start the simulation.
 * 
 * @author Calvin
 */
public class MainDisplay extends JFrame implements DisplayScreen
{
	private TetraFaceDisplayScreen screen;
	private Command testCaseOneCommand;
	private Command testCaseTwoCommand;
	private TestCaseInvoker testInvoker;
	private JFrame mainFrame;
	private JComboBox testCaseCombo;
	final int numberOfTestCases = 4;
	private JButton start, reset, setScreen;
	private JPanel sidePanel;
	private JLabel testCaseDesc;
	private JOptionPane messageLabel;
	private String testCaseName = null;

	/**
	 * Constructor
	 */
	public MainDisplay() {}

	/**
	 *  Starts the game by setting the screen with the grids and characters.
	 */
	public void startGame()
	{
		//Create and set up the window.

		sidePanel = createSidePanel();

		screen = new defaultDisplay();
		screen.createTheTetraFace();
		screen.setInitialPositions();

		mainFrame = new JFrame("TetraRoverGame Simulation");
		mainFrame.pack();

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
		mainFrame.getContentPane().add(sidePanel , BorderLayout.NORTH);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH); 
		mainFrame.setVisible(true);

		// Sets the screen to default display 
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				screen = new defaultDisplay();
				screen.createTheTetraFace();
				screen.setInitialPositions();
				mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
				mainFrame.setVisible(true);
			}
		});

		// Gets the testcase name choosen.
		testCaseCombo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox testCase = (JComboBox)e.getSource();
				testCaseName = (String)testCase.getSelectedItem();	                   	            	    	    	    
			}
		});

		// Starts the simulation.
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Thread(() ->{
					if(testCaseName.equals("Scenario 1"))
					{
						screen = new ScenarioOne();
						screen.createTheTetraFace();
						screen.setInitialPositions();
						mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
						mainFrame.setVisible(true);
						String text = " Case 1 : Hero1 moves in all directions";
						testCaseDesc.setText(text);
						testCaseDesc.setFont(new Font("Serif", Font.BOLD, 25));
						testCaseDesc.setForeground(Color.white);
						testCaseOneCommand = new TestCaseOneCommand(screen);
						testInvoker = new TestCaseInvoker(testCaseOneCommand);
						testInvoker.invoke();
					}
					else if(testCaseName.equals("Scenario 2"))
					{
						testCaseOneCommand = new TestCaseOneCommand(screen);
						testInvoker = new TestCaseInvoker(testCaseOneCommand);
						testInvoker.invoke();

					}
					else if(testCaseName.equals("Scenario 3"))
					{
						testCaseOneCommand = new TestCaseOneCommand(screen);
						testInvoker = new TestCaseInvoker(testCaseOneCommand);
						testInvoker.invoke();

					}
				}).start();

			}
		});

		// Sets the screen depending upon the case choosen.
		setScreen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(testCaseName.equals("Scenario 1"))
				{	    			
					screen = new ScenarioOne();		    			
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = " Case 1 : Hero1 moves in all directions";					
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 25));
					testCaseDesc.setForeground(Color.white);

				}
				else if(testCaseName.equals("Scenario 2"))
				{
					screen = new ScenarioFour();
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = "<html>Case 3: Vader moving towards mapbase, stealing map<br> and returning to vaderbase</html>";
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 20));
					testCaseDesc.setForeground(Color.white);

				}
				else if(testCaseName.equals("Scenario 3"))
				{
					screen = new ScenarioFive();
					screen.createTheTetraFace();
					screen.setInitialPositions();
					mainFrame.getContentPane().add(screen, BorderLayout.CENTER);
					mainFrame.setVisible(true);
					String text = "<html>Case 3: Vader moving towards mapbase, stealing map<br> and returning to vaderbase</html>";
					testCaseDesc.setText(text);
					testCaseDesc.setFont(new Font("Serif", Font.BOLD, 20));
					testCaseDesc.setForeground(Color.white);

				}
			}
		});


	}

	/**
	 * Creates the side panel containing the buttons and combo box.
	 * @return Jpanel
	 */
	private JPanel createSidePanel()
	{
		sidePanel = new JPanel();
		sidePanel.setLayout(new FlowLayout());
		sidePanel.setBackground(Color.GREEN);
		sidePanel.setPreferredSize(new Dimension(550,100));


		JLabel message1 = new JLabel("TetraRover Game\n");
		message1.setFont(new Font("Serif", Font.BOLD, 25));	  
		message1.setForeground(Color.white);
		sidePanel.add(message1);
		sidePanel.add(Box.createRigidArea(new Dimension(50, 70)));

		JLabel message2 = new JLabel("Game Scenario :\n");
		message2.setFont(new Font("Serif", Font.BOLD, 20));
		message2.setForeground(Color.white);
		sidePanel.add(message2);		
		sidePanel.add(Box.createRigidArea(new Dimension(50, 0)));

		String[] testCases =  new String[numberOfTestCases];
		for(int i = 0 ; i < numberOfTestCases ; i++ )
		{
			if(i==0)
				continue;
			else
				testCases[i] = "Scenario " +  (i);
		}

		testCaseCombo = new JComboBox(testCases);
		testCaseCombo.setSize(30, 50);
		testCaseCombo.setBackground(Color.white);
		testCaseCombo.setForeground(Color.black);
		sidePanel.add(testCaseCombo);	
		sidePanel.add(Box.createRigidArea(new Dimension(70, 100)));

		start = new JButton("Start Game");
		start.setActionCommand("START SIMULATION");

		reset = new JButton("Reset");
		reset.setActionCommand("RESET SCREEN");

		setScreen = new JButton("Set Display Screen");
		setScreen.setActionCommand("SET SCREEN");

		testCaseDesc = new JLabel();
				
		sidePanel.add(setScreen);
		sidePanel.add(Box.createRigidArea(new Dimension(50, 50)));
		sidePanel.add(start);
		sidePanel.add(Box.createRigidArea(new Dimension(50, 50)));
		sidePanel.add(reset);
		sidePanel.add(Box.createRigidArea(new Dimension(500, 60)));
		sidePanel.add(testCaseDesc);
		

		return sidePanel;

	}	

}


