package cz.cvut.fel.omo.hw.functions.utils;

import cz.cvut.fel.omo.hw.functions.data.model.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*Deklaraci proměnných omezte a použijte místo toho pomocnou private metodu. Informace z cw.fel.cvut.cz.
Proto jsem udelal nekolik metod :D you can't say that there are variables:D*/
@RequiredArgsConstructor
public class ElectionsUtilsImpl implements ElectionsUtils {

    private final CompletableFuture<List<RegionResults>> regionResultsFuture;
    private final CompletableFuture<AbroadResults> abroadResultsFuture;

    @Override
    public List<Vote> getAllVotes() {
        try {
            return Stream.concat(
                    getAbroadVotes(),
                    getRegionVotes()
            ).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<VoterTurnout> getAllVoterTurnouts() {
        try {
            return Stream.concat(
                    getAbroadVoterTurnouts(),
                    getRegionVoterTurnouts()
            ).collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Vote> getAbroadVotes() throws InterruptedException, ExecutionException {
        return abroadResultsFuture.get().getAbroad().getContinents().stream()
                .flatMap(continent -> continent.getCountries().stream())
                .flatMap(country -> country.getVotes().stream());
    }

    private Stream<Vote> getRegionVotes() throws InterruptedException, ExecutionException {
        return regionResultsFuture.get().stream()
                .flatMap(region -> region.getRegions().stream())
                .flatMap(district -> district.getDistricts().stream())
                .flatMap(city -> city.getCities().stream())
                .flatMap(votesList -> votesList.getVotes().stream());
    }

    private Stream<VoterTurnout> getAbroadVoterTurnouts() throws InterruptedException, ExecutionException {
        return abroadResultsFuture.get().getAbroad().getContinents().stream()
                .flatMap(countryList -> countryList.getCountries().stream())
                .map(Country::getVoterTurnout);
    }

    private Stream<VoterTurnout> getRegionVoterTurnouts() throws InterruptedException, ExecutionException {
        return regionResultsFuture.get().stream()
                .flatMap(regionList -> regionList.getRegions().stream())
                .flatMap(districtList -> districtList.getDistricts().stream())
                .flatMap(cityList -> cityList.getCities().stream())
                .map(City::getVoterTurnout);
    }
}
