/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.password.model;

import java.time.LocalDateTime;

/**
 *
 * @author Nicolau Alfredo
 */
public class PasswordLog {

    private LocalDateTime timestamp;
    private int length;
    private boolean includeUppercase;
    private boolean includeNumbers;
    private boolean includeSymbols;
    private String password;

    public PasswordLog() {
    }

    public PasswordLog(LocalDateTime timestamp, int length, boolean includeUppercase,
            boolean includeNumbers, boolean includeSymbols, String password) {
        this.timestamp = timestamp;
        this.length = length;
        this.includeUppercase = includeUppercase;
        this.includeNumbers = includeNumbers;
        this.includeSymbols = includeSymbols;
        this.password = password;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getLength() {
        return length;
    }

    public boolean isIncludeUppercase() {
        return includeUppercase;
    }

    public boolean isIncludeNumbers() {
        return includeNumbers;
    }

    public boolean isIncludeSymbols() {
        return includeSymbols;
    }

    public String getPassword() {
        return password;
    }
}
