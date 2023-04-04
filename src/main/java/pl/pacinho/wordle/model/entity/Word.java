package pl.pacinho.wordle.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
public class Word {

    @Id
    @GenericGenerator(name = "wordIdGen", strategy = "increment")
    @GeneratedValue(generator = "wordIdGen")
    private Long id;
    private String uuid;
    private String name;

    public Word(String name) {
        this.name = name.toUpperCase();
        this.uuid = UUID.randomUUID().toString();
    }

}
