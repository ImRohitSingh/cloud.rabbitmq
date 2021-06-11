package com.test.cloud.rabbitmq.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Component
@Data
public class Metric implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnoreProperties
	private Integer id;

	@Column(value = "wifiSpeed")
	private double wifiSpeed;

	@Column(value = "timeStamp")
	private String timeStamp;

	@Column(value = "isConsumed")
	private boolean isConsumed;

}
