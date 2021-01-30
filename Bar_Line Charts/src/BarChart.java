import java.awt.Graphics;

public class BarChart {
	private int value = 0; 
	private String label ="";
	int barWidth = 0; 
	int barHeight = 0; 
	
	
	// constructor
	public BarChart() {
		
	}
	
	public void draw(Graphics g, int w, int h) {
		barWidth = w/3; // change 3 with keys
		barHeight = h*2/3; // change with height*ratio
		
		g.fillRect(120, 10, barWidth, barHeight);
		
		
	}
	
	public void draw(Graphics g) {

		
		
	}
}
