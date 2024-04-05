package cz.cvut.fel.omo.hw.functions.statistics;

import cz.cvut.fel.omo.hw.functions.data.model.*;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtils;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtilsImpl;
import cz.cvut.fel.omo.hw.functions.utils.ElectionsUtils;
import cz.cvut.fel.omo.hw.functions.utils.ElectionsUtilsImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ElectionStatisticsImpl implements ElectionStatistics {

    private final ElectionsUtils electionsUtils;
    private final CandidateUtils candidateUtils;

    public ElectionStatisticsImpl(CompletableFuture<List<RegionResults>> regionResultsFuture, CompletableFuture<AbroadResults> abroadResultsFuture, CompletableFuture<Candidates> candidatesFuture) {
        this.candidateUtils = new CandidateUtilsImpl(candidatesFuture);
        this.electionsUtils = new ElectionsUtilsImpl(regionResultsFuture, abroadResultsFuture);
    }

    @Override
    public int getTotalValidVotes() {
        return electionsUtils.getAllVotes()
                .stream()
                .map(Vote::getVotes)
                .reduce(0, Integer::sum);
    }
    @Override
    public int getTotalInvalidVotes() {
        return electionsUtils.getAllVoterTurnouts().stream()
                .mapToInt(voterTurnout -> voterTurnout.getNumberOfSubmittedVotingEnvelopes())
                .reduce(0, Integer::sum) - getTotalValidVotes();
    }

    @Override
    public int getTotalVoterCount() {
        return electionsUtils.getAllVoterTurnouts().stream()
                .mapToInt(voterTurnout -> voterTurnout.getNumberOfRegisteredVoters())
                .reduce(0, Integer::sum);
    }

    @Override
    public int getTotalIssuedEnvelopes() {
        return electionsUtils.getAllVoterTurnouts().stream()
                .mapToInt(voterTurnout -> voterTurnout.getNumberOfIssuedVotingEnvelopes())
                .reduce(0, Integer::sum);
    }

    @Override
    public double getTotalVoterTurnout() {
        return getTotalVoterCount() == 0 ? 0 : (getTotalIssuedEnvelopes() * 100.0) / getTotalVoterCount();
    }

    @Override
    public Map<String, Integer> getCandidateVotesMap() {
        return electionsUtils.getAllVotes().stream()
                .collect(Collectors.groupingBy(
                        vote -> candidateUtils.getCandidateFullName(vote.getCandidateId())
                                .map(String::valueOf)
                                .orElse("Unknown"),
                        Collectors.reducing(0, Vote::getVotes, Integer::sum)
                ));
    }

    @Override
    public Map<String, Double> getCandidateVotesPercentMap() {
        return getCandidateVotesMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue().doubleValue() / getTotalValidVotes()) * 100
                ));
    }

    @Override
    public String getCandidatesByVotesDesc() {
        return getCandidateVotesMap().entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> entry.getKey() + " (" + String.format("%.2f%%", ((entry.getValue() / (double) getTotalValidVotes()) * 100)) + ")")
                .collect(Collectors.joining(", "));
    }
}
