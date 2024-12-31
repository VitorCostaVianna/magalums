package vitor.projects.magalums.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vitor.projects.magalums.entity.Notification;
import vitor.projects.magalums.entity.Status;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime); // find notifications by status and dateTime
    
}
