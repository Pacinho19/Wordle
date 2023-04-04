package pl.pacinho.wordle.config;

public class UIConfig {
    public static final String PREFIX = "/wordle";
    public static final String GAME = PREFIX + "/game";
    public static final String NEW_GAME = GAME + "/new";
    public static final String GAME_PAGE = GAME + "/{gameId}";
    public static final String GAME_BOARD = GAME_PAGE + "/board";
    public static final String GAME_BOARD_RELOAD = GAME_BOARD + "/reload";
}