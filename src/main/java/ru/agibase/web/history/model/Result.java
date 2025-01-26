package ru.agibase.web.history.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Result {

    private final BigDecimal timeInSeconds;
    private final BigDecimal timeFaults;
    private final BigDecimal courseFaults;
    private final BigDecimal totalFaults;

    private final Integer place;
    private final boolean eliminated;
    private final boolean absent;
    private final boolean disqualified;
    private final boolean unrated;

    public String getPlace() {
        var builder = new StringBuilder();
        if (disqualified) {
            builder.append("Дисквал.");
        } else if (eliminated) {
            builder.append("Снят");
        } else if (absent) {
            builder.append("Неявка");
        } else if (place != null) {
            builder.append(place);
        }
        if (unrated) {
            if (builder.isEmpty()) {
                builder.append("Вне зачёта");
            } else {
                builder.append(" (Вне зачёта)");
            }
        }
        return builder.toString();
    }
}
