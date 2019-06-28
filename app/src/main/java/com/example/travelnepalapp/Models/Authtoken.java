package com.example.travelnepalapp.Models;

public class Authtoken
{
    private  String Success;
    private String message;
    private String token;
    private String username;
    private String _id;


    public String getSuccess() {
        return Success;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
    public String getUsername() {
        return username;
    }


    public void setSuccess(String success) {
        Success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
