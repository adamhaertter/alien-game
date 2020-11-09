package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerGUI extends JFrame implements ActionListener {
  JButton attack, reload, west, north, east, south, move, drop, getOne, getTwo;

  public ControllerGUI() {
    JPanel panel = new JPanel();
    setLayout(new BorderLayout());

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

    panel.add("West", attack);
    panel.add("West", reload);
    panel.add("West", west);
    panel.add("North", north);
    panel.add("South", south);
    panel.add("Center", move);
    panel.add("East", east);
    panel.add("East", drop);
    panel.add("East", getOne);
    panel.add("East", getTwo);

    add(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}