package environment;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
  private final List<Commands> cmds = new ArrayList<>();

  public String executeCommand(Commands cmd) {
    cmds.add(cmd);
    return cmd.execute();
  }
}
