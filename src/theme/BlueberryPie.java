package theme;

import java.awt.Color;

public class BlueberryPie extends Theme {

	public BlueberryPie() {
		// TODO Auto-generated constructor stub
		this.darkCellColor = Color.BLUE;
		this.lightCellColor = Color.WHITE;
		this.pieceImageAddition = new String("Blueberry-");
	}

	@Override
	public Color getLightCellColor() {
		// TODO Auto-generated method stub
		return this.lightCellColor;
	}

	@Override
	public void setLightCellColor(Color lightCellColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getDarkCellColor() {
		// TODO Auto-generated method stub
		return this.darkCellColor;
	}

	@Override
	public void setDarkCellColor(Color darkCellColor) {
		// TODO Auto-generated method stub
		
	}


}
