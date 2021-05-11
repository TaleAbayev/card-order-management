package az.baku.card.controller;

import az.baku.card.model.response.CardDetailsResponse;
import az.baku.card.model.request.CardOrderRequest;
import az.baku.card.model.dto.CardOrderDto;
import az.baku.card.service.CardOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/card/order")
public class CardOrderController {

    private final CardOrderService cardOrderService;


    @GetMapping
    public ResponseEntity<List<CardOrderDto>> getCardOrders() {
        return ResponseEntity.ok(cardOrderService.getCardOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CardOrderDto> getCardOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(cardOrderService.getCardOrder(orderId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crateCardOrder(@RequestBody  CardOrderRequest request) {
        cardOrderService.createCardOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateCardOrder(@PathVariable Long orderId,
                                          @RequestBody @Valid CardOrderRequest request) {
        cardOrderService.updateCardOrder(orderId, request);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteCardOrder(@PathVariable Long orderId) {
        cardOrderService.deleteCardOrder(orderId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();

    }

    @PutMapping("/submit/{orderId}")
    public ResponseEntity<CardDetailsResponse> submitOrder(@PathVariable Long orderId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardOrderService.submitCardOrder(orderId));

    }


}
