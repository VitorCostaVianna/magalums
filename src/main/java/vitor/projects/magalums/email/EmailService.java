package vitor.projects.magalums.email;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
