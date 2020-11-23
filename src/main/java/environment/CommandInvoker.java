package environment;

import java.util.ArrayList;
import java.util.List;

import lifeform.LifeForm;

/**
 * @author Brennan Mulligan - modified by Scott Bucher and Adam Haertter
 *
 */
public class CommandInvoker {
  private final List<Invokers> cmds = new ArrayList<>();

  /**
   * Command invoker
   *
   * @param cmd - command
   * @param lf  - life form
   * @param env - environment
   */
  public void executeCommand(Invokers cmd, LifeForm lf, Environment env) {
    cmds.add(cmd);
    cmd.execute(lf, env);
  }
}