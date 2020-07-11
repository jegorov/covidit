package com.jegorovje.covidit.engine.context.cmd;

import com.jegorovje.covidit.engine.context.CommandContext;
import org.hibernate.Session;

public interface Command<T> {

  T execute(CommandContext commandContext);
}
