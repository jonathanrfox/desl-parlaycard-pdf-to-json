package com.parlay;

import java.util.List;

import com.parlay.Game;
import com.parlay.ParlayCard;


class IrregularCard extends ParlayCard {
    public IrregularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> textList) {
        for (int i=0; i < textList.size(); i++) {
            Game game = new Game();

            System.out.println(textList.get(i));
            parseTeamsAndSpreads(game, textList.get(i));
            addGame(game);
        }
    }
}
