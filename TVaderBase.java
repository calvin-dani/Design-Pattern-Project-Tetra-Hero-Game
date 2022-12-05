/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.util.Date;

/**
 * TVaderBase represents Home base location of Vader.
 * TVaderBase act as Concrete Colleague as well as Singleton instance.
 */

class TVaderBase extends Location
{	
	/**
	 * Single VaderBase only.
	 */
	private static TVaderBase tVaderBase = null;

	/**
	 * Initially Home base store null star Map object.
	 */
	private TVaderBase(){
		starMap = new nullStarMap();
	}

	/**
	 * Get the instance of VaderBase.
	 * @return
	 */
	public static TVaderBase getInstance()
	{
		if(tVaderBase == null)
		{	
			tVaderBase = new TVaderBase();
		}
		return tVaderBase;
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
		String name = "map";
		return name;
	}

	/**
	 * Process the request.
	 * @param tPeople
	 * @return 
	 */
	void processMap(TetraPeople tPeople) throws CloneNotSupportedException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		// Process request according to different Tetra People.
		if(tPeople instanceof TetraHero) 
		{
			TetraHero tHero = (TetraHero)tPeople;
			boolean mapPresent = starMap.showSignal(this.getGridLocation());

			if(mapPresent)
			{
				String s="Map Present in VaderBase";
				createMsg(s);
				StarMapComponent newMap = (StarMapComponent)starMap.clone();

				/* Get the unique strategy of hero for encryption */
				String strategy = tHero.getStrategy();

				/* Using reflection to instantiate Encryption strategy */
				EncryptionAlgorithm encryptionAlgo = (EncryptionAlgorithm)Class.forName(strategy).newInstance();

				starMap.setEncryptionAlgorithm(encryptionAlgo);
				starMap.encrypt(tHero.getId(), new Date(), tHero.getSymbol());

				tHero.flyWithMap(starMap, newMap , locations, this.getGridLocation(), tHero.getMapToVader());

			}
		}
		else if(tPeople instanceof TetraRover) 
		{
			String message = "Rover cannot enter VaderBase";
			createMsg(message);
		}
		else if(tPeople instanceof TetraVader) 
		{
			TetraVader tVader = (TetraVader)tPeople;

			if(tVader.getStarMap() != null) {
				String message = "Vader enters its VaderBase with Map";
				createMsg(message);
				this.starMap = tVader.getStarMap();
			}
			else {
				String message = "Vader enters its VaderBase";
				createMsg(message);
				tVader.reInitPath();
			}
		}
	}
}
