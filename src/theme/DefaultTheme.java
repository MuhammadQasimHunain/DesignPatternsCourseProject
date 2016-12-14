package theme;

import java.awt.Color;
import java.util.ArrayList;

import pieces.Piece;

public class DefaultTheme extends Theme implements SubjectInterfaceObserverPattern {

	private DefaultTheme() {
		// TODO Auto-generated constructor stub
		super();
		this.darkCellColor = new Color(93, 235, 253);
		this.lightCellColor = new Color(235, 235, 235);
		this.pieceImageAddition = new String("");
		this.observees = new ArrayList<Piece>();
	}
	
	public static DefaultTheme createDefaultTheme() {
		return new DefaultTheme();
	}

	@Override
	public void setLightCellColor(Color lightCellColor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDarkCellColor(Color darkCellColor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerObserver(Piece p) {
		// TODO Auto-generated method stub
		this.observees.add(p);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		for (Piece piece : observees) {
			piece.update();
		}
		
	}

}
