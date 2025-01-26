package ru.agibase.web.history.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Result {
    private final BigDecimal timeInSeconds;
    private final BigDecimal timeFaults;
    private final BigDecimal courseFaults;
    private final BigDecimal totalFaults;
}
