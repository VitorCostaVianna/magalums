package vitor.projects.magalums.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vitor.projects.magalums.controller.dto.SchedudleNotificationDto;
import vitor.projects.magalums.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping
    public ResponseEntity<Void> schedudleNotification(@RequestBody SchedudleNotificationDto dto) {
        
        notificationService.scheduleNotification(dto);
        
        return ResponseEntity.accepted().build();
   }
}
