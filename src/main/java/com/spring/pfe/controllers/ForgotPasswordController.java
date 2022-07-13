package com.spring.pfe.controllers;

import com.spring.pfe.models.Demande;
import com.spring.pfe.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;

@RestController
@RequestMapping("/forgot")
@CrossOrigin("*")
public class ForgotPasswordController {


    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping("/")
    public void findAllDemande () {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("labidii.najett@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

        System.out.println("email done");;
    }



}
