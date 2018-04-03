package Piece;

import java.awt.Point;

import Board.Board;

public abstract class PieceImpl implements Piece {
	protected boolean firstMove = true;
	protected PieceType type;
	protected PieceColor color;
	
	public PieceImpl(PieceType type, PieceColor color) {
		this.type = type;
		this.color = color;
	}
		
	public boolean isSameColor(Piece otherPiece) {
		return this.color == otherPiece.getColor();
	}
	
	public boolean isOtherColor(Piece otherPiece) {
		return (this.color != otherPiece.getColor()) && (otherPiece.getColor() != PieceColor.NONE);
	}
	
	public PieceType getType() {
		return this.type;
	}
	
	public PieceColor getColor() {
		return this.color;
	}
	
	public String textIcon() {
		return color.toString().charAt(0) + type.toString().substring(0,2);
	}
	
	public String imagePath() {
		return color.toString() + type.toString() + ".png";
	}
	
	public boolean validMove(Board board, Point[] points) {
		return false;
	}
	
	public void setFirstMove(boolean firstMove) {
		this.firstMove = firstMove;
	}
}
