import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.openmbean.ArrayType;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

/**
 * Tetra Star Simulation
 * Contains logic for hero movement and flight.
 * @author Calvin
 *
 */
public class TetraHero extends TetraPeople
{
	private int rowNumber;
	private int colNumber;
	private String heroText = "HERO";
	private int heroId;
	private int heroLocation;
	private Image img, currentImage, newImage = null;
	private String txt, currentTxt, newTxt;
	private TFlier flyVehicle;	
	private char symbol;
	private StarMapComponent oldstarMap = null; // Original map - encrypted
	private StarMapComponent NewstarMap = null; // cloned
	private TFaceGrid mapToVader;
	private String strategy;
	private boolean enteredMapbase = false;
	private boolean enteredHomebase = false;
	private String heroType;

	private static TetraHero hero = null;

	private HeroMetaData heroArr[] = new HeroMetaData[2];

	/**
	 * Constructor 
	 */
	public TetraHero()
	{
		peopleObservable = new TetraPeopleObservable();
//		this.symbol = Symbol;
		strategy = "simpleEncryption";
		oldLocation = new TFaceGrid();
	}
	/**
	 *  Get the strategy
	 */
	public String getStrategy() {
		return strategy;
	}
	/**
	 * Set the map to vader loc
	 * @param loc
	 */
	public void setMapToVader(TFaceGrid loc)
	{
		mapToVader = loc;
	}

	/**
	 *  Get the old star map
	 * @return StarMapComponent
	 */
	public StarMapComponent getOldstarMap() {
		return oldstarMap;
	}

	/**
	 *  Set the old star map
	 * @param oldstarMap
	 */
	public void setOldstarMap(StarMapComponent oldstarMap) {
		this.oldstarMap = oldstarMap;
	}

	/**
	 * Get the new star map after encryption
	 * @return StarMapComponent
	 */
	public StarMapComponent getNewstarMap() {
		return NewstarMap;
	}

	/**
	 * Set the new star map
	 * @param newstarMap
	 */
	public void setNewstarMap(StarMapComponent newstarMap) {
		NewstarMap = newstarMap;
	}

	/**
	 * Get the map to vader location.
	 * @return
	 */
	public TFaceGrid getMapToVader()
	{
		return mapToVader;
	}
	public int getId()
	{
		return heroId;
	}
	/**
	 * Get the symbol
	 * @return
	 */
	public char getSymbol()
	{
		return symbol;
	}

	@Override
	public void setId(int id) 
	{
		heroId = id;		
	}


	/**
	 * Set the icons in the given locations
	 * @param locations
	 * @param currentTxt
	 * @param newTxt
	 */
	public void setIcons(JLabel[][] locations,String currentTxt, String newTxt,TFaceGrid currentLocation,TFaceGrid newLocation)
	{
		// TODO = change back the code if problem of button text appears.
		if(currentTxt == null)
		{
			locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");
			locations[currentLocation.getRow()][currentLocation.getColumn()].repaint();
		}
		else
		{
			locations[currentLocation.getRow()][currentLocation.getColumn()].setText(currentTxt);
			locations[currentLocation.getRow()][currentLocation.getColumn()].repaint();
		}

		locations[newLocation.getRow()][newLocation.getColumn()].setText(newTxt);
		locations[newLocation.getRow()][newLocation.getColumn()].repaint();

		currentLocation.setRow(newLocation.getRow());
		currentLocation.setColumn(newLocation.getColumn());

	}

