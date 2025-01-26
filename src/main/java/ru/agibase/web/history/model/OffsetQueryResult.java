package ru.agibase.web.history.model;

import lombok.Data;

import java.util.List;

@Data
public class OffsetQueryResult<T> {
    private final List<T> content;
    private final long totalElements;
}
