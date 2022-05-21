package org.example.controller;


import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.spec.SpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repo;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    @ResponseBody
    public List<User> search(@RequestParam(value = "search") String search) {
        Specification<User> spec = new SpecificationsBuilder<User>().buildSpecification(search);
        return repo.findAll(spec);
    }
}