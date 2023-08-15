package com.example.diffsvcserver.favorite;

import com.example.diffsvcserver.user.User;
import com.example.diffsvcserver.voice.ModelVoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {

    Optional<Favorite> findByUserAndModelVoice(User user, ModelVoice modelVoice);
    List<Favorite> findByUser(User user);
}
