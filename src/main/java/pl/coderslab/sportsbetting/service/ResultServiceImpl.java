package pl.coderslab.sportsbetting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sportsbetting.entity.Result;
import pl.coderslab.sportsbetting.repository.ResultRepository;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    @Override
    public List<Result> findResultsWithGameId(Long id) {
        return resultRepository.findAllByGame_Id(id);
    }

    @Override
    public Result findByGameIdAndHorseId(Long gameId, Long horseId) {
        return resultRepository.findByGame_IdAndHorse_Id(gameId,horseId);
    }


}
