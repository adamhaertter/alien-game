package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
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
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import environment.Cell;
import environment.Environment;
import exceptions.AttachmentException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import weapon.*;

public class GameGUI extends JFrame implements ActionListener {

  JButton testButton = new JButton("The Test Button");
  JPanel mainPanel, legend, focus;
  JLabel currentCellDisplay, currentLFData, currentWeaponData;
  static Environment environment = Environment.getEnvironment(5, 6);
  JButton cellsOnBoard[][];
  List<JButton> cellScreen = new ArrayList<>();

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

    // mainPanel.setLayout(new GridLayout());
    mainPanel.setLayout(new GridLayout(environment.getNumRows(), environment.getNumCols()));
    // Double check that this is the right way later when less tired ^
    // TODO Populate Grid
    cellsOnBoard = new JButton[environment.getNumRows()][environment.getNumCols()];
    buildCellGrid();

    focus.setLayout(new BorderLayout());
    focus.add("North", new JLabel("Focus Panel"));
    currentCellDisplay = new JLabel("Current Cell Here", JLabel.CENTER);
    focus.add("West", currentCellDisplay);
    currentLFData = new JLabel("Current LifeForm Data");
    focus.add("Center", currentLFData);
    currentWeaponData = new JLabel("Current Weapon Data");
    focus.add("East", currentWeaponData);
    focus.add("South", testButton);
    createCellText(0, 0);

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
    environment.addLifeForm(new Human("TestHuman", 10, 10), 1, 1);
    environment.addLifeForm(new Alien("TestAlien", 10), 1, 2);
    environment.addLifeForm(new MockLifeForm("TestMock", 10, 10), 1, 0);

