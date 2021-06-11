CREATE TABLE metric (
	id int NOT NULL AUTO_INCREMENT, 
	wifiSpeed double NOT NULL, 
	timeStamp VARCHAR(255) NOT NULL,
	isConsumed boolean,
	PRIMARY KEY (id)
);