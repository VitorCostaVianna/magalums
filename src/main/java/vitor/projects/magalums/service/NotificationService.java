package vitor.projects.magalums.service;

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
}
