import java.awt.Image;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * TetraGameSimulation
 * 
 * Test case 4 scenario - Shows the movement of hero getting the map back from 
 * vaderbase and another hero reaching the same mapbase as hero1.
 * 
 * @author Calvin
 *
 */
public class ScenarioFour extends TetraFaceDisplayScreen
{	
	/**
	 *  Constructor
	 */
	public ScenarioFour()
	{}

	/**
	 *  Contains the scenarios of the test case.
	 * @throws IOException 
	 */
	void startSimulation() throws IOException 
	{	
		createMsg("Vader flies to North");
		vader.fly(panel, new TFaceGrid(1,2),tVaderLoc);
		holdClass();
		
		createMsg("Vader moves to East");
		vader.makeMove("E", panel,"VADER",tVaderLoc);
		
		createMsg("vader fly");
		try 
		{
			vader.fly(panel, tMapbLoc,tVaderLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hero1.requestFly();
		hero1.fly(panel,new TFaceGrid(2,1),tHero1Loc);



	}

	/**
	 *  Set the initial position of the characters on the grid.
	 */
	@Override
	void setInitialPositions() 
	{	
		
		// Create the different base location objects.
		vaderBaseLoc = TVaderBase.getInstance();
		hero1BaseLoc  = new THeroBase();
		hero2BaseLoc = new THeroBase();
		mapBaseLoc = new TMapBase();
		mapBase1Loc = new TMapBase();
		
		// create instances of heroes , vader and rovers.
		RoverFactory rover = new RoverFactory();		
		hero1 = rover.createRovers("Hero");
		hero2 = rover.createRovers("Hero");
		vader = rover.createRovers("Vader");
		rover1 = rover.createRovers("Rover");
		rover2 = rover.createRovers("Rover");

//		Image img = null;
		String txt = null;
		
		tHero1Loc = new TFaceGrid(2, 2);
		tHero2Loc = new TFaceGrid(4, 2);
		tRover1Loc = new TFaceGrid(5, 0);
		tRover2Loc = new TFaceGrid(6, 4);
		tVaderLoc = new TFaceGrid(3, 3);
		tVaderbloc = new TFaceGrid(3, 3);
		tMapLoc = new TFaceGrid(2,6);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2,6);
		tMapb1Loc = new TFaceGrid(4,6);
		
		this.setRoversToLocation();	
		
		/* TODO: ID Generator */
		StarAtlas stAtlas1 = new StarAtlas(10, tMapLoc);
		StarMap stMap1 = new StarMap(11, tMapLoc, "Welcome to tetra Planet");
		/* Get the unique strategy of hero for encryption */
		String strategy = hero1.getStrategy();
		
		/* Using reflection to instantiate Encryption strategy */
		EncryptionAlgorithm encryptionAlgo;
		try {
			encryptionAlgo = (EncryptionAlgorithm)Class.forName(strategy).newInstance();	
			stAtlas1.addStarMap(stMap1);
			stAtlas1.setEncryptionAlgorithm(encryptionAlgo);
			//stAtlas1.encrypt(hero1.getId(), new Date(), '*');
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
		
		mapBaseLoc.setStarMapComponent(stAtlas1);
				
		TMediator tMediator = new TMediator();
		
		// Register the tetra people to mediator
		tMediator.registerTPeople(hero1);
		tMediator.registerTPeople(hero2);
		tMediator.registerTPeople(vader);
		tMediator.registerTPeople(rover1);
		tMediator.registerTPeople(rover2);
		
		
		try 
		{
//			img = ImageIO.read(getClass().getResource("tFlier.jpg"));
			txt = "Tetra Flier";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		panel[0][6].setText(txt);
//		panel[0][6].setDisabledIcon(new ImageIcon(img));
		
		flyLoc = new TFaceGrid(4, 6);
		
		
		// Register the locations to the mediator
		tMediator.registerTHomeBase(hero1BaseLoc);	
		tMediator.registerTHomeBase(hero2BaseLoc);
		tMediator.registerTHomeBase(vaderBaseLoc);
		tMediator.registerTHomeBase(mapBaseLoc);
		
	}
	
}