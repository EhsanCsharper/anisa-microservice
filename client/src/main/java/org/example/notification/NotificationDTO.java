package org.example.notification;

import java.time.LocalDate;

public class NotificationDTO {
    private String callerService;
    private String message;
    private LocalDate date;

    public String getCallerService() {
        return callerService;
    }

    public void setCallerService(String callerService) {
        this.callerService = callerService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
                "callerService='" + callerService + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
