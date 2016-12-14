package theme;

final public class ThemeFactory {

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
		return Classic.createClassicTheme();
	}
	
	public Theme createBlueberryPieTheme() {
		return BlueberryPie.createBlueberryPieTheme();
	}
	
	public Theme createHersheyChocolateTheme() {
		return HersheyChocolate.createHersheyChocoloateTheme();
	}
	

}
