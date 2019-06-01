package cn.caipiaoq.push.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class RabbitmqController {
    protected static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqController.class);


}
