package az.baku.card.error;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class RestErrorResponse {

    private String uuid;
    private String code;
    private String message;

}
