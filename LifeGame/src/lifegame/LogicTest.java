package lifegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LogicTest {
  private static Logic test = new Logic(3,3);

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testLogic() {
   
  }

  @Test
  public void testGetTemp() {
    assertEquals(0, test.getTemp(1,1));
  }

  @Test
  public void testChangeTemp() {
    int[][] temp = {{0,0,0,0,0},{0,1,1,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0}};
    test.changeTemp(temp);
    assertEquals(0, test.getTemp(1,1));
  }

  @Test
  public void testDiff() {
    int[][] temp = {{0,0,0,0,0},{0,1,1,1,0},{0,1,1,1,0},{0,1,1,1,0},{0,0,0,0,0}};
    test.diff(temp);
    assertEquals(0, test.getTemp(2,2));
    assertEquals(0, test.getTemp(1,1));
  }

  @Test
  public void testCount() {
    assertEquals(0, test.count(2,2));
  }

  @Test
  public void testJug() {
    assertEquals(1, test.jug(2,1));
  }

}
