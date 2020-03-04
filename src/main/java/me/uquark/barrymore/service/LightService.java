package me.uquark.barrymore.service;

import me.uquark.barrymore.avatar.IAvatar;

import java.util.ArrayList;

public class LightService extends AbstractService {
    private static final String STRINGS_DICT_RESOURCE = "/lightservice.dictionary.strings.strings";
    private static final String VALUES_DICT_RESOURCE = "/lightservice.dictionary.values";

    public LightService(IAvatar avatar) {
        super(avatar);
        loadDictionary(STRINGS_DICT_RESOURCE, VALUES_DICT_RESOURCE);
    }

    @Override
    protected void process(ArrayList<String> keywords) {
        if (keywords.contains("light")) {
            byte modeMask = 0;
            if (keywords.contains("turnon"))
                modeMask += 1;
            if (keywords.contains("turnoff"))
                modeMask += 2;

            if (modeMask == 1) {
                avatar.say("Включаю свет в вашей комнате");
                return;
            }

            if (modeMask == 2) {
                avatar.say("Выключаю свет в вашей комнате");
                return;
            }

            if (modeMask == 3) {
                avatar.say("Повторите пожалуйста.");
                return;
            }

        }
    }
}
