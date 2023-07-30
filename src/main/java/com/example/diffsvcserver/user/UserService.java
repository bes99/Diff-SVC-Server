package com.example.diffsvcserver.user;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public void join(UserFormDTO userFormDTO){
        if(userRepository.existsByUserId(userFormDTO.getUserId())){
            throw new InvalidInputException(MessageUtils.DUPLICATE_USER_ID);
        }
        User user = User.builder()
                .userId(userFormDTO.getUserId())
                .userPwd(userFormDTO.getUserPwd())
                .sex(userFormDTO.getSex())
                .email(userFormDTO.getEmail())
                .build();

        userRepository.save(user);
    }
    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        return user;
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        userRepository.delete(user);
    }

}
