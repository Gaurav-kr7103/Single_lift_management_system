package org.generic.wildcard;

public class SingleLiftManager {
    private static Elevator lift;
    private static SingleLiftManager instance;
    private SingleLiftManager() {
    }
    public SingleLiftManager getInstance() {
        if (instance == null) {
            instance = new SingleLiftManager();
            lift = new Elevator();
            activateLift();
        }
        return instance;
    }
    public void pressButton(int floorButton) {
        lift.recordButtonAction(floorButton);
    }
    public void activateLift() throws InterruptedException {
        while(true) {
            if (lift.getLiftDirection().equals(SingleLiftConstants.UP)) {
                while(!lift.isUpQueueEmpty()) {
                    //logic to empty the upward queue
                    emptyUpwardQueue();
                }
            } else if (lift.getLiftDirection().equals(SingleLiftConstants.DOWN)) {
                while (!lift.isDownQueueEmpty()) {
                    //logic to empty the downward queue
                    emptyDownwardQueue();
                }
            }
        }
    }

    private void emptyUpwardQueue() throws InterruptedException {
        int nextFloor = lift.removeElementUpQueue();
        if (nextFloor == -1) {
            //edge case to change the direction

        }
        //logic to move the lift in the upward direction
        int time_to_move = Math.abs(lift.getCurrentFloor() - nextFloor);
        while (time_to_move>0) {
            int currentFloor = lift.getCurrentFloor();
            System.out.println("Lift Current floor : " + currentFloor);
            //1 second pause
            Thread.sleep(1000);
            time_to_move--;
            lift.setCurrentFloor(currentFloor+1);
        }
    }
    private void emptyDownwardQueue() throws InterruptedException {
        int nextFloor = lift.removeElementDownQueue();
        if (nextFloor == -1) {
            //edge case to change the direction
        }
        //  logic to move lift in the downward direction
        int time_to_move = Math.abs(lift.getCurrentFloor() - nextFloor);
        while (time_to_move>0) {
            int currentFloor = lift.getCurrentFloor();
            System.out.printf("Lift Current floor : " + currentFloor);
            //1 second pause
            Thread.sleep(1000);
            time_to_move--;
            lift.setCurrentFloor(currentFloor-1);
        }
    }
}