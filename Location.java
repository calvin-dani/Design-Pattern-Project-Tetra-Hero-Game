import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**  TetraGameSimulation
	@authors: 	Calvin
 */

/* Creates the different location objects.
 * 
 * Location is a abstract class. The other class derive from it. 
 */
public abstract class Location 
{
	protected int locationId;
	protected String locationText;
	protected TFaceGrid gridLocation;
	protected Image img = null;
	protected String txt  = null;
	protected JLabel[][] locations;
	protected StarMapComponent starMap;

	abstract void setLocationText(String name);
	abstract void setLocationId(int id);
	abstract int getLocation();
	abstract String getLocationText();
	abstract String getImageName();
	abstract void processMap(TetraPeople tPeople) throws IOException, CloneNotSupportedException, InstantiationException, IllegalAccessException, ClassNotFoundException;

	/**
	 * Set the start map component
	 * @param starMap
	 */
	public void setStarMapComponent(StarMapComponent starMap) {
		this.starMap = starMap;
	}

	/**
	 *  Set the base grid( TFace) with the given image and text
	 * @param locations
	 * @param gridLocation
	 * @param baseText
	 */
	public void setBaseGridLocation(JLabel[][] locations, TFaceGrid gridLocation, String baseText)
	{
		this.gridLocation = gridLocation;	
		this.locations = locations;

		int rowNumber = gridLocation.getRow();
		int colNumber = gridLocation.getColumn();

		locations[rowNumber][colNumber].setText(baseText);

		try 
		{
			txt = baseText;
//			String imgName = this.getImageName();
//			img = ImageIO.read(getClass().getResource(imgName));
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		locations[rowNumber][colNumber].setText(txt);
//		locations[rowNumber][colNumber].setIcon(new ImageIcon(img));
//		locations[rowNumber][colNumber].setDisabledIcon(new ImageIcon(img));
	}

	/**
	 * Get the location (TFace)
	 * @return
	 */
	public TFaceGrid getGridLocation()
	{
		return this.gridLocation;
	}
	
	/**
	 * Creates message on the JOptionPane
	 * @param msg
	 */
	public void createMsg(String msg)
	{
		String message = "<html><font name='sansserif' size='5'>" + msg +"</font></html>";
		JOptionPane optionPane = new JOptionPane(message
                , JOptionPane.PLAIN_MESSAGE
                , JOptionPane.DEFAULT_OPTION
                , null, null, null);
		
		JDialog dialog = optionPane.createDialog(null, "SCENARIO");			
        dialog.setLocation(900, 430);
        dialog.setVisible(true);
	
	}
}






