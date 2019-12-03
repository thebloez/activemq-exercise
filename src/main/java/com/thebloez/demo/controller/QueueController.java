package com.thebloez.demo.controller;

import com.thebloez.demo.model.Person;
import com.thebloez.demo.model.request.ReqPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@RestController
@RequestMapping("/api")
public class QueueController {

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("message/{message}")
    public ResponseEntity<String> publish(@PathVariable("message")final String message){
        jmsTemplate.convertAndSend(queue, message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> save(@RequestBody Person person){
        jmsTemplate.convertAndSend("mysql-create",person.getName() + "|" + person.getSalary());
        return new ResponseEntity<>(person.getName() + "|" + person.getSalary().toString(), HttpStatus.OK);
    }
}
