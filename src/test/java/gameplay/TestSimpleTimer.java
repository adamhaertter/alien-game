package gameplay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestSimpleTimer {

  /**
   * Tests to ensure that the SimpleTimer is initialized properly.
   */
  @Test
  public void testInitialization() {
    SimpleTimer st = new SimpleTimer();
    assertEquals(st.getSleepTime(), 1000);
    assertEquals(st.getRound(), 0);
  }

  /**
   * Tests to ensure that the rounds are updated properly under a SimpleTimer.
   */
  @Test
  public void testTimeUpdate() {
    SimpleTimer st = new SimpleTimer();
    assertEquals(st.getRound(), 0);
    st.timeChanged();
    assertEquals(st.getRound(), 1);
    st.timeChanged();
    assertEquals(st.getRound(), 2);
  }

  /**
   * Creates a SimpleTimer and MockSimpleTimerObserver and, once the observer is
   * added, tests to see that the observer is added properly.
   */
  @Test
  public void testAddObserver() {
    MockSimpleTimerObserver msto = new MockSimpleTimerObserver();
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(msto);
    assertEquals(st.getNumObservers(), 1);
  }

  /**
   * Creates a SimpleTimer and an example observer for that timer. This test
   * checks that the basic example observer is able to keep track of round updates
   * from the SimpleTimer.
   */
  @Test
  public void testReceiveUpdate() {
    MockSimpleTimerObserver msto = new MockSimpleTimerObserver();
    SimpleTimer st = new SimpleTimer();
    st.addTimeObserver(msto);
    st.timeChanged();
    assertEquals(msto.myTime, 1);
    st.timeChanged();
    assertEquals(msto.myTime, 2);
  }

  /**
   * This tests that SimpleTimer will update time once every second. This method
   * was provided by the instructor in the lab outline.
   */
  @Test
  public void testSimpleTimerAsThread() throws InterruptedException {
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x = 0; x < 5; x++) {
      assertEquals(x, st.getRound()); // assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }
  
  class MockSimpleTimerObserver implements TimerObserver {
    public int myTime = 0;

    public void updateTime(int time) {
      myTime = time;
    }
  }
}
