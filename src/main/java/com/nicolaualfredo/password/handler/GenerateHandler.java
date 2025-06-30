/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.password.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicolaualfredo.password.model.PasswordRequest;
import com.nicolaualfredo.password.model.PasswordResponse;
import com.nicolaualfredo.password.service.PasswordService;
import com.nicolaualfredo.password.util.CorsUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Nicolau Alfredo
 */
/**
 * Handles HTTP POST requests to /generate endpoint. Expects JSON input with
 * password options and returns generated password.
 */
public class GenerateHandler implements HttpHandler {

    private final ObjectMapper mapper = new ObjectMapper();
    private final PasswordService passwordService = new PasswordService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        CorsUtil.handleCors(exchange); // Enable CORS for frontend

        // Allow only POST requests
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            return;
        }

        // Read and parse request JSON body
        InputStream requestBody = exchange.getRequestBody();
        PasswordRequest request = mapper.readValue(requestBody, PasswordRequest.class);

        // Generate password
        String generatedPassword = passwordService.generate(
                request.getLength(),
                request.isIncludeUppercase(),
                request.isIncludeNumbers(),
                request.isIncludeSymbols()
        );

        // Save to local log
        passwordService.savePassword(
                generatedPassword,
                request.getLength(),
                request.isIncludeUppercase(),
                request.isIncludeNumbers(),
                request.isIncludeSymbols()
        );

        // Prepare response
        PasswordResponse response = new PasswordResponse(generatedPassword);
        String jsonResponse = mapper.writeValueAsString(response);

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
