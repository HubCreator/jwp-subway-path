package subway.ui.line.dto;

import java.util.List;

public class GetAllStationsInLineResponse {

    private final Long lineId;
    private final String lineName;
    private final List<StationDto> stations;

    public GetAllStationsInLineResponse(final Long lineId, final String lineName, final List<StationDto> stationDtos) {
        this.lineId = lineId;
        this.lineName = lineName;
        this.stations = stationDtos;
    }

    public Long getLineId() {
        return lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public List<StationDto> getStations() {
        return stations;
    }
}