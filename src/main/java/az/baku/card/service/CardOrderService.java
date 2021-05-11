package az.baku.card.service;

import az.baku.card.error.DataNotFoundException;
import az.baku.card.error.OrderAlreadySubmitted;
import az.baku.card.model.response.CardDetailsResponse;
import az.baku.card.model.request.CardOrderRequest;
import az.baku.card.model.dto.CardOrderDto;
import az.baku.card.model.entity.CardDetails;
import az.baku.card.model.entity.CardOrder;
import az.baku.card.model.entity.User;
import az.baku.card.model.mapper.CardOrderMapper;
import az.baku.card.repository.CardDetailsRepository;
import az.baku.card.repository.CardOrderRepository;
import az.baku.card.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class CardOrderService {

    private final CardOrderMapper cardOrderMapper;
    private final CardOrderRepository cardOrderRepository;
    private final CardDetailsRepository cardDetailsRepository;
    private final UserService userService;

    public List<CardOrderDto> getCardOrders() {
        User user = userService.findCurrentUser();
        log.info("Getting all orders by userId: {}", user.getId());
        return cardOrderMapper.toCardOrderDtos(cardOrderRepository.findAllByUserId(user.getId()));


    }

    public CardOrderDto getCardOrder(Long orderId) {
        log.info("Getting card order by orderId: {}", orderId);
        CardOrder cardOrder = cardOrderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Card order not found"));
        return cardOrderMapper.toCardOrderDto(cardOrder);
    }

    public void deleteCardOrder(Long orderId) {
        log.info("Getting card order by orderId: {}", orderId);
        CardOrder cardOrder = cardOrderRepository.findById(orderId)
                .orElseThrow(() -> new DataNotFoundException("Card order not found"));

        if (cardOrder.isSubmitted()) {
            log.info("Card order already submitted orderId: {}", orderId);
            throw new OrderAlreadySubmitted("Cannot delete, order already submitted");
        }
        log.warn("Deleting card order. orderId: {}", orderId);
        cardOrderRepository.delete(cardOrder);
    }

    public void createCardOrder(CardOrderRequest orderRequest){
        User user = userService.findCurrentUser();
        log.info("Creating new card order,  userId {}", user.getId());

        CardOrder cardOrder = cardOrderMapper.toCardOrder(orderRequest);
        cardOrder.setSubmitted(false);
        cardOrder.setCreateDate(LocalDateTime.now());
        cardOrder.setUpdateDate(LocalDateTime.now());
        cardOrder.setUser(user);
        cardOrderRepository.save(cardOrder);
    }


    public void updateCardOrder(Long orderId, CardOrderRequest cardOrderRequest){
        User user = userService.findCurrentUser();

        log.info("Getting card order by orderId: {}", orderId);
        CardOrder cardOrder = cardOrderRepository.findById(orderId)
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new DataNotFoundException("Order not found"));

        if (cardOrder.isSubmitted()){
            log.info("Card order already submitted orderId: {}", orderId);
            throw  new OrderAlreadySubmitted("Order already submitted");
        }
        log.info("Updating card order, orderId: {}", orderId);
        cardOrder.setCardPeriod(cardOrderRequest.getCardPeriod());
        cardOrder.setCardType(cardOrderRequest.getCardType());
        cardOrder.setCardHolderName(cardOrderRequest.getCardHolderName());
        cardOrder.setUrgent(cardOrderRequest.isUrgent());
        cardOrder.setCodeword(cardOrderRequest.getCodeword());
        cardOrder.setUpdateDate(LocalDateTime.now());
        cardOrderRepository.save(cardOrder);
    }

    @Transactional
    public CardDetailsResponse submitCardOrder(Long orderId){
        User user = userService.findCurrentUser();
        CardOrder cardOrder = cardOrderRepository.findById(orderId)
                .filter(order -> order.getUser().getId().equals(user.getId()))
                .orElseThrow(() -> new DataNotFoundException("Order not found"));

        if(cardOrder.isSubmitted()){
            log.info("Card order already submitted orderId: {}", orderId);
            throw  new OrderAlreadySubmitted("Order already submitted");
        }
        cardOrder.setSubmitted(true);
        cardOrder.setUpdateDate(LocalDateTime.now());
        cardOrderRepository.save(cardOrder);

        log.info("Generating card number and  account number");
        String cardNumber = RandomUtil.generateCardNumber(16);
        String accountNumber = RandomUtil.generateAccountNumber(32);

        CardDetails cardDetails = new CardDetails();
        cardDetails.setCardOrder(cardOrder);
        cardDetails.setCardNumber(cardNumber);
        cardDetails.setAccountNumber(accountNumber);
        cardDetails.setCreateDate(LocalDateTime.now());
        cardDetailsRepository.save(cardDetails);

        return CardDetailsResponse.builder()
                .carNumber(cardNumber)
                .accountNumber(accountNumber)
                .build();
    }

}
