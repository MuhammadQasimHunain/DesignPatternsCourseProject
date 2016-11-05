package theme;

import java.awt.Color;

public class Classic extends Theme {

	public Classic (){
//		this.darkCellColor = Color.black;
		this.darkCellColor = new Color((float)0.0, (float)0.0, (float)0.0, (float)0.5);
		this.lightCellColor = Color.white;
		this.pieceImageFolderPath = "";
		
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
	public String getPieceImageFolderPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPieceImageFolderPath(String pieceImageFolderPath) {
		// TODO Auto-generated method stub
		
	}
}
