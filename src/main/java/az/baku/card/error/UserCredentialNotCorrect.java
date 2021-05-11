package az.baku.card.error;

public class UserCredentialNotCorrect extends CommonException{

    public UserCredentialNotCorrect( String errorMessage) {
        super(ErrorCodes.USERNAME_OR_PASSWORD_NOT_CORRECT, errorMessage);
    }
}
