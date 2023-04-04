package pl.pacinho.wordle.initializer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.pacinho.wordle.model.entity.Word;
import pl.pacinho.wordle.repository.WordRepository;
import pl.pacinho.wordle.utils.FileUtils;

@RequiredArgsConstructor
@Component
public class StartApp {

    private final WordRepository wordRepository;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        initWords();
    }

    private void initWords() {
        if (wordRepository.count() > 0) return;

        FileUtils.readTxt(FileUtils.getFileFromResource("dictionary/Words.txt"))
                .stream()
                .distinct()
                .forEach(name -> wordRepository.save(
                        new Word(name)
                ));
    }
}