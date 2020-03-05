package me.uquark.barrymore.internal;

public class Location {
    public final int roomId;
    public final String roomName;

    public static Location undefined = new Location(-1, "undefined");

    public Location(int roomId, String roomName) {
        this.roomName = roomName;
        this.roomId = roomId;
    }
}
