package cz.cvut.fel.omo.hw.functions.utils;

import cz.cvut.fel.omo.hw.functions.data.model.Candidate;
import cz.cvut.fel.omo.hw.functions.data.model.Candidates;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

@RequiredArgsConstructor
public class CandidateUtilsImpl implements CandidateUtils {

    private final CompletableFuture<Candidates> candidates;

    @Override
    public Optional<String> getCandidateFullName(int id) {
        try {
            return candidates.thenApply(Candidates::getCandidatesList)
                    .thenApply(candidateList -> candidateList.stream()
                            .filter(candidate -> candidate.getId() == id)
                            .findFirst()
                            .map(Candidate::getFullName))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> getCandidateAge(int id) {
        try {
            return candidates.thenApply(Candidates::getCandidatesList)
                    .thenApply(candidateList -> candidateList.stream()
                            .filter(candidate -> candidate.getId() == id)
                            .findFirst()
                            .map(Candidate::getAge))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> getCandidateAttribute(int id, Function<Candidate, T> mappingFunction) {
        try {
            return candidates.thenApply(Candidates::getCandidatesList)
                    .thenApply(candidateList -> candidateList.stream()
                            .filter(candidate -> candidate.getId() == id)
                            .findFirst()
                            .map(mappingFunction))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Candidate> getCandidate(int id) {
        try {
            return candidates.thenApply(Candidates::getCandidatesList)
                    .thenApply(candidateList -> candidateList.stream()
                            .filter(candidate -> candidate.getId() == id)
                            .findFirst())
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }
}
