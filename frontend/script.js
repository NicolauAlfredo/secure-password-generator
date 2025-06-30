/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

const form = document.getElementById("password-form");
const result = document.getElementById("password-result");

form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const length = parseInt(document.getElementById("length").value);
    const includeUppercase = document.getElementById("uppercase").checked;
    const includeNumbers = document.getElementById("numbers").checked;
    const includeSymbols = document.getElementById("symbols").checked;

    const body = {
        length,
        includeUppercase,
        includeNumbers,
        includeSymbols
    };

    try {
        const response = await fetch("http://localhost:8000/generate", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body)
        });

        const data = await response.json();
        result.textContent = data.password;
    } catch (err) {
        result.textContent = "Failed to generate password.";
        console.error(err);
    }
});
