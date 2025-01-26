package ru.agibase.web.history.model;

import lombok.Data;

@Data
public class Range<T> {
    private final T from;
    private final T to;
}
