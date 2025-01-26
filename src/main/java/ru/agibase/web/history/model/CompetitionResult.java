package ru.agibase.web.history.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Data
public class CompetitionResult {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.of("ru"));

    private final long id;
    private final String name;
    private final Range<LocalDate> competitionPeriod;
    private final Venue venue;
    private final List<RatingResult> ratings;

    public String getCompetitionPeriod() {
        var from = competitionPeriod.getFrom();
        var to = competitionPeriod.getTo();
        if (from.isEqual(to)) {
            return from.format(FORMATTER);
        }
        return from.getDayOfMonth() + " - " + to.format(FORMATTER);
    }

    @Data
    public static class Venue {
        private final String name;
    }
}
