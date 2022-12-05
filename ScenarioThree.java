import java.awt.Image;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * TetaStar Simulation
 * 
 * Test case 3 scenario. Shows the movement of vader.
 * @author Calvin
 *
 */
public class ScenarioThree extends TetraFaceDisplayScreen
{
		
	/**
	 *  Constructor
	 */
	public ScenarioThree()
	{}

	/**
	 *  Contains the scenarios of the test case.
	 * @throws IOException 
	 */
	void startSimulation() throws IOException 
	{
		createMsg("Vader flies to a empty location");
		vader.fly(panel, new TFaceGrid(0,4),tVaderLoc);
		holdClass();
		
		hero1.setVaderExit(true);
		
		createMsg("Request Flier: Hero1");
		hero1.requestFly();
		holdClass();
		
		createMsg("FLY TO MAPBASE LOCATION: Hero1");
		hero1.fly(panel, tMapbLoc,tHero1Loc);
		holdClass();
			
		createMsg("Request Flier: Hero2");
		hero2.requestFly();
		holdClass();
		
		createMsg("FLY TO MAPBASE LOCATION: Hero2");
		hero2.fly(panel, tMapbLoc,tHero2Loc);
		holdClass();
					
	}

	/**
	 *  Set the initial position of the characters on the grid.
	 */
	@Override
	void setInitialPositions() 
	{	
		
		// Create different home base objects.
		vaderBaseLoc = TVaderBase.getInstance();
		hero1BaseLoc  = new THeroBase();
		hero2BaseLoc = new THeroBase();
		mapBaseLoc = new TMapBase();
		mapBase1Loc = new TMapBase();
		
		// Create TetraPeople
		RoverFactory rover = new RoverFactory();		
		hero = rover.createRovers("Hero");
		vaderR = rover.createRovers("Vader");
		roverR = rover.createRovers("Rover");

		tHero1Loc = new TFaceGrid(2, 2);
		tHero2Loc = new TFaceGrid(4, 2);
		tRover1Loc = new TFaceGrid(5, 0);
		tRover2Loc = new TFaceGrid(6, 4);
		tVaderLoc = new TFaceGrid(3, 3);
		tMapLoc = new TFaceGrid(3,3);
		tMap1Loc = new TFaceGrid(3,6);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2,6);
		tMapb1Loc = new TFaceGrid(3,6);
		
		setRoversToLocation();	
		
		/* Initializing Mediator */
		TMediator tMediator = new TMediator();
		

		
		/* Initializing StarMaps */
		/* StarAtlas - Case 1 */
		StarAtlas stAtlas1 = new StarAtlas(idGenerator.nextId(), tMapLoc);
		StarMap stMap1 = new StarMap(idGenerator.nextId(), tMapLoc, "Welcome to tetra Planet:StarMap1");
		StarMap stMap2 = new StarMap(idGenerator.nextId(), tMapLoc, "Welcome to tetra Planet:StarMap2");
		/* Get the unique strategy of hero for encryption */
		String strategy = hero1.getStrategy();
		
		/* Using reflection to instantiate Encryption strategy */
		EncryptionAlgorithm encryptionAlgo;
		try {
			encryptionAlgo = (EncryptionAlgorithm)Class.forName(strategy).newInstance();	
			stAtlas1.addStarMap(stMap1);
			stAtlas1.addStarMap(stMap2);
			stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
			stAtlas1.encrypt(hero1.getId(), new Date(), '*');
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vaderBaseLoc.setStarMapComponent(stAtlas1);
		
		/* StarMap - Case 2 */
		StarMap stMap3 = new StarMap(idGenerator.nextId(), tMap1Loc, "Goto north,then east and there is plenty of gold");
		strategy = hero2.getStrategy();
		try {
			encryptionAlgo = (EncryptionAlgorithm)Class.forName(strategy).newInstance();	
			stMap3.setEncryptionAlgorithm(encryptionAlgo);
			stMap3.encrypt(hero2.getId(), new Date(), '#');
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mapBase1Loc.setStarMapComponent(stMap3);
		
		/* Initializing Flier */
		try 
		{
			Image img = ImageIO.read(getClass().getResource("tFlier.jpg"));
			panel[0][6].setIcon(new ImageIcon(img));
			panel[0][6].setDisabledIcon(new ImageIcon(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		flyLoc = new TFaceGrid(4, 6);
	}
	
}