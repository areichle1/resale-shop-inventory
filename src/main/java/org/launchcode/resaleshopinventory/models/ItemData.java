package org.launchcode.resaleshopinventory.models;

import java.util.ArrayList;

public class ItemData {

    static ArrayList<Item> items = new ArrayList<>();

    public static ArrayList<Item> getAll() {
        return items;
    }

    public static void add(Item newItem) {
        items.add(newItem);
    }

    public static void remove(int id) {
        Item itemToRemove = getById(id);
        items.remove(itemToRemove);
    }

    public static Item getById(int id) {
        Item theItem = null;

        for (Item candidateItem : items) {
            if (candidateItem.getItemId() == id) {
                theItem = candidateItem;
            }
        }

        return theItem;
    }
}
