package org.generic.wildcard;

import java.net.Inet4Address;
import java.util.*;

public class Elevator {
    private static final int MAX_FLOOR = SingleLiftConstants.MAX_FLOOR;
    private int currentFloor;
    private String liftDirection;
    private Queue<Integer> upwardQueue;
    private Set<Integer> upSet;
    private Queue<Integer> downwardQueue;
    private Set<Integer> downSet;

    public Elevator() {
        currentFloor = 0;
        liftDirection = SingleLiftConstants.UP;
        upwardQueue = new PriorityQueue<>();
        upSet = new HashSet<>();
        downwardQueue = new PriorityQueue<>(Collections.reverseOrder());
        downSet = new HashSet<>();
    }

    //function to fill the queue
    private boolean getClientDirection(int buttonPressed) {
        return buttonPressed - currentFloor >= 0;
    }
    public void recordButtonAction(int buttonPressed) {
        boolean clientDirection = getClientDirection(buttonPressed);
        if (clientDirection && !upSet.contains(buttonPressed)) {
            upwardQueue.add(buttonPressed);
            upSet.add(buttonPressed);
        }
        else if (!clientDirection && !downSet.contains(buttonPressed)) {
            downwardQueue.add(buttonPressed);
            downSet.add(buttonPressed);
        }
    }

    public boolean isIdle() {
        return upSet.isEmpty() && downSet.isEmpty();
    }

    public boolean isUpQueueEmpty() {
        return upwardQueue.isEmpty();
    }
    public boolean isDownQueueEmpty() {
        return downwardQueue.isEmpty();
    }
    public int removeElementUpQueue() {
        if (!isUpQueueEmpty()) {
            int floor = upwardQueue.remove();
            upSet.remove(floor);
            return floor;
        }
        return -1;
    }
    public int removeElementDownQueue() {
        if (!isDownQueueEmpty()) {
            int floor = downwardQueue.remove();
            downSet.remove(floor);
            return floor;
        }
        return -1;
    }

    public String getLiftDirection() {
        return liftDirection;
    }
    public void setLiftDirection(String direction) {
        this.liftDirection = direction;
    }
    public int getCurrentFloor() {
        return this.currentFloor;
    }
    public void setCurrentFloor(int floor) {
        this.currentFloor = floor;
    }
}
