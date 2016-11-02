package theme;

import java.awt.Color;

public abstract class Theme {

	Color lightCellColor;
	Color darkCellColor;
	String pieceImageFolderPath;

	public Color getLightCellColor() {
		return lightCellColor;
	}
	public abstract void setLightCellColor(Color lightCellColor) ;

	public Color getDarkCellColor() {
		return darkCellColor;
	}
	public abstract void setDarkCellColor(Color darkCellColor) ;

	public abstract String getPieceImageFolderPath() ;
	public abstract void setPieceImageFolderPath(String pieceImageFolderPath) ;
	
//	public abstract Color get
	
}
