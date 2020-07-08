package com.jegorovje.covidit.engine.worker;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.context.cmd.InsertAllCovidStatisticToDatabaseCmd;
import com.jegorovje.covidit.engine.manager.CovidStatisticEntityManager;
import io.micronaut.scheduling.annotation.Scheduled;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CovidDataScrapperLiveWorker {

  private static final Logger LOG = LoggerFactory.getLogger(CovidDataScrapperLiveWorker.class);


  @Inject
  CovidStatisticEntityManager covidStatisticEntityManager;

  @Inject
  CommandContext commandContext;

  public CovidDataScrapperLiveWorker(CovidStatisticEntityManager covidStatisticEntityManager) {
    this.covidStatisticEntityManager = covidStatisticEntityManager;
    Thread.currentThread().setName("asyncDataScrapperLiveThread");
  }

  @Scheduled(fixedDelay = "10s")
  void executeScript() {
    try {
      commandContext.execute(new InsertAllCovidStatisticToDatabaseCmd(covidStatisticEntityManager));
    } catch (Exception e) {
      LOG.error("IOException during AsyncDataScrapperLiveThread", e);
    }
  }
}
