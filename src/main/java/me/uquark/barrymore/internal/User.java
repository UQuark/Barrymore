package me.uquark.barrymore.internal;

public class User {
    public final int id;
    public final Group group;
    public final String name;
    public Location location = Location.undefined;

    public enum Group {
        Unknown,
        Guest,
        Member,
        Owner,
    }

    public static User root = new User(0, Group.Owner, "root");
    public static User unknown = new User(-1, Group.Unknown, "unknown");

    public User(int id, Group group, String name) {
        this.id = id;
        this.group = group;
        this.name = name;
    }

    public static User getUserById(int roomId) {
        return unknown;
    }
}
