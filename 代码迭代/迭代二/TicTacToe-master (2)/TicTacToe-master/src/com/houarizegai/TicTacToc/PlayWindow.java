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

public class PlayWindow extends JFrame implements ActionListener {

    /** 分数板部件 */
    private JLabel 	TableOfScore;

    /** X棋的分数 */
    public int xScore = 0;

    /** O棋的分数 */
    public int oScore = 0;

    /** 输入X或O的按钮 */
    public JButton buttonsXO[];

    /** 清空棋盘并重置分数按钮 */
    public JButton btnReset;

    /** 只清空棋盘的按钮 */
    public JButton btnClear;

    /** */

    /**  返回主界面的按钮 */
    public JButton btnBackToMain;
    private final static int BUTTON_XO_WIDTH = 80;
    private final static int BUTTON_XO_HEIGHT = 80;
    private final static int POSITION_XO_H[] =
            {30, BUTTON_XO_WIDTH * 1 + 30, BUTTON_XO_WIDTH * 2 + 30, BUTTON_XO_WIDTH * 3 + 30}; // 按钮的水平方向的尺寸
    private final static int POSITION_XO_V[] =
            {120, BUTTON_XO_WIDTH * 1 + 120, BUTTON_XO_WIDTH * 2 + 120, BUTTON_XO_WIDTH * 3 + 120 + 20}; //按钮的竖直方向的尺寸
    /** 循环部分使用变量 */
    private static int i = 0;

    /** 游戏模式 */
    private final int CHOIX_LEVEL;
    /** 双人对战模式 */
    private final int CHOIX_FRIEND = 0;
    /** 简单人机模式 */
    private final int CHOIX_EASY = 1;
    /** 中等人机模式 */
    private final int CHOIX_MEDIUM = 2;
    /** 困难人机模式 */
    private final int CHOIX_HARD = 3;

    /* 双人模式使用的变量 */
    public static boolean player1 = true;
    public String mes="出错";
    /** 平局则用此记录上一轮先开始玩家 */
    public boolean preplayer=player1;
    /** 为了记录是否进入下一轮，false为进入下一轮 */
    public boolean nextplay=true;
    /** 记录谁获胜，1为己方，2为对方，3为平局 */
    public int whowin=1;
    /** 记录棋盘上是否有棋，0无1有 */
    public int[] haveChess=new int[9];

    /* 人机简单模式使用的变量 */
    private static Random rand = new Random();
    private boolean printRand = true;


    /* 人机中等或者困难模式使用的变量*/
    private int arrayRows[] = new int[8]; // 数组里1代表X，-1代表O
    private boolean mCenterFirst = false;
    private boolean mCornerFirst = false;
    private boolean mHvFirst = false;
    private boolean mHvAfterCorner = false;
    private boolean mCornerAfterHv = false;
    private boolean mCornerAfterCenter = false;
    private boolean mHvAfterHv = false;
    private int mCounter = 0;
    private boolean mCorner = false;
    private boolean mHvAfterCenter = false;

