package pl.pacinho.wordle.model.dto.mapper;

import pl.pacinho.wordle.model.dto.GameDto;
import pl.pacinho.wordle.model.entity.memory.Game;
import pl.pacinho.wordle.model.enums.GameStatus;
import pl.pacinho.wordle.tools.GameUtils;

public class GameDtoMapper {

    public static GameDto parse(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .startTime(game.getStartTime())
                .player(game.getPlayer().getName())
                .status(game.getStatus())
                .wordLength(game.getWord().length())
                .roundCount(game.getRoundCount())
                .answers(game.getAnswers())
                .resultMessage(
                        getResultMessage(game)
                )
                .build();
    }

    private static String getResultMessage(Game game) {
        if (game.getStatus() != GameStatus.FINISHED)
            return "";

        return GameUtils.checkAllLettersCorrect(game)
                ? "You WIN!"
                : game.getAnswers().size() == game.getRoundCount() ? "You LOSE ! Correct word: " + game.getWord() : "";
    }
}