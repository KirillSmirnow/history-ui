package ru.agibase.web.history.model;

import lombok.Data;

import java.util.List;

@Data
public class CompetitionResult {

    private final long id;
    private final String name;
    private final Venue venue;
    private final List<RatingResult> ratings;

    @Data
    public static class Venue {
        private final String name;
    }
}
