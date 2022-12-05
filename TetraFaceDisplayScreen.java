import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Abstract class - Receiver(abstract class) in Command Pattern 
 * @author Calvin
 *
 */
abstract class TetraFaceDisplayScreen extends JPanel
{	
	final int rows = 7;
	final int columns = 7;
	protected IdGenerator idGenerator;

	protected JLabel[][] panel =  new JLabel[rows][columns];
	final int numberOfBlocks = 49;

	// Character objects
	protected TetraPeople hero1, hero2, vader, rover1, rover2, hero, vaderR , roverR;

	// Grid location objects for characters.
	protected TFaceGrid tHero1Loc, tHero2Loc;
	protected TFaceGrid tRover1Loc, tRover2Loc, tMap1Loc, tVaderbloc;
	protected TFaceGrid tVaderLoc, tMapLoc, tMapbLoc, tMapb1Loc;
	protected TFaceGrid flyLoc;
	protected TFaceGrid hero1bLoc, hero2bLoc;
	protected Location vaderBaseLoc;
	protected Location hero1BaseLoc, hero2BaseLoc;
	protected Location mapBaseLoc, mapBase1Loc;
	protected TFlier flyIcon;
	protected JOptionPane messageLabel;

//	private StarMapComponent starMap;
	protected StarMapComponent starMap;

	// abstract methods
	abstract void setInitialPositions();

	abstract void startSimulation() throws IOException;


	/**
	 * Constructor
	 */
	public TetraFaceDisplayScreen() 
	{
		super();
		this.idGenerator = IdGenerator.getinstance();
	}


	public JLabel[][] getPanel()
	{
		return panel;
	}
	public TetraPeople getHero1()
	{
		return hero1;
	}

	public TetraPeople getHero2()
	{
		return hero2;
	}
	public TFaceGrid getFlyLoc()
	{
		return flyLoc;
	}

	/**
	 *  Puts the thread to sleep for the specified amount of time.
	 */
	public static void holdClass()
	{
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException ex) 
		{
			System.out.println("problem");
		}
	}

	/**
	 * Display the appropriate message on the screen.
	 * @param msg
	 */
	public void createMsg(String msg)
	{
		String message = "<html><font name='sansserif' size='5'>" + msg +"</font></html>";
		JOptionPane optionPane = new JOptionPane(message
				, JOptionPane.PLAIN_MESSAGE
				, JOptionPane.DEFAULT_OPTION
				, null, null, null);

		JDialog dialog = optionPane.createDialog(null, "SCENARIO");			
		dialog.setLocation(900, 430);
		dialog.setVisible(true);

	}

	/**
	 *  Create the rectangular grid of buttons and disable the buttons
	 */
	public void createTheTetraFace()
	{
		System.out.println("entered the creation method / class");

		this.setBackground(Color.green);

		for (int i = 0; i < rows; i++) 
		{
			for(int j = 0; j < columns; j++)
			{
				panel[i][j] = new JLabel();
				panel[i][j].setBackground(Color.BLACK);
				panel[i][j].setEnabled(false);
			}            
		}

		this.setLayout(new GridLayout(7,7));

		for (int i = 0; i < rows; i++) 
		{
			for(int j = 0; j < columns; j++)
			{
				add(panel[i][j]);
			}
		}

	}	

	/**
	 * Set the positions of vader, rovers, heroes, bases, mapbases 
	 */
	public void setRoversToLocation()
	{
		// set the initial location of the heroes
		hero1.setLocation(panel, tHero1Loc,"Hero 1");
		hero2.setLocation(panel, tHero2Loc,"Hero 2");

		// set the initial location of the rovers
		rover1.setLocation(panel, tRover1Loc,"Rover 1");
		rover2.setLocation(panel, tRover2Loc,"Rover 2");

		// set the location of the vaderbase
		vaderBaseLoc.setBaseGridLocation(panel,tVaderLoc,"Vader Base");

		// set the initial location of the vader.
		vader.setLocation(panel, tVaderLoc,"Vader");

		// sets the id of the heroes.
		hero1.setId(idGenerator.nextId());
		hero2.setId(idGenerator.nextId());


		// set the location of the herobase and set the id to the respective heroid
		hero1BaseLoc.setBaseGridLocation(panel, hero1bLoc, "Hero Base");
		hero1BaseLoc.setLocationId(hero1.getId());

		hero2BaseLoc.setBaseGridLocation(panel, hero2bLoc, "Hero Base");
		hero2BaseLoc.setLocationId(hero2.getId());

		// set the location of the mapbase.
		mapBaseLoc.setBaseGridLocation(panel,tMapbLoc,"Map Base");
		// set the location of the mapbase.
		mapBase1Loc.setBaseGridLocation(panel,tMapb1Loc,"Map Base");
	}

}

/**
 * Default screen. 
 * @author Calvin 
 *
 */
class defaultDisplay extends TetraFaceDisplayScreen
{

	/**
	 *  Constructor
	 */
	public defaultDisplay(){}

	/**
	 *  Set the positions
	 */
	void setInitialPositions() 
	{}

	
	/** Start the simulation - for default no steps are given
	 * @Override
	 */
	void startSimulation() {
		// TODO Auto-generated method stub
	}

}



