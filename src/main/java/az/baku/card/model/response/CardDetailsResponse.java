package az.baku.card.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetailsResponse {

    private String carNumber;
    private String accountNumber;
}
