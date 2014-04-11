import info.gridworld.grid.Location;

import java.awt.Color;


public class TetrisBlockO extends TetrisBlock{

	/**
	 * @param args
	 */
	public TetrisBlockO()
	{
		super();
		TetrisBug b= new TetrisBug(Color.GREEN);
		TetrisBug c=new TetrisBug(Color.GREEN);
		b.putSelfInGrid(gr, new Location(0,6));
		c.putSelfInGrid(gr, new Location(1,6));
		blocks.add(b);
		blocks.add(c);
	}
	
	public void moveDown() {
			blocks.get(2).move();
			move();
			blocks.get(1).move();
			blocks.get(0).move();

	}
	
	public boolean canMoveDown() {	
			return blocks.get(2).canMove() && canMove();
	}

	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		blocks.get(1).move();
		blocks.get(2).move();
		blocks.get(0).move();
		move();	
	}
	
	public void moveLeft() {

		setDirection(270);
		for (TetrisBug tb : blocks)
			tb.setDirection(270);	
		blocks.get(0).move();
		move();
		blocks.get(1).move();
		blocks.get(2).move();
			}
		

	

	/**
	 * If the TetrisBlock and its TetrisBugs can rotate, then they will all move
	 * to their proper location for the given rotation designated by
	 * rotationPos... Update rotationPos.
	 */
	public void rotate() {
	}
}
