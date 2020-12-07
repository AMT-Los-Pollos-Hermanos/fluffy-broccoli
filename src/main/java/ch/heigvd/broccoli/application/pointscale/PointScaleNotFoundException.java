package ch.heigvd.broccoli.application.pointscale;

public class PointScaleNotFoundException extends RuntimeException {

    public PointScaleNotFoundException(Long id) {
        super("Could not find point-scale " + id);
    }
}
