package cz.cvut.fel.omo.hw.functions.statistics;

import cz.cvut.fel.omo.hw.functions.data.model.*;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtils;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtilsImpl;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NationalStatisticsImpl implements NationalStatistics {

    private final CompletableFuture<List<RegionResults>> regionResults;
    private final CandidateUtils candidateUtils;

    public NationalStatisticsImpl(CompletableFuture<List<RegionResults>> regionResults, CompletableFuture<Candidates> candidates) {
        this.regionResults = regionResults;
        this.candidateUtils = new CandidateUtilsImpl(candidates);
    }

    @Override
    public String getNameOfCityWithTheLowestVoterTurnout() {
        return getCityWithLowestTurnout().orElse(null);
    }

    private Optional<String> getCityWithLowestTurnout() {
        return regionResults.join().stream()
                .flatMap(region -> region.getRegions().stream())
                .flatMap(region -> region.getDistricts().stream())
                .flatMap(district -> district.getCities().stream())
                .min(Comparator.comparing(city -> calculateTurnout(city.getVoterTurnout())))
                .map(City::getName);
    }
    private double calculateTurnout(VoterTurnout voterTurnout) {
        return voterTurnout.getNumberOfSubmittedVotingEnvelopes() / (double) voterTurnout.getNumberOfRegisteredVoters();
    }


    @Override
    public String getNameOfCityWithTheHighestNonValidVotesRatio() {
        try {
            return regionResults.thenApply(regionResultsList -> regionResultsList.stream()
                            .flatMap(regionResults -> regionResults.getRegions().stream())
                            .flatMap(region -> region.getDistricts().stream())
                            .flatMap(district -> district.getCities().stream())
                            .max(Comparator.comparingDouble(city ->
                                    (double) (city.getVoterTurnout().getNumberOfIssuedVotingEnvelopes() - city.getVoterTurnout().getNumberOfValidVotes())
                                            / city.getVoterTurnout().getNumberOfIssuedVotingEnvelopes()
                            ))
                            .map(City::getName)
                            .orElse(null))
                    .get();
        } catch (Exception e) {
            return null;
        }
    }



    private boolean hasCandidateWonInCity(City city, int candidateId) {
        return city.getVotes().stream()
                .collect(Collectors.groupingBy(Vote::getCandidateId, Collectors.summingInt(Vote::getVotes)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .stream().findFirst()
                .map(entry -> entry.getKey().equals(candidateId))
                .orElse(false);
    }

    private int getTotalVotesForCandidateInCity(City city, int candidateId) {
        return city.getVotes().stream()
                .filter(vote -> vote.getCandidateId() == candidateId)
                .mapToInt(Vote::getVotes)
                .sum();
    }

    @Override
    public List<String> getTop10CitiesWhereCandidateWonOrderedByNumberOfVotesDesc(int candidateId) {
        return regionResults.thenApply(regResults ->
                regResults.stream()
                        .flatMap(regionResults -> regionResults.getRegions().stream()
                                .flatMap(region -> getCitiesWhereCandidateWon(region, candidateId))
                        )
                        .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed())
                        .limit(10)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList())
        ).join();
    }

    private Stream<Map.Entry<String, Integer>> getCitiesWhereCandidateWon(Region region, int candidateId) {
        return region.getDistricts().stream()
                .flatMap(district -> district.getCities().stream()
                        .filter(city -> hasCandidateWonInCity(city, candidateId))
                        .map(city -> new AbstractMap.SimpleEntry<>(city.getName(), getTotalVotesForCandidateInCity(city, candidateId)))
                );
    }

    @Override
    public Map<String, String> getRegionWinnerMap() {
        try {
            return regionResults.thenApply(regionResultsList -> regionResultsList.stream()
                            .flatMap(regionResults -> regionResults.getRegions().stream())
                            .collect(Collectors.toMap(
                                    Region::getName,
                                    region -> region.getDistricts().stream()
                                            .flatMap(district -> district.getCities().stream())
                                            .flatMap(city -> city.getVotes().stream())
                                            .collect(Collectors.groupingBy(
                                                    vote -> candidateUtils.getCandidateFullName(vote.getCandidateId()).orElse("Unknown"),
                                                    Collectors.summingInt(Vote::getVotes)
                                            ))
                                            .entrySet().stream()
                                            .max(Map.Entry.comparingByValue())
                                            .map(Map.Entry::getKey)
                                            .orElse("Unknown")
                            )))
                    .get();
        } catch (Exception e) {
            return null;
        }
    }


}
