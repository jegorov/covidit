package com.jegorovje.covidit.engine.util;

import com.jegorovje.covidit.engine.context.CommandContext;
import javax.inject.Singleton;

@Singleton
public class ThreadExecutionUtil {


  public static void executeThread(Runnable runnable) {
    CommandContext.getCommandContext().getEngineConfiguration().getExecutorService()
        .execute(runnable);
  }
}
