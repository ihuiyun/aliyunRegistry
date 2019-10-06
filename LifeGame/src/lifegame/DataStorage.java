package lifegame;
// �洢ϸ��״̬�Ķ�ά����
//0����ϸ������   1����ϸ������

public class DataStorage {
  private int width;      //ϸ�����̵Ŀ�
  private int height;      //ϸ�����̵ĸ�
  private int[][] storage;
  
  public DataStorage(int width,int hight) {
    this.width = width;
    this.height = hight;
    storage = new int[hight + 2][width + 2];
    for (int i = 0; i <= hight + 1; i++) {
      for (int j = 0; j <= width + 1; j++) {
        storage[i][j] = 0;
      }
    }
  }
  
  public int get(int i,  int j) {
    return storage[i][j];
  }
  
  public void change(int[][] next) {
    for (int i = 1; i <= height; i++) {
      for (int j = 1; j <= width; j++) {
        storage[i][j] = next[i][j];
      }
    }
  }
}