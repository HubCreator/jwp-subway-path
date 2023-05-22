package subway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subway.domain.fare.basic.DistanceBasicPolicy;
import subway.domain.fare.discount.AgeGroupDiscount;
import subway.domain.graph.SubwayGraph;
import subway.domain.line.Fare;
import subway.domain.line.Line;
import subway.domain.station.Station;
import subway.exception.StationNotFoundException;
import subway.repository.LineRepository;
import subway.repository.StationRepository;
import subway.ui.line.dto.ShortestPathResponse;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PathService {

    private final LineRepository lineRepository;
    private final StationRepository stationRepository;

    public PathService(final LineRepository lineRepository, final StationRepository stationRepository) {
        this.lineRepository = lineRepository;
        this.stationRepository = stationRepository;
    }

    public ShortestPathResponse getShortestPath(final Long fromId, final Long toId, final Integer age) {
        final Station fromStation = stationRepository.findById(fromId)
                .orElseThrow(() -> new StationNotFoundException(fromId));
        final Station toStation = stationRepository.findById(toId)
                .orElseThrow(() -> new StationNotFoundException(toId));

        final List<Line> lines = lineRepository.findAllWithSections();

        final SubwayGraph subwayGraph = SubwayGraph.from(lines);
        final int distance = subwayGraph.getShortestPathDistance(fromStation, toStation);
        final List<Station> stations = subwayGraph.getShortestPath(fromStation, toStation);
        final Fare fare = new Fare(new DistanceBasicPolicy(distance, lines))
                .applyDiscount(new AgeGroupDiscount(age));

        return new ShortestPathResponse(distance, stations, fare.getFare());
    }
}
