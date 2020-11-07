package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;

public class GameGUI extends JFrame implements ActionListener {

  JButton testButton = new JButton("The Test Button");
  JPanel mainPanel, legend, focus;
  JLabel currentCellDisplay, currentLFData, currentWeaponData;
  //Environment environment;

  public GameGUI() {
    mainPanel = new JPanel();
    legend = new JPanel();
    focus = new JPanel();
    this.setTitle("Design Patterns - Lab 6");
    setSize(800, 700); // TODO NO HARDCODE
    setLocation(300, 50); // TODO NO HARDCODE

    mainPanel.setSize((int) (getWidth() * 0.8), (int) (getHeight() * 0.7));
    legend.setPreferredSize(new Dimension((int) (getWidth() * 0.2), (int) (getHeight() * 0.7)));
    legend.setLocation(mainPanel.getWidth(), 0);
    focus.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.3)));
    // focus.setLocation(0, mainPanel.getHeight());

    this.getContentPane().setLayout(new BorderLayout());

    // mainPanel.add(new JLabel("Main Panel"));
    legend.add(new JLabel("Legend"));
    // focus.add(new JLabel("Focus"));

    testButton.addActionListener(this);
    // focus.add(testButton);

    mainPanel.setLayout(new GridLayout());
    //mainPanel.setLayout(new GridLayout(environment.getNumRows(), environment.getNumCols()));
    // Double check that this is the right way later when less tired ^
    //TODO Populate Grid
    
    focus.setLayout(new BorderLayout());
    focus.add("North", new JLabel("Focus Panel"));
    currentCellDisplay = new JLabel("Current Cell Here", JLabel.CENTER);
    focus.add("West", currentCellDisplay);
    currentLFData = new JLabel("Current LifeForm Data");
    focus.add("Center", currentLFData);
    currentWeaponData = new JLabel("Current Weapon Data");
    focus.add("East", currentWeaponData);
    focus.add("South", testButton);
    createCellText(new Cell());
    
    mainPanel.setBackground(java.awt.Color.RED);
    legend.setBackground(java.awt.Color.BLUE);
    focus.setBackground(java.awt.Color.YELLOW);

    // setSize(getWidth(), testButton.getLocation().y+testButton.getHeight());
    getContentPane().add("Center", mainPanel);
    getContentPane().add("East", legend);
    getContentPane().add("South", focus);
    // pack();
    // setResizable(false);
    setVisible(true);
  }

  public static void main(String[] args) {
    GameGUI gui = new GameGUI();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    if (e.getSource() == testButton) {
      if (testButton.getText().equals("The Test Button")) {
        mainPanel.setBackground(java.awt.Color.ORANGE);
        legend.setBackground(java.awt.Color.MAGENTA);
        focus.setBackground(java.awt.Color.GREEN);
        testButton.setText("The Magic Button");
      } else {
        mainPanel.setBackground(java.awt.Color.RED);
        legend.setBackground(java.awt.Color.BLUE);
        focus.setBackground(java.awt.Color.YELLOW);
        testButton.setText("The Test Button");
      }
    }

  }

  public void createCellText(Cell cell) {
    LifeForm lf = cell.getLifeForm();
    String str = "";
    
    if(lf!=null) {
    str = "<html>LifeForm Type: " + lf.toString() + "<br>";
    str += "Current Health: " + lf.getCurrentLifePoints() + "<br>";
    if(lf.hasWeapon()) { str += "Current Weapon: " + lf.getWeapon().toString() + "<br>"; }
    else { str += "Current Weapon: N/A<br>"; }
    str += "Direction: " + lf.getDirection() + "<br></html>";
    } else {
      str = "<html>LifeForm Type: N/A<br>Current Health: N/A<br>Current Weapon: N/A<br>Direction: N/A<br></html>";
    }
    currentLFData.setText(str);
    
    str = "<html>";
    str += "Weapons Here:<br>";
    if(cell.getWeapon1()!=null) { str += "Weapon 1: " + cell.getWeapon1().toString() + "<br>"; }
    else { str += "Weapon 1: N/A<br>"; }
    if(cell.getWeapon2()!=null) { str += "Weapon 2: " + cell.getWeapon2().toString() + "<br>"; }
    else { str += "Weapon 2: N/A<br>"; }
    str += "</html>";
    currentWeaponData.setText(str);
  }
  
  public void updateCellImage(Cell cell) {
    LifeForm lf = cell.getLifeForm();
    String str = "";
    
    if(lf != null) {
      str = lf.toString();
          if(lf.hasWeapon()) {
            str += " " + lf.getWeapon().toString();
          }
    }
    
    currentCellDisplay.setText(str);
    //Change this to work with the necessary images;
    
    repaint();    
  }
  
}
