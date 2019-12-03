package com.thebloez.demo.consumer;

import com.thebloez.demo.model.Person;
import com.thebloez.demo.repository.RepositoryPerson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
@EnableJms
@Slf4j
public class MessageConsumer {

    @Autowired
    RepositoryPerson repositoryPerson;

    @JmsListener(destination = "test.queue")
    public void listener(String message){
        log.info("Message received : {}", message);
    }

    @JmsListener(destination = "mysql-create")
    public void listenerSave(String message){
        String name = StringUtils.substringBefore(message,"|");
        BigDecimal salary = new BigDecimal(StringUtils.substringAfter(message,"|"));
        Person person = new Person();
        person.setName(name);
        person.setSalary(salary);
        repositoryPerson.save(person);
        log.info("Person with name : {} has been created", name);
    }
}
