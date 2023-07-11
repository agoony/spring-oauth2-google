package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChatRoomId(Long chatRoomId);
    // Additional custom query methods can be added here if needed
}
