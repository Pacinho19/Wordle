package pl.pacinho.wordle.model.entity.memory;

import lombok.Getter;
import lombok.Setter;
import pl.pacinho.wordle.model.entity.Word;
import pl.pacinho.wordle.model.enums.GameStatus;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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

    public Game(String player1) {
        this.player = new Player(player1, 1);
        this.id = UUID.randomUUID().toString();
        this.status = GameStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

}
