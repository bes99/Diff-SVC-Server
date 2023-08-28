package com.example.diffsvcserver.user;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.voice.ModelVoice;
import com.example.diffsvcserver.voice.ModelVoiceRepository;
import com.example.diffsvcserver.voice.ResponseModelVoice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelVoiceRepository modelVoiceRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Transactional
    public void join(UserFormDTO userFormDTO){
        if(userRepository.existsByUserId(userFormDTO.getUserId())){
            throw new InvalidInputException(MessageUtils.DUPLICATE_USER_ID);
        }
        String encryptedPassword = passwordEncoder.encode(userFormDTO.getUserPwd());

        User user = User.builder()
                .userId(userFormDTO.getUserId())
                .userPwd(encryptedPassword)
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
        userRepository.applyModel(userId,modelId);
    }

    public ResponseModelVoice viewAppliedModel(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        ModelVoice modelVoice = modelVoiceRepository.findById(user.getAppliedModel()).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_MODELVOICE_ID));

        ResponseModelVoice responseModelVoice = ResponseModelVoice.builder()
                .id(modelVoice.getId())
                .name(modelVoice.getName())
                .description(modelVoice.getDescription())
                .image(modelVoice.getImage())
                .build();
        return responseModelVoice;
    }
}
