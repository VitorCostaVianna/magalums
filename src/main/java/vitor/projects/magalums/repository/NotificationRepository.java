package vitor.projects.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vitor.projects.magalums.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    
}
