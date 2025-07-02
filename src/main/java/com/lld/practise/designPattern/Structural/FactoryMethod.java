package com.lld.practise.designPattern.Structural;


interface Notification {
    void notifyUser();
}
 class EmailNotification implements Notification {
//     private static final Logger logger = Logger.getLogger(EmailNotification.class.getName());

     public void notifyUser() {
        System.out.println("Sending an Email Notification");
    }
}

 class SMSNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending an SMS Notification");
    }
}

 class PushNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending a Push Notification");
    }
}

 abstract class NotificationFactory {
    public abstract Notification createNotification();
}

 class EmailNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new EmailNotification();
    }
}

 class SMSNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new SMSNotification();
    }
}

 class PushNotificationFactory extends NotificationFactory {
    public Notification createNotification() {
        return new PushNotification();
    }
}


public class FactoryMethod {
     public void implementFactoryMethod(){
         NotificationFactory factory = new SMSNotificationFactory();
         Notification notification = factory.createNotification();
         notification.notifyUser();
     }
}
