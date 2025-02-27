package ai.ready.ready.ai;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import static ai.ready.ready.ai.AIConfiguration.CHAT_MODEL;
import static ai.ready.ready.ai.AIConfiguration.FIND_BOOK_SYSTEM_MESSAGE;

@Service
@AllArgsConstructor
public class AIService {

    private final OpenAiChatModel openAiChatModel;

    public String findBook(MultipartFile image) {

        var outputConverter = new BeanOutputConverter<>(FindBookResponseFormat.class);

        var jsonSchema = outputConverter.getJsonSchema();

        var userMessage = new UserMessage(FIND_BOOK_SYSTEM_MESSAGE,new Media(MimeTypeUtils.IMAGE_PNG, image.getResource()));

        Prompt prompt = new Prompt(userMessage,
                OpenAiChatOptions.builder()
                        .model(CHAT_MODEL)
                        .responseFormat(new ResponseFormat(ResponseFormat.Type.JSON_SCHEMA, jsonSchema))
                        .build()
                );

        ChatResponse response = openAiChatModel.call(prompt);

        String content = response.getResult().getOutput().getText();

        return content;
    }
}
