package me.sam.designpattern.responsibilitychain.advanced;

/**
 * desc :
 * date : 2019-11-07 10:32
 *
 * @author : dongSen
 */
public class Task1 implements ITask {
    @Override
    public void doAction(ITask task) {
        System.out.println("do something");

        task.doAction(task);
    }
}
