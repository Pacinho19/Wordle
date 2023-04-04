package pl.pacinho.wordle.model.entity.memory;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.wordle.model.dto.AnswerDto;
import pl.pacinho.wordle.model.enums.GameStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.UUID;

@Getter
public class Game {

    private String id;
    @Setter
    private GameStatus status;
    private Player player;
    private LocalDateTime startTime;
    @Setter
    private String word;
    private int roundCount;
    private LinkedList<AnswerDto> answers;


    public Game(String player1, int roundCount) {
        this.player = new Player(player1, 1);
        this.roundCount = roundCount;
        this.id = UUID.randomUUID().toString();
        this.status = GameStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
        this.answers = new LinkedList<>();
    }

    public void addAnswer(AnswerDto answerDto) {
        this.answers.add(answerDto);
    }
}
