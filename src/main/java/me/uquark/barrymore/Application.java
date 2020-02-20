package me.uquark.barrymore;

import me.uquark.barrymore.avatar.Barrymore;
import me.uquark.barrymore.avatar.IAvatar;
import me.uquark.barrymore.ui.ConsoleUserInterface;

public class Application {
    public static void main(String[] args) {
        IAvatar barrymore = new Barrymore(new ConsoleUserInterface());
        barrymore.run();
        while (barrymore.isRunning());
    }
}
