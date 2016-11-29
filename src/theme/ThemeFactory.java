package theme;

public class ThemeFactory {

	private static ThemeFactory sharedFactory;
	
	private ThemeFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static ThemeFactory getSharedFThemeFactory() {
		if(sharedFactory == null) {
			sharedFactory = new ThemeFactory();
		}
		return sharedFactory;
	}
	
	public Theme createClassicTheme() {
		return new Classic();
	}
	
	public Theme createBlueberryPieTheme() {
		return new BlueberryPie();
	}
	
	public Theme createHersheyChocolateTheme() {
		return new HersheyChocolate();
	}
	

}
