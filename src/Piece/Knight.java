package Piece;

import java.awt.Point;

import Board.Board;

public class Knight extends PieceImpl{
	public Knight(PieceColor color) {
		super(PieceType.KNIGHT, color);
	}
	
	@Override
	public boolean validMove(Board board, Point[] points) {

		int xDiff = (int)points[0].getX() - (int)points[1].getX();
		int yDiff = (int)points[0].getY() - (int)points[1].getY();
		
		/*
		 * Checks Knight moves using absKnightCheck and the normal piece check
		 * Calls absKnightCheck twice flipping the xDiff and yDiff
		 * If either of those true, then checks to see if same piece color exists at destination
		 */
		return (absKnightCheck(xDiff, yDiff) || 
			   absKnightCheck(yDiff, xDiff)) && 
			   !this.isSameColor(board.getPiece(points[1]));
	}
	
	/*
	 * First diff must be one, second two, to be true
	 */
	private boolean absKnightCheck (int diffOne, int diffTwo) {
		
		return (Math.abs(diffOne) == 1) && (Math.abs(diffTwo) == 2);
	}
}
