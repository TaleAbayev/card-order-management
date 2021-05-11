package az.baku.card.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "card_details")
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String accountNumber;

    private LocalDateTime createDate;

    @OneToOne
    @JoinColumn(name = "card_order_id", nullable = false, updatable = false)
    private CardOrder cardOrder;
}
