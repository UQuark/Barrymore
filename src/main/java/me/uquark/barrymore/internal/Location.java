package me.uquark.barrymore.internal;

public class Location {
    public final String roomName;
    public final int roomId;

    public static Location undefined = new Location(-1, "undefined");

    public Location(int roomId, String roomName) {
        this.roomName = roomName;
        this.roomId = roomId;
    }

    public static Location getLocationById(int roomId) {
        return undefined;
    }
}
