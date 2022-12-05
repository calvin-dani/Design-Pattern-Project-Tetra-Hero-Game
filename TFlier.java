import java.awt.Image;
import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Fly vehicle object which is used by the thero and tvader to fly from 
 * one location to another.
 * @author Calvin
 *
 */
public class TFlier 
{
	private int flierId;
	private boolean flightSuccess = false;
	private Image img = null;
	private String txt = null;
	private String message = null;
	private ArrayList<TFaceGrid> locationSet;

	/**
	 *  Default constructor
	 */
	public TFlier(){}


	/**
	 *  Constructor
	 * @param id
	 */
	public TFlier(int id)
	{
		flierId = id;
	}

	/**
	 * Set the vehicle id
	 * @param id
	 */
	public void setVehicleId(int id)
	{
		flierId = id;
	}

	/**
	 * Sets the character to the new fly location and returns the currrent and new position values.
	 * @param locations
	 * @param currentLocation
	 * @param newLocation
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public ArrayList<TFaceGrid> flyToLocation(JLabel[][] locations, TFaceGrid currentLocation, TFaceGrid newLocation, String type) throws IOException
	{
		int row = newLocation.getRow();
		int col = newLocation.getColumn();					


		if(type.equals("HERO 1"))
		{
			txt = "HERO 1";
		}
		else if(type.equals("HERO 2"))
		{
			txt = "HERO 2";
		}
		else			
		{
			txt = "Tetra Vader";
		}

		// Set the new location with the image.
//		String curLocTxt = locations[row][col].getText();
		locations[currentLocation.getRow()][currentLocation.getColumn()].setText("");
		locations[row][col].setText(txt);

		// Pass the current and New location back.
		locationSet = new ArrayList<TFaceGrid>();
		locationSet.add(currentLocation);
		locationSet.add(newLocation);

		return locationSet;


	}
}
