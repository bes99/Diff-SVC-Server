package com.example.diffsvcserver.favorite;

import com.example.diffsvcserver.error.InvalidInputException;
import com.example.diffsvcserver.error.MessageUtils;
import com.example.diffsvcserver.user.User;
import com.example.diffsvcserver.user.UserRepository;
import com.example.diffsvcserver.voice.ModelVoice;
import com.example.diffsvcserver.voice.ModelVoiceRepository;
import com.example.diffsvcserver.voice.ResponseModelVoice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ModelVoiceRepository modelVoiceRepository;
    @Transactional
    public void addFavorite(Long userId, Long modelVoiceId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        ModelVoice modelVoice = modelVoiceRepository.findById(modelVoiceId).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_MODELVOICE_ID));

        Optional<Favorite> optionalFavorite = favoriteRepository.findByUserAndModelVoice(user, modelVoice);
        if (!optionalFavorite.isPresent()) {
            Favorite favorite = Favorite.builder()
                    .user(user)
                    .modelVoice(modelVoice)
                    .status(true)
                    .build();
            favoriteRepository.save(favorite);
        } else {
            favoriteRepository.delete(optionalFavorite.get());
        }
    }
    @Transactional
    public List<ResponseModelVoice> viewFavoriteModels(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() ->
                new InvalidInputException(MessageUtils.INVALID_USER_ID));
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        List<ModelVoice> modelVoices = favorites.stream()
                .map(Favorite::getModelVoice)
                .collect(Collectors.toList());
        return modelEntityToResponseVO(modelVoices);
    }
    public List<ResponseModelVoice> modelEntityToResponseVO(List<ModelVoice> modelVoices){
        List<ResponseModelVoice> responseModelVoices = new ArrayList<>();
        modelVoices.forEach(v->{
            responseModelVoices.add(ResponseModelVoice.builder()
                    .image(v.getImage())
                    .name(v.getName())
                    .description(v.getDescription())
                    .build());
        });
        return responseModelVoices;
    }
}
