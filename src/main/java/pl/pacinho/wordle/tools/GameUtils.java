package pl.pacinho.wordle.tools;

import pl.pacinho.wordle.model.dto.AnswerDto;
import pl.pacinho.wordle.model.entity.memory.Game;
import pl.pacinho.wordle.model.enums.LetterStatus;
import pl.pacinho.wordle.utils.LettersUtils;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GameUtils {

    public static boolean checkAllLettersCorrect(Game game) {
        if (game.getAnswers().isEmpty())
            return false;

        AnswerDto lastAnswer = game.getAnswers().get(game.getAnswers().size() - 1);

        return lastAnswer.letters()
                .stream()
                .allMatch(l -> l.status() == LetterStatus.CORRECT);
    }

    public static LetterStatus checkLetterStatus(char c, String word, Integer index) {
        if (c == word.charAt(index))
            return LetterStatus.CORRECT;
        else if (word.indexOf(c) > -1)
            return LetterStatus.EXISTING;
        return LetterStatus.USED;
    }

    public static TreeMap<Character, LetterStatus> initLettersStatus() {
        return new TreeMap<>(
                LettersUtils.LETTERS.stream()
                        .collect(Collectors.toMap(letter -> letter, letter -> LetterStatus.NONE))
        );
    }
}
