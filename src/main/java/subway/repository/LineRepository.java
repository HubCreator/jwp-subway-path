package subway.repository;

import org.springframework.stereotype.Repository;
import subway.dao.LineDao;
import subway.dao.SectionDao;
import subway.domain.line.Line;
import subway.domain.section.Section;
import subway.domain.section.Sections;
import subway.exception.LineNotFoundException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LineRepository {

    private final LineDao lineDao;
    private final SectionDao sectionDao;

    public LineRepository(final LineDao lineDao, final SectionDao sectionDao) {
        this.lineDao = lineDao;
        this.sectionDao = sectionDao;
    }

    public Line save(final Line line) {
        final Line insertedLine = lineDao.insert(line.getLineNameValue(), line.getExtraFareValue());
        sectionDao.insertAllByLineId(insertedLine.getId(), line.sections());

        return findLineWithSectionsByLineId(insertedLine.getId())
                .orElseThrow(() -> new LineNotFoundException(insertedLine.getId()));
    }

    public Optional<Line> findLineByName(final Line line) {

        return lineDao.findByName(line.getLineNameValue());
    }

    public Optional<Line> findLineWithSectionsByLineId(final Long lineId) {
        final Line findLine = lineDao.findById(lineId)
                .orElseThrow(() -> new LineNotFoundException(lineId));
        final List<Section> findSections = sectionDao.findAllByLineId(findLine.getId());
        final Sections sections = new Sections(findSections);

        return Optional.of(new Line(findLine.getId(), findLine.getLineNameValue(), findLine.getExtraFareValue(), sections));
    }

    public Optional<Line> findById(final Long lineId) {

        return lineDao.findById(lineId);
    }

    public List<Line> findAllWithSections() {
        final List<Line> allLine = lineDao.findAll();

        return allLine.stream()
                .map(line -> new Line(
                        line.getId(), line.getLineNameValue(),
                        line.getExtraFareValue(),
                        new Sections(new LinkedList<>(sectionDao.findAllByLineId(line.getId())))))
                .collect(Collectors.toList());
    }
}
