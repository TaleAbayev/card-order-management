package az.baku.card.service;

import az.baku.card.error.DataNotFoundException;
import az.baku.card.model.entity.User;
import az.baku.card.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findCurrentUser(){

        // TODO:  //get user from security?

        return userRepository.findById(1L)
                .orElseThrow(()->  new DataNotFoundException("User not found"));
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()->  new DataNotFoundException("User not found"));
    }

}
