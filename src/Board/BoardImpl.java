package Board;

import java.awt.Point;

import GUI.ChessGui;
import Piece.*;

public class BoardImpl implements Board {
	//Empty square constant
	private static final Piece EMPTY = new Empty();
	
	//Chess board of type Piece (instantiated to 8x8 standard board)
	private Piece[][] board;
	
	private PieceColor currentTurn;
	
	public BoardImpl() {
		//Construct Board with Pieces
		board = new Piece[8][8];
		//Construct White and Black Pawns
		for (int cols = 0; cols < 8; cols++) {
			board[cols][1] = new Pawn(PieceColor.BLACK);
			board[cols][6] = new Pawn(PieceColor.WHITE);
		}
		//Construct Empty Spots
		for (int rows = 2; rows < 6; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				board[cols][rows] = EMPTY;
			}
		}
		//Black Row of Special Pieces
		board[0][0] = new Rooke(PieceColor.BLACK);
		board[1][0] = new Knight(PieceColor.BLACK);
		board[2][0] = new Bishop(PieceColor.BLACK);
		board[3][0] = new King(PieceColor.BLACK);
		board[4][0] = new Queen(PieceColor.BLACK);
		board[5][0] = new Bishop(PieceColor.BLACK);
		board[6][0] = new Knight(PieceColor.BLACK);
		board[7][0] = new Rooke(PieceColor.BLACK);
		//White Row of Special Pieces
		board[0][7] = new Rooke(PieceColor.WHITE);
		board[1][7] = new Knight(PieceColor.WHITE);
		board[2][7] = new Bishop(PieceColor.WHITE);
		board[3][7] = new King(PieceColor.WHITE);
		board[4][7] = new Queen(PieceColor.WHITE);
		board[5][7] = new Bishop(PieceColor.WHITE);
		board[6][7] = new Knight(PieceColor.WHITE);
		board[7][7] = new Rooke(PieceColor.WHITE);
		
		currentTurn = PieceColor.WHITE;
	
	}
	
	
	
	//True if there is  Piece at location Point
	public boolean isPieceAt(Point p) {
		return !(this.getPiece(p).getType() == PieceType.EMPTY);
	}
	
	//Gets Piece at specific Point
	public Piece getPiece(Point p) {
		return board[(int)p.getX()][(int)p.getY()];
	}
	
	/*
	 * Moves Pieces within the board class
	 * Used in the event handlers to move pieces
	 */
	public void move(Point[] points) {
		//Changes coords to easily readable integers
		int startX = (int)points[0].getX();
		int startY = (int)points[0].getY();
		int endX = (int)points[1].getX();
		int endY = (int)points[1].getY();
		//Checks validity
		this.getPiece(points[0]);
		this.getPiece(points[1]);
		if (validAndTurn(points)){
			board[endX][endY] = board[startX][startY];
			board[startX][startY] = EMPTY;
			board[endX][endY].setFirstMove(false);
			flipColor();
		}
	}
	
	public boolean validAndTurn (Point[] points) {
		return ((this.getPiece(points[0]).validMove(this, points)) && (this.getPiece(points[0]).getColor() == currentTurn));
	}
	
	public PieceColor getCurrentTurn() {
		return currentTurn;
	}
	
	public void flipColor() {
		if (currentTurn == PieceColor.WHITE) {
			currentTurn = PieceColor.BLACK;
		}
		else {
			currentTurn = PieceColor.WHITE;
		}
	}
	
	//Displays Board in Console
	public void textDisplay() {
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				System.out.print(board[cols][rows].textIcon() + " ");
			}
			System.out.println("");
		}
	}
	
	//Returns Board
	public Piece[][] getBoard() {
		return board;
	}

	public boolean isPossMoves (Point p) {
		Point[] move = new Point[2];
		move[0] = p;
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				move[1] = new Point(rows, cols);
				if (this.validAndTurn(move)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public PieceColor checkMate() {
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				if (getPiece(new Point(rows, cols)).getType() == PieceType.KING) {
					Point end = new Point(rows, cols);
					Piece p = getPiece(end);
					if (!(((King) p).isCheck(this, end))) {
						if (!(isPossMoves(end))) {
							System.out.println("CHECKMATA");
						}
					}
				}
			}
		}
		return null;
	}
}
