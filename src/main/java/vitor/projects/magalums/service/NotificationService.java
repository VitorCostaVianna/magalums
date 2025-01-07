package vitor.projects.magalums.service;

import vitor.projects.magalums.entity.Channel;
import vitor.projects.magalums.entity.Notification;
import vitor.projects.magalums.entity.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vitor.projects.magalums.controller.dto.SchedudleNotificationDto;
import vitor.projects.magalums.email.EmailServiceImpl;
import vitor.projects.magalums.repository.NotificationRepository;
import vitor.projects.magalums.service.Strategy.EmailNotificationStrategy;
import vitor.projects.magalums.service.Strategy.PushNotificationStrategy;
import vitor.projects.magalums.service.Strategy.SmsNotificationStrategy;
import vitor.projects.magalums.service.Strategy.WhatsappNotificationStrategy;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final EmailServiceImpl emailService;

    private final Map<Channel, NotificationStrategy> mapStrategy;

    public NotificationService(NotificationRepository notificationRepository,
            EmailServiceImpl emailService) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.mapStrategy = Map.of(Channel.Values.EMAIL.toChannel(),
                new EmailNotificationStrategy(emailService, notificationRepository),
                Channel.Values.SMS.toChannel(), new SmsNotificationStrategy(),
                Channel.Values.WHATSAPP.toChannel(), new WhatsappNotificationStrategy(),
                Channel.Values.PUSH.toChannel(), new PushNotificationStrategy());
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

    public void checkAndSend(LocalDateTime dateTime) {
        var notifications = notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()),
                dateTime);
        
        if (notifications.isEmpty()) return; // no notifications to send

        notifications.forEach(n -> {
            mapStrategy.get(n.getChannel()).sendNotification(n);
        }); 
    }

}
