package com.sayan.linkedin.ConnectionService.controllers;

import com.sayan.linkedin.ConnectionService.entities.Person;
import com.sayan.linkedin.ConnectionService.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {
    private final PersonService personService;

    @GetMapping("/{userId}/first-degree-connections")
    public ResponseEntity<List<Person>> getFirstDegreeConnection(@PathVariable Long userId){
        var personList = personService.getFirstDegreeConnectionOfUser(userId);
        return ResponseEntity.ok(personList);
    }
}
