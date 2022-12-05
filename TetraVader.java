import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * TVader is a singleton class.
 * @author Calvin
 *
 */
public class TetraVader extends TetraPeople
{
	private int rowNumber;
	private int colNumber;
	private String vaderText = "VADER";
	private int vaderId;
	private int vaderLocation;
	private Image img, img2 = null;
	private String txt,txt_Two = null;
	private TFlier flyVehicle;
	private StarMapComponent starMap;
	private boolean enteredMapbase = false;
	private Stack<TFaceGrid> backTrackPath;
	private boolean backTrackPathStatus = false;
	
	private static TetraVader vader = null;
		

	/**
	 *  Constructor
	 */
	private TetraVader()
	{
		peopleObservable = new TetraPeopleObservable();
		backTrackPath = new Stack<TFaceGrid>();
		addToPath(new TFaceGrid(3,3));
		flyVehicle = new TFlier();
	}

	/**
	 * Get star map
	 * @return
	 */
	public StarMapComponent getStarMap() {
		return starMap;
	}

	/**
	 *  set the map
	 * @param starMap
	 */
	public void setStarMap(StarMapComponent starMap) {
		this.starMap = starMap;
	}

	/**
	 *  set the back track status 
	 * @param status
	 */
	public void setBackTrackPathStatus(boolean status) {
		backTrackPathStatus = status;
	}
	
	/**
	 *  Add the location to paths 
	 * @param newLoc
	 */
	public void addToPath(TFaceGrid newLoc) {
		if(newLoc != null) {
			backTrackPath.push(newLoc);
		}
	}
	/**
	 * Extract from path a location
	 * @return  TFaceGrid
	 */
	public TFaceGrid extractFromPath() {
		if(!backTrackPath.isEmpty()) {
			return backTrackPath.pop();
		}
		return null;
	}
	
	/**
	 *  Initialize the path
	 */
	public void reInitPath() {
		while(!backTrackPath.isEmpty()) {
			backTrackPath.pop();
		}
		addToPath(new TFaceGrid(3,3));
	}
	
//	@Override
//	/**
//	 *  Calls the appropriate move depending upon the direction given.
//	 */
//	void makeMove(String direction, JButton[][] locations)
//	{
//		// If the movement is in north direction.
//		if(direction.equals("NORTH"))
//		{
//			makeNorthMove(locations);
//		}
//
//		if(direction.equals("SOUTH"))
//		{
//			makeSouthMove(locations);
//		}
//
//		if(direction.equals("EAST"))
//		{
//			makeEastMove(locations);
//		}
//
//		if(direction.equals("WEST"))
//		{
//			makeWestMove(locations);
//		}
//	}

	/**
	 * Moves the TVader in the North direction by one step.
	 * @param locations
	 */
//	private void makeNorthMove(JButton[][] locations)
//	{
//		int newRow = currentLocation.getRow() - 1;
//		if(newRow < 0)
//		{
//			String s="Cannot move North. Out of the Grid";
//			JOptionPane.showMessageDialog(null,s, "Test Case Simulation"
//					, JOptionPane.PLAIN_MESSAGE);
//		}
//		else
//		{
//			newLocation = new TFaceGrid(newRow, currentLocation.getColumn());
//
//			checkAndMove(locations, newLocation);
//		}
//	}

	/**
	 * Moves Thero in south direction by one step.
	 * @param locations
	 */

	/**
	 * MOves THero in East direction by one step.
	 * @param locations
	 */

	/**
	 * MOves THero in WEst direction by one step.
	 * @param locations
	 */

	/**
	 *  Set Icons in given location.
	 * @param locations
	 */
	private void setIcons(JLabel[][] locations,String vaderNum, TFaceGrid oldLoc, TFaceGrid newLoc)
	{
		locations[currentLocation.getRow()][currentLocation.getColumn()].setText(null);
//		locations[currentLocation.getRow()][currentLocation.getColumn()].setIcon(null);
//		locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");

		locations[newLocation.getRow()][newLocation.getColumn()].setText(txt);
//		locations[newLocation.getRow()][newLocation.getColumn()].setIcon(new ImageIcon(img));
//		locations[newLocation.getRow()][newLocation.getColumn()].setDisabledIcon(new ImageIcon(img));

		oldLoc.setColumn(newLoc.getColumn());
		oldLoc.setRow(newLoc.getRow());
	}


