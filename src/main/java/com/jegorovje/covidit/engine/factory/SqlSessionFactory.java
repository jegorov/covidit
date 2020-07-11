package com.jegorovje.covidit.engine.factory;

import io.micronaut.transaction.annotation.TransactionalAdvice;
import io.micronaut.transaction.hibernate5.HibernateTransactionManager;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@TransactionalAdvice
public class SqlSessionFactory {

  private static final Logger logger = LoggerFactory.getLogger(SqlSessionFactory.class);
  @Inject
  private SessionFactory sessionFactory;

  @Inject
  HibernateTransactionManager transactionManager;


  public Session openSession() {
   return sessionFactory.openSession();
  }

  public Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
