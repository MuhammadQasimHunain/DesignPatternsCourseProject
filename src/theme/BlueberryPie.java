package theme;

import java.awt.Color;

public class BlueberryPie extends Theme {

	public BlueberryPie() {
		// TODO Auto-generated constructor stub
		this.darkCellColor = Color.BLUE;
		this.lightCellColor = Color.WHITE;
		this.pieceImageFolderPath = "";
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

	@Override
	public String getPieceImageFolderPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPieceImageFolderPath(String pieceImageFolderPath) {
		// TODO Auto-generated method stub
		
	}

}
