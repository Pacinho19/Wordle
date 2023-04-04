package pl.pacinho.wordle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pacinho.wordle.model.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findById(long number);
}
