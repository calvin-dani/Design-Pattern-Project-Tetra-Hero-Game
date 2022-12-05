/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * THeroBase represents Home base location of Hero.
 * THeroBase act as Concrete Colleague.
 */
class THeroBase extends Location
{
	/**
	 * This baseId corresponds to hero ID.
	 */
	private int baseId;
	
	/**
	 * Initially Home base store null star Map object.
	 */
	public THeroBase() {
		starMap = new nullStarMap();
	}

	/**
	 * Set the home base Id.
	 * @param id
	 * @return 
	 */
	void setLocationId(int id)
	{
		this.locationId = id;
	}
	
	/**
	 * Set the location text.
	 * @param name
	 * @return 
	 */
	void setLocationText(String name)
	{
		this.locationText = name;
	}
	
	/**
	 * Get the location text. 
	 * @return 
	 */
	String getLocationText()
	{
		return locationText;
	}
	
	/**
	 * Get the location.
	 * @return 
	 */
	int getLocation()
	{
		return locationId;
	}

	/**
	 * Get the Image Name.
	 * @return 
	 */
	String getImageName() 
	{
		String name = "heroBase.jpg";
		return name;
	}

	/**
	 * Process the request.
	 * @param tPeople
	 * @return 
	 */
	void processMap(TetraPeople tPeople) 
	{
		// Process request according to different Tetra People.
		if(tPeople instanceof TetraHero) 
		{
			TetraHero thero = (TetraHero)tPeople;

			if(thero.getNewstarMap()!=null)
			{
				starMap = thero.getNewstarMap();
				String s="HERO in HEROBASE with Clone Map";
				createMsg(s);
			}
			else 
			{
				String s="HERO in HEROBASE ";
				createMsg(s);
				
				String currentTxt = null, newTxt = null;

				try 
				{					
//					newImage = ImageIO.read(getClass().getResource("thero.png"));
					newTxt = "HERO PROCESS";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
//				thero.setIcons(locations,currentTxt, newTxt);
			}		
		}
		else if(tPeople instanceof TetraRover) 
		{
			String message = "Rover cannot enter HeroBase";
			createMsg(message);
		}
		else if(tPeople instanceof TetraVader) 
		{
			String message = "Vader cannot enter HeroBase";
			createMsg(message);
		}
	}
}