package Piece;

import java.awt.Point;

import Board.Board;

public class Queen extends PieceImpl{
	public Queen(PieceColor color) {
		super(PieceType.QUEEN, color);
	}
	
	@Override
	public boolean validMove(Board board, Point[] points) {
		/*
		 * These objects are temporary, just used to get access to the Bishop and Rookes validMove methods
		 * The Queens move is simply both of these validMove's with the or operator
		 */
		Bishop tempBishop = new Bishop(this.getColor());
		Rooke tempRooke = new Rooke(this.getColor());
		return tempRooke.validMove(board, points) || 
			   tempBishop.validMove(board, points);
	}
}
