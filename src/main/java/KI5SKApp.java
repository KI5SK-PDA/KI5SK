import card.controller.CardController;
import card.service.CardServiceImpl;

public class KI5SKApp {
    public static void main(String[] args) {
        CardServiceImpl cardService = new CardServiceImpl();
        CardController cardController = new CardController(cardService);
        cardController.runView();
    }
}
