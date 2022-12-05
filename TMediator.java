/**
 *  TetraGameSimulation
 *	@authors: Calvin
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * TMediator acts as Observer and Observing all Tetra People.
 * TMediator acts as Mediator for handling all action's of
 * tetra People.
 */
public class TMediator implements Observer 
{
	/** 
	 * List of different Concrete Colleagues.
	 */
	public List<TMapBase> mapHomes;
	public List<THeroBase> heroBaseHomes;
	public TVaderBase vaderBaseHome;
	
	/**
	 * Initializing TMediator.
	 */
	public TMediator() {
		mapHomes = new ArrayList<TMapBase>();
		heroBaseHomes = new ArrayList<THeroBase>();
	}

	/**
	 * Register TetraPeople.
	 * @param tPeople
	 */
	public void registerTPeople(TetraPeople tPeople) 
	{
		tPeople.addObserver(this);	
	}
	
	/**
	 * Create Message on GUI.
	 * @param msg
	 */
	private void createMsg(String msg)
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
	
	/**
	 * Register Colleagues.
	 * @param homeBase
	 */
	public void registerTHomeBase(Location homeBase) 
	{
		if(homeBase instanceof TMapBase) {
			mapHomes.add((TMapBase)homeBase);
		}
		else if(homeBase instanceof THeroBase) {
			heroBaseHomes.add((THeroBase)homeBase);
		}
		else if(homeBase instanceof TVaderBase) {
			vaderBaseHome = (TVaderBase)homeBase;
		}
	}
	
	/**
	 * Handle Request upon Observable change.
	 * @param arg0
	 * @param arg1
	 */
	public void update(Observable arg0, Object arg1) 
	{
		PeopleNotify receivedObject = (PeopleNotify)arg1;
		
		if(receivedObject.type.equals("MAPBASE"))
		{
			System.out.println("TMediator: Mapbase");
			for (int i = 0; i < mapHomes.size(); ++i)
			{
				TMapBase mapHome = mapHomes.get(i);
				
				if(mapHome.getGridLocation().getRow() == receivedObject.baseLoc.getRow() &&
				   mapHome.getGridLocation().getColumn() == receivedObject.baseLoc.getColumn())
				{
					try {
						mapHome.processMap(receivedObject.people);
					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		else if (receivedObject.type.equals("VADERBASE"))
		{
			System.out.println("TMediator: VaderBase");
			try {
				vaderBaseHome.processMap(receivedObject.people);
			}
			catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			if(receivedObject.people instanceof TetraHero) {
				System.out.println("Hero with id" + receivedObject.people.getId());
				for (int i = 0; i < heroBaseHomes.size(); ++i)
				{
					THeroBase heroBase = heroBaseHomes.get(i);
					
					if(heroBase.getLocation() == receivedObject.people.getId()) {
						String s="HERO wants to enter his own house";
						createMsg(s);
						heroBase.processMap(receivedObject.people);
					}
				}
			}
		}
	}
}