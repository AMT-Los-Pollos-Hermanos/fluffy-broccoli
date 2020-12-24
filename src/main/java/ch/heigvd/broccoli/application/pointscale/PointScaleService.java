package ch.heigvd.broccoli.application.pointscale;

import ch.heigvd.broccoli.application.BaseService;
import ch.heigvd.broccoli.domain.pointscale.PointScale;
import ch.heigvd.broccoli.domain.pointscale.PointScaleRepository;
import org.springframework.stereotype.Service;

@Service
public class PointScaleService extends BaseService<PointScaleDTO, PointScale> {

    PointScaleService(PointScaleRepository repository) { this.repository = repository; }

    @Override
    public PointScaleDTO add(PointScaleDTO entity) {
        return toDTO(repository.save(PointScale.builder()
                .name(entity.getName())
                .application(app())
                .build()));
    }

    @Override
    public PointScaleDTO update(Long id, PointScaleDTO entity) {
        return toDTO(repository.findByIdAndApplication(id, app()).map(pointScale -> {
            pointScale.setName(entity.getName());
            return repository.save(pointScale);

            // TODO: Create global exception type for not found items
        }).orElseThrow(() -> new RuntimeException("Pointscale not found")));
    }

    @Override
    public PointScaleDTO toDTO(PointScale entity) {
        return PointScaleDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

}
