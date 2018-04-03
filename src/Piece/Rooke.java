package Piece;

import java.awt.Point;

import Board.Board;

public class Rooke extends PieceImpl{
	public Rooke(PieceColor color) {
		super(PieceType.ROOKE, color);
	}
	
	@Override
	public boolean validMove(Board board, Point[] points) {
		int startX = (int)points[0].getX();
		int startY = (int)points[0].getY();
		int endX = (int)points[1].getX();
		int endY = (int)points[1].getY();
		int xIterator;
		int yIterator;
		
		try {
			xIterator = rookeTransversal(points)[0];
			yIterator = rookeTransversal(points)[1];
		}
		catch (IllegalArgumentException | ArithmeticException e) {
			return false;
		}
		
		startX += xIterator;
		startY += yIterator;
		
		while ((startX != endX) || (startY != endY)) {
			
			
			if(board.isPieceAt(new Point(startX, startY))) {
				return false;
			}
			
			startX += xIterator;
			startY += yIterator;
		}
		
		return !(this.isSameColor(board.getPiece(points[1])));
		
	}
	
	private int[] rookeTransversal (Point[] points) throws IllegalArgumentException, ArithmeticException{
		int xDiff = (int)points[1].getX() - (int)points[0].getX();
		int yDiff = (int)points[1].getY() - (int)points[0].getY();
		
		int[] iteratorSigns = {0, 0};
		
		if (xDiff == 0) {
			iteratorSigns[1] = yDiff / (Math.abs(yDiff));
			return iteratorSigns;
		}
		else if (yDiff == 0) {
			iteratorSigns[0] = xDiff / (Math.abs(xDiff));
			return iteratorSigns;
		}
		
		throw new IllegalArgumentException();
	}
}
