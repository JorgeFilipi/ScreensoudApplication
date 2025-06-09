package br.com.alura.ScreensoundApplication.service;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import io.github.cdimascio.dotenv.Dotenv;

public class ConsultaChatGPT {

    public static String obterInformacao(String texto) {
        Dotenv dotenv = Dotenv.load();
        String openIaApiKey = dotenv.get("OPENIA_API_KEY");

        if (openIaApiKey == null || openIaApiKey.isBlank()) {
            throw new IllegalStateException("Variável OPENIA_API_KEY não definida no .env");
        }

        OpenAiService service = new OpenAiService(openIaApiKey);


        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-4o-mini-tts")
                .prompt("me fale sobre o artista: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();


        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}