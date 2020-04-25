package fr.entertainment.gaming.controllers;

import fr.entertainment.gaming.entities.GameCharacter;
import fr.entertainment.gaming.repositories.IGameCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/gamecharacters")
public class GameCharacterController {

    @Autowired
    private IGameCharacter gameCharacterRepository;

    @GetMapping("/")
    public ResponseEntity findAllGameCharacters(){
        return ResponseEntity.ok(gameCharacterRepository.findAll());
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

    @PostMapping("/")
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
        gameCharacter.setSharedGameCharacter(isShared);
        return ResponseEntity.ok(gameCharacterRepository.save(gameCharacter));
    }
}