	/**
	 *  Set the initial locations of hero
	 */
	public void setLocation(JLabel[][] locations, TFaceGrid loc,String type)
	{
		rowNumber = loc.getRow();
		colNumber = loc.getColumn();	
		heroType = type;

		try 
		{
			if(type.equals("Hero 1"))
			{	
				txt = "HERO 1";
			}
			else {
				txt = "HERO 2";
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setText(txt);
		locations[rowNumber][colNumber].repaint();

		currentLocation = loc;
	}

	/**
	 * Check if something present at the given location
	 * @param locations
	 * @param loc
	 * @return
	 */
	public String checkLocation(JLabel[][] locations, TFaceGrid loc)
	{
		// TODO Auto-generated method stub
		int row = loc.getRow();
		int col = loc.getColumn();
		String characterObject;

		if(locations[row][col].getText().equals(""))
				characterObject = "NO CHARACTERS";
		else 
			characterObject = locations[row][col].getText();

		return characterObject;

	}

	/**
	 *  Fly to a given location
	 */
	void fly(JLabel[][] locations, TFaceGrid loc, TFaceGrid curLoc) throws IOException
	{
		String flyTxt = null;
		ArrayList newSetLocation;


		if(flyVehicle == null)
		{
			String s=" Hero cannot fly without the flier. Request flier";
			createMsg(s);
		}
		else
		{
			String characterObj = checkLocation(locations, loc);
			String currcharacterObj = checkLocation(locations,curLoc );

			if(characterObj.equals("NO CHARACTER") || characterObj.equals("NO CHARACTERS") ||characterObj.equals("HEROBASE") || characterObj.equals("Map Base") || characterObj.equals("VADERBASE") || characterObj.equals("Tetra Vader"))
			{
				oldLocation = curLoc;
				currentLocation = loc;

				if(currcharacterObj.equals("HERO 1"))
					newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, curLoc, loc, "HERO 1");
				else if(currcharacterObj.equals("HERO 2"))
					newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, curLoc, loc, "HERO 2");


				// Set the old and the new locations of hero


				String oldLocationText = locations[oldLocation.getRow()][oldLocation.getColumn()].getText();
				// Check if the hero had entered the Mapbase anytime.
				if(enteredMapbase)
				{
					if(oldLocationText.equals("MAPBASE"))
				{
					flyTxt = "Map Base";
					locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
				}
				else if(oldLocationText.equals("VADERBASE"))
				{
					if(vaderExit)
						flyTxt = "Vader House";
					else
						flyTxt = "TVader";
					locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
				}
				else if (oldLocationText.equals("empty"))
				{
					locations[oldLocation.getRow()][oldLocation.getColumn()].setIcon(null);
				}
				else if (oldLocationText.equals("HEROBASE"))
				{
					flyTxt = "Hero Base";
					locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
				}

				}
				else
				{
					locations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
				}
				oldLocation.setRow(currentLocation.getRow());
				oldLocation.setColumn(currentLocation.getColumn());
				if(! characterObj.equals("NO CHARACTER") && ! characterObj.equals("NO CHARACTERS")) {

					if (characterObj.equals("Tetra Vader")){
						StarMap starMap = new StarMap(11, loc, "Welcome to tetra Planet");
						Boolean isThere = starMap.showSignal(curLoc);
						if(isThere){
							Date dateNow = new Date();
							starMap.encrypt(1,dateNow,'a');
							String message = "Encrypting star Map";
							createMsg(message);
//							this.fly(locations,curLoc,new TFaceGrid(2,1));
							starMap.setLocation(new TFaceGrid(2,1));
//							this.fly(locations,new TFaceGrid(2,1),new TFaceGrid(0, 0));
						}
					}
					if(characterObj.equals("Map Base"))
					{
						Date dateNow = new Date();
//						starMap.encrypt(1,dateNow,'a');
						createMsg("Encrypted by HERO 1");
					}
				}
			}
		}
	}	
	/**
	 *  Request to fly
	 */
	void requestFly()
	{

		flyVehicle = new TFlier();
	}

	/**
	 *  Check location and then make a move
	 * @param locations
	 * @param newLocation
	 */
	public void checkAndMove(JLabel[][] locations, TFaceGrid newLocation, String heroNum, TFaceGrid currentLocation)
	{
		String  characterObject = checkLocation(locations, newLocation);
		if(characterObject.equals("NO CHARACTERS"))
		{
			try
			{
				System.out.println("Hero wants to move " + newLocation.getRow() + "  " + newLocation.getColumn());
				if(heroNum.equals("HERO 1")){
					newTxt = "HERO 1";
				}
//					newImage = ImageIO.read(getClass().getResource("thero.png"));
				else{
					newTxt = "HERO 2";
				}
//					newImage = ImageIO.read(getClass().getResource("hero2.jpg"));
				if(enteredHomebase == true)
				{
					currentTxt = "Hero Base";
//					currentImage = ImageIO.read(getClass().getResource("heroBase.jpg"));
					enteredHomebase = false;
				}
				else{
					currentTxt = null;
				}
//					currentImage = null;

			} 
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			setIcons(locations,currentTxt, newTxt,currentLocation,newLocation);
		}
		else if (characterObject.equals("HEROBASE") || characterObject.equals("MAPBASE"))
		{
			System.out.println("TetraHero:CheckandMove");
			PeopleNotify notification = new PeopleNotify();
			notification.people = this;
			notification.baseLoc = newLocation;
			if(characterObject.equals("HEROBASE"))
			{
				notification.type = "HEROBASE";
				enteredHomebase = true;
			}

			else
				notification.type = "MAPBASE";

			// Notify observers that the hero has entered the base.
			peopleObservable.setChanged();
			peopleObservable.notifyObservers(notification);
		}
		else if (characterObject.equals("VADERBASE"))
		{
			String s="HERO needs a fly vehicle to enter the vaderbase.";
			createMsg(s);			
		}    		
		else
		{
			String s=heroNum + " has TFACE occupied. Making the next move";
			createMsg(s);
		}

	}
	/**
	 *  Fly with the map to a given location.
	 * @param originalMap
	 * @param newMap
	 * @param locations
	 * @param currentLoc
	 * @param newLoc
	 */
	public void flyWithMap(StarMapComponent originalMap, StarMapComponent newMap ,JLabel[][] locations, TFaceGrid currentLoc, TFaceGrid newLoc)
	{
		this.oldstarMap = originalMap;
		this.NewstarMap = newMap;


		try {
			this.fly(locations,newLoc,currentLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Get an instance of TetraVader.
	 * @return TetraPeople
	 */
	public static TetraPeople getInstance() {

		// TODO Auto-generated method stub
		if (hero == null) {
			hero = new TetraHero();
			hero.heroArr[0] = new HeroMetaData("Hero 1","!", "M");
			hero.heroArr[1] = new HeroMetaData("Hero 2","@" , "F");
		}
		return hero;
	}
}

class HeroMetaData{
	String heroName = null;
	String heroSymbol = null;
	String gender =  null;

	HeroMetaData(String name, String symbol, String gender){
		this.heroName = name;
		this.heroSymbol = symbol;
		this.gender = gender;

	}
}

