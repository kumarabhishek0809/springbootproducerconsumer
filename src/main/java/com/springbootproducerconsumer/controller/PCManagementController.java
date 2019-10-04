package com.springbootproducerconsumer.controller;


import com.springbootproducerconsumer.dto.ProducerConsumerRequest;
import com.springbootproducerconsumer.dto.response.CustomResponse;
import com.springbootproducerconsumer.service.IProcessorRequestHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Producer Consumer Control System")
public class PCManagementController {


    @Autowired
    private IProcessorRequestHandler processorRequestHandler;

    @ApiOperation(value = "Update the Value of No Of Producers and Consumers",
            notes = "Update the Value of No Of Producers and Consumers")
    @RequestMapping(value = "/producerconsumer", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<CustomResponse> updateThreads(
            @RequestBody ProducerConsumerRequest producerConsumerRequest) {

        return processorRequestHandler.updateThreads(producerConsumerRequest);
    }


    @ApiOperation(value = "Update the Value of No Of Producers and Consumers",
            notes = "Update the Value of No Of Producers and Consumers")
    @RequestMapping(value = "/producerconsumer/counter", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<CustomResponse> updateCounter(
            @RequestParam("counter") int counter) {

        return processorRequestHandler.updateCounter(counter);
    }

    @ApiOperation(value = "Try to shutdown program")
    @RequestMapping(value = "/producerconsumer/counter", method = RequestMethod.PATCH)
    public @ResponseBody
    ResponseEntity<CustomResponse> shutDownProgram() {
        return processorRequestHandler.shutDownProgram();
    }
}
