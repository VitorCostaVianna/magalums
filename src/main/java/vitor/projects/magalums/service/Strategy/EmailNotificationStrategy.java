package vitor.projects.magalums.service.Strategy;

import vitor.projects.magalums.email.EmailServiceImpl;
import vitor.projects.magalums.entity.Notification;
import vitor.projects.magalums.entity.Status;
import vitor.projects.magalums.repository.NotificationRepository;
import vitor.projects.magalums.service.NotificationStrategy;

public class EmailNotificationStrategy implements NotificationStrategy {

    private final EmailServiceImpl emailService;

    private final NotificationRepository notificationRepository;

    public EmailNotificationStrategy(EmailServiceImpl emailService , NotificationRepository notificationRepository) {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendNotification(Notification notification) {
        try {
            emailService.sendSimpleMessage(notification.getDestination(), "Magalu Notification", notification.getMessage());
            notification.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(notification);
        } catch (Exception e) {
            notification.setStatus(Status.Values.ERROR.toStatus());
            notificationRepository.save(notification);
            return;
        }
    }

}
