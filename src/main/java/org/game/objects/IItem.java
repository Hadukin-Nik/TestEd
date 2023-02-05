package org.game.objects;

import org.game.actions.GameAction;

public interface IItem {
    public void setName(String name);

    public String getName();

    public GameAction use(String sender, String receiver);
}
