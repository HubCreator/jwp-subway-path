package subway.ui.line.dto;

import subway.domain.line.Line;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllStationsInLineResponses {

    private final List<GetAllStationsInLineResponse> lines;

    public GetAllStationsInLineResponses(final List<Line> lines) {
        this.lines = lines.stream()
                .map(line -> {
                    final List<StationDto> stationDtos = line.stations().stream()
                            .map(station -> new StationDto(station.getId(), station.getName()))
                            .collect(Collectors.toList());
                    return new GetAllStationsInLineResponse(line.getId(), line.getName(), stationDtos);
                })
                .collect(Collectors.toList());
    }

    public List<GetAllStationsInLineResponse> getLines() {
        return lines;
    }
}