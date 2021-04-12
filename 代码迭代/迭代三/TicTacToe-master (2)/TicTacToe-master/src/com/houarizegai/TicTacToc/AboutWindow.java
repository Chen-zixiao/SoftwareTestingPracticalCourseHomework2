package com.houarizegai.TicTacToc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;

public class AboutWindow extends JDialog implements MouseListener {
    
    private static JLabel labelCopyRight; // 展示copyRight
    private static JLabel labelImg[]; // 展示社交媒体和博客等图片
    private static JLabel labelLogo; // 展示logo
    private final static String IMAGE_NAME[] = {"facebook", "twitter", "youtube", "github"};
    private final static int NUMBER_OF_IMAGES = IMAGE_NAME.length;

    public AboutWindow() {
        // 加载图片
        labelLogo = new JLabel(new ImageIcon(getClass().getResource("/com/houarizegai/TicTacToc/images/logo.png")));
    	labelLogo.setBounds(90, 60, 150, 150); //设置位置
    	this.add(labelLogo); // 添加到该窗口
    	
    	/* 改变社交媒体的label */
    	
    	labelImg = new JLabel[NUMBER_OF_IMAGES]; // 初始化
        for (int i = 0; i < NUMBER_OF_IMAGES; i++) {
        	labelImg[i] = new JLabel(new ImageIcon(getClass().getResource("/com/houarizegai/TicTacToc/images/" + IMAGE_NAME[i]  + ".png")));
        	labelImg[i].setBounds(90 + 40 * i, 290, 32, 32);
                labelImg[i].addMouseListener(this);
        	this.add(labelImg[i]);
            labelImg[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        
        /* 改变copyRight的label */

        labelCopyRight = new JLabel("Copyright Ⓒ EasyCodeTeam, All Right Reserved 2021."); // 实例化label
        labelCopyRight.setBounds(15, 330, 300, 30);
        this.add(labelCopyRight);
        
        /* 设置该窗口其他参数 */
        
        this.setBounds(400, 200, 330, 400);
        this.setTitle("关于作者");
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for(int i = 0; i < labelImg.length; i++) {
            if (e.getSource().equals(labelImg[i])) {
                try {//跳转到社交媒体
                    Desktop.getDesktop().browse(new URI("https://www." + IMAGE_NAME[i] + ".com/HouariZegai"));
                } catch (Exception ex) {

                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }


    public static void main(String[] args) {
        new AboutWindow().setVisible(true);
    }
}