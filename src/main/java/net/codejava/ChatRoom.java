package net.codejava;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "adoption_id")
    private Long adoptionId;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Message> messages;

    // Otros atributos y métodos

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private User candidate;

    // Constructor vacío (obligatorio para JPA)
    public ChatRoom() {
    }

    // Constructor con parámetros
    public ChatRoom(Long adoptionId ) {
        this.adoptionId = adoptionId;
        
    }

    // Getters y Setters

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdoptionId() {
        return adoptionId;
    }

    public void setAdoptionId(Long adoptionId) {
        this.adoptionId = adoptionId;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
