package ch.heigvd.broccoli.award;

import java.io.Serializable;

public interface Award extends Serializable {
    void apply(); // TODO userid comme paramètre
}
