package ru.agibase.web.history.model;

import lombok.Data;

import java.util.List;

@Data
public class RatingResult {
    private final String name;
    private final Dog dog;
    private final Sportsman sportsman;
    private final Result result;
    private final List<CourseResult> courses;
}
