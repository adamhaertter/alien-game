package gui;

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

public class GameGUI extends JFrame implements ActionListener {

  JButton testButton = new JButton("The Test Button");
  JPanel mainPanel, legend, focus;
  
  public GameGUI() {
    mainPanel = new JPanel();
    legend = new JPanel();
    focus = new JPanel();
    this.setTitle("Design Patterns - Lab 6");
    setSize(800, 700);            //TODO NO HARDCODE
    setLocation(300, 50);         //TODO NO HARDCODE
    
    mainPanel.setSize((int)(getWidth()*0.7), (int)(getHeight()*0.7));
    legend.setSize((int)(getWidth()*0.3), (int)(getHeight()*0.7));
    legend.setLocation(mainPanel.getWidth(), 0);
    focus.setSize(getWidth(), (int)(getHeight()*0.3));
    focus.setLocation(0, mainPanel.getHeight());
    
    this.getContentPane().setLayout(null);
    
    mainPanel.add(new JLabel("Main Panel"));
    legend.add(new JLabel("Legend"));
    focus.add(new JLabel("Focus"));
    
    testButton.addActionListener(this);
    focus.add(testButton);
    
    mainPanel.setBackground(java.awt.Color.RED);
    legend.setBackground(java.awt.Color.BLUE);
    focus.setBackground(java.awt.Color.YELLOW);
    
    add(mainPanel);
    add(legend);
    add(focus);
    setResizable(false);
    setVisible(true);
  }

  public static void main(String[] args) {
    GameGUI gui = new GameGUI();
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
    if(e.getSource() == testButton) {
      if(testButton.getText().equals("The Test Button")) {
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
  
}
