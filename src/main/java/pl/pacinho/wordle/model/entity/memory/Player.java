package pl.pacinho.wordle.model.entity.memory;

import lombok.Getter;

@Getter
public class Player {
    private final String name;

    public Player(String name, int index) {
        this.name = name;
    }


}