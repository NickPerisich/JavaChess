package Piece;

import java.awt.Color;

public enum PieceColor {
	WHITE(Color.GREEN, Color.RED), BLACK(Color.BLUE, Color.MAGENTA), NONE(Color.YELLOW);

	private Color startColor;
	private Color endColor;

	private PieceColor(Color s, Color e) {
		startColor = s;
		endColor = e;
	}

	private PieceColor(Color s) {
		startColor = s;
		endColor = null;
	}

	public Color getStartColor() {
		return startColor;
	}

	public Color getEndColor() {
		return endColor;
	}
}
