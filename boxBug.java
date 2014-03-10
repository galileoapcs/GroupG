import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
 
 //If this is your Jumper class, please name it correctly!
 
public class boxBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public boxBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
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
    

    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location hi = next.getAdjacentLocation(getDirection());
        if(!gr.isValid(hi))
        {
        	return false;
        }
        Actor neighbor = gr.get(next);
        Actor neighbortwo = gr.get(hi);
        if(neighbortwo instanceof Rock || neighbortwo instanceof boxBug)
        {
        	return false;
        }
        return (neighbor  == null) || (neighbor instanceof Flower);
        
    }

    public void act()
    {
    	Grid<Actor> gr = getGrid();
    	Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location hi = next.getAdjacentLocation(getDirection());
        Actor neighbor = gr.get(next);
       if(canMove())
        {
        	move();
            move();
            steps++;
        }
        else if(neighbor instanceof Rock)
        {
        	moveTo(hi);
  
        }
        else
        {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}
