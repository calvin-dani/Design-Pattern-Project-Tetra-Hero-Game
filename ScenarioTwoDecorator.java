import java.io.IOException;

import javax.swing.*;

/**
 * Tetra Star Simulation
 * 
 * Decorator class - ie. wrapper around test case 1.
 * Adding aditional functionality to test case 1
 * @author Calvin
 *
 */
public class ScenarioTwoDecorator extends TestCaseDecorator 
{	
	TetraPeople hero1,rover2;
	JLabel[][] panel;

	/**
	 *  Constructor
	 * @param scenario
	 */
	public ScenarioTwoDecorator(TetraFaceDisplayScreen scenario) 
	{
		super(scenario);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Set the initia POSITIONS
	 */
	@Override
	void setInitialPositions() 
	{
		// TODO Auto-generated method stub
		//scenario.setInitialPositions();
	}

	/**
	 * Start the simulation - steps in the test case.
	 */
	@Override
	void startSimulation() throws IOException 
	{
		// TODO Auto-generated method stub
		try {
			scenario.startSimulation();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		hero1 = scenario.getHero1();
		hero2 = scenario.getHero2();
		panel = scenario.getPanel();
						
		flyLoc = scenario.getFlyLoc();
		
		createMsg("Hero2 tries to fly to mapbase location without flier");
//		hero2.fly(panel, flyLoc);
		holdClass();
		
		createMsg("Hero2 request the flier");
		hero2.requestFly();
		holdClass();
		
		
		createMsg("Hero2 flies to the mapbase location");		
		try 
		{
//			hero2.fly(panel, flyLoc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		holdClass();
		

	}

}
