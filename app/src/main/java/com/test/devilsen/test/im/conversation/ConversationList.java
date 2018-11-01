package com.test.devilsen.test.im.conversation;

import java.util.ArrayList;

/**
 * desc :
 * date : 2018/9/18
 *
 * @author : dongSen
 */
interface ConversationList {

    void add(IConversation conversation);

    void add(ArrayList<IConversation> conversations);

    int remove(IConversation conversation);

    void set(int position, IConversation conversation);

    void set(IConversation conversation);

    void update(IConversation conversation);

    IConversation get(int position);

    IConversation get(String targetId);

    int getPosition(IConversation conversation);

    ArrayList<IConversation> getAll();

    int size();

    void clear();

    void sort();

}
