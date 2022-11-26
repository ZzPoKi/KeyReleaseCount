package Test1;


import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;

public class KeyPressedCount extends JFrame implements ActionListener, NativeKeyListener {

    JLabel key1 = new JLabel();
    JLabel key2 = new JLabel();
    JLabel key3 = new JLabel();
    JLabel key4 = new JLabel();
    JLabel sum = new JLabel();
    JLabel time = new JLabel();
    JButton reset = new JButton();
    int key1Count = 0;
    int key2Count = 0;
    int key3Count = 0;
    int key4Count = 0;
    int sumCount = 0;

    public KeyPressedCount() {
        this.initJFrame();
        this.showText(key1Count, key2Count, key3Count, key4Count);
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.out.println();
        }
        this.initJButton();
    }

    private void initJButton() {
        reset.setBounds(60, 15, 100, 40);
        reset.setText("重置");
        reset.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 20));
        this.getContentPane().add(reset);
        reset.addActionListener(this);
    }

    private void showText(int key1Count, int key2Count, int key3Count, int key4Count) {
        key1.setBounds(25, 70, 175, 40);
        key1.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 17));
        key1.setText("key1: " + key1Count);
        this.getContentPane().add(key1);
        key2.setBounds(135, 70, 175, 40);
        key2.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 17));
        key2.setText("key2: " + key2Count);
        this.getContentPane().add(key2);
        key3.setBounds(25, 130, 175, 40);
        key3.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 17));
        key3.setText("key3: " + key3Count);
        this.getContentPane().add(key3);
        key4.setBounds(135, 130, 175, 40);
        key4.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 17));
        key4.setText("key4: " + key4Count);
        this.getContentPane().add(key4);
        sum.setBounds(70, 180, 175, 40);
        sum.setFont(new Font("UD Digi Kyokasho NP-B", Font.BOLD, 20));
        sumCount = key1Count + key2Count + key3Count + key4Count;
        sum.setText("sum: " + sumCount);
        this.getContentPane().add(sum);
    }

    private void initJFrame() {
        this.setTitle("按键统计");
        this.setBounds(1290, 0, 250, 290);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//隐藏界面
        this.setVisible(true);
        this.setFocusable(true);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        this.setVisible(true);
        // 31 32 39 40
        if (e.getKeyCode() == 31) {
            key1Count++;
            showText(key1Count, key2Count, key3Count, key4Count);
        }
        if (e.getKeyCode() == 32) {
            key2Count++;
            showText(key1Count, key2Count, key3Count, key4Count);
        }
        if (e.getKeyCode() == 39) {
            key3Count++;
            showText(key1Count, key2Count, key3Count, key4Count);
        }
        if (e.getKeyCode() == 40) {
            key4Count++;
            showText(key1Count, key2Count, key3Count, key4Count);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            key1Count = 0;
            key2Count = 0;
            key3Count = 0;
            key4Count = 0;
            sumCount = 0;
            showText(key1Count, key2Count, key3Count, key4Count);
        }
    }
}
