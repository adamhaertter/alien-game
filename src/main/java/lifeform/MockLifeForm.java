package lifeform;

public class MockLifeForm extends LifeForm {

  /**
   * Creates an instance of MockLifeForm, an object nearly identical to the
   * abstract class LifeForm. Mainly used for testing purposes
   * 
   * @param name   The name of the MockLifeForm
   * @param points The Health Points of the MockLifeForm
   */
  public MockLifeForm(String name, int points) {
    super(name, points);
  }
}
