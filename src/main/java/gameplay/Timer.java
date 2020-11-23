package gameplay;

/**
 * @author Adam Haertter
 *
 */
public interface Timer {

  /**
   * Adds a passed TimerObserver to observe this Timer
   * 
   * @param o the TimerObserver set to observe the Timer in question
   */
  public void addTimeObserver(TimerObserver o);

  /**
   * Updates the round count
   */
  public void timeChanged();

  /**
   * Removes a passed TimerObserver from observing this Timer
   * 
   * @param o the TimerObserver to be removed from observing this Timer
   */
  public void removeTimeObserver(TimerObserver o);

}
