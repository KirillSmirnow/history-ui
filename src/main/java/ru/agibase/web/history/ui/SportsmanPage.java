package ru.agibase.web.history.ui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.agibase.web.history.ParticipationHistoryClient;
import ru.agibase.web.history.ui.HistoryGrid.Type;

@Slf4j
@UIScope
@Route("/sportsmen")
@RequiredArgsConstructor
public class SportsmanPage extends VerticalLayout implements HasUrlParameter<String> {

    private final ParticipationHistoryClient participationHistoryClient;

    private long sportsmanId;

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        sportsmanId = Long.parseLong(parameter);
        log.info("sportsmanId: {}", sportsmanId);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var historyGrid = new HistoryGrid(participationHistoryClient, Type.SPORTSMAN, sportsmanId, null);
        add(historyGrid);
        setWidthFull();
        setAlignItems(Alignment.CENTER);
    }
}
