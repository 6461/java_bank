package com.mma.bank;

import java.util.ArrayList;
import java.util.Date;

public class UserList {
    private static UserList instance = null;
    private ArrayList<User> list;
    private String filename = "user_list.json";
    private FileHandler file;

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }

        return instance;
    }

    private UserList() {
        list = new ArrayList<User>();
        file = FileHandler.getInstance();
        // Read data from file.
        file.readFile(list, filename);
    }

    public ArrayList<User> getList() {
        return list;
    }

    public boolean add(User user) {
        boolean add = false;

        if (user != null) {
            add = list.add(user);
            // Write data to file.
            file.writeFile(list, filename);
        }

        return add;
    }

    public boolean remove(User user) {
        boolean remove = false;

        if (user != null) {
            remove = list.remove(user);
            // Write data to file.
            file.writeFile(list, filename);
        }

        return remove;
    }
}
