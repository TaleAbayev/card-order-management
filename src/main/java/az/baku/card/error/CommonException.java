package az.baku.card.error;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{


    private String errorCode;
    private String errorMessage;

    public CommonException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
