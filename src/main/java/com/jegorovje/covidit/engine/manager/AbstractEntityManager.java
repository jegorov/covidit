package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.context.CommandContext;
import com.jegorovje.covidit.engine.data.entity.Entity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.TransactionalAdvice;
import javax.inject.Singleton;

@Singleton
@Repository
@TransactionalAdvice
public abstract class AbstractEntityManager {

  public void merge(Entity entity) {
    CommandContext.getCommandContext().getEngineConfiguration().getEntityManager().merge(entity);
  }

}
