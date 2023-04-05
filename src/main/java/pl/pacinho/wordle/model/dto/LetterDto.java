package pl.pacinho.wordle.model.dto;

import pl.pacinho.wordle.model.enums.LetterStatus;

public record LetterDto(Character letter, LetterStatus status) {
}
