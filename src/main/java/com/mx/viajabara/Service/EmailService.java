package com.mx.viajabara.Service;

import com.mx.viajabara.Dto.EmailDetails;
import com.mx.viajabara.Entity.Response;

public interface EmailService {

    Response sendSimpleEmail(String correo);
}
