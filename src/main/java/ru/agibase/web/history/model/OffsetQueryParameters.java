package ru.agibase.web.history.model;

import lombok.Data;

@Data
public class OffsetQueryParameters {

    private static final int PAGE_SIZE = 10;

    private final int offset;
    private final int limit;

    public static OffsetQueryParameters ofPage(int page) {
        return new OffsetQueryParameters(page * PAGE_SIZE, PAGE_SIZE);
    }
}
