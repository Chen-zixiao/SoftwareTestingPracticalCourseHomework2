package com.houarizegai.TicTacToc;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame implements ActionListener{
    
    private final JLabel labelSelect;
    private JLabel labelChoix;//选择难度的JLabel
    private final JButton btnWithFriend; // 双人对战按钮
    private final JButton btnWithPc; // 人机对战按钮
    private final JButton btnAbout; // 进入关于作者界面的按钮
    private JRadioButton choixWithPc[] = new JRadioButton[3]; // 选择人机模式难度的按钮
    private boolean showHidePcPlay = false; // 是否展示难度界面的判断变量
    //private final Font FontForLabel = new Font("Comic Sans MS", Font.PLAIN, 16); // 根据Font的数值创建不同的实例
    private final Font FontForLabel = new Font(Font.DIALOG,  Font.BOLD, 20);
    private static int i = 0; // 循环使用变量
    //主界面
    MainWindow() {

        labelSelect = new JLabel(PlayWindow.setColor("请选择游戏模式 :", "blue")); // 创建新的JLabel实例并设置颜色
        labelSelect.setBounds(20, 40, 315, 50); // 设置大小和位置
        labelSelect.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15)); // 改变font值
        this.add(labelSelect); // 将JLabel添加到主界面
        //设置双人对战模式跳转
        btnWithFriend = new JButton("玩家对战");//双人对战模式的设置
        btnWithFriend.setBounds(20, 100, 250, 50);
        btnWithFriend.setFont(FontForLabel);
        btnWithFriend.addActionListener(event -> {
        	new PlayWindow(0); // 创建双人对战模式的窗口
            this.setVisible(false); // 隐藏主界面
        });
        this.add(btnWithFriend);
        
        btnWithPc = new JButton("电脑对战");//人机对战模式的设置
        btnWithPc.setBounds(20, 160, 250, 50);
        btnWithPc.setFont(FontForLabel);
        btnWithPc.addActionListener(event -> {
        	showHidePcPlay = (showHidePcPlay)? false : true;//根据变量判断是要弹出或者隐藏难度选择窗口，并重新改变主界面大小
        	labelChoix.setVisible(showHidePcPlay);
        	for(i = 0; i < 3; i++)
        		choixWithPc[i].setVisible(showHidePcPlay);
        	
            this.setSize(310, (showHidePcPlay)?450:270);
        });
        this.add(btnWithPc);
        
        labelChoix = new JLabel(PlayWindow.setColor("请选择您的道路 :", "green"));//难度选择的界面的设置
        labelChoix.setBounds(30, 220, 250, 50);
        labelChoix.setVisible(false);
        this.add(labelChoix);
        
        choixWithPc[0] = new JRadioButton("康庄大道");
        choixWithPc[1] = new JRadioButton("丛林小路");
        choixWithPc[2] = new JRadioButton("荆棘之路");
        
        for(int i = 0; i < 3; i++) {
        	choixWithPc[i].setBounds(40, 260 + 30 * i, 150, 30);
        	choixWithPc[i].setVisible(false);
        	choixWithPc[i].addActionListener(this);
	        this.add(choixWithPc[i]);
        }
        
        btnAbout = new JButton("关于我们");//关于作者的界面的设置
        btnAbout.setBounds(180, 10, 100, 25);
        btnAbout.addActionListener(event -> {
            new AboutWindow();
        });
        this.add(btnAbout);
        
        setTitle("井字棋"); // 主界面标题
        setBounds(400, 170, 310, 270);
        setLayout(null); //不需要layout因为通过setBounds（）函数控制组件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置退出按钮
        setVisible(true); // 在屏幕上展示窗口
        setIconImage(new ImageIcon(getClass().getResource("/com/houarizegai/TicTacToc/images/logo.png")).getImage());
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 3; i++)
			if(e.getSource() == choixWithPc[i]) {
			    	new PlayWindow(i+1);
			        this.setVisible(false);
			}
	}
	
	public static void main(String[] args) {
        new MainWindow(); // 开始游戏
    }
}