	/**
	 * Set the vader icon on the grid, in the location given.
	 */
	@Override
	void setLocation(JLabel[][] locations, TFaceGrid loc, String type)
	{
		// TODO Auto-generated method stub
		rowNumber = loc.getRow();
		colNumber = loc.getColumn();

		try {
			txt = "Tetra Vader";
			txt_Two = "River";
//			img = ImageIO.read(getClass().getResource("tVader.jpg"));
//			img2 = ImageIO.read(getClass().getResource("river.png"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setText(txt);
//		locations[rowNumber][colNumber].setIcon(new ImageIcon(img));
//		locations[rowNumber][colNumber].setDisabledIcon(new ImageIcon(img));

		// set the image of river around the vader.
//		locations[rowNumber - 1][colNumber].setIcon(new ImageIcon(img2));
//		locations[rowNumber - 1][colNumber].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber - 1][colNumber].setText(txt_Two);

//		locations[rowNumber + 1][colNumber].setIcon(new ImageIcon(img2));
//		locations[rowNumber + 1][colNumber].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber + 1][colNumber].setText(txt_Two);
		
//		locations[rowNumber][colNumber - 1].setIcon(new ImageIcon(img2));
//		locations[rowNumber][colNumber - 1].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber - 1].setText(txt_Two);
		
//		locations[rowNumber][colNumber + 1].setIcon(new ImageIcon(img2));
//		locations[rowNumber][colNumber + 1].setDisabledIcon(new ImageIcon(img2));
		locations[rowNumber][colNumber + 1].setText(txt_Two);

		// set the current location of the vader which will be used in the simulation
		currentLocation = loc;
	}

	/**
	 *  Check if location is empty
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

		if(locations[row][col].getText() == null  || locations[row][col].getText().equals(""))
			characterObject = "NO CHARACTER";
		else 
			characterObject = locations[row][col].getText();

		return characterObject;
	}

	@Override
	/**
	 * get the vader id.
	 */
	int getId() 
	{
		// TODO Auto-generated method stub
		return vaderId;
	}

	@Override
	/**
	 *  Set the vader id.
	 */
	void setId(int id) 
	{
		// TODO Auto-generated method stub
		vaderId = id;
	}

	@Override
	/**
	 *  Fly to a given location
	 */
	void fly(JLabel[][] locations, TFaceGrid loc, TFaceGrid curLoc) throws IOException
	{		
		
//		Image flyImg = null;
		String flyTxt = null;

		String characterObj = checkLocation(locations, loc);
		String oldLocationText = locations[curLoc.getRow()][curLoc.getColumn()].getText();

		if(characterObj.equals("NO CHARACTER")||characterObj.equals("HEROBASE") || characterObj.equals("MAPBASE") || characterObj.equals("VADERBASE"))
		{		
//			ArrayList newSetLocation = (ArrayList) flyVehicle.flyToLocation(locations, currentLocation, loc, "VADER");

			// Set the old and the new locations of hero
			oldLocation = curLoc;
			currentLocation = loc;

			String LocationText = locations[loc.getRow()][loc.getColumn()].getText();

			// Check if the vader had entered the Mapbase anytime.
				if(LocationText == null){
					flyTxt = "Tetra Vader";
					locations[currentLocation.getRow()][currentLocation.getColumn()].setText(flyTxt);
				}
				else if(LocationText.equals("MAPBASE"))
				{
					flyTxt = "Map Base";
					locations[currentLocation.getRow()][currentLocation.getColumn()].setText(flyTxt);
				}
				else if(LocationText.equals("VADERBASE"))
				{
					flyTxt = "Vader House";
					locations[currentLocation.getRow()][currentLocation.getColumn()].setText(flyTxt);
				}
				else if (LocationText.equals("HEROBASE"))
				{
					flyTxt = "Hero Home";
					locations[currentLocation.getRow()][currentLocation.getColumn()].setText(flyTxt);
				}
				else{
					flyTxt = "Tetra Vader";
					locations[currentLocation.getRow()][currentLocation.getColumn()].setText(flyTxt);
				}
		}
		if(oldLocationText.equals("Map Base"))
		{
			flyTxt = "Map Base";
			locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
		}
		else if(oldLocationText.equals("VADERBASE"))
		{
			flyTxt = "Vader House";
			locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
		}
		else if (oldLocationText.equals("HEROBASE"))
		{
			flyTxt = "Hero Home";
			locations[oldLocation.getRow()][oldLocation.getColumn()].setText(flyTxt);
		}
		else{
			locations[oldLocation.getRow()][oldLocation.getColumn()].setText("");
		}

		curLoc.setRow(loc.getRow());
		curLoc.setColumn(loc.getColumn());


		if(! characterObj.equals("NO CHARACTER"))
		{
			PeopleNotify notification = new PeopleNotify();
			notification.people = this;
			notification.baseLoc = loc;

			if(characterObj.equals("HEROBASE"))
				notification.type = "HEROBASE";
			else if (characterObj.equals("VADERBASE"))
				notification.type = "VADERBASE";
			else
			{
				String message = "Vader enters MapBase to steal map";
				createMsg(message);
				StarMap starMap = new StarMap(11, loc, "Welcome to tetra Planet");
				this.setBackTrackPathStatus(true);

				// Vader flies back to its Vaderbase by backtracking its path.
//				oldLoc = loc
				TFaceGrid tmp = this.extractFromPath();
				curLoc.setColumn(loc.getColumn());
				curLoc.setRow(loc.getRow());
				if(tmp == null){
					loc = null;
				}
				else{
					loc.setColumn(tmp.getColumn());
					loc.setRow(tmp.getRow());
				}
				while(loc != null) {
					message = "Vader moving to VaderBase";
					createMsg(message);
					this.flyWithMap(starMap, locations, curLoc, loc);
					curLoc.setColumn(loc.getColumn());
					curLoc.setRow(loc.getRow());
					tmp = this.extractFromPath();
					if(tmp == null){
						loc = null;
					}
					else{
						loc.setColumn(tmp.getColumn());
						loc.setRow(tmp.getRow());
					}

				}
				starMap.setLocation(curLoc);
				// Map set to nullObject
//				this.starMap = new nullStarMap();
				notification.type = "MAPBASE";
				enteredMapbase = true;
			}
			peopleObservable.setChanged();
			peopleObservable.notifyObservers(notification);
		}
		else {
			if(!backTrackPathStatus) {
				addToPath(currentLocation);
			}
		}
	}

/**
 *  Fly with the map
 * @param map
 * @param locations
 * @param currentLoc
 * @param newLoc
 */
	public void flyWithMap(StarMapComponent map, JLabel[][] locations, TFaceGrid currentLoc, TFaceGrid newLoc)
	{
		this.starMap = map;		

		try {
			this.fly(locations, newLoc,currentLoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  Request fly vehicle.  - Does nothing
	 */
	void requestFly()
	{}


	@Override
	String getStrategy() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 *  Check and make a move to a given location.
	 * @param locations
	 * @param newLocation
	 */
	public void checkAndMove(JLabel[][] locations, TFaceGrid newLocation,String vaderNum, TFaceGrid oldLocation)
	{
		String characterObject = checkLocation(locations, newLocation);
		if(characterObject.equals("NO CHARACTER"))
		{
			try {
//				img = ImageIO.read(getClass().getResource("tVader.jpg"));
				txt = "Tetra Vader";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!backTrackPathStatus) {
				addToPath(newLocation);
			}
			setIcons(locations,vaderNum,oldLocation,newLocation);

		}
		else
		{
			String s="TFACE GRID OCCUPIED. FLY TO SOME OTHER LOCATION";
			createMsg(s);
		}
	}

	/**
	 *  Get an instance of TetraVader.
	 * @return TetraPeople
	 */
	public static TetraPeople getInstance() {
		
		// TODO Auto-generated method stub
		if (vader == null) {
			vader = new TetraVader();
		}
		return vader;
	}

}
