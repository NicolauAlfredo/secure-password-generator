/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nicolaualfredo.password.util;

import com.sun.net.httpserver.HttpExchange;

/**
 *
 * @author Nicolau Alfredo
 */
/**
 * Utility class to enable CORS for Java HTTP Server responses.
 */
public class CorsUtil {

    /**
     * Adds standard CORS headers to the response. Allows frontend apps (running
     * on another port or domain) to access the API.
     */
    public static void handleCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    }
}
