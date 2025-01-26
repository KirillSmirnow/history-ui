package ru.agibase.web.history;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.agibase.web.history.model.CompetitionResult;
import ru.agibase.web.history.model.OffsetQueryParameters;
import ru.agibase.web.history.model.OffsetQueryResult;
import ru.agibase.web.history.model.ParticipationHistoryFilter;

@FeignClient(name = "participation-history", url = "https://dev.agibase.ru/api")
public interface ParticipationHistoryClient {

    @GetMapping("/participations/history")
    OffsetQueryResult<CompetitionResult> search(ParticipationHistoryFilter filter, OffsetQueryParameters parameters);
}
