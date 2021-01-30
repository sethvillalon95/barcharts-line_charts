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
        double max = 0;
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
        
        int x=0, y =-10;
        int howManyBars = relativeData.keySet().size();
        int xSpacing = w/ (howManyBars+1);
        x = xSpacing-100;
        for (var jerico : relativeData.keySet()) {
            double barHeight = getWidth() * relativeData.get(jerico);
//            g.drawRect(x,y,(int)barHeight, y);
            String s = jerico;
            g.setColor(Color.black);
            g.drawString(s, x+10, 500);

            g.setColor(Color.BLUE);

            g.fillRect(x, 300, xSpacing, xSpacing);
            System.out.println(jerico);
            x += xSpacing+10;
        }
        
        
       
   
    }



}
