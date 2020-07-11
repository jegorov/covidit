package com.jegorovje.covidit.engine.data.entity;

public interface CovidStatisticEntity  {

  public String getCountry();

  public void setCountry(String country);

  public String getNewCases();

  public void setNewCases(String newCases);

  public String getNewDeath();

  public void setNewDeath(String newDeath);

  public String getNewRecovered();

  public void setNewRecovered(String newRecovered);

  public Integer getSharp();

  public void setSharp(Integer sharp);

  public Integer getTotalCases();

  public void setTotalCases(Integer totalCases);

  public Integer getTotalDeath();

  public void setTotalDeath(Integer totalDeath);

  public Integer getTotalRecovered();

  public void setTotalRecovered(Integer totalRecovered);

  public Integer getActiveCases();

  public void setActiveCases(Integer activeCases);

  public Integer getSeriousCritical();

  public void setSeriousCritical(Integer seriousCritical);

  public Integer getTotalTests();

  public void setTotalTests(Integer totalTests);

  public Integer getPopulation();

  public void setPopulation(Integer population);

  public Double getTotalCasesPerMln();

  public void setTotalCasesPerMln(Double totalCasesPerMln);

  public Double getDeathsPerMln();

  public void setDeathsPerMln(Double deathsPerMln);

  public Double getTestsPerMln();

  public void setTestsPerMln(Double testsPerMln);
}
