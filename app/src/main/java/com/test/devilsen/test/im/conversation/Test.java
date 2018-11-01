package com.test.devilsen.test.im.conversation;

/**
 * desc :
 * date : 2018/11/1
 *
 * @author : dongSen
 */
public class Test {

    private static ConversationArrayList list;

    private static int max = 100000;

    public static void main(String[] args) {
        init();

        for (int i = 0; i < max; i = i + 500) {
            getRandom(i);
            getRandomFor(i);
        }
    }

    private static void getRandom(int random) {
        long star = System.nanoTime();
        IConversation iConversation = list.get(String.valueOf(random));
        long end = System.nanoTime();

        System.out.println("id: " + random + "   time: " + (end - star));
    }

    private static void getRandomFor(int random) {
        long star = System.nanoTime();

        for (int j = 0; j < list.size(); j++) {
            String targetId = String.valueOf(random);
            if (targetId.equals(list.get(j).getTargetId())) {
                break;
            }
        }

        long end = System.nanoTime();
        System.out.println("id: " + random +"   for time: " + (end - star));

    }

    private static void init() {
        list = new ConversationArrayList();

        for (int i = 0; i < max; i++) {
            IConversation iConversation = new IConversation();
            iConversation.setTargetId(String.valueOf(i));
            iConversation.setSendTime((long) i);
            list.add(iConversation);
        }

    }

}
