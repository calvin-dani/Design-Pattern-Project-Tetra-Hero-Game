import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * TetraStarSimulation
 * Abstract class
 * @author Calvin
 *
 */
abstract class TetraPeople 
{
	
	protected final int maxRow = 7;
	protected final int maxCol = 7;
	protected TetraPeopleObservable peopleObservable;	
	protected TFaceGrid currentLocation, newLocation, oldLocation;
	protected boolean vaderExit;

	protected StarMapComponent starMap;
	
	/**
	 * Get the old locaiton.
	 * @return TFaceGrid
	 */
	public TFaceGrid getOldLocation() 
	{
		return oldLocation;
	}

	/**
	 * Set the old location
	 * @param oldLocation
	 */
	public void setOldLocation(TFaceGrid oldLocation) 
	{
		this.oldLocation = oldLocation;
	}
	
	/**
	 *  Make the first move on the grid
	 * @param direction
	 * @param locations
	 * @param loc
	 */
	public void makeInitialMove(String direction, JLabel[][] locations, TFaceGrid loc, String heroType)
	{
	   makeMove(direction, locations, heroType,loc);
	}
	
	/**
	 *  Display message on a JOptionPane
	 * @param msg
	 */
	public void createMsg(String msg)
	{
		String message = "<html><font name='sansserif' size='4'>" + msg +"</font></html>";
		JOptionPane optionPane = new JOptionPane(message
                , JOptionPane.PLAIN_MESSAGE
                , JOptionPane.DEFAULT_OPTION
                , null, null, null);
		
		JDialog dialog = optionPane.createDialog(null, "SCENARIO");			
        dialog.setLocation(900, 430);
        dialog.setVisible(true);
	
	}
	
	/**
	 *  Add observer to tetrapeople
	 * @param ob
	 */
	public void addObserver (Observer ob) 
	{ 
		peopleObservable.addObserver(ob);
	}

	public void makeMove(String direction, JLabel[][] locations,String heroType,TFaceGrid loc)
	{
		// If the movement is in north direction.
		if(direction.equals("N"))
		{
			int newRow = loc.getRow() - 1;

			if(newRow < 0)
			{
				String s="Cannot move North. Out of the Grid";
				createMsg(s);

			}
			else
			{
				newLocation = new TFaceGrid(newRow, loc.getColumn());
				checkAndMove(locations, newLocation,heroType,loc);

			}
		}

		if(direction.equals("S"))
		{
			int newRow = loc.getRow() + 1;
			if(newRow > maxRow)
			{
				String s="Cannot move South. Out of the Grid";
				createMsg(s);

			}
			else
			{
				newLocation = new TFaceGrid(newRow, loc.getColumn());

				checkAndMove(locations, newLocation,heroType,loc);
			}
		}

		if(direction.equals("E"))
		{
			int newCol = loc.getColumn() + 1;
			if(newCol > maxCol)
			{
				String s="Cannot move East. Out of the Grid";
				createMsg(s);
			}
			else
			{
				newLocation = new TFaceGrid(loc.getRow(), newCol);
				checkAndMove(locations, newLocation,heroType,loc);
			}
		}

		if(direction.equals("W"))
		{
			int newCol = loc.getColumn() - 1;
			if(newCol < 0)
			{
				String s="Cannot move West. Out of the Grid";
				createMsg(s);
			}
			else
			{
				newLocation = new TFaceGrid(loc.getRow(), newCol);
				checkAndMove(locations, newLocation,heroType,loc);
			}
		}
	}
	
	// abstract methods to be implemented by the sub classes - tetravader, tetrarover and tetrahero
//	 abstract void makeMove(String direction, JButton[][] locations);

	 abstract void checkAndMove(JLabel[][] locations, TFaceGrid newLocation, String heroNum, TFaceGrid currentLocation);
	 abstract void setLocation(JLabel[][] locations,TFaceGrid loc,String type);
	 abstract int getId();
	 abstract void setId(int id);	 
	 abstract void fly(JLabel[][] locations, TFaceGrid loc, TFaceGrid curLoc) throws IOException;
	 abstract void requestFly();
	 abstract String getStrategy();
	 
	 /**
	  *  Indicates the vader has/ has not  left the vaderbase.
	  * @param val
	  */
	 public void setVaderExit(boolean val)
	 {
		 vaderExit = val;
	 }
}

class PeopleNotify 
{
	public TetraPeople people;
	public TFaceGrid baseLoc;
	public String type;
	
	
}


class TetraPeopleObservable extends Observable
{
	public void setChanged()
	{
		super.setChanged();
	}
}