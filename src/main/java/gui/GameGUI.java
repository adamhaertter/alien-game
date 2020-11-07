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

public class GameGUI extends JFrame implements ActionListener {

  JButton testButton = new JButton("The Test Button");
  JPanel mainPanel, legend, focus;
  JLabel currentCellDisplay, currentLFData, currentWeaponData;

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

    mainPanel.setLayout(new GridLayout(1, 1));
    mainPanel.add(new JLabel("Main Grid Panel"));

    focus.setLayout(new BorderLayout());
    focus.add("North", new JLabel("Focus Panel"));
    currentCellDisplay = new JLabel("Current Cell Here", JLabel.CENTER);
    focus.add("West", currentCellDisplay);
    currentLFData = new JLabel("Current LifeForm Data", JLabel.CENTER);
    focus.add("Center", currentLFData);
    currentWeaponData = new JLabel("Current Weapon Data", JLabel.CENTER);
    focus.add("East", currentWeaponData);
    focus.add("South", testButton);

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

}
