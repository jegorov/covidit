package com.jegorovje.covidit.http.json.covid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.impl.CovidStatisticEntityImpl;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/covid")
public class CovidRequest {


  @Get(value = "/country/{name}", produces = MediaType.APPLICATION_JSON)
  public String stocks(String name) {
    try {

      CovidStatisticEntityImpl covidStatisticEntity = CommandContext.getCommandContext()
          .getEngineConfiguration().getCovidStatisticEntityManager()
          .findCovidStatisticByCountry(name);

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(covidStatisticEntity);

    } catch (Exception e) {
      return "error: " + e.toString();
    }
  }
}
