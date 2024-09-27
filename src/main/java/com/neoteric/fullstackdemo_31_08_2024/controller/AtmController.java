package com.neoteric.fullstackdemo_31_08_2024.controller;

import com.neoteric.fullstackdemo_31_08_2024.model.Atm;
import com.neoteric.fullstackdemo_31_08_2024.service.AccountService;
import com.neoteric.fullstackdemo_31_08_2024.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin /*(origins = "http://localhost:57200", // Frontend origin
        allowedHeaders = "*", // Allow all headers
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS}) */ // Allow POST, GET, OPTIONS
public class AtmController {

    AtmService atmService = new AtmService();

    @PostMapping(value = "/api/createAtm", consumes = "application/json", produces = "application/json")
    public Atm createAtm(@RequestBody Atm atm) throws Exception {
        String cardNumber = atmService.createAtm(atm);
        atm.setCardNumber(cardNumber);
        return atm;
    }

    @PostMapping(value = "/api/validateAtm", consumes = "application/json", produces = "application/json")
    public boolean validateAtm(@RequestBody Atm atm) throws Exception {
        return atmService.validateAtmLogin(atm.getCardNumber(), atm.getPin());
    }

    // Optional: If you need to handle preflight requests explicitly
  /*  @RequestMapping(value = "/api/createAtm", method = RequestMethod.OPTIONS)
    public void handlePreflight() {
        // This method is just to respond to preflight requests.
    } */
}
