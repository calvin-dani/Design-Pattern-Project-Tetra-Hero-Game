import java.io.IOException;

/**
 * TetraStarSimulation
 * Command Pattern - concrete Command. Calls the method of receiver.
 * @author Calvin
 *
 */
public class TestCaseOneCommand implements Command 
{
	TetraFaceDisplayScreen sceneOne;

	/**
	 * Constructor
	 * @param sceneOne
	 */
	public TestCaseOneCommand(TetraFaceDisplayScreen sceneOne)
	{
		this.sceneOne = sceneOne;
	}
	
	/**
	 * Call the method of receiver.
	 */
	@Override
	public void execute() 
	{
		// TODO Auto-generated method stub
	    try {
				sceneOne.startSimulation();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
}
