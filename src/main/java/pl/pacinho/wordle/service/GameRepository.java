package pl.pacinho.wordle.service;

import org.springframework.stereotype.Repository;
import pl.pacinho.wordle.model.dto.GameDto;
import pl.pacinho.wordle.model.dto.mapper.GameDtoMapper;
import pl.pacinho.wordle.model.entity.memory.Game;
import pl.pacinho.wordle.model.enums.GameStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GameRepository {
    private final int ROUND_COUNT = 6;
    private Map<String, Game> gameMap;

    public GameRepository() {
        gameMap = new HashMap<>();
    }

    public Game newGame(String playerName) {
        Game game = new Game(playerName, ROUND_COUNT);
        gameMap.put(game.getId(), game);
        return game;
    }

    public List<GameDto> getAvailableGames(String name) {
        return gameMap.values()
                .stream()
                .filter(game -> game.getStatus() != GameStatus.FINISHED && game.getPlayer().getName().equals(name))
                .map(GameDtoMapper::parse)
                .toList();
    }

    public Optional<Game> findById(String gameId) {
        return Optional.ofNullable(gameMap.get(gameId));
    }
}