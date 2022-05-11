package com.example.demo.taco.Controller;

import com.example.demo.taco.repository.TacoRepository;
import com.example.demo.taco.exception.ResourceNotFoundException;
import com.example.demo.taco.model.TacoEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/tacos")
public class TacoController {

    @Autowired
    private TacoRepository tacoRepository;

    // get all tacos
    @GetMapping
    public List<TacoEntity> getAllTacos() {
        return this.tacoRepository.findAll();
    }

    // get taco by id
    @GetMapping("/{id}")
    public TacoEntity getTacoById(@PathVariable(value = "id") long id) {
        return this.tacoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
    }

    // create one taco
    @PostMapping
    public TacoEntity createUser(@RequestBody TacoEntity taco) {
        return this.tacoRepository.save(taco);
    }

    // edit one taco
    @PutMapping("/{id}")
    public TacoEntity updateUser(@RequestBody TacoEntity taco, @PathVariable("id") long id) {
        TacoEntity existingTaco = this.tacoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taco not found with id :" + id));
        existingTaco.setName(taco.getName());
        existingTaco.setType(taco.getType());
        return this.tacoRepository.save(existingTaco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TacoEntity> deleteUser(@PathVariable("id") long id) {
        TacoEntity existingUser = this.tacoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + id));
        this.tacoRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
