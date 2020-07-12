package com.jegorovje.covidit.engine.data.entity;

public interface CovidDataEntity {

  public String getCountry();

  public void setCountry(String country);

  public String getNewCases();

  public void setNewCases(String newCases);

  public String getNewDeath();

  public void setNewDeath(String newDeath);

  public String getNewRecovered();

  public void setNewRecovered(String newRecovered);

  public String getSharp();

  public void setSharp(String sharp);

  public String getTotalCases();

  public void setTotalCases(String totalCases);

  public String getTotalDeath();

  public void setTotalDeath(String totalDeath);

  public String getTotalRecovered();

  public void setTotalRecovered(String totalRecovered);

  public String getActiveCases();

  public void setActiveCases(String activeCases);

  public String getSeriousCritical();

  public void setSeriousCritical(String seriousCritical);

  public String getTotalTests();

  public void setTotalTests(String totalTests);

  public String getPopulation();

  public void setPopulation(String population);

  public String getTotalCasesPerMln();

  public void setTotalCasesPerMln(String totalCasesPerMln);

  public String getDeathsPerMln();

  public void setDeathsPerMln(String deathsPerMln);

  public String getTestsPerMln();

  public void setTestsPerMln(String testsPerMln);
}
