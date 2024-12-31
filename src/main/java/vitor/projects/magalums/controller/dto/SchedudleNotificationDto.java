package vitor.projects.magalums.controller.dto;

import java.time.LocalDateTime;

import vitor.projects.magalums.entity.Channel;
import vitor.projects.magalums.entity.Notification;
import vitor.projects.magalums.entity.Status;

public record SchedudleNotificationDto(LocalDateTime dateTime,
                                        String destination,
                                        String message,
                                        Channel.Values channel) {
    public Notification toNotification() {
        return new Notification(
            dateTime,
            destination,
            message,
            channel.toChannel(),
            Status.Values.PENDING.toStatus()
        );
    }
}
