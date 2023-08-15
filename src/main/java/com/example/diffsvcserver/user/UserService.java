package com.example.diffsvcserver.user;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.voice.ModelVoice;
import com.example.diffsvcserver.voice.ModelVoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelVoiceRepository modelVoiceRepository;
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

    @Transactional
    public void applyModel(Long userId, Long modelId){
        ModelVoice modelVoice = modelVoiceRepository.findById(modelId).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_MODELVOICE_ID));
        userRepository.applyModel(userId,modelVoice.getUrl());
    }

}
