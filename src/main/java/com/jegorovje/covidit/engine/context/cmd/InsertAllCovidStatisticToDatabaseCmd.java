package com.jegorovje.covidit.engine.context.cmd;

import static com.jegorovje.covidit.engine.util.PythonScriptUtil.getGsonFromWorldmapCovid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.Entity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class InsertAllCovidStatisticToDatabaseCmd implements Command<Void> {

  @SneakyThrows
  @Override
  public Void execute(CommandContext commandContext) {

    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> countriesCovidMap = mapper
        .readValue(getGsonFromWorldmapCovid(),
            new TypeReference<>() {
            });
    for (LinkedHashMap map : (ArrayList<LinkedHashMap>) countriesCovidMap.get("lines")) {
      Entity entity = CommandContext.getCommandContext().getEngineConfiguration()
          .getCovidStatisticEntityManager()
          .createCovidStatisticFromMap(map);
      commandContext.getEngineConfiguration().getCovidStatisticEntityManager().merge(entity);
    }

    return null;
  }
}
