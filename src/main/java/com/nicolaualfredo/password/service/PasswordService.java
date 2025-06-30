/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.password.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolaualfredo.password.model.PasswordLog;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nicolau Alfredo
 */
public class PasswordService {

    private static final String PASSWORDS_FILE = "data/passwords.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final Random random = new Random();

    /**
     * Gera uma senha com os parâmetros desejados.
     */
    public String generate(int length, boolean includeUppercase, boolean includeNumbers, boolean includeSymbols) {
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{};:,.<>?/";

        StringBuilder pool = new StringBuilder(lowercase);

        if (includeUppercase) {
            pool.append(uppercase);
        }
        if (includeNumbers) {
            pool.append(numbers);
        }
        if (includeSymbols) {
            pool.append(symbols);
        }

        if (pool.length() == 0) {
            return ""; // Nothing to use
        }
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(pool.length());
            password.append(pool.charAt(idx));
        }

        return password.toString();
    }

    /**
     * Saves the generated password with metadata in the JSON file.
     */
    public void savePassword(String password, int length, boolean includeUppercase,
            boolean includeNumbers, boolean includeSymbols) {

        PasswordLog log = new PasswordLog(LocalDateTime.now(), length, includeUppercase, includeNumbers, includeSymbols, password);
        List<PasswordLog> existing = loadPasswords();
        existing.add(log);

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(PASSWORDS_FILE), existing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads already generated passwords from JSON.
     */
    public List<PasswordLog> loadPasswords() {
        try {
            File file = new File(PASSWORDS_FILE);
            if (!file.exists()) {
                return new ArrayList<>();
            }

            String content = Files.readString(file.toPath());
            if (content.isBlank()) {
                return new ArrayList<>();
            }

            return mapper.readValue(content, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
