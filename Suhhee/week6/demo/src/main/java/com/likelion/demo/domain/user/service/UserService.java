package com.likelion.demo.domain.user.service;

import com.likelion.demo.domain.user.entity.User;
import com.likelion.demo.domain.user.exception.UserAlreadyExistException;
import com.likelion.demo.domain.user.repository.UserRepository;
import com.likelion.demo.domain.user.web.dto.UserSaveReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(UserSaveReq req){
        userRepository.findByUsername(req.getUsername())
                .ifPresent(user -> {
                    throw  new UserAlreadyExistException();
                });

        User user = User.builder()
                .username(req.getUsername())
                .gender(req.getGender())
                .build();

        userRepository.save(user);
    }
}
