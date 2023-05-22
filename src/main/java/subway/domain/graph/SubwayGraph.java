package subway.domain.graph;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.domain.line.Line;
import subway.domain.station.Station;

import java.util.List;

public class SubwayGraph {

    private final DijkstraShortestPath<Station, DefaultWeightedEdge> graph;

    public SubwayGraph(final DijkstraShortestPath<Station, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    public static SubwayGraph from(final List<Line> lines) {
        final WeightedMultigraph<Station, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        for (final Line line : lines) {
            line.stations().forEach(station -> {
                if (!graph.containsVertex(station)) {
                    graph.addVertex(station);
                }
            });
            line.sections()
                    .forEach(section -> {
                        final DefaultWeightedEdge edge = graph.addEdge(section.getUpStation(), section.getDownStation());
                        graph.setEdgeWeight(edge, section.getDistanceValue());
                    });
        }

        return new SubwayGraph(new DijkstraShortestPath<>(graph));
    }

    public int getShortestPathDistance(final Station fromStation, final Station toStation) {

        return (int) graph.getPathWeight(fromStation, toStation);
    }

    public List<Station> getShortestPath(final Station fromStation, final Station toStation) {

        return graph.getPath(fromStation, toStation).getVertexList();
    }
}
