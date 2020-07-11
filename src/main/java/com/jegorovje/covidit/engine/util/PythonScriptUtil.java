package com.jegorovje.covidit.engine.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jegorovje.covidit.engine.worker.CovidDataScrapperLiveWorker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.inject.Singleton;
import lombok.SneakyThrows;

//todo Создать аннотацию для привтных конструкторов
@Singleton
public class PythonScriptUtil {

  private PythonScriptUtil() {
  }

  @SneakyThrows
  public static String getGsonFromWorldmapCovid() {
    String path = "worldmometersCovid.scripts/script.py";
    String filePath = CovidDataScrapperLiveWorker.class.getClassLoader().getResource(path)
        .getPath();

    ProcessBuilder processBuilder = new ProcessBuilder("python3", filePath);
    processBuilder.redirectErrorStream(true);

    Process process = processBuilder.start();
    String jsonValues;
    try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
      String response = in.readLine();
      JsonObject json = JsonParser.parseString(response).getAsJsonObject();

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      jsonValues = gson.toJson(json);
    }
    return jsonValues;
  }

}
