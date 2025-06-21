package com.project.banque.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/bank-account")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BankAccountController {
}
