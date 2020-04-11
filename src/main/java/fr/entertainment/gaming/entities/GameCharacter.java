package fr.entertainment.gaming.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCharacter implements Serializable {

    @Id
    @GeneratedValue
    private Long idGameCharacter;

    private String nameGameCharacter;

    private String categoryGameCharacter;

    private String strengthGameCharacter;

    @Lob
    private byte[] photoGameCharacter;

    private boolean sharedGameCharacter;

    @ManyToOne
    @JoinColumn(name="idGamer")
    private GameUser gamer;
}
