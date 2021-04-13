package co.kruzr.bernoulli;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Model class to encapsulate the actual present state of permissions and settings for a given Stream object.
 */
class EvaluatedStream {

    /**
     * The method whose evaluated state is encapsulated in this object.
     */
    @Getter
    private Stream stream;

    /**
     * The list of permissions that the stream (method) requires and which are missing, after incorporating the
     * disabled policy.
     */
    @Getter
    private List<Permission> missingPermissions = new ArrayList<>();

    /**
     * The list of settings that the stream (method) requires and which are disabled, after incorporating the
     * disabled policy.
     */
    @Getter
    private List<Settings> missingSettings = new ArrayList<>();

    public EvaluatedStream(Stream stream) {
        this.stream = stream;
    }
}
