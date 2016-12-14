package theme;

import java.awt.Color;
import java.util.ArrayList;

import pieces.Piece;

public class HersheyChocolate extends Theme implements SubjectInterfaceObserverPattern {

	private HersheyChocolate() {
		// TODO Auto-generated constructor stub
		super();
		this.darkCellColor = new Color(88, 56, 50);
		this.lightCellColor = Color.white;
		this.pieceImageAddition = new String("Hershey-");
		this.observees = new ArrayList<Piece>();
	}
	
	public static HersheyChocolate createHersheyChocoloateTheme() {
		return new HersheyChocolate();
	}

//	@Override
//	public Color getLightCellColor() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void setLightCellColor(Color lightCellColor) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Color getDarkCellColor() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void setDarkCellColor(Color darkCellColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerObserver(final Piece piece) {
		// TODO Auto-generated method stub
		this.observees.add(piece);
	}

	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		for (Piece piece : observees) {
			piece.update();
		}
		
	}


}
