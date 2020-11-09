package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import environment.*;

public class ControllerGUI extends JFrame implements ActionListener {
  JButton attack, reload, west, north, east, south, move, drop, getOne, getTwo;
  GameGUI parent;

  public ControllerGUI(GameGUI gui) {
    parent = gui;

    JPanel corePanel = new JPanel();
    corePanel.setLayout(new BorderLayout());
    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BorderLayout());
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    this.setLayout(new BorderLayout());
    this.setTitle("Controller");

    attack = new JButton("Attack");
    attack.addActionListener(this);
    reload = new JButton("Reload");
    reload.addActionListener(this);
    west = new JButton("West");
    west.addActionListener(this);
    north = new JButton("North");
    north.addActionListener(this);
    south = new JButton("South");
    south.addActionListener(this);
    east = new JButton("East");
    east.addActionListener(this);
    move = new JButton("Move");
    move.addActionListener(this);
    drop = new JButton("Drop");
    drop.addActionListener(this);
    getOne = new JButton("GetOne");
    getOne.addActionListener(this);
    getTwo = new JButton("GetTwo");
    getTwo.addActionListener(this);

    leftPanel.add("Center", attack);
    leftPanel.add("South", reload);

    corePanel.add("West", west);
    corePanel.add("North", north);
    corePanel.add("South", south);
    corePanel.add("Center", move);
    corePanel.add("East", east);

    rightPanel.add("North", drop);
    rightPanel.add("Center", getOne);
    rightPanel.add("South", getTwo);

    add("West", leftPanel);
    add("Center", corePanel);
    add("East", rightPanel);
    pack();
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    CommandInvoker cInvoker = new CommandInvoker();
    if (e.getSource() == attack && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new attackCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("attacked");
    } else if (e.getSource() == reload && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new reloadCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("reloaded");
    } else if (e.getSource() == drop && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new dropCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("dropped");
    } else if (e.getSource() == move && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new moveCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("moved");
    } else if (e.getSource() == east && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new turnEastCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("east");
    } else if (e.getSource() == north && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new turnNorthCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("north");
    } else if (e.getSource() == west && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new turnWestCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("west");
    } else if (e.getSource() == south && parent.focusedCell.getLifeForm()!=null) {
      cInvoker.executeCommand(new turnSouthCommand(), parent.focusedCell.getLifeForm(), parent.environment);
      System.out.println("south");
    }
    parent.boardUpdate();
  }
}