package Board;

import java.awt.Point;
import Piece.Piece;
import Piece.PieceColor;

public interface Board {
	void textDisplay();
	Piece[][] getBoard();
	void move(Point[] points);
	Piece getPiece(Point point);
	boolean isPieceAt(Point p);
	PieceColor getCurrentTurn();
	boolean validAndTurn(Point[] points);
	PieceColor checkMate();
}
