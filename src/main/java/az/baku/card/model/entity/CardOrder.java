package az.baku.card.model.entity;

import az.baku.card.model.type.CardOrderStatus;
import az.baku.card.model.type.CardType;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "card_orders")
public class CardOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private String cardHolderName;

    private Integer cardPeriod;

    private boolean urgent;

    private String codeword;

    private boolean submitted;

    private LocalDateTime updateDate;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;


}
