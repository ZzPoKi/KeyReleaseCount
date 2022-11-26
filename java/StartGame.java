package Test1;

import com.github.kwhat.jnativehook.GlobalScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class StartGame extends JFrame implements ActionListener, KeyListener {
    JButton startGameButton = new JButton();
    JButton temp = new JButton();
    JButton endFrameButton = new JButton();
    JButton endGameButton = new JButton();
    JLabel time = new JLabel();

    public StartGame() {
        this.initJFrame();
        this.initJButton();
        while (true) {
            this.showCurrentText();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println();
            }
        }
    }
    private void showCurrentText() {
        time.setBounds(10, 200, 250, 40);
        time.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 18));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String rs = sdf.format(d);
        time.setText(rs);
        this.getContentPane().add(time);
    }

    private void initJButton() {
        startGameButton.setBounds(40, 25, 150, 30);
        startGameButton.setText("点击开始游戏");
        startGameButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
        startGameButton.addActionListener(this);
        endFrameButton.setBounds(40, 85, 150, 30);
        endFrameButton.setText("点击关闭界面");
        endFrameButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
        endFrameButton.addActionListener(this);
        endGameButton.setBounds(40, 145, 150, 30);
        endGameButton.setText("点击结束游戏");
        endGameButton.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
        endGameButton.addActionListener(this);
        temp.setText("");
        temp.setBounds(0, 0, 0, 0);
        this.getContentPane().add(temp);
        this.getContentPane().add(startGameButton);
        this.getContentPane().add(endFrameButton);
        this.getContentPane().add(endGameButton);
    }

    private void initJFrame() {
        this.setSize(250, 300);
        this.setTitle("游戏进程");
        //this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭界面时结束程序运行
        this.addKeyListener(this);
        this.setVisible(true);
        this.setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.requestFocus(true);
        if (e.getSource() == startGameButton) {
            File f = new File("D:\\osu!\\osu!.exe");
            Runtime r = Runtime.getRuntime();
            try {
                r.exec(f.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            GlobalScreen.addNativeKeyListener(new KeyPressedCount());
        }
        if (e.getSource() == endFrameButton) {
            while (true) {
                long startTime = System.currentTimeMillis();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Random r = new Random();
                int y = r.nextInt(780);
                int x = r.nextInt(1670);
                this.setBounds(x, y, 250, 300);
                long endTime = System.currentTimeMillis();
                System.out.println("执行所花费的时间为：" + (double) (endTime - startTime) / 1000 + "s");
            }
        }
        if (e.getSource() == endGameButton) {
            File f = new File("D:\\osu!\\osu!.exe");
            Runtime r = Runtime.getRuntime();
            try {
                r.exec(f.getAbsolutePath());
                String command = "cmd.exe /c c:\\windows\\system32\\taskkill /f /im  osu!.exe";//通过cmd指令实现exe文件的关闭
                Process proc = Runtime.getRuntime().exec(command);
                Runtime.getRuntime().exec("taskkill osu");//关闭程序
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 27) {
            System.exit(0);
        }
        //回车
        if (e.getKeyCode() == 10) {
            this.requestFocus(true);
            File f = new File("D:\\osu!\\osu!.exe");
            Runtime r = Runtime.getRuntime();
            try {
                r.exec(f.getAbsolutePath());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            GlobalScreen.addNativeKeyListener(new KeyPressedCount());
        }
    }
}
