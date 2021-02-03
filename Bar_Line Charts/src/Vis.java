import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;


public class Vis extends JPanel {

    private String textToDisplay;
    private Map<String, Double> data;
    private Map<String, Double> relativeData;
    double max_num;
    SpinnerNumberModel yMax;


    public Vis() {
        super();
        textToDisplay = "There's nothing to see here.";
        relativeData = new HashMap<>();
        data = new HashMap<>();
        
        yMax = new SpinnerNumberModel();
    }

    public void setText(String t) {
        textToDisplay = t;
        repaint();
    }

	public void setData(Map<String, Double> acacia) {
		// TODO Auto-generated method stub
        data = acacia;
        var allValues = data.values();
        double max=0;
        max_num=0;
        for (var kaipo : allValues) {
            if (kaipo > max) {
                max = kaipo;
                max_num=kaipo;
            }
        }
        for (var key : data.keySet()) {
            relativeData.put(key, data.get(key) / max);
        }
        repaint();	
	}
	
	public void clearMap() {
//		System.out.println("clearMap ran");
//		System.out.println("data.clear() is  "+ relativeData.isEmpty());
		if(!relativeData.isEmpty() && !data.isEmpty()) {
			relativeData.clear();
		}
		


	}
    @Override
    public void paintComponent(Graphics g1) {
        Graphics2D g = (Graphics2D)g1;
        int h= getHeight();
        int w = getWidth();
        int x=0, y =0;
        int howManyBars;
        int xSpacing;
    	double barWidth;
    	double ratio;
    	double barHeight;
        int yLabel;
        int xLine;
        int yLine;
        int scaler;
        

        //draw blank background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        //render visualization
        g.setColor(Color.BLACK);
//        g.drawString(textToDisplay, 10, 20);
        
//        BarChart bc = new BarChart();
//        bc.draw(g1, w, h);
        

        
        // how many bars/data we need to draw
        howManyBars = relativeData.keySet().size();
        
//        System.out.println("The number of bars is "+ howManyBars);
        
        // calculates the spacing
        xSpacing = w/ (howManyBars+1);
        x = xSpacing-100;

        
        
        // renders the bar 
        for (var jerico : relativeData.keySet()) {
//           double barHeight = getWidth() * relativeData.get(jerico);
       	
        	barWidth= (w/howManyBars)/2;
        	ratio = relativeData.get(jerico);
        	
        	
        	barHeight = (int)(h*ratio*.90);
        	
        	System.out.println("relativeData.get is "+relativeData.get(jerico));

        	System.out.println("The barHeight is "+ barHeight);
        	
            String s = jerico;
            g.setColor(Color.black);
            
            yLabel = (int)(h*.98);
            g.drawString(s, x+10, yLabel);
            
            
            // draw the vertical line on the left
            xLine =(int)(w*.03);
            yLine =(int)(h*.96);
            // vertical line;
            g.drawLine(xLine, 0, xLine, yLine);
            
            //horizontal line; 
            g.drawLine(xLine,yLine,w,yLine);
            
            
            
            
            
            
            

            g.setColor(Color.BLUE);
//            System.out.println("barWidth is: "+ barWidth +" BarHeight is: "+ barHeight);
            
            // Draw the bars
            y =(int) ((h*.95)-barHeight);
            String yValue = Double.toString(ratio*max_num);

            g.drawString(yValue, xLine,y);

            g.fillRect(x, y, (int)barWidth,(int)barHeight);

            
//            g.fillRect(x, 300, 156,120);

           x += xSpacing;
        }
        
        System.out.println("*******************End *******************");

       
   
    }



}
