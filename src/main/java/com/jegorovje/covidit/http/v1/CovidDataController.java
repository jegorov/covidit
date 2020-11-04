package com.jegorovje.covidit.http.v1;

import com.jegorovje.covidit.engine.data.entity.impl.CovidDataEntity;
import com.jegorovje.covidit.engine.manager.CovidDataEntityManager;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;
import javax.inject.Inject;

@Controller("/covid")
public class CovidDataController {

  @Inject
  CovidDataEntityManager covidDataEntityManager;

  @Get("/all")
  public List<CovidDataEntity> getAllCovidData() {

    return covidDataEntityManager.getCovidDataList();
  }
}
