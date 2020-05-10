package fr.entertainment.gaming.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameUser implements Serializable {

    @Id
    @GeneratedValue
    private Long idGamer;

    private String firstNameGamer;

    private String lastNameGamer;

    private String mailGamer;

    private String passwordGamer;

    @Lob
    private byte[] photoGamer;

    @OneToMany(mappedBy = "gamer",fetch = FetchType.EAGER)
    private List<GameCharacter> charactersOfGamer;
}
