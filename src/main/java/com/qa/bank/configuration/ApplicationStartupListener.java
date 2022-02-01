package com.qa.bank.configuration;

import com.qa.bank.data.entity.User;
import com.qa.bank.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

/**
 * Populates database on startup in dev mode
 * @author Vinay
 */
@Profile("dev")
@Configuration
public class ApplicationStartupListener implements ApplicationListener<ApplicationReadyEvent> {

    private UserRepository userRepository;

    @Autowired
    public ApplicationStartupListener(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userRepository.saveAll(List.of(
                new User("neo1", "pass1", "Neo", "Chan", 23),
                new User("jackr", "pass2", "Jack", "Reacher", 25),
                new User("lilya", "pass3", "Lily", "Adams", 34)
        ));
    }
}
