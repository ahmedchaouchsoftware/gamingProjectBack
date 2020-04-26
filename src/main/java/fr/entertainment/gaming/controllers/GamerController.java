package fr.entertainment.gaming.controllers;

import fr.entertainment.gaming.entities.GameUser;
import fr.entertainment.gaming.repositories.IGamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/gamers")
public class GamerController {

    @Autowired
    private IGamer gamerRepository;

    @GetMapping("/listGamers")
    public ResponseEntity findAllGamers(){
        return ResponseEntity.ok(gamerRepository.findAll());
    }

    @GetMapping("/{idGamer}")
    public ResponseEntity findGamerById(@PathVariable(name="idGamer") Long idGamer){
        if(idGamer == null){
            return ResponseEntity.badRequest().body("Can not Retrieve Gamer with NULL id");
        }
        GameUser gamer = gamerRepository.getOne(idGamer);
        if(gamer == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gamer);
    }

    @PostMapping("/add")
    public ResponseEntity createGamer(@RequestBody GameUser gamer){
        if(gamer == null){
            return ResponseEntity.badRequest().body("Cannot create gamer with empty fields ");
        }
        GameUser createdGamer = gamerRepository.save(gamer);
        return ResponseEntity.ok(createdGamer);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(name="mail") String gamerMail,@RequestParam(name="password") String gamerPassword){
        if(StringUtils.isEmpty(gamerMail) || StringUtils.isEmpty(gamerPassword)){
            return ResponseEntity.badRequest().body("Cannot login with empty mail or password");
        }
        GameUser authenticatedGamer = gamerRepository.findByMailGamerAndPasswordGamer(gamerMail,gamerPassword);
        if(authenticatedGamer == null){
          return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedGamer);
    }
}
