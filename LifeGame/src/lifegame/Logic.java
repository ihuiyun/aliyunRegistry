package lifegame;
//细胞迭代逻辑处理类

public class Logic {
  private int[][] temp;
  private int[][] data;
  private int width;
  private int height;
  private DataStorage  realCell;
  /*
   * Logic.
   * */
  
  public Logic(int width,int hight) {
    this.width = width;
    this.height = hight;
    realCell = new DataStorage(height,width);
    temp = new int[height + 2][width + 2];
    data = new int[height + 2][width + 2];
    for (int i = 1; i < height + 1; i++) {
      for (int j = 1; j < width + 1; j++) {
        temp[i][j] = 0;
        data[i][j] = 0;
      }
    }
  }
      
  public int getTemp(int i, int j) {
    return realCell.get(i + 1,j + 1);
  }

  public void changeTemp(int[][] user) {
    for (int i = 1; i <= height; i++) {
      for (int j = 1; j <= width; j++) {
        temp[i][j] = user[i - 1][j - 1];
      }
    }
    realCell.change(data);
  }

  public void diff(int[][] cell) {
    for (int i = 1; i <= height; i++) {
      for (int j = 1; j <= width; j++) {
        data[i][j] = jug(count(i,j),temp[i][j]);
        cell[i - 1][j - 1] = data[i][j];
      }
    }
    realCell.change(data);
  }
      
  public int count(int i,int j) {
    int sum = 0;
    for (int k = i - 1; k <= i + 1; k++) {
      for (int l = j - 1; l <= j + 1; l++) {
        if (temp[k][l] == 1) {
          sum++;
        }
      }
    }
    if (temp[i][j] == 1) {
      sum--;
    }
    return sum;
  }
      
  public int jug(int x,int stat) {
    if (stat == 0) {
      if (x == 3) {
        return 1;
      } else {
        return 0;
      }
    } else {
      if (stat == 1 && (x == 3 || x == 2)) {
        return 1;
      } else {
        return 0;
      }
    }
  }
}
