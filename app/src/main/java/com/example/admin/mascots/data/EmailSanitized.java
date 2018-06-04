package com.example.admin.mascots.data;

public class EmailSanitized {

    public String emailSanitized(String email){

        return email.replace("@", "AT").replace(".", "DOT");
    }
}
