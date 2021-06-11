package com.test.cloud.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cloud.rabbitmq.dao.MetricRepository;
import com.test.cloud.rabbitmq.models.Metric;

import reactor.core.publisher.Mono;

@Service
public class Consumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

	@Autowired
	private MetricRepository metricRepository;

	@RabbitListener(queues = "metric")
	public void consumeCloudMetric(Metric metric) {
		LOGGER.info("Consumed: {}", metric);

		Mono<Metric> receivedMono = metricRepository.findById(metric.getId());
		receivedMono.doOnNext(r -> {
			r.setConsumed(!r.isConsumed());
			metricRepository.save(r).subscribe();
			LOGGER.info("Listened: {} ", r);
		}).subscribe();

	}

}
