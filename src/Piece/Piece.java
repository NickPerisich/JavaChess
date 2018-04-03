package Piece;

import java.awt.Point;

import Board.Board;

public interface Piece {
	PieceType getType();
	PieceColor getColor();
	String textIcon();
	String imagePath();
	boolean validMove(Board board, Point[] points);
	void setFirstMove(boolean firstMove);
	boolean isOtherColor (Piece otherPiece);
	boolean isSameColor (Piece otherPiece);
}
