package vitor.projects.magalums.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vitor.projects.magalums.controller.dto.SchedudleNotificationDto;
import vitor.projects.magalums.entity.Notification;
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

   @GetMapping("/{notificationId}")
   public ResponseEntity<Notification> getNotification(@PathVariable Long notificationId) {
       var notification = notificationService.findById(notificationId);

       if (notification.isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(notification.get());
   }

   @PutMapping("/{notificationId}")
   public ResponseEntity<Void> cancelNotification(@PathVariable Long notificationId){
      notificationService.cancelNotification(notificationId);
      return ResponseEntity.noContent().build();
   }
}
