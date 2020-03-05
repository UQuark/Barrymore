package me.uquark.barrymore.avatar;

import me.uquark.barrymore.Application;
import me.uquark.barrymore.service.AbstractService;
import me.uquark.barrymore.service.LightService;
import me.uquark.barrymore.ui.IUserInterface;
import me.uquark.barrymore.ui.UserOrder;

import java.io.IOException;
import java.util.ArrayList;

public class Barrymore implements IAvatar {
    private IUserInterface ui;

    private LightService lightService = new LightService(this);

    private ArrayList<AbstractService> services = new ArrayList<AbstractService>();

    public Barrymore(IUserInterface ui) throws IOException {
        this.ui = ui;

        services.add(lightService);
    }

    @Override
    public void userOrder(UserOrder uo) {
        String adjustedMessage = uo.message;
        adjustedMessage = adjustedMessage.replace("[,.;:!?-]", "");

        double max = 0;
        int index = -1;

        for (int i = 0; i < services.size(); i++) {
            AbstractService service = services.get(i);
            double confidence = service.analyzeMessage(adjustedMessage);
            if (confidence > max) {
                max = confidence;
                index = i;
            }
        }

        services.get(index).userOrder(uo);
    }

    @Override
    public String getName() {
        return "Barrymore";
    }

    @Override
    public void halt() {
        ui.close();
        Application.halt();
    }

    @Override
    public void run() {
        ui.setAvatar(this);
        ui.open();
    }

    @Override
    public void say(String message) {
        ui.putMessage(message);
    }
}
