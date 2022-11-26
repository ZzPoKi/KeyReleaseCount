package Test1;

import com.github.kwhat.jnativehook.GlobalScreen;

//程序入口
public class StartApp {
    public static void main(String[] args) {
        GlobalScreen.addNativeKeyListener(new KeyPressedCount());
        //new StartGame();
    }
}
