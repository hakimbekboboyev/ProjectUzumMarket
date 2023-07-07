package com.example.projectuzummarket.controller;

import com.example.projectuzummarket.dto.RegisterDTO;
import com.example.projectuzummarket.entity.RegisterDB;
import com.example.projectuzummarket.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegisterController {
    @Autowired
    RegisterRepository registerRepository;


    @PostMapping("/register")
    public ResponseEntity saveUser(@RequestBody RegisterDTO registerDTO){
        Optional<RegisterDB> isEmptyRegister = registerRepository.findByUsername(registerDTO.getUsername());
        if(!isEmptyRegister.isPresent()){
            RegisterDB registerDB = new RegisterDB();
            registerDB.setFullName(registerDTO.getFullName());
            registerDB.setEmail(registerDTO.getEmail());
            registerDB.setUsername(registerDTO.getUsername());
            registerDB.setPassword(registerDTO.getPassword());
            registerDB.setActive(true);
            registerRepository.save(registerDB);
            return ResponseEntity.ok("Successful");
        }
        else {
            return ResponseEntity.badRequest().body("Username is busy");
        }
    }

    @GetMapping("/register")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(registerRepository.findAll());
    }

    @GetMapping("/register/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){
        Optional<RegisterDB> byId = registerRepository.findById(id);
        if(byId.isPresent()){
            return ResponseEntity.ok(byId.get());
        }else {
            return ResponseEntity.badRequest().body("User Not Found");
        }
    }

    @PutMapping("/register/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,
                                     @RequestBody RegisterDTO registerDTO){
        Optional<RegisterDB> byId = registerRepository.findById(id);
        Optional<RegisterDB> byUsername = registerRepository.findByUsername(registerDTO.getUsername());



        if(byId.isPresent()){
            RegisterDB registerDBById = byId.get();

            if(!byUsername.isPresent()) {


                RegisterDB registerDB = byId.get();
                registerDB.setFullName(registerDTO.getFullName());
                registerDB.setEmail(registerDTO.getEmail());
                registerDB.setUsername(registerDTO.getUsername());
                registerDB.setPassword(registerDTO.getPassword());
                registerRepository.save(registerDB);
                return ResponseEntity.ok("Update successful!");
            } else if (byUsername.isPresent()) {
                RegisterDB registerDBByUsername = byUsername.get();
                if(registerDBById.getId().equals(registerDBByUsername.getId())) {
                    RegisterDB registerDB = byId.get();
                    registerDB.setFullName(registerDTO.getFullName());
                    registerDB.setUsername(registerDTO.getUsername());
                    registerDB.setEmail(registerDTO.getEmail());
                    registerDB.setPassword(registerDTO.getPassword());
                    registerRepository.save(registerDB);
                    return ResponseEntity.ok("Update successful!");
                }
            } else {
                return ResponseEntity.badRequest().body("Username Already Use");
            }


        }
        return ResponseEntity.badRequest().body("User Not Found!");
    }

    @DeleteMapping("/register/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        Optional<RegisterDB> byId = registerRepository.findById(id);
        if(byId.isPresent()){
            RegisterDB registerDB = byId.get();
            registerDB.setActive(false);

            registerRepository.save(registerDB);
            return ResponseEntity.ok("Delete User");
        }
        return ResponseEntity.badRequest().body("User Not Found");
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity restoreUser(@PathVariable Integer id){
        Optional<RegisterDB> byIdRestoreUser = registerRepository.findByIdRestoreUser(id);
        if(byIdRestoreUser.isPresent()){
            RegisterDB registerDB = byIdRestoreUser.get();
            registerDB.setActive(true);
            registerRepository.save(registerDB);
            return ResponseEntity.ok("Restore User");
        }
        return ResponseEntity.notFound().build();
    }

}
