package factFinder;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.speechlet.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles Alexa Skill requests for finding facts.
 */
@Slf4j
public class FactFinderSpeechlet implements SpeechletV2 {

    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {

    }

    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        return null;
    }

    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {
        return null;
    }

    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {

    }
}
