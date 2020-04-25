package fr.entertainment.gaming.repositories;

import fr.entertainment.gaming.entities.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGamer extends JpaRepository<GameUser,Long> {

    GameUser findByMailGamerAndPasswordGamer(String mail,String password);
}
