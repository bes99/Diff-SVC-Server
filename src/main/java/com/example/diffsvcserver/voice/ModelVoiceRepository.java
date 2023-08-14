package com.example.diffsvcserver.voice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelVoiceRepository extends JpaRepository<ModelVoice,Long> {
    @Query(value = "select m from ModelVoice m where m.tag = :tag")
    List<ModelVoice> findByTag(String tag);
}
