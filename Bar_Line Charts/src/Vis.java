import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


public class Vis extends JPanel {

    private String textToDisplay;
    private Map<String, Double> data;
    private Map<String, Double> relativeData;
    double max = 0;


    public Vis() {
        super();
        textToDisplay = "There's nothing to see here.";
        relativeData = new HashMap<>();
    }

    public void setText(String t) {
        textToDisplay = t;
        repaint();
    }

	public void setData(Map<String, Double> acacia) {
		// TODO Auto-generated method stub
        data = acacia;
        var allValues = data.values();

        for (var kaipo : allValues) {
            if (kaipo > max) {
                max = kaipo;
            }
        }
        for (var key : data.keySet()) {
            relativeData.put(key, data.get(key) / max);
        }
        repaint();	
	}
    @Override
    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int h= getHeight();
        int w = getWidth();

        //draw blank background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        //render visualization
        g.setColor(Color.BLACK);
//        g.drawString(textToDisplay, 10, 20);
        
//        BarChart bc = new BarChart();
//        bc.draw(g1, w, h);
        
        int x=0, y =0;
        
        // how many bars/data we need to draw
        int howManyBars = relativeData.keySet().size();
        
        System.out.println("The number of bars is "+ howManyBars);
        
        // calculates the spacing
        int xSpacing = w/ (howManyBars+1);
        x = xSpacing-100;
        
//        double test = 150/max;
//        g.fillRect(10, 300, 156,h*(int)test);
        
        
        // renders the bar 
        for (var jerico : relativeData.keySet()) {
//           double barHeight = getWidth() * relativeData.get(jerico);
       	
        	double barWidth= w/howManyBars;
        	double ratio = relativeData.get(jerico);
        	double barHeight = h*ratio;
          System.out.println("relativeData.get is "+relativeData.get(jerico));

        	System.out.println("The barHeight is "+ barHeight);
        	
            String s = jerico;
            g.setColor(Color.black);
            g.drawString(s, x+10, 500);

            g.setColor(Color.BLUE);
//            System.out.println("barWidth is: "+ barWidth +" BarHeight is: "+ barHeight);
            
            // Draw the bars
            y =(int) ((h*.95)-barHeight);
            g.fillRect(x, y, (int)barWidth,(int)barHeight);
//            g.fillRect(x, 300, 156,120);

           System.out.println(jerico);
           x += xSpacing+90;
        }
        
        
       
   
    }



}
