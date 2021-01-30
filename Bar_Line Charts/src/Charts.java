import java.awt.Graphics;
import java.util.ArrayList;

public class Charts {
	private ArrayList<BarChart> barcharts;
	
	public Charts() {
		barcharts= new ArrayList<>();
		
		
	}
	public void draw(Graphics g) {
		for(var b : barcharts) {
			b.draw(g, 0, 0);
		}
	}
}
