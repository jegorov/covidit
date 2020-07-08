package com.jegorovje.covidit.http.json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.RxHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/json")
public class StocksNasdaqJson {

  private static final Logger LOG = LoggerFactory.getLogger(StocksNasdaqJson.class);
  private final RxHttpClient httpClient;

  public StocksNasdaqJson(RxHttpClient httpClient) {
    this.httpClient = httpClient;
  }


  @Get(value = "/stocks/{name}", produces = MediaType.APPLICATION_JSON)
  public String stocks(String name) {
    try {

      HttpRequest<?> request = HttpRequest
          .GET("https://sandbox.tradier.com/v1/markets/quotes?symbols=" + name)
          .bearerAuth("Xs64OgwelWrp9SG4thnaIaGxAQJq").accept(MediaType.APPLICATION_JSON_TYPE);

      String response = httpClient.toBlocking().retrieve(request);
      JsonObject json = JsonParser.parseString(response).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(json);
    } catch (Exception e) {
      return "error";
    }
  }

  //doesn't work because of account plan
  @Get(value = "/company/{name}", produces = MediaType.APPLICATION_JSON)
  public String company(String name) {
    try {
      HttpRequest<?> request = HttpRequest
          .GET("https://api.tradier.com/beta/markets/fundamentals/company?symbols=" + name)
          .bearerAuth("Xs64OgwelWrp9SG4thnaIaGxAQJq").accept(MediaType.APPLICATION_JSON_TYPE);

      String response = httpClient.toBlocking().retrieve(request);
      JsonObject json = JsonParser.parseString(response).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(json);
    } catch (Exception e) {
      return "error";
    }
  }

  @Get(value = "/covid/{code}", produces = MediaType.APPLICATION_JSON)
  public String covid(String code) {
    try {
      HttpRequest<?> request;
      if (code.equals("help")) {
        request = HttpRequest
            .GET("https://api.covid19api.com/").accept(MediaType.APPLICATION_JSON_TYPE);
      } else {
        request = HttpRequest
            .GET("https://api.covid19api.com/" + code).accept(MediaType.APPLICATION_JSON_TYPE);
      }

      String response = httpClient.toBlocking().retrieve(request);
      JsonObject json = JsonParser.parseString(response).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(json);
    } catch (Exception e) {
      return "error";
    }
  }

  @Get(value = "/covid/country/{code}", produces = MediaType.APPLICATION_JSON)
  public String country(String code) {
    try {
      HttpRequest<?> request = HttpRequest
          .GET("https://api.covid19api.com/country/" + code).accept(MediaType.APPLICATION_JSON_TYPE);

      String response = httpClient.toBlocking().retrieve(request);
      JsonObject json = JsonParser.parseString(response).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      return gson.toJson(json);
    } catch (Exception e) {
      return "error";
    }
  }
}


