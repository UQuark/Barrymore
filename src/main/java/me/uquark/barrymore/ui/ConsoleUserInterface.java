package me.uquark.barrymore.ui;

import me.uquark.barrymore.avatar.IAvatar;
import me.uquark.barrymore.internal.User;

import java.util.Scanner;

public class ConsoleUserInterface extends Thread implements IUserInterface {
    private IAvatar avatar;
    private volatile boolean running = false;

    @Override
    public void setAvatar(IAvatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public void putMessage(String message) {
        System.out.println(avatar.getName() + " > " + message);
    }

    @Override
    public void open() {
        running = true;
        start();
    }

    @Override
    public void close() {
        running = false;
    }

    @Override
    public void run() {
        super.run();
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("root > ");
            String message = scanner.nextLine();
            UserOrder uo = new UserOrder(User.root, message);
            if (avatar != null)
                avatar.userOrder(uo);
        }
    }
}
