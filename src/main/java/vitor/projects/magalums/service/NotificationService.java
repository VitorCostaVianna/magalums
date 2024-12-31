package vitor.projects.magalums.service;

import vitor.projects.magalums.entity.Notification;
import vitor.projects.magalums.entity.Status;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vitor.projects.magalums.controller.dto.SchedudleNotificationDto;
import vitor.projects.magalums.repository.NotificationRepository;

@Service
public class NotificationService {
    
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(SchedudleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
         var notification = notificationRepository.findById(notificationId);

         if (notification.isPresent()) {
            notification.get().setStatus(Status.Values.CANCELED.toStatus());
            notificationRepository.save(notification.get());
        }
    }

}
