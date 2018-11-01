package com.test.devilsen.test.im.conversation;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc :
 * date : 2018/11/1
 *
 * @author : dongSen
 */
public class ConversationArrayList implements ConversationList {

    private final ArrayList<IConversation> conversations = new ArrayList<>();

    private final ConcurrentHashMap<String, IConversation> conversationCache = new ConcurrentHashMap<>();

    @Override
    public void add(IConversation conversation) {
        if (checkNull(conversation))
            return;

        IConversation con = get(conversation.getTargetId());

        if (con == null) {
            conversations.add(conversation);
            conversationCache.put(conversation.getTargetId(), conversation);
        } else {
            if (conversation.getSendTime() > con.getSendTime()) {
                set(conversation);
            }
        }
    }

    @Override
    public void add(ArrayList<IConversation> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public int remove(IConversation conversation) {
        if (checkNull(conversation))
            return -1;

        int position = getPosition(conversation);

        conversations.remove(conversation);
        conversationCache.remove(conversation.getTargetId());

        return position;
    }

    @Override
    public void set(int position, IConversation conversation) {
        if (checkNull(conversation) || position < 0 || position > conversations.size())
            return;

        conversations.set(position, conversation);
        conversationCache.put(conversation.getTargetId(), conversation);
    }

    @Override
    public void set(IConversation conversation) {
        int position = getPosition(conversation);
        set(position, conversation);
    }

    @Override
    public void update(IConversation conversation) {
        if (checkNull(conversation))
            return;

        IConversation position = get(conversation.getTargetId());

        if (position == null) {
            add(conversation);
        } else {
            set(conversation);
        }
    }

    @Override
    public IConversation get(int position) {
        return conversations.get(position);
    }

    @Override
    public IConversation get(String targetId) {
        return conversationCache.get(targetId);
    }

    /**
     * 获取会话的位置
     */
    @Override
    public int getPosition(IConversation conversation) {
        if (checkNull(conversation))
            return -1;

        String targetId = conversation.getTargetId();
        for (int i = 0; i < conversations.size(); i++) {
            IConversation iConversation = conversations.get(i);
            if (iConversation != null && TextUtils.equals(targetId, iConversation.getTargetId())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ArrayList<IConversation> getAll() {
        return conversations;
    }

    @Override
    public int size() {
        return conversations.size();
    }

    @Override
    public void clear() {
        conversations.clear();
        conversationCache.clear();
    }

    @Override
    public void sort() {
//        Collections.sort(conversations, conComparator);
    }

    private boolean checkNull(IConversation conversation) {
        return conversation == null || conversation.getTargetId() == null;
    }
}
