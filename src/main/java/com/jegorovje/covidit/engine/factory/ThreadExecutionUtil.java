package com.jegorovje.covidit.engine.factory;

import com.jegorovje.covidit.engine.context.CommandContext;
import javax.inject.Singleton;

@Singleton
public class ThreadExecutionUtil {


  public static void executeThread(Runnable runnable) {
    CommandContext.getCommandContext().getEngineConfiguration().getExecutorService()
        .execute(runnable);
  }
}
