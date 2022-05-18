package com.fleetlize.notificationmanager.gateways.rest;

import com.fleetlize.notificationmanager.gateways.rest.request.NotifyRequest;
import com.fleetlize.notificationmanager.gateways.rest.response.NotifyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/notify")
public class NotificationController {

    @PostMapping
    public ResponseEntity notify(@RequestBody final NotifyRequest notifyRequest) {

        log.info(notifyRequest.toString());

        return ResponseEntity.ok(NotifyResponse.builder().uuid(UUID.randomUUID().toString()).build());
    }

}
