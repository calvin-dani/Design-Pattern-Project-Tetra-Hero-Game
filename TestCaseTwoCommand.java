import java.io.IOException;

/**
 * TetraStarSimulation
 * Command Pattern - concrete Command. Calls the method of receiver.
 * @author Calvin
 *
 */
public class TestCaseTwoCommand implements Command
{
	TetraFaceDisplayScreen scene;

	/**
	 * Constructor
	 * @param scene
	 */
	public TestCaseTwoCommand(TetraFaceDisplayScreen scene)
	{
		this.scene = scene;
	}

	/**
	 * Call the method of receiver. which here is the concerte decorator class.
	 */
	@Override
	public void execute() 
	{
		// TODO Auto-generated method stub
		scene = new ScenarioTwoDecorator(scene);		
		try {
			scene.startSimulation();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
