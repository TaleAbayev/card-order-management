package az.baku.card.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardOrderDto {

    private Long orderId;
    private boolean submitted;
    private LocalDateTime createDate;

}
