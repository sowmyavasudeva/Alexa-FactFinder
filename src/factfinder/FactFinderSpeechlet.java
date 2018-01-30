package factfinder;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;

import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles Alexa Skill requests for finding facts.
 */
@Slf4j
public class FactFinderSpeechlet implements SpeechletV2 {

    private static final String[] FACTS = new String[] {
            "Hot water will turn into ice faster than cold water.",
            "The Mona Lisa has no eyebrows.",
            "The sentence, “The quick brown fox jumps over the lazy dog” uses every letter in the English language.",
            "The strongest muscle in the body is the tongue.",
            "Ants never sleep!",
            "“I Am” is the shortest complete sentence in the English language.",
            "Coca-Cola was originally green.",
            "The most common name in the world is Mohammed.",
            "When the moon is directly overhead, you will weigh slightly less.",
            "Camels have three eyelids to protect themselves from the blowing desert sand.",
            "There are only two words in the English language that have all five vowels in order: “abstemious” and “facetious.”",
            "The name of all the continents end with the same letter that they start with.",
    };

    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {
        log.info("Session Started with session id = {}, request = id = {}", speechletRequestEnvelope.getSession().getSessionId(),
                speechletRequestEnvelope.getRequest().getRequestId());
    }

    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        log.info("Session launched with session id = {}, request = id = {}", speechletRequestEnvelope.getSession().getSessionId(),
                speechletRequestEnvelope.getRequest().getRequestId());
        return getResponse();
    }

    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {
        IntentRequest request = speechletRequestEnvelope.getRequest();


        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("GetNewFactIntent".equals(intentName)) {
            return getResponse();

        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();

        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            return getAskResponse("FactFinder", "I'm sorry. Can you try again");
        }
    }

    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {

    }

    private SpeechletResponse getResponse() {
        int factIndex = (int) Math.floor(Math.random() * FACTS.length);
        String fact = FACTS[factIndex];

        String speechText = "Here is your new space fact " + fact;
        SimpleCard card = getSimpleCard("SpaceGeek", speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText =
                "You can ask Space Geek tell me a space fact, or, you can say exit. What can I "
                        + "help you with?";
        return getAskResponse("SpaceGeek", speechText);
    }

    private SimpleCard getSimpleCard(String title, String content) {
        SimpleCard card = new SimpleCard();
        card.setTitle(title);
        card.setContent(content);
        return card;
    }

    private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);
        return speech;
    }

    private Reprompt getReprompt(OutputSpeech outputSpeech) {
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        return reprompt;
    }

    private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);
        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

}
