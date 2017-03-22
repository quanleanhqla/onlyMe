package com.example.quanla.quannet.events;

/**
 * Created by QuanLA on 3/20/2017.
 */

public class ActivityReplaceEvent {
    private ReplaceEvent event;

    public ActivityReplaceEvent(ReplaceEvent event) {
        this.event = event;
    }

    public ReplaceEvent getEvent() {
        return event;
    }

    public void setEvent(ReplaceEvent event) {
        this.event = event;
    }
}
