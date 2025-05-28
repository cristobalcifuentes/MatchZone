package com.matchzone.auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${app.email.from}")
    private String from;

    @Value("${app.email.verification-url}")
    private String verificationUrl;

    public void sendVerificationEmail(String to, String token) {
        String subject = "Verifica tu correo electrónico en MatchZone";
        String verifyLink = verificationUrl + "?token=" + token;
        String content = """
                <p>Hola,</p>
                <p>Gracias por registrarte en MatchZone.</p>
                <p>Haz clic en el siguiente enlace para verificar tu correo:</p>
                <p><a href="%s">Verificar Email</a></p>
                <p>Si no solicitaste este registro, ignora este mensaje.</p>
                """.formatted(verifyLink);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true); // HTML enabled

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el email de verificación", e);
        }
    }
    
    public void sendPasswordResetEmail(String to, String token) {
        String resetLink = verificationUrl.replace("/verify", "/reset-password") + "?token=" + token;

        String content = """
                <p>Hola,</p>
                <p>Hemos recibido una solicitud para restablecer tu contraseña en MatchZone.</p>
                <p>Haz clic en el siguiente enlace para crear una nueva contraseña:</p>
                <p><a href="%s">Restablecer Contraseña</a></p>
                <p>Este enlace expirará en 30 minutos.</p>
                <p>Si no solicitaste esto, puedes ignorar este mensaje.</p>
                """.formatted(resetLink);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject("Restablece tu contraseña en MatchZone");
            helper.setText(content, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo de restablecimiento", e);
        }
    }
}