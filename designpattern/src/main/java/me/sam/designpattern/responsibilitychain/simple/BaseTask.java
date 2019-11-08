package me.sam.designpattern.responsibilitychain.simple;

/**
 * desc :
 * date : 2019-11-07 09:59
 *
 * @author : dongSen
 */
public abstract class BaseTask {

    private BaseTask nextTask;

    public abstract void doAction();

    public void addNext(BaseTask task) {
        nextTask = task;
    }

    public void action() {
        if (nextTask != null) {
            nextTask.action();
        }
    }

}
