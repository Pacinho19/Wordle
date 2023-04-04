package pl.pacinho.wordle.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.pacinho.wordle.config.UIConfig;
import pl.pacinho.wordle.model.dto.GameDto;
import pl.pacinho.wordle.service.GameService;

@RequiredArgsConstructor
@Controller
public class GameController {

    private final GameService gameService;

    @GetMapping(UIConfig.PREFIX)
    public String gameHome(Model model) {
        return "home";
    }

    @PostMapping(UIConfig.GAME)
    public String availableGames(Model model, Authentication authentication) {
        if (authentication != null)
            model.addAttribute("games", gameService.getAvailableGames(authentication.getName()));
        return "fragments/available-games :: availableGamesFrag";
    }

    @PostMapping(UIConfig.NEW_GAME)
    public String newGame(Model model, Authentication authentication) {
        try {
            return "redirect:" + UIConfig.GAME + "/" + gameService.newGame(authentication.getName());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return gameHome(model);
        }
    }

    @GetMapping(UIConfig.GAME_PAGE)
    public String gamePage(@PathVariable(value = "gameId") String gameId,
                           Model model,
                           Authentication authentication) {
        try {
            GameDto game = gameService.findDtoById(gameId);

            if (!game.getPlayer().equals(authentication.getName()))
                throw new IllegalStateException("Cannot play game " + gameId);

            model.addAttribute("game", game);
            return "game";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return gameHome(model);
        }
    }

    @GetMapping(UIConfig.GAME_BOARD_RELOAD)
    public String reloadBoard(Model model,
                              @PathVariable(value = "gameId") String gameId) {
        model.addAttribute("game", gameService.findDtoById(gameId));
        return "fragments/board :: boardFrag";
    }

}