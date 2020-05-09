package fr.entertainment.gaming.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(value = { "gamer" })
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
    @JoinColumn(name = "idGamer")
    public GameUser gamer;
}
