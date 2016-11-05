package theme;

public class ThemeFactory {

	public static BlueberryPie blueberryPieTheme = new BlueberryPie();
	public static HersheyChocolate hersheyChocolateTheme = new HersheyChocolate();
	public static Classic classicTheme = new Classic();
	
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
