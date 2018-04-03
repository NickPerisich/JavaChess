package Piece;

import java.awt.Point;

import Board.Board;

public class Pawn extends PieceImpl{
	public Pawn(PieceColor color) {
		super(PieceType.PAWN, color);
	}
	
	//Decorator and Design Pattern?
	@Override
	public boolean validMove(Board board, Point[] points) {
		//Difference in y maximum(yDelta), default is set to one
		int yDelta = 1;
		/*
		 * Code relating to how the first move is handled
		 * Sets max y difference to two and provides a middle Point object
		 */
		Point middle = null;
		boolean noImpediment = true;
		if (firstMove) {
			middle = new Point((int)(points[0].getX()+points[1].getX()) / 2, 
							   (int)(points[0].getY()+points[1].getY()) / 2);
			yDelta = 2;
		}
		/*
		 * Deals with calculating the inputed x and y differences
		 * yDiff is declared based off a conditional
		 * xDiff can be calculated independent of color
		 */
		int yDiff;
		int xDiff = (int)(points[0].getX() - points[1].getX());
		if (color == PieceColor.WHITE) {
			yDiff = (int)(points[0].getY() - points[1].getY());
		}
		else {
			yDiff = (int)(points[1].getY() - points[0].getY());
		}
		/*
		 * Vertical move checks
		 * Checks to see if pawn is skipping a piece (in cases where yDelta is 2)
		 * Keeps pawn within costraints
		 */
		if (xDiff == 0) {
			if ((yDelta == 2) && (yDiff == 2)) {
				noImpediment = !(board.isPieceAt(middle));
			}
			return (yDiff <= yDelta) && 
				   (yDiff > 0) && 
				   noImpediment && 
				   !(board.isPieceAt(points[1]));
		}
		//Checks to see if the diagonal take move is valid
		else if (yDiff == 1) {
			if ((xDiff == 1) || (xDiff == -1)) {
				return board.getPiece(points[0]).isOtherColor(board.getPiece(points[1]));
			}
		}
		return false;
	}
}

