package pl.pacinho.wordle.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import pl.pacinho.wordle.model.enums.GameStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GameDto {

    private String id;
    private GameStatus status;
    private String player;
    private LocalDateTime startTime;
}
