package me.uquark.barrymore.ui;

import me.uquark.barrymore.internal.User;

public class UserOrder {
    public final User user;
    public final String message;

    public UserOrder(User user, String message) {
        this.user = user;
        this.message = message;
    }
}
