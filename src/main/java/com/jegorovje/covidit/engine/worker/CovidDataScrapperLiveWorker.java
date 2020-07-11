package com.jegorovje.covidit.engine.worker;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.context.cmd.InsertAllCovidStatisticToDatabaseCmd;
import io.micronaut.scheduling.annotation.Scheduled;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Transactional
public class CovidDataScrapperLiveWorker {

  private static final Logger LOG = LoggerFactory.getLogger(CovidDataScrapperLiveWorker.class);


  @Inject
  CommandContext commandContext;

  public CovidDataScrapperLiveWorker() {
    Thread thread = Thread.currentThread();
    thread.setName("asyncDataScrapperLiveThread");
  }

  @Scheduled(fixedDelay = "10s")
  void executeScript() {
    try {
      commandContext.execute(new InsertAllCovidStatisticToDatabaseCmd());
    } catch (Exception e) {
      LOG.error("IOException during AsyncDataScrapperLiveThread", e);
    }
  }
}
