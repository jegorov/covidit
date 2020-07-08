package com.jegorovje.covidit.engine.manager;

import com.jegorovje.covidit.engine.data.entity.AbstractEntity;
import com.jegorovje.covidit.engine.factory.SqlSessionFactory;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Singleton
@Repository
public abstract class AbstractEntityManager<T, String> implements CrudRepository<T, String> {

  @Inject
  private SqlSessionFactory sessionFactory;

  abstract public AbstractEntity create();

  @NonNull
  @Override
  public <S extends T> S save(@NonNull @Valid @NotNull S entity) {
    return (S) sessionFactory.openSession().save(entity);
  }

  @NonNull
  @Override
  public <S extends T> S update(@NonNull @Valid @NotNull S entity) {
    return null;
  }

  @NonNull
  public <S extends T> S merge(@NonNull S entity) {
    return (S) sessionFactory.getCurrentSession().merge(entity);
  }

  @NonNull
  @Override
  public <S extends T> Iterable<S> saveAll(@NonNull @Valid @NotNull Iterable<S> entities) {
    return null;
  }

  @NonNull
  @Override
  public Optional<T> findById(@NonNull @NotNull String string) {
    return Optional.empty();
  }

  @Override
  public boolean existsById(@NonNull @NotNull String string) {
    return false;
  }

  @NonNull
  @Override
  public Iterable<T> findAll() {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(@NonNull @NotNull String string) {

  }

  @Override
  public void delete(@NonNull @NotNull T entity) {

  }

  @Override
  public void deleteAll(@NonNull @NotNull Iterable<? extends T> entities) {

  }

  @Override
  public void deleteAll() {

  }

//  @NonNull
//  @Override
//  public T save(@NonNull Object entity) {
//    return (T) sessionFactory.openSession().save(entity);
//  }
//
//  @NonNull
//  public Object merge(@NonNull Object entity) {
//    return (T) sessionFactory.getCurrentSession().merge(entity);
//  }
//
//  @NonNull
//  @Override
//  public Object update(@NonNull Object entity) {
//    sessionFactory.openSession().update(entity);
//    return 1;
//  }
//
//  @NonNull
//  @Override
//  public Iterable saveAll(@NonNull @Valid @NotNull Iterable entities) {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @NonNull
//  @Override
//  public Optional findById(@NonNull Object o) {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @Override
//  public boolean existsById(@NonNull Object o) {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @NonNull
//  @Override
//  public Iterable findAll() {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @Override
//  public long count() {
//    return 0;
//  }
//
//  @Override
//  public void deleteById(@NonNull Object o) {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @Override
//  public void delete(@NonNull Object entity) {
//    sessionFactory.openSession().delete(entity);
//  }
//
//  @Override
//  public void deleteAll(@NonNull @NotNull Iterable entities) {
//    throw new RuntimeException("doesn't implemented");
//  }
//
//  @Override
//  public void deleteAll() {
//    throw new RuntimeException("doesn't implemented");
//  }
}
