package org.mytranslator;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/translate")
public class TranslatorResource {

    private static final String API_KEY = "your api_key";
    private static final String GEMINI_API_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=" + API_KEY;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TranslationResponse translate(String inputJson) {
        try {
            JSONObject inputObj = new JSONObject(inputJson);
            String text = inputObj.getString("text");

            // Construire le JSON pour Gemini
            JSONObject requestBody = new JSONObject();
            requestBody.put("contents", new JSONArray()
                    .put(new JSONObject()
                            .put("role", "user")
                            .put("parts", new JSONArray()
                                    .put(new JSONObject().put("text",
                                            "Translate this to Darija (Moroccan Arabic) in Arabic script."
                                                    + " Give only one simple phrase translation, no explanation: " + text)))
                    )
            );

            // Appel HTTP
            URL url = new URL(GEMINI_API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                os.write(requestBody.toString().getBytes("UTF-8"));
            }

            // Lire la réponse
            InputStream is = con.getResponseCode() < 400 ? con.getInputStream() : con.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                responseStr.append(line);
            }

            JSONObject responseJson = new JSONObject(responseStr.toString());

            // Récupérer la traduction courte
            String translatedText;
            if (responseJson.has("candidates")) {
                String fullText = responseJson.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text")
                        .trim();

                // GARDER SEULEMENT LA PREMIERE LIGNE (texte simple)
                translatedText = fullText.split("\n")[0];
            } else {
                translatedText = text; // fallback si erreur
            }

            // Retourner la réponse propre
            return new TranslationResponse(translatedText);

        } catch (Exception e) {
            e.printStackTrace();
            return new TranslationResponse("Translation failed: " + e.getMessage());
        }
    }
}
