package com.sayan.linkedin.ConnectionService.services;

import com.sayan.linkedin.ConnectionService.entities.Person;
import com.sayan.linkedin.ConnectionService.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public List<Person> getFirstDegreeConnectionOfUser(Long userid){
        log.info("getting first degree connection of user with userId {}",userid);
        return personRepository.getFirstDegreeConnections(userid);
    }
}
