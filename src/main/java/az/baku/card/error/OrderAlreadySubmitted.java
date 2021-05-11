package az.baku.card.error;

public class OrderAlreadySubmitted extends CommonException {

    public OrderAlreadySubmitted(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public OrderAlreadySubmitted( String errorMessage) {
        super(ErrorCodes.ORDER_SUBMITTED, errorMessage);
    }
}
