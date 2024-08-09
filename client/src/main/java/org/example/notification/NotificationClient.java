package org.example.notification;

import org.example.discount.CouponDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("NOTIFICATION")
public interface NotificationClient {

    @PostMapping("/api/v1/notification/")
    CouponDTO sendNotification(@ModelAttribute("notificationDTO") NotificationDTO notificationDTO);
}
