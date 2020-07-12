package com.jegorovje.covidit.http.json.covid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.impl.CovidDataEntityImpl;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/covid")
public class CovidRequest {

  private static final Logger log = LoggerFactory.getLogger(CovidRequest.class);

  @Get(value = "/country/{name}", produces = MediaType.APPLICATION_JSON)
  public String stocks(String name) {
    try {
      return CommandContext.getCommandContext().getEngineConfiguration()
          .getExecutorService().submit(() -> {
            CovidDataEntityImpl covidDataEntity = CommandContext.getCommandContext()
                .getEngineConfiguration().getCovidDataEntityManager()
                .findCovidStatisticByCountry(name);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            return gson.toJson(covidDataEntity);
          }).get();

    } catch (Exception e) {
      log.error("Error", e);
      return "error: " + e.toString();
    }
  }
}
