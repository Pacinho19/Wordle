package pl.pacinho.wordle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.wordle.exception.GameNotFoundException;
import pl.pacinho.wordle.model.entity.memory.Game;
import pl.pacinho.wordle.repository.WordRepository;
import pl.pacinho.wordle.utils.RandomUtils;

@RequiredArgsConstructor
@Service
public class GameLogicService {

    private final GameRepository gameRepository;
    private final WordRepository wordRepository;

    public Game findById(String gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException(gameId))
                ;
    }

    public void drawWord(Game game) {
        int number = RandomUtils.randomInt(wordRepository.count());
        game.setWord(wordRepository.findById(number).getName());
    }


}
