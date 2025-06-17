package br.com.unicuritiba.projetoA3.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.unicuritiba.projetoA3.models.User;
import br.com.unicuritiba.projetoA3.service.UserService;
import br.com.unicuritiba.projetoA3.dto.UserDTO;

@RestController
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (ajuste para produção)
public class UserController {

    @Autowired
    UserService service;
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(service.getAllUser());    
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id){
        return ResponseEntity.ok(service.getUserById(id));    
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(
            @RequestBody UserDTO userDTO){
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setTelefone(userDTO.getTelefone());
        return ResponseEntity.ok(service.saveUser(user));
    }
    
    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable long id) {
         service.removeUser(id);
    }
    
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id,
            @RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(id, user));
    }
}