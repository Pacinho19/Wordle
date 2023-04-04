package pl.pacinho.wordle.model.dto.mapper;

import pl.pacinho.wordle.model.dto.GameDto;
import pl.pacinho.wordle.model.entity.memory.Game;

public class GameDtoMapper {

    public static GameDto parse(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .startTime(game.getStartTime())
                .player(game.getPlayer().getName())
                .status(game.getStatus())
                .build();
    }
}