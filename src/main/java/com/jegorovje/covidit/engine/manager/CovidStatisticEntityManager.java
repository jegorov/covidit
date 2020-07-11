package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.data.entity.impl.CovidStatisticEntityImpl;
import io.netty.util.internal.StringUtil;
import java.util.Map;
import java.util.UUID;
import javax.inject.Singleton;
import lombok.SneakyThrows;


@Singleton
public abstract class CovidStatisticEntityManager extends AbstractEntityManager {


  public AbstractEntity create() {
    return new CovidStatisticEntityImpl();
  }

//  @Query("SELECT * FROM user WHERE enabled = false")
//  abstract List<CovidStatisticEntityImpl> findDisabled();

  public CovidStatisticEntityImpl createCovidStatisticFromMap(Map<String, String> map) {
    CovidStatisticEntityImpl entity = (CovidStatisticEntityImpl) create();
    entity.setActiveCases((Integer) parseJsonObject(Integer.class, map.get("activeCases")));
    entity.setTotalCases((Integer) parseJsonObject(Integer.class, map.get("totalCases")));
    entity.setTotalDeath((Integer) parseJsonObject(Integer.class, map.get("totalDeath")));
    entity
        .setTotalRecovered((Integer) parseJsonObject(Integer.class, map.get("totalRecovered")));
    entity.setSharp((Integer) parseJsonObject(Integer.class, map.get("sharp")));
    entity.setSeriousCritical(
        (Integer) parseJsonObject(Integer.class, map.get("seriousCritical")));
    entity.setTotalTests((Integer) parseJsonObject(Integer.class, map.get("totalTests")));
    entity.setPopulation((Integer) parseJsonObject(Integer.class, map.get("population")));

    entity.setTestsPerMln((Double) parseJsonObject(Double.class, map.get("tests1M")));
    entity.setDeathsPerMln((Double) parseJsonObject(Double.class, map.get("deaths1M")));
    entity.setTotalCasesPerMln((Double) parseJsonObject(Double.class, map.get("totalCases1M")));

    entity.setCountry(map.get("country"));
    entity.setNewCases(map.get("newCases"));
    entity.setNewDeath(map.get("newDeath"));
    entity.setNewRecovered(map.get("newRecovered"));
    entity.setId(UUID.nameUUIDFromBytes(entity.getCountry().getBytes()));
    return entity;
  }

  @SneakyThrows
  private Object parseJsonObject(Class clazz, String value) {
    if (StringUtil.isNullOrEmpty(value.strip()) || value.equals("N/A")) {
      return null;
    }
    Object object = null;
    value = value.strip().replaceAll(",", "");
    switch (clazz.getSimpleName()) {
      case "Integer": {
        return Integer.parseInt(value);
      }
      case "Double": {
        return Double.parseDouble(value);
      }
      case "String": {
        return value;
      }
      default: {
        throw new IllegalArgumentException("IllegalType type " + clazz.getSimpleName());
      }
    }
  }
}
