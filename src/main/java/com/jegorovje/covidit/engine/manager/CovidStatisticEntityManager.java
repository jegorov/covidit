package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.data.entity.impl.CovidStatisticEntityImpl;
import io.micronaut.transaction.annotation.ReadOnly;
import java.util.Map;
import java.util.UUID;
import javax.inject.Singleton;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import lombok.SneakyThrows;


@Singleton
public abstract class CovidStatisticEntityManager extends AbstractEntityManager {


  public AbstractEntity create() {
    return new CovidStatisticEntityImpl();
  }

  @ReadOnly
  public CovidStatisticEntityImpl findCovidStatisticByCountry(@NotNull String country) {
    String qlString = "from CovidStatisticEntityImpl as a where LOWER(country) LIKE LOWER(:country)";
    TypedQuery<CovidStatisticEntityImpl> query = CommandContext.getCommandContext()
        .getEngineConfiguration()
        .getEntityManager().createQuery(qlString, CovidStatisticEntityImpl.class);
    query.setParameter("country", country);

    return query.getSingleResult();
  }

  public CovidStatisticEntityImpl createCovidStatisticFromMap(Map<String, String> map) {
    CovidStatisticEntityImpl entity = (CovidStatisticEntityImpl) create();
    entity.setActiveCases(parseToString(map.get("activeCases")));
    entity.setTotalCases(parseToString(map.get("totalCases")));
    entity.setTotalDeath(parseToString(map.get("totalDeath")));
    entity
        .setTotalRecovered(parseToString(map.get("totalRecovered")));
    entity.setSharp(parseToString(map.get("sharp")));
    entity.setSeriousCritical(parseToString(map.get("seriousCritical")));
    entity.setTotalTests(parseToString(map.get("totalTests")));
    entity.setPopulation(parseToString(map.get("population")));

    entity.setTestsPerMln(parseToString(map.get("tests1M")));
    entity.setDeathsPerMln(parseToString(map.get("deaths1M")));
    entity.setTotalCasesPerMln(parseToString(map.get("totalCases1M")));

    entity.setCountry(parseToString(map.get("country")));
    entity.setNewCases(parseToString(map.get("newCases")));
    entity.setNewDeath(parseToString(map.get("newDeath")));
    entity.setNewRecovered(parseToString(map.get("newRecovered")));
    entity.setId(UUID.nameUUIDFromBytes(entity.getCountry().getBytes()));
    return entity;
  }

  @SneakyThrows
  private String parseToString(String value) {
    if (value == null || value.strip().isEmpty()) {
      return null;
    }
    if (value.strip().equals("N/A")) {
      return value.strip();
    }
    value = value.strip().replace(",", "");
    return value;
  }
}
