package edu.palevobot.entities;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

public class UserPalevo {
    private int userId;
    private int palevoId;

    public static ArrayList<UserPalevo> userPalevos = new ArrayList<>();

    public UserPalevo(User user, Palevo palevo) {
        userId = user.getId();
        palevoId = palevo.getId();
    }

    public User getUser() throws ExecutionControl.NotImplementedException {
        if(userId == 0)
            return null;
        for (User user : User.users) {
            if(user.getId() == userId)
                return user;
        }
        //Реализовать заброс к БД
        throw new ExecutionControl.NotImplementedException("заброс к БД");

    }

    public Palevo getPalevo() throws ExecutionControl.NotImplementedException {
        if(palevoId == 0)
            return null;
        for (Palevo palevo : Palevo.palevos) {
            if(palevo.getId() == palevoId)
                return palevo;
        }
        //Реализовать запрос к БД
        throw new ExecutionControl.NotImplementedException("запрос к БД");
    }
}
