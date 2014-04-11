//TetrisBlock.java
import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;
import java.awt.Color;

/**
 * TetrisBlock is a type of Bug. It will act in GridWorld by moving down
 * (direction 180) if it can, otherwise it will ask TetrisGame to make a new
 * TetrisBlock for the game.
 */
public class TetrisBlockI extends TetrisBlock {

	 
	public TetrisBlockI() {
		super();
		TetrisBug b= new TetrisBug(Color.YELLOW);
		TetrisBug c=new TetrisBug(Color.YELLOW);
		b.putSelfInGrid(gr, new Location(2,5));
		c.putSelfInGrid(gr, new Location(3,5));
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
		} else if (rotationPos == 1) {
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
			move();
		}
	}

	/**
	 * Returns true if the TetrisBlock and its TetrisBugs can move (they should
	 * already be facing down) Otherwise, returns false.
	 */
	public boolean canMoveDown() {
		if (rotationPos == 0)
			return blocks.get(2).canMove();
		else if (rotationPos == 1)
			return blocks.get(1).canMove();
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
			if (canMove() && blocks.get(0).canMove() && blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(0).move();
				move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
		} else if (rotationPos == 1) {
			if (canMove()) {
				move();
				blocks.get(0).move();
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
			if (canMove() && blocks.get(0).canMove() && blocks.get(2).canMove() && blocks.get(1).canMove()) {
				blocks.get(0).move();
				move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
		} else if (rotationPos == 1) {
			if (blocks.get(0).canMove()) {
				blocks.get(0).move();
				move();
				
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
		if (rotationPos == 0) {
			// only one block must move
			nextLoc = new Location(getLocation().getRow() - 1,
					getLocation().getCol() + 1);
			Location nextLoc2 = new Location(getLocation().getRow() - 1,
					getLocation().getCol() + 2);
			Location nextLoc3 = new Location(getLocation().getRow() - 1,
					getLocation().getCol() + 3);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null &&
					gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) {
				moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(2).moveTo(nextLoc3);
				rotationPos = (rotationPos + 1) % 2;// will be % 4 with 4 blocks
			}
		} else if (rotationPos == 1) {

			nextLoc = new Location(getLocation().getRow()+1,
					getLocation().getCol() - 1);
			Location nextLoc2 = new Location(getLocation().getRow() + 2,
					getLocation().getCol() - 1);
			Location nextLoc3 = new Location(getLocation().getRow() + 3,
					getLocation().getCol() - 1);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null &&
					gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) {
				moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(2).moveTo(nextLoc3);
				rotationPos = (rotationPos + 1) % 2;// will be % 4 with 4 blocks
			}
		

	}

}
}