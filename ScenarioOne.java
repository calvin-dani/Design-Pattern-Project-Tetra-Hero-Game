import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Test case 1 scenario.
 * Displays the hero movement in all directions.
 * @author Calvin
 *
 */
public class ScenarioOne extends TetraFaceDisplayScreen
{	
	/**
	 *  Constructor
	 */
	public ScenarioOne()
	{}

	/**
	 *  Contains the scenarios of the test case.
	 * @throws IOException 
	 */
	void startSimulation() throws IOException 
	{
		hero1.makeInitialMove("E", panel, tHero1Loc,"HERO 1");
		holdClass();

		hero1.makeInitialMove("N", panel, tHero1Loc,"HERO 1");
		holdClass();

		hero1.makeMove("N", panel,"HERO 1",tHero1Loc);
		holdClass();

		hero1.makeMove("E", panel,"HERO 1",tHero1Loc);
		holdClass();

		hero2.makeMove("W", panel,"HERO 2",tHero2Loc);
		holdClass();

		hero2.makeMove("E", panel,"HERO 2",tHero2Loc);
		holdClass();

		vader.makeMove("N",panel,"VADER",tVaderLoc);
		holdClass();

		rover1.makeMove("E",panel,"ROVER 1",tRover1Loc);
		holdClass();

		rover2.makeMove("W",panel,"ROVER 2",tRover2Loc);
		holdClass();

		hero1.fly(panel,new TFaceGrid(3,3),tHero1Loc);
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

		hero1.starMap = new nullStarMap();
		hero2.starMap = new nullStarMap();
		vader.starMap = new nullStarMap();

		Image img = null;
		String txt = null;

		tHero1Loc = new TFaceGrid(2, 2);
		tHero2Loc = new TFaceGrid(4, 2);
		tRover1Loc = new TFaceGrid(5, 0);
		tRover2Loc = new TFaceGrid(6, 4);
		tVaderLoc = new TFaceGrid(3, 3);
		tMapLoc = new TFaceGrid(3,3);
		hero1bLoc = new TFaceGrid(0, 0);
		hero2bLoc = new TFaceGrid(6, 6);
		tMapbLoc = new TFaceGrid(2,1);
		tMapb1Loc = new TFaceGrid(4,6);

		/* TODO: ID Generator */
		StarAtlas stAtlas1 = new StarAtlas(10, tMapLoc);
		StarMap stMap1 = new StarMap(11, tMapLoc, "Welcome to tetra Planet");
		stAtlas1.addStarMap(stMap1);
		vaderBaseLoc.setStarMapComponent(stAtlas1);

		txt = "Tetra Flier";
		panel[0][6].setText(txt);

		//set the fly location
		flyLoc = new TFaceGrid(4, 6);

		this.setRoversToLocation();	

	}

}