package state;

public class DeadState extends ActionState {

  DeadState() { //when done add AIContext
    //context = ai;
  }
  
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
        e.getCell(lifeform.getCol(), lifeform.getRow()).removeLifeForm();
        e.getCell(x, y).addLifeForm(lifeform);
        placed = true;
      }
    }
  }
  
}
