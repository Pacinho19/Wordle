package pl.pacinho.wordle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.pacinho.wordle.model.enums.GameStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;

@Getter
@Builder
@AllArgsConstructor
public class GameDto {

    private String id;
    private GameStatus status;
    private String player;
    private LocalDateTime startTime;
    private int wordLength;
    private int roundCount;
    private LinkedList<AnswerDto> answers;
}
