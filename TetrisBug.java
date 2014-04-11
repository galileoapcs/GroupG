
import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.util.ArrayList;
import java.awt.Color;


public class TetrisBug extends Bug{

	/**
	 * @param args
	 */
	public TetrisBug(Color x)
	{
		super(x);
		this.setDirection(180);
		
	}
	public void move()
	{
		Grid<Actor> gr = getGrid(); 
		if (gr == null) 
		 return; 
		Location loc = getLocation(); 
		Location next = loc.getAdjacentLocation(getDirection()); 
		if (gr.isValid(next)) 
		 moveTo(next); 
		else 
		 removeSelfFromGrid();
	}
	public void act()
	{	
	}
	

}
