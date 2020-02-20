package me.uquark.barrymore.avatar;

import me.uquark.barrymore.ui.UserOrder;

public interface IAvatar {
    void userOrder(UserOrder uo);
    String getName();
    void halt();
    boolean isRunning();
    void run();
}
