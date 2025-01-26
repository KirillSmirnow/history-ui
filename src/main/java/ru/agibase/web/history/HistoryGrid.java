package ru.agibase.web.history;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static ru.agibase.web.history.model.OffsetQueryParameters.ofPage;
import static ru.agibase.web.history.model.ParticipationHistoryFilter.bySportsman;

@Slf4j
@UIScope
@Route("")
@RequiredArgsConstructor
public class HistoryGrid extends VerticalLayout {

    private final ParticipationHistoryClient participationHistoryClient;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        var result = participationHistoryClient.search(bySportsman(1), ofPage(0));
        result.getContent().forEach(competition -> {
        });
    }
}
