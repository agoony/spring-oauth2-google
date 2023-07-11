package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.codejava.ChatRoom;


@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // Additional custom query methods can be added here if needed
    List<ChatRoom> findAllByAdoptionId(Long AdoptionId);

}
