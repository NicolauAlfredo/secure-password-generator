/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.nicolaualfredo.password;

import com.nicolaualfredo.password.handler.GenerateHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 *
 * @author Nicolau Alfredo
 */
public class PasswordApp {

    public static void main(String[] args) {
        try {
            // Create HTTP server on port 8000
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

            // Register the /generate endpoint
            server.createContext("/generate", new GenerateHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("Password Generator API running at http://localhost:8000");

        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
