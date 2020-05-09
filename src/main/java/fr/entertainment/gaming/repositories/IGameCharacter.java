package fr.entertainment.gaming.repositories;

import fr.entertainment.gaming.entities.GameCharacter;
import fr.entertainment.gaming.entities.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGameCharacter extends JpaRepository<GameCharacter,Long> {

    List<GameCharacter> findByGamerOrSharedGameCharacter(GameUser gamer, boolean shared);
}
