package pl.coderslab.sportsbetting.service;

import pl.coderslab.sportsbetting.entity.Result;

import java.util.List;

public interface ResultService {

    void saveResult(Result result);

    List<Result> findResultsWithGameId(Long id);

    Result findByGameIdAndHorseId(Long gameId, Long horseId);
}