    environment.addWeapon(new Pistol(), 0, 1);
    environment.addLifeForm(new Human("T", 5, 1), 0, 1);
    environment.getLifeForm(0, 1).pickUpWeapon(new PlasmaCannon());
    environment.addWeapon(new ChainGun(), 0, 1);
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
      return;
    }

    if (cellScreen.contains(e.getSource())) {
      JButton src = (JButton) e.getSource();
      int r = cellScreen.indexOf(src) / environment.getNumCols();
      int c = cellScreen.indexOf(src) % environment.getNumCols();
      updateCellImage(r, c);
      src.setIcon(new ImageIcon(drawSingleCell(r, c)));
      src.setText("");
      createCellText(r, c);
      try {
        // src.setIcon(new ImageIcon(ImageIO.read(new File("img/testimg.png"))));
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      return;
    }

  }

  public void buildCellGrid() {
    /*
     * for (int r = 0; r < environment.getNumRows(); r++) { for (int c = 0; c <
     * environment.getNumCols(); c++) { cellsOnBoard[r][c] = new
     * JButton("r: "+r+", c: "+c); mainPanel.add(cellsOnBoard[r][c]); } }
     */

    for (int i = 0; i < environment.getNumRows() * environment.getNumCols(); i++) {
      cellScreen.add(new JButton("i: " + i));
      cellScreen.get(i).addActionListener(this);
      mainPanel.add(cellScreen.get(i));
    }
  }

  public void createCellText(int row, int col) {
    LifeForm lf = environment.getLifeForm(row, col);
    String str = "";

    if (lf != null) {
      str = "<html>LifeForm Type: " + lf.toString() + "<br>";
      str += "Current Health: " + lf.getCurrentLifePoints() + "<br>";
      if (lf.hasWeapon()) {
        str += "Current Weapon: " + lf.getWeapon().toString() + "<br>";
      } else {
        str += "Current Weapon: N/A<br>";
      }
      str += "Direction: " + lf.getDirection() + "<br></html>";
    } else {
      str = "<html>LifeForm Type: N/A<br>Current Health: N/A<br>Current Weapon: N/A<br>Direction: N/A<br></html>";
    }
    currentLFData.setText(str);

    str = "<html>";
    str += "Weapons Here:<br>";
    if (environment.getWeapons(row, col)[0] != null) {
      str += "Weapon 1: " + environment.getWeapons(row, col)[0].toString() + "<br>";
    } else {
      str += "Weapon 1: N/A<br>";
    }
    if (environment.getWeapons(row, col)[1] != null) {
      str += "Weapon 2: " + environment.getWeapons(row, col)[1].toString() + "<br>";
    } else {
      str += "Weapon 2: N/A<br>";
    }
    str += "</html>";
    currentWeaponData.setText(str);
  }

  public void updateCellImage(int row, int col) {
    LifeForm lf = environment.getLifeForm(row, col);
    String str = "";
    Image img = null;

    if (lf != null) {
      str = lf.toString();

      try {
        img = ImageIO.read(new File("img/" + str + ".png"));
        if (lf.hasWeapon()) {
          Graphics gi = img.getGraphics();
          String weaponText = lf.getWeapon().toString();
          if (lf.getWeapon().getNumAttachments() > 0)
            weaponText = weaponText.substring(0, weaponText.indexOf(" +"));
          Image subImage = ImageIO.read(new File("img/" + weaponText + ".png"));
          subImage = subImage.getScaledInstance(img.getWidth(null) / 2, img.getHeight(null) / 2, Image.SCALE_SMOOTH);
          gi.drawImage(subImage, img.getWidth(null) / 2, img.getHeight(null) / 2, null);
          gi.dispose();
        }
        img = img.getScaledInstance(focus.getHeight() * 3 / 4, focus.getHeight() * 3 / 4, Image.SCALE_DEFAULT);
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println("Image not found, displaying text instead");
      }

    } else {
      str = "No LifeForm Present in this Cell";
    }

    if (img != null) {
      currentCellDisplay.setIcon(new ImageIcon(img));
      currentCellDisplay.setText("");
    } else {
      currentCellDisplay.setText(str);
      currentCellDisplay.setIcon(null);
    }
    // Change this to work with the necessary images;

    repaint();
  }

  public Image drawSingleCell(int row, int col) {
    Image img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
    LifeForm lf = environment.getLifeForm(row, col);
    if (lf != null) {
      try {
        img = ImageIO.read(new File("img/" + lf.toString() + ".png"));
        Graphics gi = img.getGraphics();
        // Draw Held Weapon to Bottom-Left Corner
        if (lf.hasWeapon()) {
          String weaponText = lf.getWeapon().toString();
          if (lf.getWeapon().getNumAttachments() > 0)
            weaponText = weaponText.substring(0, weaponText.indexOf(" +"));
          Image subImage = ImageIO.read(new File("img/" + weaponText + ".png"));
          subImage = subImage.getScaledInstance(img.getWidth(null) / 2, img.getHeight(null) / 2, Image.SCALE_SMOOTH);
          gi.drawImage(subImage, 0, img.getHeight(null) / 2, null);
        }

        // Draw Direction Icon to Bottom-Right Corner
        Image subImage = ImageIO.read(new File("img/" + lf.getDirection() + ".png"));
        subImage = subImage.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, Image.SCALE_SMOOTH);
        gi.drawImage(subImage, img.getWidth(null) * 3 / 4, img.getHeight(null) * 3 / 4, null);

        // Draw Weapon Count in Top-Left Corner
        Weapon[] weaponsInCell = environment.getWeapons(row, col);
        if (weaponsInCell[0] != null || weaponsInCell[1] != null) {
          subImage = ImageIO.read(new File("img/" + weaponsInCell.length + "_weapon.png"));
          subImage = subImage.getScaledInstance(img.getWidth(null) / 4, img.getHeight(null) / 4, Image.SCALE_SMOOTH);
          gi.drawImage(subImage, img.getWidth(null) / 16, img.getHeight(null) / 16, null);
        }

        gi.dispose();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        System.out.println("Image not found, displaying text instead");
      }
    }

    JButton me = cellScreen.get((row * col) + col);
    img = img.getScaledInstance(me.getWidth(), me.getHeight(), Image.SCALE_SMOOTH);
    return img;
  }

}
