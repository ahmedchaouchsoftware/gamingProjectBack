package fr.entertainment.gaming.repositories;

import fr.entertainment.gaming.entities.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameCharacter extends JpaRepository<GameCharacter,Long> {
}
