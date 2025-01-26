package ru.agibase.web.history;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.agibase.web.history.model.CompetitionResult;
import ru.agibase.web.history.model.OffsetQueryResult;

@FeignClient(name = "participation-history", url = "https://dev.agibase.ru/api")
public interface ParticipationHistoryClient {

    @GetMapping("/participations/history")
    OffsetQueryResult<CompetitionResult> search(
            @RequestParam Long sportsmanId,
            @RequestParam Long dogId,
            @RequestParam int offset,
            @RequestParam int limit
    );
}
