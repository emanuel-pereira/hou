package smarthome.controller;

import smarthome.services.ComfortLevelService;

public class ComfortLevelCTRL {

    private ComfortLevelService comfortLevelService;

    public ComfortLevelCTRL() {
        this.comfortLevelService = new ComfortLevelService();
    }
}
