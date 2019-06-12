package com.me_22k.spring.boot.blog.mail;

public interface MailService {
    public void sendSimpleEmail(String to,String subject,String content);

    public void sendHtmlMail(String to, String subject, String content);
}
