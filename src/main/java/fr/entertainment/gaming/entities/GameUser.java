package fr.entertainment.gaming.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy = "gamer")
    private List<GameCharacter> charactersOfGamer;
}
