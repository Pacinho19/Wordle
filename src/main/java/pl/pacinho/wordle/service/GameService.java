package pl.pacinho.wordle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pl.pacinho.wordle.model.dto.AnswerRequestDto;
import pl.pacinho.wordle.model.dto.GameDto;
import pl.pacinho.wordle.model.dto.mapper.GameDtoMapper;
import pl.pacinho.wordle.model.entity.memory.Game;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameLogicService gameLogicService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public List<GameDto> getAvailableGames(String playerName) {
        return gameRepository.getAvailableGames(playerName);
    }

    public String newGame(String playerName) {
        List<GameDto> activeGames = getAvailableGames(playerName);
        if (activeGames.size() >= 10)
            throw new IllegalStateException("Cannot create new Game! Active game count : " + activeGames.size());

        Game game = gameRepository.newGame(playerName);
        gameLogicService.drawWord(game);
        return game.getId();
    }

    public GameDto findDtoById(String gameId) {
        return GameDtoMapper.parse(gameLogicService.findById(gameId));
    }

    public void answer(String gameId, AnswerRequestDto answerRequestDto) {
        Game game = gameLogicService.findById(gameId);
        gameLogicService.checkAnswer(game, answerRequestDto);
        simpMessagingTemplate.convertAndSend( "/reload-board/" + game.getId(), true);
    }
}
