package com.houarizegai.TicTacToc;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zixiao chen
 */
public class MainWindow extends JFrame implements ActionListener{
    
    private final JLabel labelSelect;
    private final JButton btnWithFriend; // 双人对战按钮
    private boolean showHidePcPlay = false; // 是否展示难度界面的判断变量
    private final Font FontForLabel = new Font("Comic Sans MS", Font.PLAIN, 16); // 根据Font的数值创建不同的实例
    private static int i = 0; // 循环使用变量
    public boolean isOn=false;//判断是否进入游戏界面，true为进入了
    public boolean isOpen=false; //记录游戏界面已打开
    //主界面
    public MainWindow() {

    /**
     *
     */
    MainWindow() {

        labelSelect = new JLabel(PlayWindow.setColor("请选择游戏模式 :", "blue")); // 创建新的JLabel实例并设置颜色
        labelSelect.setBounds(20, 40, 315, 50); // 设置大小和位置
        labelSelect.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15)); // 改变font值
        this.add(labelSelect); // 将JLabel添加到主界面
        //设置双人对战模式跳转
        btnWithFriend = new JButton("play with friends");//双人对战模式的设置
        btnWithFriend.setBounds(20, 100, 250, 50);
        btnWithFriend.setFont(FontForLabel);
        btnWithFriend.addActionListener(event -> {
        	new PlayWindow(0); // 创建双人对战模式的窗口
            this.setVisible(false); // 隐藏主界面
            isOpen=true;
        });
        this.add(btnWithFriend);
        btnWithFriend.addActionListener(this);
        setTitle("井字棋"); // 主界面标题
        setBounds(400, 170, 310, 270);
        setLayout(null); //不需要layout因为通过setBounds（）函数控制组件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置退出按钮
        setVisible(true); // 在屏幕上展示窗口
        setIconImage(new ImageIcon(getClass().getResource("/com/houarizegai/TicTacToc/images/logo.png")).getImage());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        isOn=true;
    }

	public static void main(String[] args) {
        new MainWindow(); // 开始游戏
    }

   
}