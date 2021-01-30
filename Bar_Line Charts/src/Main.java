
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main extends JFrame {

    private Vis mainPanel;

    public Main() {

        JMenuBar mb = setupMenu();
        setJMenuBar(mb);

        mainPanel = new Vis();
        setContentPane(mainPanel);

        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Put the title of your program here");
        setVisible(true);
    }

    //select count(*) from derbyDB
    private int runSimpleCountQuery(String q) {
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:pollster");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            rs.next();
            int count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            System.out.println("could not connect to Derby!");
            return 0;
        }
    }

    private Map<String, Double> performTwoColumnQuery(String q) {
        Map<String, Double> results = new HashMap<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:derby:pollster");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {
                double num = rs.getDouble(1);
                String label = rs.getString(2);
                results.put(label, num);
            }
        } catch (SQLException e) {
        	
            System.out.println("could not connect to Derby!");
        }
        return results;
    }

    private JMenuBar setupMenu() {
        //instantiate menubar, menus, and menu options
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem item1 = new JMenuItem("Item 1");
        JMenu subMenu = new JMenu("Submenu");
        JMenuItem item2 = new JMenuItem("Item 2");

        //setup action listeners
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 1");
                int gilmo = runSimpleCountQuery("SELECT COUNT(*) FROM cis2019");
                System.out.println("I found " + gilmo + " rows in the table.");
                mainPanel.setText("I found " + gilmo + " rows in the table.");
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Just clicked menu item 2");
                ArrayList<Double> values = new ArrayList<Double>(); 
                
                var lauren = performTwoColumnQuery("select count(*), home from cis2019 group by home");
                for (var k : lauren.keySet()) {
                    double num = lauren.get(k);
                    System.out.println(k + " : " + num);
                    //instatiate new Chart class 
                    
//                    values.add(num);
                }
                
                mainPanel.setData(lauren);
//                double max = maxNumber(values);
//                
//                System.out.println("The max number is "+max);
            }
        });

        //now hook them all together
        subMenu.add(item2);
        fileMenu.add(item1);
        fileMenu.add(subMenu);
        menuBar.add(fileMenu);

        return menuBar;
    }
//    
//    private double maxNumber(ArrayList<Double> myList) {
//    	double largest=0;
//    	Collections.sort(myList);
//    	largest =(double) myList.get(myList.size()-1);
//    	return largest;
//    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}