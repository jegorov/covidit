package com.jegorovje.covidit.engine.factory;

import com.jegorovje.covidit.engine.context.CommandContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import javax.inject.Singleton;

@Singleton
public class ThreadPoolFactory {


  void executeThread(Runnable runnable) {
    ThreadFactory namedThreadFactory =
        new ThreadFactoryBuilder().setNameFormat("my-sad-thread-%d").build()
    ExecutorService executorService  = CommandContext.getCommandContext().getEngineConfiguration().getExecutorService();
  }
}
