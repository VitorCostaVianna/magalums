package vitor.projects.magalums.service;

import vitor.projects.magalums.entity.Notification;

public interface NotificationStrategy {
    
    void sendNotification(Notification notification);
}
