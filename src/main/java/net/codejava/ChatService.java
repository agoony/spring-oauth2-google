package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.ChatRoom;
import net.codejava.Message;
import net.codejava.repository.MessageRepository;
import net.codejava.repository.ChatRoomRepository;

import java.util.List;

@Service
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public ChatService(ChatRoomRepository chatRoomRepository, MessageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }

    public ChatRoom createChatRoom(Long adoptionId) {
        ChatRoom chatRoom = new ChatRoom(adoptionId);
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoom getChatRoomById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId).orElse(null);
    }

    public List<ChatRoom> getAllChatRooms() {
        return chatRoomRepository.findAll();
    }

    public List<ChatRoom> getChatRoomByAdoptionId(long adoptionId) {
        return chatRoomRepository.findAllByAdoptionId(adoptionId);
    }

    public void deleteChatRoomById(Long chatRoomId) {
        chatRoomRepository.deleteById(chatRoomId);
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessagesByChatRoomId(Long chatRoomId) {
        return messageRepository.findAllByChatRoomId(chatRoomId);
    }
}
