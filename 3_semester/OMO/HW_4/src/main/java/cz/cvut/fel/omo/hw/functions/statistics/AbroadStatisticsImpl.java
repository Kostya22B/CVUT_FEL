package cz.cvut.fel.omo.hw.functions.statistics;

import cz.cvut.fel.omo.hw.functions.data.model.*;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtils;
import cz.cvut.fel.omo.hw.functions.utils.CandidateUtilsImpl;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class AbroadStatisticsImpl implements AbroadStatistics {

    private final CompletableFuture<AbroadResults> abroadResults;
    private final CandidateUtils candidateUtils;

    public AbroadStatisticsImpl(CompletableFuture<AbroadResults> abroadResults, CompletableFuture<Candidates> candidates) {
        this.abroadResults = abroadResults;
        this.candidateUtils = new CandidateUtilsImpl(candidates);
    }

    @Override
    public String getNameOfCountryWithTheHighestNonValidVotesRatio() {
        try {
            return abroadResults.thenApply(AbroadResults::getAbroad)
                    .thenApply(Abroad::getContinents)
                    .thenApply(this::findCountryWithHighestNonValidVotesRatio)
                    .get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Map<String, List<String>> getCandidateVictoryCountryMap() {
        try {
            return abroadResults.get().getAbroad().getContinents().stream()
                    .flatMap(continent -> continent.getCountries().stream())
                    .collect(Collectors.groupingBy(
                            this::getCandidateFullNameForCountry,
                            Collectors.mapping(Country::getName, Collectors.toList())
                    ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String, Integer> getContinentRegisteredVoterCountMap() {
        try {
            return abroadResults.thenApply(AbroadResults::getAbroad)
                    .thenApply(Abroad::getContinents)
                    .thenApply(this::calculateContinentRegisteredVoterCount)
                    .get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getNameOfCountryWithMostRegisteredVoters() {
        try {
            return abroadResults.thenApply(AbroadResults::getAbroad)
                    .thenApply(Abroad::getContinents)
                    .thenApply(this::findCountryWithMostRegisteredVoters)
                    .get();
        } catch (Exception e) {
            return null;
        }
    }

    private String findCountryWithHighestNonValidVotesRatio(List<Continent> continents) {
        return continents.stream()
                .flatMap(continent -> continent.getCountries().stream())
                .collect(Collectors.toMap(Country::getName, country -> 1.0 - ((double) country.getVoterTurnout().getNumberOfValidVotes() / country.getVoterTurnout().getNumberOfIssuedVotingEnvelopes())))
                .entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private String getCandidateFullNameForCountry(Country country) {
        return candidateUtils.getCandidateFullName(
                country.getVotes().stream()
                        .max(Comparator.comparingInt(Vote::getVotes))
                        .map(Vote::getCandidateId)
                        .orElseThrow(IllegalStateException::new)
        ).orElseThrow(IllegalStateException::new);
    }

    private Map<String, Integer> calculateContinentRegisteredVoterCount(List<Continent> continents) {
        return continents.stream()
                .collect(Collectors.toMap(Continent::getName, continent -> continent.getCountries().stream()
                        .mapToInt(country -> country.getVoterTurnout().getNumberOfRegisteredVoters())
                        .sum()));
    }

    private String findCountryWithMostRegisteredVoters(List<Continent> continents) {
        return continents.stream()
                .flatMap(continent -> continent.getCountries().stream())
                .collect(Collectors.toMap(Country::getName, country -> country.getVoterTurnout().getNumberOfRegisteredVoters()))
                .entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
