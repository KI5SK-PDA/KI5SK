import card.controller.CardController;
import card.service.CardServiceImpl;

public class KI5SKApp {
    public static void main(String[] args) {
        new CardController(new CardServiceImpl());
    }
}
