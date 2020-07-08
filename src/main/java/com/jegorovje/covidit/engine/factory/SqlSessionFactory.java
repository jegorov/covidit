package com.jegorovje.covidit.engine.factory;

import javax.inject.Inject;
import javax.inject.Singleton;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Singleton
public class SqlSessionFactory {

  @Inject
  private SessionFactory sessionFactory;


  //todo потом переписать. Вместо опен сессию постоянно запилить что-то с контекстом туда сюда
  public Session openSession(){
    return sessionFactory.openSession();
  }

  public Session getCurrentSession(){
    return sessionFactory.getCurrentSession();
  }
}
