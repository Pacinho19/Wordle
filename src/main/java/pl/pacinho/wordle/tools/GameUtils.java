package pl.pacinho.wordle.tools;

import pl.pacinho.wordle.model.dto.AnswerDto;
import pl.pacinho.wordle.model.dto.LetterDto;
import pl.pacinho.wordle.model.entity.memory.Game;

public class GameUtils {

    public static boolean checkAllLettersCorrect(Game game) {
        if (game.getAnswers().isEmpty())
            return false;

        AnswerDto lastAnswer = game.getAnswers().get(game.getAnswers().size() - 1);

        return lastAnswer.letters()
                .stream()
                .allMatch(LetterDto::correctPosition);
    }
}
