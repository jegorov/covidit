package com.jegorovje.covidit.engine.context.cmd;

import static com.jegorovje.covidit.engine.util.PythonScriptUtil.getGsonFromWorldmapCovid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.CovidStatisticEntity;
import com.jegorovje.covidit.engine.data.entity.impl.CovidStatisticEntityImpl;
import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

@AllArgsConstructor
public class InsertAllCovidStatisticToDatabaseCmd implements Command {

  CovidStatisticEntityManager covidStatisticEntityManager;

  @SneakyThrows
  @Override
  public Object execute(CommandContext commandContext) {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> carMap = mapper
        .readValue(getGsonFromWorldmapCovid(),
            new TypeReference<>() {
            });
    for (LinkedHashMap map : (ArrayList<LinkedHashMap>) carMap.get("lines")) {
      CovidStatisticEntity entity = covidStatisticEntityManager.createCovidStatisticFromMap(map);
      covidStatisticEntityManager.merge(entity);
    }

    return null;
  }

  @SneakyThrows
  @Override
  public Object execute(CommandContext commandContext, Session session) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> carMap = mapper
        .readValue(getGsonFromWorldmapCovid(),
            new TypeReference<>() {
            });
    for (LinkedHashMap map : (ArrayList<LinkedHashMap>) carMap.get("lines")) {
      CovidStatisticEntityImpl entity = (CovidStatisticEntityImpl) commandContext
          .getCovidStatisticEntityManager()
          .createCovidStatisticFromMap(map);
      session.merge(entity);
    }
    return null;
  }
}
