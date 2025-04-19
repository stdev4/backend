package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.daos.NewUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User saveUser(NewUserReq req) {
        return userRepository.save(req.toEntity());
    }
}
