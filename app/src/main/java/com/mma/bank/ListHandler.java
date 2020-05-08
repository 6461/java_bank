package com.mma.bank;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ListHandler<T> {
    private ArrayList<T> list;
    private Type type;
    private String filename;
    private FileHandler file = FileHandler.getInstance();

    public ListHandler(Type type, String filename) {
        this.type = type;
        this.filename = filename;

        // Read data from file.
        list = (ArrayList<T>) file.readFile(type, filename);

        if (list == null) {
            list = new ArrayList<>();
        }
    }

    public ArrayList<T> getList() {
        return list;
    }

    public boolean add(T object) {
        boolean add = false;

        if (object != null) {
            add = list.add(object);
            // Write data to file.
            file.writeFile(type, list, filename);
        }

        return add;
    }

    public boolean remove(T object) {
        boolean remove = false;

        if (object != null) {
            remove = list.remove(object);
            // Write data to file.
            file.writeFile(type, list, filename);
        }

        return remove;
    }

    public void update() {
        file.writeFile(type, list, filename);
    }
}
