package com.parlay;

import java.util.List;

import com.parlay.Game;
import com.parlay.ParlayCard;
import com.parlay.ParlayUtils;
import com.parlay.SplitString;


public class RegularCard extends ParlayCard {

    public RegularCard(String parlayType) {
        super(parlayType);
    }

    public void consume(List<String> lines) {
        for (int i = 0; i < lines.size() - 1; i += 2) {
            SplitString top = new SplitString(lines.get(i), ':', 1);
            SplitString bottom = new SplitString(lines.get(i + 1), '½', 2);

            String lhTeam = ParlayUtils.parseTeam(top.lhs);
            String lhSpread = ParlayUtils.parseSpread(top.lhs);

            String rhTeam = ParlayUtils.parseTeam(top.rhs);
            String rhSpread = ParlayUtils.parseSpread(top.rhs);

            String over = ParlayUtils.parseOver(bottom.lhs);
            String under = ParlayUtils.parseUnder(bottom.rhs);

            // log data
            // System.out.println(top.lhs);
            // System.out.println(top.rhs);

            // System.out.println(bottom.lhs);
            // System.out.println(bottom.rhs);

            // System.out.println(lhTeam);
            // System.out.println(lhSpread);

            // System.out.println(rhTeam);
            // System.out.println(rhSpread);

            // System.out.println(over);
            // System.out.println(under);

            addGame(new Game(lhTeam, lhSpread, rhTeam, rhSpread, over, under));
        }
    }
}
