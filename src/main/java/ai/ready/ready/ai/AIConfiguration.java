package ai.ready.ready.ai;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    static final String FIND_BOOK_SYSTEM_MESSAGE =
            "\"Given a book cover image, analyze the text and visual elements to extract relevant details. Additionally, use online sources to verify and supplement the extracted information. Determine the following details:\n" +
                    "\n" +
                    "Title (Extract from the image and verify online)\n" +
                    "Author (Identify from the image and confirm via online sources)\n" +
                    "ISBN-13 & ISBN-10 (Extract if visible; otherwise, retrieve from online databases)\n" +
                    "Language (Detect from the cover text and verify against book listings)\n" +
                    "Number of Pages (If not available on the cover, fetch from online sources)\n" +
                    "Description (Summarize from the image if possible, otherwise retrieve a verified summary online)\n" +
                    "Date of First Publication (Extract or verify using book databases)\n" +
                    "Ensure accuracy by cross-referencing details from multiple reliable online sources where necessary.\""
            ;

    static final OpenAiApi.ChatModel CHAT_MODEL = OpenAiApi.ChatModel.GPT_4_O_MINI;
}
