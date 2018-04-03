package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Board.*;
import Piece.PieceColor;

public class ChessGui {
	// Actual Window
	private final JFrame gameFrame;
	// Chess Board
	private final BoardPanel boardPanel;
	//
	private final JButton showWhiteMoves;
	//
	private final JButton showBlackMoves;
	//
	private final JButton clearMove;

	// Dimensions of JFrames (Window and the Board)
	private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(1100, 900);
	private static final Dimension BOARD_PANEL_DIMENSION = new Dimension(900, 900);

	// Chessboard of type BoardImpl
	Board board = new BoardImpl();

	/*
	 * Index 0: Start Index 1: Finish
	 */
	private Point[] points = new Point[2];

	public ChessGui() {
		// General Setup Items
		this.gameFrame = new JFrame("Nicks Chess");
		this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
		this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.gameFrame.setLayout(new BorderLayout());
		this.gameFrame.setResizable(false);

		// Initializes a GridLayout of JButtons
		this.boardPanel = new BoardPanel();
		this.gameFrame.add(boardPanel, BorderLayout.WEST);

		// JPanel GridLayout to hold hint/utility buttons. Too add buttons, simply
		// change how many spots the GridLayout has and then add buttons.
		JPanel temp = new JPanel(new GridLayout(3, 1));
		temp.setBackground(Color.BLUE);
		gameFrame.add(temp);

		// Initialize the showBlackMoves button
		this.showBlackMoves = new JButton("Show Black Moves");
		this.showBlackMoves.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				possColorSet(PieceColor.BLACK);
			}
		});
		temp.add(showBlackMoves);

		// Initialize the showWhiteMoves button
		this.showWhiteMoves = new JButton("Show White Moves");
		this.showWhiteMoves.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				possColorSet(PieceColor.WHITE);
			}
		});
		temp.add(showWhiteMoves);

		// Clears selected clicks
		this.clearMove = new JButton("Clear Selected Move");
		this.clearMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardRefresh(board);
			}
		});
		temp.add(clearMove);

		// Puts Icons on the board, and makes it visible
		boardRefresh(board);
		this.gameFrame.setVisible(true);
	}

	// Refreshes the board to reflect current locations of pieces
	public void boardRefresh(Board values) {
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				boardPanel.getTilePanel(cols, rows)
						.setIcon(new ImageIcon(getClass().getResource(values.getBoard()[cols][rows].imagePath())));
				boardPanel.getTilePanel(cols, rows).setColor();
			}
		}
	}

	public void possColorSet(PieceColor c) {
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				Point point = new Point(rows, cols);
				if (board.getPiece(point).getColor() == c) {
					possColorSet(point);
				}
			}
		}
	}
	
	

	public void possColorSet(Point p) {
		Point[] move = new Point[2];
		move[0] = p;
		boardPanel.getTilePanel(move[0]).setBackground(board.getPiece(move[0]).getColor().getStartColor());
		for (int rows = 0; rows < 8; rows++) {
			for (int cols = 0; cols < 8; cols++) {
				move[1] = new Point(rows, cols);
				if (board.validAndTurn(move)) {
					boardPanel.getTilePanel(move[1]).setBackground(board.getPiece(move[0]).getColor().getEndColor());
					boardPanel.getTilePanel(move[1]).revalidate();
					
				}
			}
		}
	}

	// JPanel containing the 64 JButtons of the chess board
	@SuppressWarnings("serial")
	private class BoardPanel extends JPanel {
		TilePanel[][] tileValues = new TilePanel[8][8];

		BoardPanel() {
			super(new GridLayout(8, 8));
			for (int y = 0; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
					TilePanel tilePanel = new TilePanel(x, y);
					tileValues[x][y] = tilePanel;
					add(tilePanel);
				}
			}
			setPreferredSize(BOARD_PANEL_DIMENSION);
		}

		private TilePanel getTilePanel(Point p) {
			int x = (int) (p.getX());
			int y = (int) (p.getY());
			return tileValues[x][y];
		}

		private TilePanel getTilePanel(int x, int y) {
			return tileValues[x][y];
		}

	}

	// Individual chess board tile
	@SuppressWarnings("serial")
	private class TilePanel extends JButton {
		// X and Y Coordinate in the Grid
		private int tileX;
		private int tileY;

		TilePanel(final int tileX, final int tileY) {
			this.tileX = tileX;
			this.tileY = tileY;
			setColor();
			this.setSize(10, 10);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!(points[0] != null)) {
						points[0] = new Point(tileX, tileY);
						possColorSet(points[0]);
					} else {
						points[1] = new Point(tileX, tileY);
						board.move(points);
						boardRefresh(board);
						revalidate();
						points[0] = null;
						points[1] = null;
						board.checkMate();
					}
				}
			});
		}

		// Sets color of tiles based on x and y coordinates
		private void setColor() {
			if ((tileX % 2) == (tileY % 2)) {
				setBackground(Color.BLACK);
			} else {
				setBackground(Color.WHITE);
			}
		}
	}

}
