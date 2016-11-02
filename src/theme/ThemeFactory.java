package theme;

public class ThemeFactory {

	public ThemeFactory() {
		// TODO Auto-generated constructor stub
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
