package com.pinnacle.dummyServlet.controller;


import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.JsonNode;
import com.pinnacle.dummyServlet.dao.AuthCodeResponse;
import com.pinnacle.dummyServlet.dao.GoFlipoResponse;

@RestController
public class MainController {


    @PostMapping("/api/main/scrubbing-logs")
    public GoFlipoResponse generate(@RequestBody JsonNode request) {

        String uuid = UUID.randomUUID().toString();
        AuthCodeResponse authData = new AuthCodeResponse(uuid);
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
        GoFlipoResponse<AuthCodeResponse> response =
                new GoFlipoResponse<>(true, "Scrubbing loged successfully", authData);

        return response;
    }

    //https://smartping-backend.goflipo.com/api/main/scrubbing-logs?authcode=febb5e83-4d86-499d
    //-a60b-ca0b06045974

    @GetMapping("/api/main/scrubbing-logs")
    public GoFlipoResponse pingGenerate(@RequestParam(name = "authcode") String request) {
        if (request.equals("8b012f6a-87da-404a-9cea-c4b57f33a649")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid authcode");
        }

        AuthCodeResponse authData = new AuthCodeResponse(request);
        GoFlipoResponse<AuthCodeResponse> response =
                new GoFlipoResponse<>(true, "Scrubbing loged successfully", authData);

        return response;
    }
}