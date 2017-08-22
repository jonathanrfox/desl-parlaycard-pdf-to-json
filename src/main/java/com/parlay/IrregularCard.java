package com.parlay;

import java.util.List;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;
import com.parlay.SplitString;


class IrregularCard extends ParlayCard {

    public IrregularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            SplitString line = new SplitString(lines.get(i), ':', 1);

            String lhTeam = ParlayUtils.parseTeam(line.lhs);
            String lhSpread = ParlayUtils.parseSpread(line.lhs);

            String rhTeam = ParlayUtils.parseTeam(line.rhs);
            String rhSpread = ParlayUtils.parseSpread(line.rhs);

            // log data
            // System.out.println(line.lhs);
            // System.out.println(line.rhs);

            // System.out.println(lhTeam);
            // System.out.println(lhSpread);

            // System.out.println(rhTeam);
            // System.out.println(rhSpread);

            addGame(new Game(lhTeam, lhSpread, rhTeam, rhSpread));
        }
    }
}
