package com.likelion.demo.domain.user.web.controller;

import com.likelion.demo.domain.user.entity.User;
import com.likelion.demo.domain.user.service.UserService;
import com.likelion.demo.domain.user.web.dto.UserSaveReq;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    // 회원가입
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> saveUser(
            @Valid @RequestBody UserSaveReq req
    ){
        userService.save(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.empty());
    }
}
