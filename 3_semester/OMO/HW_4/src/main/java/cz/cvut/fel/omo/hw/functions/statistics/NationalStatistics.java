package cz.cvut.fel.omo.hw.functions.statistics;

import java.util.List;
import java.util.Map;

public interface NationalStatistics {

    String getNameOfCityWithTheLowestVoterTurnout();

    String getNameOfCityWithTheHighestNonValidVotesRatio();

    List<String> getTop10CitiesWhereCandidateWonOrderedByNumberOfVotesDesc(int candidateId);

    Map<String, String> getRegionWinnerMap();

}
