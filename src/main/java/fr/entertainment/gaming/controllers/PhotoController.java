package fr.entertainment.gaming.controllers;

import fr.entertainment.gaming.entities.GameCharacter;
import fr.entertainment.gaming.repositories.IGameCharacter;
import fr.entertainment.gaming.repositories.IGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/v1/photos")
public class PhotoController {

    @Autowired
    private IGameCharacter gameCharacterRespository;
    @Autowired
    private IGamer gamerRepository;
    @GetMapping("/character/{idGameCharacter}")
    public ResponseEntity photoGameCharacter(@PathVariable Long idGameCharacter){
        if(idGameCharacter == null){
            return ResponseEntity.badRequest().body("Can not get Character photo with null ID");
        }

        GameCharacter gameCharacter = gameCharacterRespository.getOne(idGameCharacter);
        if(gameCharacter == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .contentType(MediaType.IMAGE_JPEG)
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(new ByteArrayInputStream(gameCharacter.getPhotoGameCharacter())));

    }
}
