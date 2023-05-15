package subway.domain.edge;

import subway.domain.station.Station;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UpDirectionStrategy implements DirectionStrategy {

    @Override
    public Edges calculate(final List<Edge> edges, final Station existStation, final Station newStation, final Integer distance) {

        final Optional<Edge> existEdgeOptional = edges.stream()
                .filter(edge -> edge.getDownStation().equals(existStation))
                .findFirst();

        if (existEdgeOptional.isPresent()) {
            final Edge existEdge = existEdgeOptional.get();
            if (existEdge.getDistance() <= distance) {
                throw new IllegalArgumentException("추가하려는 구간의 길이가 기존 구간의 길이보다 깁니다.");
            }
            final Edge edge1 = new Edge(existEdge.getUpStation(), newStation, existEdge.getDistance() - distance);
            final Edge edge2 = new Edge(newStation, existStation, distance);
            final int existIndex = edges.indexOf(existEdge);
            edges.remove(existIndex);
            edges.add(existIndex, edge2);
            edges.add(existIndex, edge1);
        }
        if (existEdgeOptional.isEmpty()) {
            edges.add(0, new Edge(newStation, existStation, distance));
        }
        return new Edges(new LinkedList<>(edges));
    }
}