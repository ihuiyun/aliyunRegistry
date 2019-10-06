package lifegame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DataStorageTest {
  private static DataStorage test = new DataStorage(3,3);
  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testDataStorage() {
   
  }

  @Test
  public void testGet() {
    int[][] temp = {{0,0,0,0,0},{0,1,1,1,0},{0,2,2,2,0},{0,3,3,3,0},{0,0,0,0,0}};
    test.change(temp);
    assertEquals(0, test.get(0,0));
    assertEquals(1, test.get(1,1));
    assertEquals(2, test.get(2,2));
  }

  @Test
  public void testChange() {
   
  }

}
