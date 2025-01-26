package ru.agibase.web.history;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.agibase.web.history.model.CourseResult;
import ru.agibase.web.history.model.RatingResult;

@Slf4j
@UIScope
@Route("")
@RequiredArgsConstructor
public class HistoryGrid extends VerticalLayout {

    private final ParticipationHistoryClient participationHistoryClient;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        setWidth("1400px");

        UI.getCurrent().getPage().fetchCurrentURL(url -> {
            QueryParameters.fromString(url.getQuery()).getSingleParameter("sportsmanId").ifPresent(sportsmanId -> {
                refresh(Long.valueOf(sportsmanId));
            });
        });
    }

    private void refresh(Long sportsmanId) {
        var result = participationHistoryClient.search(sportsmanId, null, 0, 10);
        result.getContent().forEach(competition -> {
            var grid = new Grid<>(RatingResult.class, false);
            grid.addColumn("name").setHeader("Зачёт");
            grid.addColumn("dog.homeName").setHeader("Собака");
            grid.addColumn("result.timeInSeconds").setHeader("Сумма времени");
            grid.addColumn("result.totalFaults").setHeader("Сумма штрафов");
            grid.addColumn("result.place").setHeader("Место");
            grid.setItemDetailsRenderer(new ComponentRenderer<>(CourseResultComponent::new, CourseResultComponent::setItems));
            grid.setHeight("400px");
            grid.setItems(competition.getRatings());

            var nameField = new TextField(null, competition.getName(), (String) null);
            nameField.setWidthFull();
            nameField.setReadOnly(true);

            var dateField = new TextField(null, competition.getCompetitionPeriod(), (String) null);
            dateField.setWidthFull();
            dateField.setReadOnly(true);
            var venueField = new TextField(null, competition.getVenue().getName(), (String) null);
            venueField.setWidthFull();
            venueField.setReadOnly(true);
            var secondLayout = new HorizontalLayout(dateField, venueField);

            var layout = new VerticalLayout(nameField, secondLayout, grid);
            layout.setSpacing(false);
            add(layout);
        });
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
            grid.setHeight("200px");
            add(grid);
        }
    }
}
