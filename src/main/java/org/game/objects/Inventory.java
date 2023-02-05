package org.game.objects;

import org.game.actions.GameAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<IItem, Integer> items;

    public Inventory() {
        items = new HashMap<>();
    }

    public Inventory(int countOfBasicMedKit) {
        this();

        add(MedKit.SMALL_HEALTH_POTION, countOfBasicMedKit);
    }

    public void add(IItem item, int count) {
        Integer currentCount = items.getOrDefault(item, 0);
        items.put(item, count + currentCount);
    }

    //---------
    public List<IItem> listByType() {
        List<IItem> res = new ArrayList<>();

        for (var entry : items.entrySet()) {
            res.add(entry.getKey());
        }
        return res;
    }
    public List<String> listItems() {
        List<String> res = new ArrayList<>();

        for (var entry : items.entrySet()) {
            res.add(entry.getKey().getName() + ":" + entry.getValue());
        }
        return res;
    }
    //---------

    public GameAction use (IItem item, String sender, String receiver) {
        Integer itemsCount = items.get(item);
        if (itemsCount == null) {
            return null;
        }

        if (itemsCount == 1) {
            items.remove(item);
        } else {
            items.put(item, itemsCount - 1);
        }
        return item.use(sender, receiver);
    }
}
