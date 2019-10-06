package lifegame;
// 存储细胞状态的二维数组
//0代表细胞死亡   1代表细胞活着

public class DataStorage {
  private int width;      //细胞棋盘的宽
  private int height;      //细胞棋盘的高
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