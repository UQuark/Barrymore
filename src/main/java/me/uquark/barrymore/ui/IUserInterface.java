package me.uquark.barrymore.ui;

import me.uquark.barrymore.avatar.IAvatar;

public interface IUserInterface {
    void setAvatar(IAvatar avatar);
    void putMessage(String message);
    void open();
    void close();
}
