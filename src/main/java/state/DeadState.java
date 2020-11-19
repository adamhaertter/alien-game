package state;

public class DeadState extends ActionState {

  /**
   * Creates a DeadState
   * @param ai
   */
  DeadState(AIContext ai) {
    super(ai);
  }
  
  /**
   * Executes DeadState's action
   * Then sets the state to HasWeaponState 
   * or NoWeaponState
   */
  public void executeAction() {
    int x;
    int y;
    if(lifeform.hasWeapon()) {
      boolean dropped = false;
      while(!dropped) {
        x = (int) (Math.random() * e.getNumCols());
        y = (int) (Math.random() * e.getNumRows());
        if(e.getCell(x, y).getWeapon1() == null || e.getCell(x, y).getWeapon2() == null) {
          e.addWeapon(lifeform.dropWeapon(), x, y);
          dropped = true; 
        } 
      }
    }
    boolean placed = false;
    while(!placed) {
      x = (int) (Math.random() * e.getNumCols());
      y = (int) (Math.random() * e.getNumRows());
      if(e.getCell(x, y).getLifeForm() == null) {
        lifeform.revive();
        e.getCell(lifeform.getCol(), lifeform.getRow()).removeLifeForm();
        e.getCell(x, y).addLifeForm(lifeform);
        placed = true;
      }
      if(lifeform.hasWeapon()) {
        context.setCurrentState(context.getHasWeaponState());
      } else {
        context.setCurrentState(context.getNoWeaponState());
      }
    }
  }
  
}
