package ru.agibase.web.history.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.agibase.web.history.ParticipationHistoryClient;
import ru.agibase.web.history.model.CompetitionResult;
import ru.agibase.web.history.model.CourseResult;
import ru.agibase.web.history.model.OffsetQueryResult;
import ru.agibase.web.history.model.RatingResult;

import java.util.function.BiConsumer;

@Slf4j
@RequiredArgsConstructor
public class HistoryGrid extends VerticalLayout {

    private static final int PAGE_SIZE = 10;

    private final ParticipationHistoryClient participationHistoryClient;
    private final Type type;
    private final Long sportsmanId;
    private final Long dogId;
    private final BiConsumer<Integer, Integer> pageUpdateListener;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        setWidth("1400px");
        refresh(0);
    }

    public void refresh(int page) {
        var results = getResults(page);
        var totalPages = (int) Math.ceilDiv(results.getTotalElements(), PAGE_SIZE);
        pageUpdateListener.accept(page, totalPages);
        log.info("Results: {}, total elements: {}, total pages: {}", results.getContent().size(), results.getTotalElements(), totalPages);
        removeAll();
        results.getContent().forEach(competition -> {
            var grid = new Grid<>(RatingResult.class, false);
            grid.setItems(competition.getRatings());
            grid.addColumn("name").setHeader("Зачёт");
            if (type == Type.SPORTSMAN) {
                grid.addColumn("dog.homeName").setHeader("Собака");
            } else {
                grid.addColumn("sportsman.name").setHeader("Спортсмен");
            }
            grid.addColumn("result.timeInSeconds").setHeader("Сумма времени");
            grid.addColumn("result.totalFaults").setHeader("Сумма штрафов");
            grid.addColumn("result.place").setHeader("Место");
            grid.setAllRowsVisible(true);
            grid.setItemDetailsRenderer(new ComponentRenderer<>(CourseResultComponent::new, CourseResultComponent::setItems));

            var bar2 = new HorizontalLayout(
                    readableField(competition.getCompetitionPeriod()),
                    readableField(competition.getVenue().getName())
            );
            bar2.setWidthFull();

            var layout = new VerticalLayout(readableField(competition.getName()), bar2, grid);
            layout.setSpacing(false);
            add(layout);
        });
    }

    private OffsetQueryResult<CompetitionResult> getResults(int page) {
        var offset = page * PAGE_SIZE;
        return switch (type) {
            case SPORTSMAN -> participationHistoryClient.search(sportsmanId, null, offset, PAGE_SIZE);
            case DOG -> participationHistoryClient.search(null, dogId, offset, PAGE_SIZE);
        };
    }

    private TextField readableField(String text) {
        var field = new TextField();
        field.setValue(text);
        field.setWidthFull();
        field.setReadOnly(true);
        return field;
    }

    public enum Type {
        SPORTSMAN,
        DOG,
    }

    private static class CourseResultComponent extends VerticalLayout {
        public void setItems(RatingResult ratingResult) {
            var grid = new Grid<>(CourseResult.class, false);
            grid.setItems(ratingResult.getCourses());
            grid.addColumn("name").setHeader("Трасса");
            grid.addColumn("result.timeInSeconds").setHeader("Время");
            grid.addColumn("result.timeFaults").setHeader("Штраф за время");
            grid.addColumn("result.courseFaults").setHeader("Штраф по трассе");
            grid.addColumn("result.speedInMetersPerSecond").setHeader("Скорость");
            grid.addColumn("result.totalFaults").setHeader("Сумма штрафа");
            grid.addColumn("result.place").setHeader("Место");
            grid.setAllRowsVisible(true);
            add(grid);
            setPadding(false);
        }
    }
}
