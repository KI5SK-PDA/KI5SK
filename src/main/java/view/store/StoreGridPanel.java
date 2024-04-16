package view.store;

import kiosk.controller.KioskController;
import view.controller.StoreToMenu;

import javax.swing.*;
import java.awt.*;

public class StoreGridPanel extends JPanel {
    private final KioskController kioskController = KioskController.newInstance();

    public StoreGridPanel(StoreToMenu transition) {
        super.setLayout(new GridLayout(0, 2, 20, 20));
        kioskController.getAllStore().forEach(storeResponse
            -> add(new StoreSummaryPanel(
                storeResponse.getId(),
            storeResponse.getName(),
            storeResponse.getImagePath(),
            new StoreClickListner(storeResponse.getId(), transition))));
    }
}
