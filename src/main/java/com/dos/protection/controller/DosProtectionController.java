package com.dos.protection.controller;

import com.dos.protection.service.DosProtectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Controller to take care of requests
 *
 * @auther Karine Camhy
 * @since 7/31/2020
 */

@RestController
public class DosProtectionController {

    private static final Logger logger = LoggerFactory.getLogger(DosProtectionController.class);

    @Autowired
    private DosProtectionService service;

    /**
     * It will gets any call to server with query param "clientId" and calls DosProtectionService that process it.
     * If the client hasn’t reached the threshold, it will get HTTP response with status code 200 (OK) otherwise status code 503 (Service Unavailable).
     * @param id
     * @retu servet that must have
     */
    @RequestMapping
    public ResponseEntity getClient(@RequestParam("clientId") Integer id){
        ResponseEntity res =null;
        if(service.isMaxRequestPerTime(id)){
            logger.debug("request got status OK, client id {}", id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        logger.debug("request  got status Unavailable, client id {}", id);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
}
