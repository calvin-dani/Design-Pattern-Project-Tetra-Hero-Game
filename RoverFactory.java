/**
 * TetraGameSimulation
 *  creates instance of TetraRovers accordingly
 * @authors: Calvin
 *
 */
public class RoverFactory 
{
	TetraPeople tetRov = null ;
	
	/**
	 *  Constructor
	 */
	public RoverFactory() {}
	
	/**
	 *  creates instance of TetraRovers accordingly
	 * @param type
	 * @return TetraPeople
	 */
	public TetraPeople createRovers(String type)
	{
		if(type.equals("Hero"))
		{
//			tetRov = new TetraHero(symbol);
			tetRov = TetraHero.getInstance();
		}
		else if (type.equals("Vader"))
		{
			tetRov = TetraVader.getInstance();
		}
		else if(type.equals("Rover"))
		{
			tetRov = TetraRover.getInstance();
//			tetRov = new TetraRover();
		}
		
		return tetRov;
	}
	
	
}
