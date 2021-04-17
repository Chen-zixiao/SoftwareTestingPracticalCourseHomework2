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

    /** 选择难度的JLabel */
    private JLabel labelChoix;

    /** 双人对战按钮 */
    private final JButton btnWithFriend;

    /** 人机对战按钮 */
    private final JButton btnWithPc;

    /** 选择人机模式难度的按钮 */
    private JRadioButton choixWithPc[] = new JRadioButton[3];

    /** 是否展示难度界面的判断变量 */
    private boolean showHidePcPlay = false;

    /** 根据Font的数值创建不同的实例 */
    private final Font FontForLabel = new Font("Comic Sans MS", Font.PLAIN, 16);

    /** 循环使用变量 */
    private static int i = 0; // 循环使用变量

    /** 主界面初始化 */
    public MainWindow() {

        // 创建新的JLabel实例并设置颜色
        labelSelect = new JLabel(PlayWindow.setColor("请选择游戏模式 :", "blue"));
        // 设置大小和位置
        labelSelect.setBounds(20, 40, 315, 50);
        // 改变font值
        labelSelect.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        // 将JLabel添加到主界面
        this.add(labelSelect);


        /* 设置双人对战模式跳转 */
        //双人对战模式的设置
        btnWithFriend = new JButton("play with friends");
        btnWithFriend.setBounds(20, 100, 250, 50);
        btnWithFriend.setFont(FontForLabel);
        btnWithFriend.addActionListener(event -> {
            // 创建双人对战模式的窗口
        	new PlayWindow(0);
            // 隐藏主界面
            this.setVisible(false);
        });
        this.add(btnWithFriend);

        /* 人机对战模式的设置 */
        btnWithPc = new JButton("play with pc");
        btnWithPc.setBounds(20, 160, 250, 50);
        btnWithPc.setFont(FontForLabel);
        btnWithPc.addActionListener(event -> {
            // 根据变量判断是要弹出或者隐藏难度选择窗口，并重新改变主界面大小
        	showHidePcPlay = (showHidePcPlay)? false : true;
        	labelChoix.setVisible(showHidePcPlay);
        	for(i = 0; i < 3; i++) {
                choixWithPc[i].setVisible(showHidePcPlay);
            }
        	
            this.setSize(310, (showHidePcPlay)?450:270);
        });
        this.add(btnWithPc);

        // 难度选择的界面的设置
        labelChoix = new JLabel(PlayWindow.setColor("请选择难度 :", "green"));
        labelChoix.setBounds(30, 220, 250, 50);
        labelChoix.setVisible(false);
        this.add(labelChoix);
        
        choixWithPc[0] = new JRadioButton("简单");
        choixWithPc[1] = new JRadioButton("中等");
        choixWithPc[2] = new JRadioButton("困难");
        
        for(int i = 0; i < 3; i++) {
        	choixWithPc[i].setBounds(40, 260 + 30 * i, 150, 30);
        	choixWithPc[i].setVisible(false);
        	choixWithPc[i].addActionListener(this);
	        this.add(choixWithPc[i]);
        }

        // 主界面标题
        setTitle("井字棋");
        setBounds(400, 170, 310, 270);
        // 不需要layout因为通过setBounds（）函数控制组件
        setLayout(null);
        // 设置退出按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 在屏幕上展示窗口
        setVisible(true);
        setIconImage(new ImageIcon(getClass().getResource("/com/houarizegai/TicTacToc/images/logo.png")).getImage());
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 3; i++) {
            if (e.getSource() == choixWithPc[i]) {
                new PlayWindow(i + 1);
                this.setVisible(false);
            }
        }
	}
	
	public static void main(String[] args) {
        // 开始游戏
        new MainWindow();
    }
}