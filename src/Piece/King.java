package Piece;

import java.awt.Point;

import Board.Board;

public class King extends PieceImpl {
	public King(PieceColor color) {
		super(PieceType.KING, color);
	}

	@Override
	public boolean validMove(Board board, Point[] points) {

		return !this.isSameColor(board.getPiece(points[1])) && kingDistance(points) && isCheck(board, points[1]);
	}

	/*
	 * Checks to see if King moves more than one space in each direction true if it
	 * doesn't, false if it does
	 */
	private boolean kingDistance(Point[] points) {

		int startX = (int) points[0].getX();
		int startY = (int) points[0].getY();
		int endX = (int) points[1].getX();
		int endY = (int) points[1].getY();

		return (startX - endX >= -1) && (startX - endX <= 1) && (startY - endY >= -1) && (startY - endY <= 1);
	}

	public boolean isCheck(Board b, Point end) {
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				Point start = new Point(rows, cols);
				Piece p = b.getPiece(start);
				if (this.isOtherColor(p)) {
					Point[] otherPoints = { start, end };
					if (p.validMove(b, otherPoints)) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
