package ru.agibase.web.history.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class CompetitionResult {

    private static final DateTimeFormatter COMPETITION_PERIOD_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");

    private final long id;
    private final String name;
    private final Range<LocalDate> competitionPeriod;
    private final Venue venue;
    private final List<RatingResult> ratings;

    public String getCompetitionPeriod() {
        var from = competitionPeriod.getFrom();
        var to = competitionPeriod.getTo();
        if (from.equals(to)) {
            return from.format(COMPETITION_PERIOD_FORMATTER);
        }
        return from.getDayOfMonth() + "-" + to.format(COMPETITION_PERIOD_FORMATTER);
    }

    @Data
    public static class Venue {
        private final String name;
    }
}
