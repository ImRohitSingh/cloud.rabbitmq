package com.test.cloud.rabbitmq.service;

import java.time.Duration;
import java.util.function.Supplier;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.test.cloud.rabbitmq.dao.MetricRepository;
import com.test.cloud.rabbitmq.models.Metric;

import reactor.core.publisher.Flux;

@Service
public class Producer {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private Queue queue;

	@Autowired
	private MetricRepository metricRepository;

	@Bean
	public Supplier<Flux<Metric>> metricConfig() {
		return () -> metricRepository.findAll().delayElements(Duration.ofSeconds(1));
	}

	public void sendToQueue(Metric metric) {
		rabbitTemplate.convertAndSend(this.queue.getName(), metric);
	}

	public void save(Metric metric) {
		metricRepository.save(metric).subscribe();
	}

	public Flux<Metric> findAll() {
		return metricRepository.findAll();
	}

}
