package ar.edu.unq.desapp.grupon.backenddesappapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
abstract class LoggingController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

}