package az.baku.card.error;

public class DataNotFoundException extends CommonException {

    public DataNotFoundException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
    public DataNotFoundException(String errorMessage){
        super(ErrorCodes.DATA_NOT_FOUND, errorMessage );
    }
}
