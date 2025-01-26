package ru.agibase.web.history;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.agibase.web.history.model.RatingResult;

@Slf4j
@UIScope
@Route("")
@RequiredArgsConstructor
public class HistoryGrid extends VerticalLayout {

    private final ParticipationHistoryClient participationHistoryClient;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var result = participationHistoryClient.search(1L, null, 0, 10);
        result.getContent().forEach(competition -> {
            var grid = new Grid<>(RatingResult.class, false);
            grid.addColumn("name").setHeader("Зачёт");
            grid.addColumn("dog.homeName").setHeader("Собака");
            grid.addColumn("result.timeInSeconds").setHeader("Сумма времени");
            grid.addColumn("result.totalFaults").setHeader("Сумма штрафов");
            grid.setItemDetailsRenderer(new ComponentRenderer<>(CourseResultComponent::new, CourseResultComponent::setItems));
            grid.setItems(competition.getRatings());

            var nameField = new TextField(null, competition.getName(), (String) null);
            nameField.setWidthFull();
            var venueField = new TextField(null, competition.getVenue().getName(), (String) null);
            venueField.setWidthFull();

            add(new VerticalLayout(nameField, venueField, grid));
        });
    }

    private static class CourseResultComponent extends VerticalLayout {
        public void setItems(RatingResult ratingResult) {
        }
    }
}
