package lifegame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class GuiUser extends JFrame implements ActionListener {

  private int maxHeight;
  private int maxWidth;
  private Logic logic;
  
  @SuppressWarnings("unused")
  private static GuiUser frame;
  private JPanel panel;   //����
  private JPanel center;
  private JPanel top;
  
  private JButton ready;     //��ť
  private JButton nextOne;
  private JButton nextFive;
  private JButton end;
  private JButton[][] userCell;
  private JLabel height;
  private JLabel width;
  @SuppressWarnings("rawtypes")
  private JComboBox cheight;
  @SuppressWarnings("rawtypes")
  private JComboBox cwidth;
  private int[][] tempCell;
    
  private static int time = 5;   //�ݻ����
  
  public GuiUser(String name) {
    super(name);
      
    maxHeight = 40;
    maxWidth = 40;
      
    //initGui();
  }    
  //��ͼ��ʼ������
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public  void initGui() {
    logic = new Logic(maxHeight, maxWidth);

    panel = new JPanel(new BorderLayout());
    center = new JPanel(new GridLayout(maxHeight, maxWidth));
    top = new JPanel();
    cheight = new JComboBox();
    for (int i = 3; i <= 40; i++) {
      cheight.addItem(String.valueOf(i));
    }
    cwidth = new JComboBox();
    for (int i = 3; i <= 40; i++) {
      cwidth.addItem(String.valueOf(i));
    }
    cheight.setSelectedIndex(maxHeight - 3);
    cwidth.setSelectedIndex(maxWidth - 3);
    ready = new JButton("ȷ��");
    nextOne = new JButton("�ݻ�һ��");
    userCell = new JButton[maxHeight][maxWidth];
    nextFive = new JButton("�ݻ����");
    end = new JButton("�˳�");
    tempCell = new int[maxHeight][maxWidth];
    width = new JLabel("ѡ������");
    height = new JLabel("ѡ������");
    this.setContentPane(panel);

    panel.add(top, "North");
    panel.add(center, "Center");

    for (int i = 0; i < maxHeight; i++) {
      for (int j = 0; j < maxWidth; j++) {
        userCell[i][j] = new JButton("");
        userCell[i][j].setBackground(Color.WHITE);
        center.add(userCell[i][j]);
      }
    }

    top.add(height);
    top.add(cheight);
    top.add(width);
    top.add(cwidth);
    top.add(ready);
    top.add(nextOne);
    top.add(nextFive);
    top.add(end);

    // ��������
    this.setSize(800, 900);
    this.setResizable(false);
    this.setLocationRelativeTo(null); // ��Ļ����
    this.setVisible(true);

    // ע�������
    this.addWindowListener(new WindowAdapter() {     
      public void windowClosed(WindowEvent e) {
        System.exit(0);
        }
      }
    );
    ready.addActionListener(this);
    nextOne.addActionListener(this);
    nextFive.addActionListener(this);
    end.addActionListener(this);
    for (int i = 0; i < maxHeight; i++) {
      for (int j = 0; j < maxWidth; j++) {
        userCell[i][j].addActionListener(this);
      }
    }
  }
  
  public int getMaxHight() {
    return maxHeight;
  }

  public void setMaxHight(int maxRow) {
    this.maxHeight = maxRow;
  }

  public int getMaxWidth() {
    return maxWidth;
  }

  public void setMaxWidth(int maxCol) {
    this.maxWidth = maxCol;
  }
   
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == ready) {
      setMaxHight(cheight.getSelectedIndex() + 3);
      setMaxWidth(cwidth.getSelectedIndex() + 3);
      initGui();
      logic = new Logic(getMaxHight(), getMaxWidth());
      logic.changeTemp(tempCell);
    } else if (e.getSource() == nextOne) {
      logic.changeTemp(tempCell);
      logic.diff(tempCell); 
      for (int i = 0; i < maxHeight; i++) {
        for (int j = 0; j < maxWidth; j++) {
          if (logic.getTemp(i, j) == 1) {
            userCell[i][j].setBackground(Color.BLACK);
          } else {
            userCell[i][j].setBackground(Color.WHITE);
          }
        }
      }
    } else if (e.getSource() == nextFive) {
      int c = 0;
      while (c != time) {
        logic.changeTemp(tempCell);
        logic.diff(tempCell); 
        for (int i = 0; i < maxHeight; i++) {
          for (int j = 0; j < maxWidth; j++) {
            if (logic.getTemp(i, j) == 1) {
              userCell[i][j].setBackground(Color.BLACK);
            } else {
              userCell[i][j].setBackground(Color.WHITE);
            }
          }
        }
        c++;
      }
    } else if (e.getSource() == end) {
      System.exit(0);
    } else {
      for (int i = 0; i < maxHeight; i++) {
        for (int j = 0; j < maxWidth; j++) {
          if (e.getSource() == userCell[i][j]) {
            if (tempCell[i][j] == 1) {
              tempCell[i][j] = 0;
              userCell[i][j].setBackground(Color.WHITE);
            } else {
              tempCell[i][j] = 1;
              userCell[i][j].setBackground(Color.BLACK);
            }
            break;
          }
        }
      }
      logic.changeTemp(tempCell);
    }
  }
  
  public static void main(String[] args) {
    GuiUser userp = new GuiUser("LifeGame");
    userp.initGui();
  }
}