package me.sam.designpattern.responsibilitychain.advanced;

import java.util.ArrayList;

/**
 * desc :
 * date : 2019-11-07 10:26
 *
 * @author : dongSen
 */
public class ChainManager implements ITask {

    private ArrayList<ITask> taskChains = new ArrayList<>();
    private int index;

    public void addTask(ITask task) {
        taskChains.add(task);
    }

    @Override
    public void doAction(ITask taskManager) {
        if (taskChains.isEmpty()) {
            System.out.println("chain is empty");
            return;
        }

        if (index >= taskChains.size()) {
            return;
        }

        ITask iTask = taskChains.get(index);
        index++;
        iTask.doAction(taskManager);
    }
}
