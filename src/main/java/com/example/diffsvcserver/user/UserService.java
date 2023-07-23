package com.example.diffsvcserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public String join(UserFormDTO userFormDTO){
        User user = User.builder()
                .userId(userFormDTO.getUserId())
                .userPwd(userFormDTO.getUserPwd())
                .sex(userFormDTO.getSex())
                .email(userFormDTO.getEmail())
                .build();

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }


}
