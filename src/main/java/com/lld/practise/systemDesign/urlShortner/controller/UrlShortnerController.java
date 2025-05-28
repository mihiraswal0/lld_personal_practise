package com.lld.practise.systemDesign.urlShortner.controller;

import com.lld.practise.systemDesign.urlShortner.dto.request.UrlCreationRequest;
import com.lld.practise.systemDesign.urlShortner.dto.response.UrlCreationResponse;
import com.lld.practise.systemDesign.urlShortner.entity.UrlDetails;
import com.lld.practise.systemDesign.urlShortner.service.CreateShortUrlService;
import com.lld.practise.systemDesign.urlShortner.service.RedirectUrlService;
import com.lld.practise.systemDesign.urlShortner.service.RefillService;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.lld.practise.systemDesign.urlShortner.util.Constants.*;


@RestController
@Api(tags = "URL Controller for redirect and create api's")
@RequestMapping(URL_CONTROLLER_PREFIX)
public class UrlShortnerController {

    @Autowired
    private RefillService refillService;
    @Autowired
    private CreateShortUrlService createShortUrlService;
    @Autowired
    private RedirectUrlService redirectUrlService;


    @GetMapping(URL_REDIRECT_PREFIX)
    @ApiOperation(value = "Redirect api for short url to long url")
    @ApiResponses(value = {
            @ApiResponse(code = 302,message = "Redirect to long url")
    })
    public ResponseEntity<Void> redirectShortUrl(@PathVariable(value = "shortUrl")String shortUrl){
        String longUrl=redirectUrlService.getLongUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.FOUND).header("Location",longUrl).build();

    }

    @PostMapping(URL_CREATE_PREFIX)
    @ApiOperation(value = "Create short url for a long url")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "Short Url Created Successfully",response = UrlCreationResponse.class),
            @ApiResponse(code = 400,message = "Invalid request"),
            @ApiResponse(code = 500,message = "Internal server error"),
    }
    )
    public ResponseEntity<UrlDetails> createShortUrl(@RequestBody @Valid UrlCreationRequest urlCreationRequest){
        UrlDetails urlDetailsResponse=createShortUrlService.shortenUrl(urlCreationRequest.getLongUrl());

        return ResponseEntity.status(HttpStatus.CREATED).body(urlDetailsResponse);
    }

    @GetMapping(REFILL_DB)
    @ApiOperation(value = "To fill db with the next X new userId")
    public ResponseEntity<?> refillDb(){
        Map<String,String> response=refillService.refillDb(10);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/refill/redis")
    public ResponseEntity<?> refillRedis(){
        String response= refillService.refillRedis();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
