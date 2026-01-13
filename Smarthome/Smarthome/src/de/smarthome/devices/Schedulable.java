package de.smarthome.devices;

public interface Schedulable {

    void schedule(String startTime, String endTime);

    void cancelSchedule();
}