    public PlayWindow(int CHOIX_LEVEL) {  // CHOIX_LEVEL = 0:双人对战, 1:人机简单, 2:人机中等, 3:人机困难

        this.CHOIX_LEVEL = CHOIX_LEVEL;

        if (this.CHOIX_LEVEL == CHOIX_FRIEND) {
            this.setTitle("双人对战");
        } else if (this.CHOIX_LEVEL == CHOIX_EASY) {
            this.setTitle("人机简单模式");
        } else if (this.CHOIX_LEVEL == CHOIX_MEDIUM) {
            this.setTitle("人机中等模式");
        } else {
            this.setTitle("人机困难模式");
        }

        TableOfScore = new JLabel("");//初始化分数板
        printScore(xScore, oScore); // 打印分数
        TableOfScore.setBounds(170, 10, 200, 100); // 设置分数板大小
        TableOfScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 16)); // 设置Font
        this.add(TableOfScore);

        buttonsXO = new JButton[9];

        for (i = 0; i < buttonsXO.length; i++) { //设置棋盘
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
    public void actionPerformed(ActionEvent e) {
        // 各模式的下棋按钮点击触发的逻辑
        if (CHOIX_LEVEL == CHOIX_FRIEND) {
            for (i = 0; i < 9; i++) {
                if (e.getSource().equals(buttonsXO[i])) {
                    nextplay = printXOForFriend(i);
                }
            }
        } else if (CHOIX_LEVEL == CHOIX_EASY) {
            for (i = 0; i < 9; i++) {
                if (e.getSource() == buttonsXO[i]) {
                    printXOForEasy(i);
                }
            }
        } else if (CHOIX_LEVEL == CHOIX_MEDIUM) {
            for (i = 0; i < 9; i++) {
                if (e.getSource() == buttonsXO[i]) {
                    if (buttonsXO[i].getText().equals("")) {
                        printXOForMeMedium(i);

                        if (getResult(true)) {
                            printXOForPcMedium();
                            getResult(false);
                        }
                    }
                }
            }
        } else if (CHOIX_LEVEL == CHOIX_HARD) {
            for (i = 0; i < 9; i++) {
                if (e.getSource() == buttonsXO[i]) {
                    if (buttonsXO[i].getText().equals("")) {
                        printXOForMeHard(i);

                        if (getResult(true)) {
                            printXOForPcHard();
                            getResult(false);
                        }
                    }
                }
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
                + "<tr><td><b>" + p1 + "</b></td><td>" + xFormat + "</td></tr>"
                + "<tr><td><b>" + p2 + "</b></td><td>" + oFormat + "</td></tr></html>");
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
                } else {
                    JOptionPane.showMessageDialog(null, setColor("你获胜了  ^_^ !", "green"));
                }
            } else {
                oScore++;
                if (CHOIX_LEVEL == CHOIX_FRIEND) {
                    JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("玩家 2 (O) ", "blue") + setColorOnly("获胜 !", "green") + "</html>");
                    mes="玩家2获胜";
                    whowin=2;
                } else {
                    JOptionPane.showMessageDialog(null, "<html>" + setColorOnly("你失败了 : (", "red") + "<br>人机 " + setColor("获胜 !", "green") + "</html>");
                }
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
            } else {
                printRand = true;
            }
        }
        return true;
    }

    public void clear() { // 清空棋盘并初始化
        for (i = 0; i < 9; i++) {
            buttonsXO[i].setText(""); // 移除棋子
            haveChess[i]=0;
        }
        if (CHOIX_LEVEL == CHOIX_EASY) { //人机简单模式
            printRand = false;
        } else { //人机中等或者困难模式
            for (i = 0; i < 8; i++) {
                arrayRows[i] = 0;
            }
            mCenterFirst = false;
            mCornerFirst = false;
            mHvFirst = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mCorner = false;
            mHvAfterCenter = false;
            mCounter = 0;
        }
    }

    public void resetScore() { // 调用clear（）并重置分数
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
        return true;
    }

    /* 只在人机简单模式使用的函数 */
    private void printXOForEasy(int i) { // Fill The Gaps By XO & Call The Function getResult()
        if (buttonsXO[i].getText().equals("")) { // 检查是否为空以落子
            buttonsXO[i].setText(setColor("X", "green"));
            getResult(true); //检查玩家是否赢了

            if (printRand) {
                while (true) { // Execute This Block Until Pc Fill 1 Gaps By The Random Value 
                    int index = rand.nextInt(9); // 获得0-8的随机数
                    if (buttonsXO[index].getText().equals("")) {
                        try {
                            Thread.sleep(100); // 暂停0.1秒
                        } catch (InterruptedException e) {
                        }

                        buttonsXO[index].setText(setColor("O", "blue"));
                        getResult(false); //检查人机是否赢了
                        break;
                    }
                }
            }
        }
    }

    /* 只在人机中等或者困难模式使用的函数 */
    private void printXOForMeMedium(int index) {//中等模式玩家落子后对人机产生的影响
        buttonsXO[index].setText(setColor("X", "green"));
        if (index == 4) {
            if (mCounter == 0) { // 检查第一个棋子是否是最中间
                mCenterFirst = true;
            }
        } else if (index == 0 || index == 2 || index == 6 || index == 8) {
            mCorner = true;
        } else {
            mCorner = false;
        }
        fillArray(index, 1);
        mCounter++;
    }

    private void printXOForMeHard(int index) {//困难模式玩家落子后对人机产生的影响
        buttonsXO[index].setText(setColor("X", "green"));
        if (index == 4) {
            if (mCounter == 0) { //检查第一个棋子是否是最中间
                mCenterFirst = true;
            }
        } else if (index == 0 || index == 2 || index == 6 || index == 8) {
            if (mCounter == 0) {
                mCornerFirst = true;
            } else {
                if (mCenterFirst) {
                    mCornerAfterCenter = true;
                    mCenterFirst = false;
                }
                mCorner = true;
                mCornerFirst = false;
                if (mHvFirst) {
                    mCornerAfterHv = true;
                }
                mHvFirst = false;
            }
        } else {
            if (mCounter == 0) {
                mHvFirst = true;
            } else {
                if (mCornerFirst) {
                    mHvAfterCorner = true;
                    mCornerFirst = false;
                } else if (mHvFirst) {
                    mHvAfterHv = true;
                    mHvFirst = false;
                } else if (mCenterFirst) {
                    mHvAfterCenter = true;
                    mCornerAfterCenter = false;
                    mCenterFirst = false;
                } else {
                    mCorner = false;
                }
            }
        }
        fillArray(index, 1);
        mCounter++;
    }

    private void printXOForPcMedium() {//中等人机下棋逻辑
        if (mCenterFirst) {
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
            mCenterFirst = false;
        } else if (buttonsXO[4].getText().equals("")) {
            fillCasePc(4);
        } else if (meWantWin() != -1) {
            int indexOfTheRow = meWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (adverserWantWin() != -1) {
            int indexOfTheRow = adverserWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (mCorner) {
            if (buttonsXO[1].getText().equals("")) {
                fillCasePc(1);
            } else if (buttonsXO[3].getText().equals("")) {
                fillCasePc(3);
            } else if (buttonsXO[5].getText().equals("")) {
                fillCasePc(5);
            } else if (buttonsXO[7].getText().equals("")) {
                fillCasePc(7);
            }
        } else {
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
        }
    }

    private void printXOForPcHard() {//困难人机下棋逻辑
        if (mCenterFirst) {
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
        } else if (mCornerFirst) {
            fillCasePc(4);
        } else if (mHvFirst) {
            fillCasePc(4);
        } else if (mHvAfterHv) {
            int indexOfTheBestCorner = getBestCorner();
            fillCasePc(indexOfTheBestCorner);
            mCorner = false;
            mHvAfterHv = false;
        } else if (mCornerAfterCenter) {
            if (buttonsXO[0].getText().equals("")) {
                fillCasePc(0);
            } else if (buttonsXO[2].getText().equals("")) {
                fillCasePc(2);
            } else if (buttonsXO[6].getText().equals("")) {
                fillCasePc(6);
            } else if (buttonsXO[8].getText().equals("")) {
                fillCasePc(8);
            }
            mCornerAfterCenter = false;
        } else if (meWantWin() != -1) {
            int indexOfTheRow = meWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (adverserWantWin() != -1) {
            int indexOfTheRow = adverserWantWin();
            int[] theTargetRow = getTargetRow(indexOfTheRow);
            int indexOfTheTargetCase = getIndexOfTheEmptyCase(theTargetRow);
            fillCasePc(indexOfTheTargetCase);
            mCorner = false;
            mCornerAfterCenter = false;
            mHvAfterHv = false;
            mHvAfterCorner = false;
            mCornerAfterHv = false;
        } else if (mCornerAfterHv) {
            int indexOfBestHV = getBestHV();
            fillCasePc(indexOfBestHV);
            mCorner = false;
            mCornerAfterHv = false;
        } else if (mHvAfterCorner) {
            int indexOfBestHV = getBestHV();
            fillCasePc(indexOfBestHV);
            mCorner = false;
            mHvAfterCorner = false;
        } else if (mCorner) {
            if (buttonsXO[1].getText().equals("")) {
                fillCasePc(1);
            } else if (buttonsXO[3].getText().equals("")) {
                fillCasePc(3);
            } else if (buttonsXO[5].getText().equals("")) {
                fillCasePc(5);
            } else if (buttonsXO[7].getText().equals("")) {
                fillCasePc(7);
            }
        } else {
            if (mCounter <= 4) {
                int indexOfTheBestCorner = getBestCorner();
                fillCasePc(indexOfTheBestCorner);
                mCorner = false;
            } else {
                if (buttonsXO[0].getText().equals("")) {
                    fillCasePc(0);
                } else if (buttonsXO[2].getText().equals("")) {
                    fillCasePc(2);
                } else if (buttonsXO[6].getText().equals("")) {
                    fillCasePc(6);
                } else if (buttonsXO[8].getText().equals("")) {
                    fillCasePc(8);
                }
            }
        }
    }

    private void fillArray(int cases, int current) {//填充数组
        switch (cases) {
            case 0:
                arrayRows[0] += current;
                arrayRows[3] += current;
                arrayRows[7] += current;
                break;
            case 1:
                arrayRows[0] += current;
                arrayRows[4] += current;
                break;
            case 2:
                arrayRows[0] += current;
                arrayRows[5] += current;
                arrayRows[6] += current;
                break;
            case 3:
                arrayRows[1] += current;
                arrayRows[3] += current;
                break;
            case 4:
                arrayRows[1] += current;
                arrayRows[4] += current;
                arrayRows[6] += current;
                arrayRows[7] += current;
                break;
            case 5:
                arrayRows[1] += current;
                arrayRows[5] += current;
                break;
            case 6:
                arrayRows[2] += current;
                arrayRows[3] += current;
                arrayRows[6] += current;
                break;
            case 7:
                arrayRows[2] += current;
                arrayRows[4] += current;
                break;
            case 8:
                arrayRows[2] += current;
                arrayRows[5] += current;
                arrayRows[7] += current;
                break;
        }
    }

    private void fillCasePc(int index) {//人机打印棋子
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        buttonsXO[index].setText(setColor("O", "blue"));
        fillArray(index, -1);
        mCounter++;
        mCorner = false;
    }

    private int adverserWantWin() {//判断人机可以赢的落点
        int index = -1;
        for (int i = 0; i < arrayRows.length; i++) {
            index = (arrayRows[i] == 2) ? i : index;
        }
        return index;
    }

    private int meWantWin() {//判断玩家可以赢的落点
        int index = -1;
        for (int i = 0; i < arrayRows.length; i++) {
            index = (arrayRows[i] == -2) ? i : index;
        }
        return index;
    }

    private static int[] getTargetRow(int index) {
        switch (index) {
            case 0:
                return new int[]{0, 1, 2};
            case 1:
                return new int[]{3, 4, 5};
            case 2:
                return new int[]{6, 7, 8};
            case 3:
                return new int[]{0, 3, 6};
            case 4:
                return new int[]{1, 4, 7};
            case 5:
                return new int[]{2, 5, 8};
            case 6:
                return new int[]{2, 4, 6};
            case 7:
                return new int[]{0, 4, 8};
            default:
                return new int[]{};
        }
    }

    private int getIndexOfTheEmptyCase(int[] array) {//得到所有空的落点
        int index = -1;
        for (int i = 0; i < 3; i++) {
            if (buttonsXO[array[i]].getText().equals("")) {
                index = array[i];
            }
        }

        return index;
    }

    private int getBestHV() {//得到最好的边缘中间落点（1，3，5，7）
        int ZeroFour = arrayRows[0] + arrayRows[4];
        int TwoFour = arrayRows[2] + arrayRows[4];
        int OneThree = arrayRows[1] + arrayRows[3];
        int OneFive = arrayRows[1] + arrayRows[5];

        if ((ZeroFour <= OneFive) && (ZeroFour <= TwoFour) && (ZeroFour <= OneThree)) {
            return 1;
        } else if ((TwoFour <= OneThree) && (TwoFour <= ZeroFour) && (TwoFour <= OneFive)) {
            return 7;
        } else if ((OneThree <= TwoFour) && (OneThree <= ZeroFour) && (OneThree <= OneFive)) {
            return 3;
        } else {
            return 5;
        }
    }

    private int getBestCorner() {//得到最好的角落落点（0，2，6，8）
        int ZeroThree = arrayRows[0] + arrayRows[3];
        int TwoThree = arrayRows[2] + arrayRows[3];
        int ZeroFive = arrayRows[0] + arrayRows[5];
        int TwoFive = arrayRows[2] + arrayRows[5];

        if ((ZeroThree >= TwoThree) && (ZeroThree >= ZeroFive) && (ZeroThree >= TwoFive)) {
            return 0;
        } else if ((TwoThree >= ZeroThree) && (TwoThree >= ZeroFive) && (TwoThree >= TwoFive)) {
            return 6;
        } else if ((ZeroFive >= ZeroThree) && (ZeroFive >= TwoThree) && (ZeroFive >= TwoFive)) {
            return 2;
        } else {
            return 8;
        }
    }

    /* getters and setters */

    public int getxScore() {
        return xScore;
    }

    public void setxScore(int xScore) {
        this.xScore = xScore;
    }

    public int getoScore() {
        return oScore;
    }

    public void setoScore(int oScore) {
        this.oScore = oScore;
    }
}