package com.jegorovje.covidit.engine.transport;

import com.jegorovje.covidit.engine.data.entity.impl.CovidDataEntityImpl;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface LiveCovidDataService {

  @Topic("live-covid-data-topic")
  void sendCovidData(@KafkaKey String country, CovidDataEntityImpl covidDataEntity);

  void sendCovidData(@Topic String topic, @KafkaKey String country,
      CovidDataEntityImpl covidDataEntity);

}
