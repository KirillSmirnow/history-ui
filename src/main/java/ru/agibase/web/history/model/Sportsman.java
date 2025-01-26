package ru.agibase.web.history.model;

import lombok.Data;

@Data
public class Sportsman {

    private final User user;

    public String getName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    @Data
    public static class User {
        private final String lastName;
        private final String firstName;
        private final String secondName;
    }
}
