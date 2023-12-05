package com.mx.viajabara.ServiceImpl;

import com.mx.viajabara.Dto.EmailDetails;
import com.mx.viajabara.Entity.Response;
import com.mx.viajabara.Entity.Usuario;
import com.mx.viajabara.Repository.UsuarioRepository;
import com.mx.viajabara.Service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired private JavaMailSender javaMailSender;

    @Autowired private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired private ClienteServiceImpl clienteService;

    @Value("${spring.mail.username}") private String sender;

    public Response sendSimpleEmail(String correo)
    {

        try {
            EmailDetails detalles = new EmailDetails();
            String newPassword = generateRandomPassword();
            Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);
            if (usuario.isPresent()){
                Usuario usuarioSave = usuario.get();
                usuarioSave.setClave(passwordEncoder.encode(newPassword));
                usuarioRepository.save(usuarioSave);
                detalles.setRecipient(correo);
                detalles.setSubject("Recuperación de contraseña: Viajabara");
                detalles.setMsgBody("<body style=\"font-family: Arial, sans-serif; background-color: #000;\">"
                        +"<div style=\"background-color: #003366; padding: 20px; color: #FFFFFF; text-align: center;\"><h1>Viajabara</h1></div>" +
                        "    <div style=\"text-align: center;\">\n" +
                        "    </div>" +
                        "\t<div style=\"padding: 20px; color: #fff;\">\n" +
                        "\t\t<p>Estimado/a usuari@ </p>\n" +
                        "\n" +
                        "\t\t<p>Este correo se le envia para recuperar su clave, se le proporcionara una nueva para su inicio de sesión</p>\n" +
                        "\n" +
                        "\t\t<p>"+ newPassword +"</p>\n" +
                        "\n" +
                        "\t\t<p>Si tiene alguna pregunta o comentario sobre este proceso, por favor no dude en ponerse en contacto con nosotros.</p>\n" +
                        "\n" +
                        "\t\t<p>Atentamente, <strong>Viajabara</strong></p>\n" +
                        "\t</div>"+
                        "\t<div style=\"background-color: #003366; padding: 20px; color: #FFFFFF; text-align: center;\">\n" +
                        "\t\t<p>No responda a este correo electrónico. Este correo es generado automáticamente.</p>\n" +
                        "\t</div>" + "</body>");
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setFrom(sender);
                helper.setTo(detalles.getRecipient());
                helper.setText(detalles.getMsgBody(), true);
                helper.setSubject(detalles.getSubject());
                javaMailSender.send(mimeMessage);
                return new Response("Correo electrónico enviado", correo, false);
            }else {
                return new Response("No se encontro el correo electrónico", correo, true);
            }

        }
        catch (Exception e) {
            System.out.println(e);
            return new Response("Error al enviar el correo", null, true);
        }
    }

    public String generateRandomPassword(){
        String caracteres = "123456789abcdefg¡?@.,";
        String password= "";

        for (int i = 0; i < 8; i++){
            password += caracteres.charAt((int)(Math.random()*(caracteres.length())));
        }
        return password;
    }
}
