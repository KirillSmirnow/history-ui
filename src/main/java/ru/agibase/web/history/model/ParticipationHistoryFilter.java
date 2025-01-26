package ru.agibase.web.history.model;

import lombok.Data;

@Data
public class ParticipationHistoryFilter {

    private final Long sportsmanId;
    private final Long dogId;

    public static ParticipationHistoryFilter bySportsman(long sportsmanId) {
        return new ParticipationHistoryFilter(sportsmanId, null);
    }

    public static ParticipationHistoryFilter byDog(long dogId) {
        return new ParticipationHistoryFilter(null, dogId);
    }
}
