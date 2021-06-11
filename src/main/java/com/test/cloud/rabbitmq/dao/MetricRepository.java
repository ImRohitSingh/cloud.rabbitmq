package com.test.cloud.rabbitmq.dao;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.test.cloud.rabbitmq.models.Metric;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MetricRepository extends ReactiveCrudRepository<Metric, Integer> {

	@Query("SELECT * FROM metric WHERE id = :id")
	Mono<Metric> findById(Integer id);

	@Query("SELECT * FROM metric WHERE isConsumed = :isConsumed")
	Flux<Metric> findByIsConsumed(boolean isConsumed);

}
