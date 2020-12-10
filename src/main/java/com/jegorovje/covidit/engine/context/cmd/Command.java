package com.jegorovje.covidit.engine.context.cmd;

import com.jegorovje.covidit.engine.context.CommandContext;

public interface Command<T> {

  T execute(CommandContext commandContext);
}
