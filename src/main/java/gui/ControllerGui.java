package gui;

import environment.AttackCommand;
import environment.CommandInvoker;
import environment.DropCommand;
import environment.ReloadCommand;
import environment.MoveCommand;
import environment.TurnEastCommand;
import environment.TurnNorthCommand;
import environment.TurnSouthCommand;
import environment.TurnWestCommand;
import environment.AcquireCommand;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ControllerGui extends JFrame implements ActionListener {
  JButton attack;
  JButton reload;
  JButton west;
  JButton north;
  JButton east;
  JButton south;
  JButton move;
  JButton drop;
  JButton acquire;
  GameGui parent;

  /**
   * Creates the controller to control the game gui, invokes commands
   *
   * @param gui parent game gui
   */
  public ControllerGui(GameGui gui) {
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
    acquire = new JButton("Acquire");
    acquire.addActionListener(this);

    leftPanel.add("Center", attack);
    leftPanel.add("South", reload);

    corePanel.add("West", west);
    corePanel.add("North", north);
    corePanel.add("South", south);
    corePanel.add("Center", move);
    corePanel.add("East", east);

    rightPanel.add("Center", acquire);
    rightPanel.add("South", drop);

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
    CommandInvoker invoker = new CommandInvoker();
    if (e.getSource() == attack && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new AttackCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == reload && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new ReloadCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == drop && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new DropCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == move && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new MoveCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == east && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new TurnEastCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == north && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new TurnNorthCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == west && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new TurnWestCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == south && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new TurnSouthCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    } else if (e.getSource() == acquire && parent.focusedCell.getLifeForm() != null) {
      invoker.executeCommand(
          new AcquireCommand(), parent.focusedCell.getLifeForm(), parent.environment
      );
    }
    parent.boardUpdate();
  }
}