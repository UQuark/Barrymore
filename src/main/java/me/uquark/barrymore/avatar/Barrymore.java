package me.uquark.barrymore.avatar;

import me.uquark.barrymore.Application;
import me.uquark.barrymore.ui.IUserInterface;
import me.uquark.barrymore.ui.UserOrder;

public class Barrymore implements IAvatar {
    private IUserInterface ui;
    private volatile boolean running = false;

    public Barrymore(IUserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void userOrder(UserOrder uo) {
        if (uo.message.equals("/exit")) {
            halt();
            return;
        }
        ui.putMessage(uo.message);
    }

    @Override
    public String getName() {
        return "Barrymore";
    }

    @Override
    public void halt() {
        ui.close();
        running = false;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {
        running = true;
        ui.setAvatar(this);
        ui.open();
    }
}
