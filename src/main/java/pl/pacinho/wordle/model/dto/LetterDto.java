package pl.pacinho.wordle.model.dto;

public record LetterDto(Character letter, boolean correctPosition, boolean existsInWord) {
}
