import info.gridworld.grid.Location;

import java.awt.Color;


public class TetrisBlockT extends TetrisBlock{

	/**
	 * @param args
	 */
	public TetrisBlockT() {
		super();
		TetrisBug b= new TetrisBug(Color.BLUE);
		TetrisBug c=new TetrisBug(Color.BLUE);
		b.putSelfInGrid(gr, new Location(0,4));
		c.putSelfInGrid(gr, new Location(0,6));
		blocks.add(b);
		blocks.add(c);
		

		// TetrisBlock subclasses will add two more TetrisBug objects to blocks

	}

	/**
	 * TetrisBlock and its TetrisBugs must face down (direction 180) If they can
	 * move down, they will. Otherwise, it will ask TetrisGame to create a new
	 * TetrisBlock since this one is stuck at the bottom.
	 */
	public void act() {
		setDirection(180);
		for (TetrisBug tb : blocks)
			tb.setDirection(180);
		if (canMoveDown())
			moveDown();
		else if (!TetrisGame.currentBlock.canMoveDown())
			TetrisGame.nextTetrisBlock();
	}

	/**
	 * Move the TetrisBlock and its TetrisBugs one cell. (they should already be
	 * facing down) Note: The order in which all the TetrisBugs move is important
	 * and depends on the current rotationPos.
	 */
	public void moveDown() {
		if (rotationPos == 0) {
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		} 
		else if (rotationPos == 1) {
			blocks.get(1).move();
			move();
			blocks.get(2).move();
			blocks.get(0).move();
		}
		else if (rotationPos==2)
		{
			blocks.get(1).move();
			move();
			blocks.get(2).move();
			blocks.get(0).move();
		}
		else if(rotationPos==3)
		{
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		}
	}

	/**
	 * Returns true if the TetrisBlock and its TetrisBugs can move (they should
	 * already be facing down) Otherwise, returns false.
	 */
	public boolean canMoveDown() {
		if (rotationPos == 0)
			return canMove();
		else if (rotationPos == 1)
			return blocks.get(1).canMove();
		else if (rotationPos == 2)
		{
			return blocks.get(1).canMove() && canMove() && blocks.get(2).canMove();
		}
		else if (rotationPos==3)
		{
			return blocks.get(2).canMove();
		}
		else
			return true;
	}

	/**
	 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
	 * they can move, they will move one cell (to the right)
	 */
	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if (rotationPos == 0) {
			if (blocks.get(2).canMove()) {
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();			
			}
		} 
		else if (rotationPos == 1) {
			if (blocks.get(2).canMove()) {
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();	
			}
		}
		else if (rotationPos==2)
		{
			if (blocks.get(2).canMove()) {
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();			
			}
		}
		else if (rotationPos==3)
		{
			if(canMove() && blocks.get(0).canMove() && blocks.get(2).canMove()){
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();	
			}
		}
	}

	/**
	 * Sets the direction of the TetrisBlock and its TetrisBugs to 90 (right) If
	 * they can move, they will move one cell (to the right)
	 */
	public void moveLeft() {

		setDirection(270);
		for (TetrisBug tb : blocks)
			tb.setDirection(270);
		if (rotationPos == 0) {
			if (blocks.get(1).canMove()) {
				blocks.get(1).move();
				blocks.get(0).move();
				move();
				blocks.get(2).move();
			}
		} 
		else if (rotationPos == 1) 
		{
			if (blocks.get(0).canMove() && blocks.get(1).canMove() && canMove()) {
				blocks.get(1).move();
				blocks.get(0).move();
				move();
				blocks.get(2).move();
			}
		}
		else if (rotationPos==2)
		{
			if (blocks.get(1).canMove()) {
				blocks.get(1).move();
				move();
				blocks.get(0).move();
				blocks.get(2).move();
			}
		}
		else if (rotationPos==3)
		{
			if(blocks.get(1).canMove()){
				blocks.get(1).move();	
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				
			}
		}
	}

	/**
	 * If the TetrisBlock and its TetrisBugs can rotate, then they will all move
	 * to their proper location for the given rotation designated by
	 * rotationPos... Update rotationPos.
	 */
	public void rotate() {
		Location nextLoc;
		Location nextLoc2;
		if (rotationPos == 0) {
			// only one block must move
			nextLoc = new Location(getLocation().getRow()+1,
					getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow(),
					getLocation().getCol()+1);
			
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null) {
				blocks.get(1).moveTo(nextLoc);
				blocks.get(2).moveTo(nextLoc2);
				rotationPos = (rotationPos + 1) % 4;// will be % 4 with 4 blocks
			}
		} 
		else if (rotationPos == 1) 
		{

			nextLoc = new Location(getLocation().getRow(),
					getLocation().getCol()-1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null ) {
				blocks.get(1).moveTo(nextLoc);
				rotationPos = (rotationPos + 1) % 4;// will be % 4 with 4 blocks
			}
		}
		else if (rotationPos==2)
		{
			nextLoc = new Location(getLocation().getRow()+1,
					getLocation().getCol());
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null ) {
				blocks.get(2).moveTo(nextLoc);
				rotationPos = (rotationPos + 1) % 4;
			}
		}
		else if (rotationPos==3)
		{
			nextLoc = new Location(getLocation().getRow() - 1,
					getLocation().getCol()-1);
			nextLoc2 = new Location(getLocation().getRow() - 1,
					getLocation().getCol() + 1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null) {
				blocks.get(1).moveTo(nextLoc);
				blocks.get(2).moveTo(nextLoc2);
				rotationPos = (rotationPos + 1) % 4;// will be % 4 with 4 blocks
			}
		}

}
}
