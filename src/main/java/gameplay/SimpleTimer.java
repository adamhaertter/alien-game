package gameplay;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Haertter
 *
 */
public class SimpleTimer extends Thread implements Timer {

  private List<TimerObserver> theObservers = new ArrayList<TimerObserver>();
  private int round;
  private int sleepTime;

  /**
   * Creates an instance of a SimpleTimer. By default, the round length is set to
   * 1 second.
   */
  public SimpleTimer() {
    sleepTime = 1000;
  }

  /**
   * Creates an instance of SimpleTimer with a specified round length.
   * 
   * @param sleep the length of each round in ms.
   */
  public SimpleTimer(int sleep) {
    this();
    sleepTime = sleep;
  }

  /**
   * Updates the round based on the time period set in the constructor
   */
  public void run() {
    int roundCap = 50;
    for (int r = 0; r < roundCap; r++) {
      try {
        sleep(sleepTime);
        timeChanged();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Adds a passed TimerObserver to observe this Timer
   * 
   * @param o the TimerObserver set to observe the Timer in question
   */
  public void addTimeObserver(TimerObserver o) {
    theObservers.add(o);
  }

  /**
   * Updates the round count
   */
  public void timeChanged() {
    round++;
    for (TimerObserver o : theObservers) {
      o.updateTime(round);
    }
  }

  /**
   * Removes a passed TimerObserver from observing this Timer
   * 
   * @param o the TimerObserver to be removed from observing this Timer
   */
  public void removeTimeObserver(TimerObserver o) {
    theObservers.remove(o);
  }

  /**
   * @return the number of TimeObservers currently observing this Timer
   */
  public int getNumObservers() {
    return theObservers.size();
  }

  /**
   * @return the current round count
   */
  public int getRound() {
    return round;
  }

  /**
   * @return the current round time period sleepTime
   */
  public int getSleepTime() {
    return sleepTime;
  }

}
