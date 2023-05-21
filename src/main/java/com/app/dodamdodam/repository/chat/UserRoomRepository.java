package com.app.dodamdodam.repository.chat;

import com.app.dodamdodam.entity.chat.UserRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRoomRepository extends CrudRepository<UserRoom, Long> {

    public Optional<UserRoom> findByUserId(Long userId);
}