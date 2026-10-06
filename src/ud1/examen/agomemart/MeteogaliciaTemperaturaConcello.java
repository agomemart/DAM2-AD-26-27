package ud1.examen.agomemart;

import com.google.gson.Gson;

/**
 * @author Adrián Gómez
 */
public class MeteogaliciaTemperaturaConcello {
    public static Double temperaturaConcello(String jsonObservacion, String concello) {
        if (jsonObservacion == null || jsonObservacion.isBlank() || concello == null || concello.isBlank()) {
            return null;
        }
        Gson gson = new Gson();
        Concello c = gson.fromJson(jsonObservacion, Concello.class);

        if (c.nomeConcello.contains(concello)) {
            return c.temperatura;
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(temperaturaConcello("observacionConcellos.json", "Marín"));
    }
}
