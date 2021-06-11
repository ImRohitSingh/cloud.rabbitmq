package com.test.cloud.rabbitmq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cloud.rabbitmq.dao.MetricRepository;
import com.test.cloud.rabbitmq.models.Metric;

import reactor.core.publisher.Flux;

@Service
public class MetricService {

	@Autowired
	private MetricRepository metricRepository;

	public void save(Metric metric) {
		metricRepository.save(metric).subscribe();
	}

	public Flux<Metric> findAll() {
		return metricRepository.findAll();
	}

}
