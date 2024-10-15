package com.shreyas;

// Thread class for Garden tasks
class GardenThread extends Thread {

    public GardenThread(Runnable task, String name) {
        super(task, name);
    }

    @Override
    public void run() {
        super.run();
    }
}