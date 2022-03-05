package com.example.letscodeinit.repos;

import com.example.letscodeinit.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message,Long> {
    List<Message> findByTag(String tag); // ищем по тегу , правила описаны в документации

}
