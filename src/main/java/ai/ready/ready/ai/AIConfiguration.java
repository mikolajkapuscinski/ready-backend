package ai.ready.ready.ai;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfiguration {

    static final String FIND_BOOK_SYSTEM_MESSAGE =
            "Given a book cover image, analyze the text and visual elements to determine the following details:  \n" +
                    "- **Title**  \n" +
                    "- **Author** \n" +
                    "- **ISBN-13** (If available)  \n" +
                    "- **ISBN-10** (If available)  \n" +
                    "- **Language** (Detected language of the book) \n" +
                    "- **Number of Pages** \n" +
                    "- **Description** (A short summary if available)  \n" +
                    "- **Date of First Publication**" +
                    "Return the extracted data in this structured JSON format, ensuring values are accurate and well-formed:" +
                    "```json\n" +
                    "{\n" +
                    "    \"title\": \"Extracted book title\",\n" +
                    "    \"author\": \"Extracted author(s)\",\n" +
                    "    \"isbn13\": \"978XXXXXXXXXX\",\n" +
                    "    \"isbn10\": \"XXXXXXXXXX\",\n" +
                    "    \"language\": \"\",\n" +
                    "    \"numberOfPages\":,\n" +
                    "    \"description\": \"Brief summary of the book\",\n" +
                    "    \"dateOfPublication\": \"YYYY-MM-DD\"\n" +
                    "}"
            ;

    static final OpenAiApi.ChatModel CHAT_MODEL = OpenAiApi.ChatModel.GPT_4_O_MINI;
}
