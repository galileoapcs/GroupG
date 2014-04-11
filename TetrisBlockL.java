import info.gridworld.grid.Location;

import java.awt.Color;


public class TetrisBlockL extends TetrisBlock{

	/**
	 * @param args
	 */
	public TetrisBlockL()
	{
		super();
		TetrisBug b= new TetrisBug(Color.GREEN);
		TetrisBug c=new TetrisBug(Color.GREEN);
		b.putSelfInGrid(gr, new Location(2,5));
		c.putSelfInGrid(gr, new Location(2,6));
		blocks.add(b);
		blocks.add(c);
	}
	
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
			blocks.get(0).move();
			blocks.get(2).move();
		}
		else if (rotationPos==2)
		{
			blocks.get(0).move();
			move();
			blocks.get(1).move();
			blocks.get(2).move();
			
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
			return blocks.get(1).canMove() && blocks.get(2).canMove();
		else if (rotationPos == 1)
			return blocks.get(1).canMove() && canMove() && blocks.get(0).canMove();
		else if (rotationPos == 2)
		{
			return blocks.get(0).canMove();
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
			if (blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(2).move();
				blocks.get(1).move();	
				move();
				blocks.get(0).move();
			}
		}
		else if (rotationPos==2)
		{
			if (blocks.get(1).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(0).move();
				blocks.get(1).move();	
				blocks.get(2).move();
			}
		}
		else if (rotationPos==3)
		{
			if(blocks.get(0).canMove()){
				blocks.get(0).move();
				move();
				blocks.get(1).move();	
				blocks.get(2).move();
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
			if (blocks.get(1).canMove() && canMove() && blocks.get(0).canMove()) {
				move();
				blocks.get(0).move();
				blocks.get(1).move();	
				blocks.get(2).move();
			}
		} 
		else if (rotationPos == 1) 
		{
			if(blocks.get(0).canMove()){
				blocks.get(0).move();
				move();
				blocks.get(1).move();	
				blocks.get(2).move();
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
			if (blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(2).move();
				blocks.get(1).move();	
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
		Location nextLoc1;
		Location nextLoc2;
		if (rotationPos == 0) {
			// only one block must move
			nextLoc = new Location(getLocation().getRow(),
					getLocation().getCol()-1);
			nextLoc1 = new Location(getLocation().getRow(),
					getLocation().getCol()+1);
			nextLoc2 = new Location(getLocation().getRow()-1,
					getLocation().getCol()+1);
			
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc1) && gr.get(nextLoc1) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null) {
				blocks.get(1).moveTo(nextLoc1);
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = (rotationPos + 1) % 4;// will be % 4 with 4 blocks
			}
		} 
		else if (rotationPos == 1) 
		{

			nextLoc = new Location(getLocation().getRow()+1,
					getLocation().getCol());
			nextLoc1 = new Location(getLocation().getRow()-1,
					getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow()-1,
					getLocation().getCol()-1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc1) && gr.get(nextLoc1) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null ) {
				blocks.get(1).moveTo(nextLoc1);
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = (rotationPos + 1) % 4;// will be % 4 with 4 blocks
			}
		}
		else if (rotationPos==2)
		{
			nextLoc = new Location(getLocation().getRow(),
					getLocation().getCol()+1);
			nextLoc1 = new Location(getLocation().getRow(),
					getLocation().getCol()-1);
			nextLoc2 = new Location(getLocation().getRow()+1,
					getLocation().getCol()-1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc1) && gr.get(nextLoc1) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null ) {
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc1);
				rotationPos = (rotationPos + 1) % 4;
			}
		}
		else if (rotationPos==3)
		{
			nextLoc = new Location(getLocation().getRow()-1,
					getLocation().getCol());
			nextLoc1 = new Location(getLocation().getRow()+1,
					getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow()+1,
					getLocation().getCol()+1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc1) && gr.get(nextLoc1) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null ) {
				blocks.get(2).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc1);
				rotationPos = (rotationPos + 1) % 4;
			}
		}


}
}
