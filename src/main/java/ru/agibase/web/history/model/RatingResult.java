package ru.agibase.web.history.model;

import lombok.Data;

import java.util.List;

@Data
public class RatingResult {
    private final String name;
    private final DogView dog;
    private final Result result;
    private final List<CourseResult> courses;
}
