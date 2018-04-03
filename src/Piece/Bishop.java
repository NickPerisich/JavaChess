package Piece;

import java.awt.Point;

import Board.Board;

public class Bishop extends PieceImpl{
	public Bishop(PieceColor color) {
		super(PieceType.BISHOP, color);
	}
	
	@Override
	public boolean validMove(Board board, Point[] points) {
		int startX = (int)points[0].getX();
		int startY = (int)points[0].getY();
		int endX = (int)points[1].getX();
		int endY = (int)points[1].getY();
		int xIterator = iteratorSign(startX, endX);
		int yIterator = iteratorSign(startY, endY);
		
		if (isLinear(startX, startY, endX, endY)) {
			
			startX += xIterator;
			startY += yIterator;
			
			while ((startX != endX) && (startY != endY)) {
				if(board.isPieceAt(new Point(startX, startY))) {
					return false;
				}
				
				startX += xIterator;
				startY += yIterator;
				
			}
			return !(this.isSameColor(board.getPiece(points[1])));
		}
		return false;
	}
	
	private boolean isLinear(int startX, int startY, int endX, int endY) {
		float slope = 0;
		try {
			slope = (float)(endY - startY) /
					(float)(endX - startX);
		} 
		catch (ArithmeticException e) {
			return false;
		}
		return ((slope == 1) || 
				(slope == -1));
	}
	
	private int iteratorSign(int start, int end) {
		if (start > end) {
			return -1;
		}
		else if (start < end) {
			return 1;
		}
		return 0;
	}
}
