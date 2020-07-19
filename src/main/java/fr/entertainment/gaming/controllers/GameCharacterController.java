package fr.entertainment.gaming.controllers;

import fr.entertainment.gaming.entities.GameCharacter;
import fr.entertainment.gaming.entities.GameUser;
import fr.entertainment.gaming.repositories.IGameCharacter;
import fr.entertainment.gaming.repositories.IGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/v1/gamecharacters")
public class GameCharacterController {

    @Autowired
    private IGameCharacter gameCharacterRepository;
    @Autowired
    private IGamer gamerRepository;

    @GetMapping("/list")
    public ResponseEntity findAllGameCharacters(){
        return ResponseEntity.ok(gameCharacterRepository.findAll());
    }

    @GetMapping("/allCharacters/{idGamer}")
    public ResponseEntity findAllGameCharactersByGamer(@PathVariable Long idGamer){
        if(idGamer == null){
            return ResponseEntity.badRequest().body("Can not find Game Characters with null Gamer");
        }

        GameUser gamer = gamerRepository.getOne(idGamer);

        if(gamer == null) {
            return ResponseEntity.notFound().build();
        }
        List<GameCharacter> gamerCharacters = gameCharacterRepository.findByGamer(gamer);
        List<GameCharacter> sharedCharacters = gameCharacterRepository.findBySharedGameCharacterAndGamerNotLike(true,gamer);
        gamerCharacters.forEach(character -> character.setIdOwner(idGamer));
        sharedCharacters.forEach(character -> character.setIdOwner(-1L));
        gamerCharacters.addAll(sharedCharacters);
        return ResponseEntity.ok(gamerCharacters);
    }

    @GetMapping("/{idGameCharacter}")
    public ResponseEntity findGameCharacterById(@PathVariable(name="idGameCharacter") Long idGameCharacter){
        if(idGameCharacter == null){
            return ResponseEntity.badRequest().body("cannot find game character with null ID");
        }
        GameCharacter gameCharacter = gameCharacterRepository.getOne(idGameCharacter);
        if(gameCharacter == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gameCharacter);
    }

    @PostMapping("/add")
    public ResponseEntity createGameCharacter(@RequestBody GameCharacter gameCharacter){
        if(gameCharacter == null){
            return ResponseEntity.badRequest().body("Cannot create game character with empty fields");
        }
        return ResponseEntity.ok(gameCharacterRepository.save(gameCharacter));
    }

    @DeleteMapping("/{idGameCharacter}")
    public ResponseEntity deleteGameCharacter(@PathVariable(name="idGameCharacter") Long idCharacterGame){
        if(idCharacterGame == null){
            return ResponseEntity.badRequest().body("Cannot remove null ID");
        }
        GameCharacter gameCharacter = gameCharacterRepository.getOne(idCharacterGame);
        if(gameCharacter == null){
            return ResponseEntity.notFound().build();
        }
        gameCharacterRepository.delete(gameCharacter);
        return ResponseEntity.ok("Character removed with success");
    }

    @GetMapping("/share/{idGameCharacter}/{isShared}")
    public ResponseEntity shareGameCharacter(@PathVariable(name="idGameCharacter") Long idCharacterGame,@PathVariable(name="isShared")boolean isShared){
        if(idCharacterGame == null){
            return ResponseEntity.badRequest().body("Cannot remove null ID");
        }
        GameCharacter gameCharacter = gameCharacterRepository.getOne(idCharacterGame);
        if(gameCharacter == null){
            return ResponseEntity.notFound().build();
        }
        gameCharacter.setSharedGameCharacter(!isShared);
        return ResponseEntity.ok(gameCharacterRepository.save(gameCharacter));
    }
}
