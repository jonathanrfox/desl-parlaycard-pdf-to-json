package com.parlay;

import java.util.List;

import com.parlay.Game;
import com.parlay.ParlayCard;


public class RegularCard extends ParlayCard {
    public RegularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> textList) {
        for (int i=0; i < textList.size() - 1; i += 2) {
            System.out.println(textList.get(i));
            System.out.println(textList.get(i + 1));
            // Game game = new Game();

            // parseTeamsAndSpreads(game, textList.get(i));
            // parseOverUnder(game, textList.get(i + 1));
            // addGame(game);
        }
    }
}
