package me.sam.designpattern.responsibilitychain.advanced;

/**
 * desc :
 * date : 2019-11-07 10:26
 *
 * @author : dongSen
 */
public interface ITask {

    void doAction(ITask task);

}
