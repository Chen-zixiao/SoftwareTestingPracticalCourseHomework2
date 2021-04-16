package com.houarizegai.TicTacToc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class PlayWindow extends JFrame implements ActionListener{
	private JLabel 	TableOfScore; // 分数板部件
	public int xScore = 0; // X棋的分数
    public int oScore = 0; // O棋的分数
    public JButton buttonsXO[]; // 输入X或O的按钮
    public JButton btnReset; // 清空棋盘并重置分数按钮
    public JButton btnClear; // 只清空棋盘的按钮
    public JButton btnBackToMain; // 返回主界面的按钮
    private final static int BUTTON_XO_WIDTH = 80;
    private final static int BUTTON_XO_HEIGHT = 80;
	private final static int POSITION_XO_H[] =
            {30, BUTTON_XO_WIDTH * 1 + 30, BUTTON_XO_WIDTH * 2 + 30, BUTTON_XO_WIDTH * 3 + 30}; // 按钮的水平方向的尺寸
	private final static int POSITION_XO_V[] =
            {120, BUTTON_XO_WIDTH * 1 + 120, BUTTON_XO_WIDTH * 2 + 120, BUTTON_XO_WIDTH * 3 + 120 + 20}; //按钮的竖直方向的尺寸
	private static int i = 0; // 循环部分使用变量

	private final int CHOIX_LEVEL;
    /* 代表双人对战模式*/
    private final int CHOIX_FRIEND = 0;

    
    /* 双人模式使用的变量 */
    public static boolean player1 = true; // 判断当前玩家的变量,true为己方
    public boolean preplayer=player1;//平局则用此记录上一轮先开始玩家
    public boolean nextplay=true;//为了记录是否进入下一轮，false为进入下一轮
    public int whowin=1;//记录谁获胜，1为己方，2为对方，3为平局
    public int[] haveChess=new int[9];//记录棋盘上是否有棋，0无1有
    public String mes="出错";

    public PlayWindow(int CHOIX_LEVEL) {  // CHOIX_LEVEL = 0:双人对战
    	
    	this.CHOIX_LEVEL = CHOIX_LEVEL;
    	
    	if (this.CHOIX_LEVEL == CHOIX_FRIEND)
    		this.setTitle("双人对战");
    	
    	TableOfScore = new JLabel("");//初始化分数板
        printScore(xScore, oScore); // 打印分数
        TableOfScore.setBounds(170, 10, 200, 100); // 设置分数板大小
        TableOfScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // 设置Font
        this.add(TableOfScore);

        buttonsXO = new JButton[9];

        for (i = 0; i < buttonsXO.length; i++) { //设置棋盘
            //棋盘的排列方式与按钮对应为123
            //                         456
            //                         789 的顺序
            buttonsXO[i] = new JButton();
            if (i < 3) {
                buttonsXO[i].setBounds(POSITION_XO_H[i], POSITION_XO_V[0], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            } else if (i < 6) {
                buttonsXO[i].setBounds(POSITION_XO_H[i - 3], POSITION_XO_V[1], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            } else if (i < 9) {
                buttonsXO[i].setBounds(POSITION_XO_H[i - 6], POSITION_XO_V[2], BUTTON_XO_WIDTH, BUTTON_XO_HEIGHT);
            }

            buttonsXO[i].setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
            buttonsXO[i].setBackground(Color.WHITE); // 改变按钮颜色
            this.add(buttonsXO[i]);
        }
        for (i = 0; i < 9; i++) {
            buttonsXO[i].addActionListener(this);
        }

        btnReset = new JButton("Reset");//设置重置按钮
        btnReset.setBounds(POSITION_XO_H[0], POSITION_XO_V[3], BUTTON_XO_WIDTH * 3 / 2 - 10, 40);
        btnReset.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnReset.addActionListener(event -> {
        	clear();
        	resetScore();
        });
        this.add(btnReset);

        btnClear = new JButton("Clear");//设置清空按钮
        btnClear.setBounds(POSITION_XO_H[0] + btnReset.getWidth() + 20, POSITION_XO_V[3], BUTTON_XO_WIDTH * 3 / 2 - 10, 40);
        btnClear.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnClear.addActionListener(event -> {
        	clear();
        });
        this.add(btnClear);

        btnBackToMain = new JButton("Back To Main");//设置返回主界面的按钮
        btnBackToMain.setBounds(POSITION_XO_H[0], POSITION_XO_V[3] + 50, BUTTON_XO_WIDTH * 3, 40);
        btnBackToMain.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        btnBackToMain.addActionListener(event -> {
            this.setVisible(false);
            new MainWindow();
        });
        add(btnBackToMain);

        this.setBounds(400, 170, buttonsXO[0].getWidth() * 3 + 75, TableOfScore.getHeight() + buttonsXO[0].getHeight() * 3 + btnReset.getHeight() + btnBackToMain.getHeight() + 120);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    	//各模式的下棋按钮点击触发的逻辑
    	if (CHOIX_LEVEL == CHOIX_FRIEND) {
    		for(i = 0; i < 9; i++){     //九个按键都循环一遍来看哪个被选中了
        		if (e.getSource().equals(buttonsXO[i]))
        			nextplay=printXOForFriend(i);
    	    }
    	}
    }
    
    /* 所有模式都使用的函数 */
    
    private void printScore(int x, int o) { // 打印分数
        String xFormat = setColorOnly(String.valueOf(x), "green"),
                oFormat = setColorOnly(String.valueOf(o), "green");
        if (x > o) {
            xFormat = setColorOnly(String.valueOf(x), "green");
            oFormat = setColorOnly(String.valueOf(o), "red");
        } else if (x < o) {
            xFormat = setColorOnly(String.valueOf(x), "red");
            oFormat = setColorOnly(String.valueOf(o), "green");
        }
        
        String p1 = "You";
        String p2 = "Pc";
        
        if (CHOIX_LEVEL == CHOIX_FRIEND) {
        	p1 = "X";
        	p2 = "O";
        }
        	
        TableOfScore.setText("<html><table border='1'><tr><th colspan='2'>Score :&nbsp;&nbsp;&nbsp;</th></tr>"
                                            + "<tr><td><b>"+ p1 + "</b></td><td>" + xFormat + "</td></tr>"
                                            + "<tr><td><b>"+ p2 + "</b></td><td>" + oFormat + "</td></tr></html>");
    }
    
	public boolean getResult(boolean Player1Win) { // 平局或胜利时弹出窗口
        if (((buttonsXO[0].getText().equals(buttonsXO[3].getText())) && (buttonsXO[0].getText().equals(buttonsXO[6].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[1].getText().equals(buttonsXO[4].getText())) && (buttonsXO[1].getText().equals(buttonsXO[7].getText())) && (!buttonsXO[1].getText().equals("")))
                || ((buttonsXO[2].getText().equals(buttonsXO[5].getText())) && (buttonsXO[2].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[2].getText().equals("")))
                || ((buttonsXO[0].getText().equals(buttonsXO[1].getText())) && (buttonsXO[0].getText().equals(buttonsXO[2].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[3].getText().equals(buttonsXO[4].getText())) && (buttonsXO[3].getText().equals(buttonsXO[5].getText())) && (!buttonsXO[3].getText().equals("")))
                || ((buttonsXO[6].getText().equals(buttonsXO[7].getText())) && (buttonsXO[6].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[6].getText().equals("")))
                || ((buttonsXO[0].getText().equals(buttonsXO[4].getText())) && (buttonsXO[0].getText().equals(buttonsXO[8].getText())) && (!buttonsXO[0].getText().equals("")))
                || ((buttonsXO[2].getText().equals(buttonsXO[4].getText())) && (buttonsXO[2].getText().equals(buttonsXO[6].getText())) && (!buttonsXO[2].getText().equals("")))) {
        	
            if (Player1Win) {
                xScore++;
                if (CHOIX_LEVEL == CHOIX_FRIEND) {
                    JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("玩家 1 (X) ", "green") + setColorOnly("获胜 !", "green") + "</html>");
                    mes="玩家1获胜";
                    whowin=1;
                }
                else
                	JOptionPane.showMessageDialog(null, setColor("你获胜了  ^_^ !" , "green"));
            } else {
                oScore++;
                if (CHOIX_LEVEL == CHOIX_FRIEND) {
                    JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("玩家 2 (O) ", "blue") + setColorOnly("获胜 !", "green") + "</html>");
                    mes="玩家2获胜";
                    whowin=2;
                }
                else
                	JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("你失败了 : (", "red") + "<br>人机 " + setColor("获胜 !", "green") + "</html>");
            }
            printScore(xScore, oScore);
            clear();
            return false;
        } else {
            if (!buttonsXO[0].getText().equals("")
	                && !buttonsXO[1].getText().equals("")
	                && !buttonsXO[2].getText().equals("")
	                && !buttonsXO[3].getText().equals("")
	                && !buttonsXO[4].getText().equals("")
	                && !buttonsXO[5].getText().equals("")
	                && !buttonsXO[6].getText().equals("")
	                && !buttonsXO[7].getText().equals("")
	                && !buttonsXO[8].getText().equals("")) {
                JOptionPane.showMessageDialog(null, " Draw !");
                mes="平局";
                clear();
                whowin=3;
                preplayer=player1;
                return false;
            }
        }
        return true;
    }
	
    private void clear() { // 清空棋盘并初始化
        for (i = 0; i < 9; i++) {
            buttonsXO[i].setText(""); // 移除棋子
            haveChess[i]=0;
        }
    }

    private void resetScore() { // 调用clear（）并重置分数
        clear();
        printScore(xScore = 0, oScore = 0);
    }
    
    protected static String setColor(String before, String color) { // 改变字符串颜色
        return "<html><font color='" + color + "'>" + before + "</font></html>";
    }
    
    private static String setColorOnly(String before, String color) { // 改变字符串颜色但移除html的tag
        return "<font color='" + color + "'>" + before + "</font>";
    }

    /* 只在双人对战模式使用的函数 */
    private boolean printXOForFriend(int index) { // 打印棋子并检查是否满足结束条件 )
        boolean res;
    	if (buttonsXO[index].getText().equals("")) {
            if (player1) {
            	buttonsXO[index].setText(setColor("X", "green"));
            	res=getResult(player1);
                player1 = false;
                return res;
            } else {
            	buttonsXO[index].setText(setColor("O", "blue"));
            	res=getResult(player1);
                player1 = true;
                return res;
            }
    	}
    	haveChess[index]=1;
    	return true;
    }

}