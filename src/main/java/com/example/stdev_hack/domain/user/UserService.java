package com.example.stdev_hack.domain.user;

import com.example.stdev_hack.daos.UserReq;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        );
    }

    @Transactional
    public User saveUser(UserReq req) {
        return userRepository.save(req.toEntity());
    }

    public boolean isNicknameExist(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    public boolean isUsernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Transactional
    public User updateUser(Long userId, UserReq req) {
        User user = findUserById(userId);
        if (req.getNickname() != null) {
            user.setNickname(req.getNickname());
        }
        if (req.getAge() != 0) {
            user.setAge(req.getAge());
        }
        if (req.getUsername() != null) {
            user.setUsername(req.getUsername());
        }
        if (req.getPassword() != null) {
            user.setPassword(req.getPassword());
        }
        if (req.getInterest() != null) {
            user.setInterest(req.getInterest());
        }
        return user;
    }
}
