package com.test.cloud.rabbitmq.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.cloud.rabbitmq.models.Metric;
import com.test.cloud.rabbitmq.service.MetricService;
import com.test.cloud.rabbitmq.service.Producer;

import reactor.core.publisher.Flux;

@RestController
public class MessagingController {

	@Autowired
	private Producer producer;

	@Autowired
	private MetricService metricService;

	@PostMapping(path = "/produce")
	@ResponseStatus(code = HttpStatus.ACCEPTED, reason = "Request has been accepted")
	public void produce(@RequestBody Metric param) {
		metricService.save(param);
		producer.sendToQueue(param);
	}

	@GetMapping(path = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Metric> findAll() {
		return metricService.findAll().delayElements(Duration.ofSeconds(1));
	}

}
