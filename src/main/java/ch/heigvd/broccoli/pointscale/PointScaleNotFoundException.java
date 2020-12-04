package ch.heigvd.broccoli.pointscale;

class PointScaleNotFoundException extends RuntimeException {

    PointScaleNotFoundException(Long id) {
        super("Could not find point-scale " + id);
    }
}
