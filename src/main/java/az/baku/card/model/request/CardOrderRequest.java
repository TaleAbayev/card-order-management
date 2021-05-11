package az.baku.card.model.request;

import az.baku.card.model.type.CardType;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CardOrderRequest {

    @NotNull
    private CardType cardType;

    @NotEmpty
    private String cardHolderName;

    @NotNull
    private Integer cardPeriod;

    @NotNull
    private boolean urgent;

    @NotEmpty
    private String codeword;
}
