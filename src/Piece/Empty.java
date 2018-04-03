package Piece;

public class Empty extends PieceImpl{
	public Empty() {
		super(PieceType.EMPTY, PieceColor.NONE);
	}
	
	@Override
	public String textIcon() {
		return "EMP";
	}
}
