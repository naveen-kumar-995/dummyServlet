package com.pinnacle.dummyServlet.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.JsonNode;
import com.pinnacle.dummyServlet.dao.AuthCodeResponse;
import com.pinnacle.dummyServlet.dao.BasicResponse;
import com.pinnacle.dummyServlet.dao.CleverTapResponse;
import com.pinnacle.dummyServlet.dao.DataDetail;
import com.pinnacle.dummyServlet.dao.DlrResponse;
import com.pinnacle.dummyServlet.dao.GoFlipoResponse;
import com.pinnacle.dummyServlet.dao.RecordData;
import com.pinnacle.dummyServlet.dao.UnprocessedRecord;

import lombok.extern.slf4j.Slf4j;

@RestController
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);


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
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }

        if (request.equals("8b012f6a-87da-404a-9cea-c4b57f33a649")) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid authcode");
        }

        AuthCodeResponse authData = new AuthCodeResponse(request);
        GoFlipoResponse<AuthCodeResponse> response =
                new GoFlipoResponse<>(true, "Scrubbing loged successfully", authData);

        return response;
    }

    @PostMapping("/callback/success")
    public DlrResponse getCleverTapSuccessResponse(@RequestBody String request)
    {
        log.debug("Received Json:  {}",request);

        List<UnprocessedRecord> unprocessedRecordsList = new ArrayList<>();

        return new DlrResponse("success", 1 ,unprocessedRecordsList);
    }

//    @PostMapping("/callback/fail")
//    public DlrResponse getCleverTapFailResponse(@RequestBody JsonNode request) {
//        log.debug("Received JsonString:  {}", request);
//        UnprocessedRecord unprocessedRecord = new UnprocessedRecord("fail",500, "Internal Server Error", request);
//
//        List<UnprocessedRecord> unprocessedRecordsList = new ArrayList<>();
//        unprocessedRecordsList.add(unprocessedRecord);
//
//        return new DlrResponse("fail", 0, unprocessedRecordsList);
//    }

    @PostMapping("/callback/fail")
    public DlrResponse getCleverTapFailResponse(@RequestBody JsonNode request) {
        log.debug("Received JsonString: {}", request);

        // unwrap array -> object
        JsonNode normalizedRequest = request;
        if (request.isArray() && request.size() > 0) {
            normalizedRequest = request.get(0); // take first element
        }

        UnprocessedRecord unprocessedRecord =
                new UnprocessedRecord("fail", 500, "Internal Server Error", normalizedRequest);

        List<UnprocessedRecord> unprocessedRecordsList = new ArrayList<>();
        unprocessedRecordsList.add(unprocessedRecord);

        return new DlrResponse("fail", 0, unprocessedRecordsList);
    }



    @PostMapping("/callback/partial")
    public DlrResponse getCleverTapPartialResponse(@RequestBody JsonNode request) {
        log.debug("Received JsonString: {}", request);

        // unwrap array -> object
        JsonNode normalizedRequest = request;
        if (request.isArray() && request.size() > 0) {
            normalizedRequest = request.get(0); // take first element
        }

        UnprocessedRecord unprocessedRecord =
                new UnprocessedRecord("fail", 400, "Internal Server Error", normalizedRequest);


        UnprocessedRecord unprocessedRecord1 =
                new UnprocessedRecord("fail", 400, "Internal Server Error", normalizedRequest);

        List<UnprocessedRecord> unprocessedRecordsList = new ArrayList<>();
        unprocessedRecordsList.add(unprocessedRecord);
        unprocessedRecordsList.add(unprocessedRecord1);

        return new DlrResponse("partial", 0, unprocessedRecordsList);
    }

    @PostMapping("callback/batch")
    public BasicResponse getCallbackBatch(@RequestBody String s)
    {
        log.debug("Received  DLR: {}", s);
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            log.error("Interrupted exception " , e);
        }
        return new BasicResponse("Sucess");
    }


}