import requests
import json
from bs4 import BeautifulSoup


def main():
  headers = {'User-Agent':
               'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36'}

  page = "https://www.worldometers.info/coronavirus/?#countries"
  pageTree = requests.get(page, headers=headers)
  soup = BeautifulSoup(pageTree.content, 'html.parser')

  results = soup.find(id='main_table_countries_today')

  content = results.find_all('tr')

  SharpList = []
  CountryList = []
  TotalCasesList = []
  NewCasesList = []
  TotalDeathsList = []
  NewDeathsList = []
  TotalRecoveredList = []
  NewRecoveredList = []
  ActiveCasesList = []
  SeriousCriticalList = []
  TotCases1MList = []
  Deaths1MList = []
  TotalTestsList = []
  Tests1MList = []
  PopulationList = []

  for data in content:
    if (data.attrs.get('style') != "display: none"):
      splited = data.text.split("\n")
      if (splited[1] != "#"):
        if (splited[1] == ""):
          SharpList.append("0")
        else:
          SharpList.append(splited[1])
        CountryList.append(splited[2])
        TotalCasesList.append(splited[3])
        NewCasesList.append(splited[4])
        TotalDeathsList.append(splited[5])
        NewDeathsList.append(splited[6])
        TotalRecoveredList.append(splited[7])
        NewRecoveredList.append(splited[8])
        ActiveCasesList.append(splited[9])
        SeriousCriticalList.append(splited[10])
        TotCases1MList.append(splited[11])
        Deaths1MList.append(splited[12])
        TotalTestsList.append(splited[13])
        Tests1MList.append(splited[14])
        PopulationList.append(splited[15])

  result = []
  for i in range(SharpList.__len__()):
    sharp = SharpList[i]
    country = CountryList[i]
    totalCases = TotalCasesList[i]
    newCases = NewCasesList[i]
    totalDeath = TotalDeathsList[i]
    newDeath = NewDeathsList[i]
    newRecovered = NewRecoveredList[i]
    totalRecovered = TotalRecoveredList[i]
    activeCases = ActiveCasesList[i]
    seriousCritical = SeriousCriticalList[i]
    totalCases1M = TotCases1MList[i]
    deaths1M = Deaths1MList[i]
    totalTests = TotalTestsList[i]
    tests1M = Tests1MList[i]
    population = PopulationList[i]
    jsonStr = {"sharp": sharp,
               "country": country,
               "totalCases": totalCases,
               "newCases": newCases,
               "totalDeath": totalDeath,
               "newDeath": newDeath,
               "totalRecovered": totalRecovered,
               "newRecovered": newRecovered,
               "activeCases": activeCases,
               "seriousCritical": seriousCritical,
               "totalCases1M": totalCases1M,
               "deaths1M": deaths1M,
               "totalTests": totalTests,
               "tests1M": tests1M,
               "population": population}
  # json_dump = json.dump(jsonStr)
    result.append(jsonStr)
  return result
result = "{\"lines\": " + str(main()) + "}"
# result = str(main())
print(result)

# path = str(pathlib.Path(__file__).parent.absolute()) + "/covidData.json"

# with open(path, 'w', encoding='utf-8') as f:
#   json.dump(main(), f, ensure_ascii=False, indent=4)
