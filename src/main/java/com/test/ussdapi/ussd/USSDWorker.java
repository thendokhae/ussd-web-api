package com.test.ussdapi.ussd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class USSDWorker {

    @Autowired
    private StateRepository stateRepository;

    public USSDResponse requestUSSD(USSDRequest request) {
        State state = stateRepository.findStateByStateKey(String.format("%s-%s", request.getSessionId(), request.getMsisdn()));
        if (state != null) {
            if (state.getStage() == 1 && Utils.isValidSecondStageEntry(request.getUserEntry())) {
                state.setStage(2);
                state.setSelectedCountry(request.getUserEntry());
                updateState(state);
            } else if (state.getStage() == 2 && Utils.isNumeric(request.getUserEntry())) {
                state.setStage(3);
                state.setAmountToSend(Double.valueOf(request.getUserEntry()));
                updateState(state);
            } else if (state.getStage() == 3 && Utils.isNumeric(request.getUserEntry())
                    && Utils.isValidThirdStageEntry(request.getUserEntry())) {
                state.setStage(4);
                updateState(state);
            } else if (state.getStage() == 4) {
                state.setStage(1);
                state.setAmountToSend(0.0);
                state.setSelectedCountry("");
                updateState(state);
            }
        } else {
            state = new State();
            state.setStage(1);
            state.setSessionId(request.getSessionId());
            state.setStateKey(String.format("%s-%s", request.getSessionId(), request.getMsisdn()));
            updateState(state);
        }
        stateRepository.save(state);
        return generateResponse(state);

    }

    private USSDResponse generateResponse(State state) {
        USSDResponse ussdResponse = new USSDResponse();
        ussdResponse.setSessionId(state.getSessionId());
        switch (state.getStage()) {
            case 1:
                ussdResponse.setMessage(Utils.getMessageByStage(state.getStage()));
                break;
            case 2:
                ussdResponse.setMessage(Utils.getMessageByStage(state.getStage())
                        .replace("<CountryName>", Utils.getCountryNameByEntry(state.getSelectedCountry())));
                break;
            case 3:
                ussdResponse.setMessage(Utils.getMessageByStage(state.getStage())
                        .replace("<Amount>", Utils.getForexAmount(state.getAmountToSend(), state.getSelectedCountry()))
                        .replace("<ForeignCurrencyCode>", Utils.getCurrencyCodeFromEntry(state.getSelectedCountry())));
                break;
            case 4:
                ussdResponse.setMessage(Utils.getMessageByStage(state.getStage()));
                clearState(state);
                break;
            default:
        }
        return ussdResponse;
    }

    private void updateState(State state) {
        stateRepository.save(state);
    }

    private void clearState(State state) {
        stateRepository.delete(state);
    }
}
