
public class Chart {
	
	// this is used to choose which render if bar or not`
	private boolean isBar = true;
	private int value = 0;
	private String label = "";
	
	
	// constructor
	
	public Chart() {
		// do something here 
	}
	
	// constructor for the plot
	public Chart(boolean typeOfChart, int val, String lab) {
			isBar = typeOfChart;
			value = val;
			label = lab;
	}
	
	
	// might need to do some helper methods 

}
