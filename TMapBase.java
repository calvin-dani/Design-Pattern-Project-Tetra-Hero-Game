/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.io.IOException;

/**
 * TMapBase represents Home base location of Maps.
 * TMapBase act as Concrete Colleague.
 */

class TMapBase extends Location
{
	/**
	 * Vaderbase Location.
	 */
	TFaceGrid vaderloc;

	/**
	 * Initially Home base store null star Map object.
	 */
	public TMapBase()
	{
		starMap = new nullStarMap();
	}

	/**
	 * Get the Image Name.
	 * @return 
	 */
	String getImageName() 
	{
//		String name = "MapBase.jpg";
		String name = "map";
		return name;
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
	 * Process the request.
	 * @param tPeople
	 * @return 
	 */
	void processMap(TetraPeople tPeople) throws IOException 
	{
		// Process request according to different Tetra People.
		if(tPeople instanceof TetraHero) 
		{
			TetraHero thero = (TetraHero)tPeople;
			if(thero.getOldstarMap()==null)
			{
				boolean mapPresent = starMap.showSignal(this.getGridLocation());
				if(mapPresent)
				{
					if(starMap.isEncrypted())
					{
						if(starMap.isEncryptedByMe(tPeople.getId()))
						{
							String s="HERO enters MapBASE and encrypted by him";
							createMsg(s);
							starMap.decrypt(tPeople.getId());
							starMap.display();
						}
						else
						{
							String s="HERO enters MapBASE and encrypted by other hero";
							createMsg(s);
							starMap.encrypt(tPeople.getId(), null,'\0');
							starMap.display();
						}
					}
					else
					{
						String s="HERO enters MapBASE and there exists original map";
						createMsg(s);
						starMap.display();
					}
				}
				else
				{
					String s="HERO enters MapBASE, but there is no map";
					createMsg(s);
					vaderloc = new TFaceGrid(3,3);
					thero.setMapToVader(this.getGridLocation());

//					thero.fly(locations,vaderloc,theroloc);
				}
			}
			else
			{
				String message = "HERO enters MapBASE with Encrypted MAP";
				createMsg(message);
				starMap = thero.getOldstarMap();
				starMap.setLocation(this.getGridLocation());
				
				if(thero.getId() == 1)
				{
//					thero.fly(locations, new TFaceGrid(0,0));
				}
				else
				{
//					thero.fly(locations, new TFaceGrid(6,6));
				}
			}
		}
		else if(tPeople instanceof TetraRover) 
		{
			String message = "Rover cannot enter MapBase";
			createMsg(message);

		}
		else if(tPeople instanceof TetraVader) 
		{
			TetraVader tVader = (TetraVader)tPeople;
			TFaceGrid oldLoc, newLoc;
			
			String message = "Vader enters MapBase to steal map";
			createMsg(message);
			
			tVader.setBackTrackPathStatus(true);
			
			// Vader flies back to its Vaderbase by backtracking its path.
			oldLoc = getGridLocation();
			newLoc = tVader.extractFromPath();
			while(newLoc != null) {
				message = "Vader moving to VaderBase";
				createMsg(message);
				tVader.flyWithMap(starMap, locations, oldLoc, newLoc); 
				oldLoc = newLoc;
				newLoc = tVader.extractFromPath();
			}
			// Map set to nullObject
			this.starMap = new nullStarMap();
		}
	}
}

