package environment;

import java.util.ArrayList;
import java.util.List;

import lifeform.LifeForm;

public class CommandInvoker {
  private final List<Invokers> cmds = new ArrayList<>();

  public void executeCommand(Invokers cmd, LifeForm lf, Environment env) {
    cmds.add(cmd);
    cmd.execute(lf, env);
  }
}