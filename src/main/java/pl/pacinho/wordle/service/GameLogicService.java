package pl.pacinho.wordle.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pacinho.wordle.exception.GameNotFoundException;
import pl.pacinho.wordle.model.dto.AnswerDto;
import pl.pacinho.wordle.model.dto.AnswerRequestDto;
import pl.pacinho.wordle.model.dto.LetterDto;
import pl.pacinho.wordle.model.entity.memory.Game;
import pl.pacinho.wordle.model.enums.GameStatus;
import pl.pacinho.wordle.repository.WordRepository;
import pl.pacinho.wordle.tools.GameUtils;
import pl.pacinho.wordle.utils.RandomUtils;

import java.util.List;
import java.util.stream.IntStream;

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
        String word = wordRepository.findById(number).getName();
        game.setWord(word);

        System.out.println("==========" + word + "=========");
    }


    public boolean checkAnswer(Game game, AnswerRequestDto answerRequestDto) {
        boolean exists = wordRepository.existsByName(answerRequestDto.word());
        if (!exists)
            return true; //error

        game.addAnswer(
                new AnswerDto(
                        checkLetters(game.getWord(), answerRequestDto.word())
                )
        );
        return false;//error
    }

    private List<LetterDto> checkLetters(String word, String answerWord) {
        return IntStream.range(0, answerWord.length())
                .boxed()
                .map(i -> checkLetter(i, word, answerWord))
                .toList();
    }

    private LetterDto checkLetter(Integer index, String word, String answerWord) {
        char c = answerWord.charAt(index);
        return new LetterDto(c, c == word.charAt(index), word.indexOf(c) > -1);
    }

    public void checkFinish(Game game) {
        if (GameUtils.checkAllLettersCorrect(game) || game.getRoundCount()==game.getAnswers().size())
            game.setStatus(GameStatus.FINISHED);
    }


}
