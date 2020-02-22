package me.uquark.barrymore.avatar;

import me.uquark.barrymore.Application;
import me.uquark.barrymore.ui.IUserInterface;
import me.uquark.barrymore.ui.UserOrder;

public class Barrymore implements IAvatar {
    private IUserInterface ui;

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
        Application.halt();
    }

    @Override
    public void run() {
        ui.setAvatar(this);
        ui.open();
    }
}
