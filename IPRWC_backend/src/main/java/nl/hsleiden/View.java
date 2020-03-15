package nl.hsleiden;

/**
 * Meer informatie over views:
 * http://www.baeldung.com/jackson-json-view-annotation
 *
 * @author Peter van Vliet
 * @since 1.0
 */
public class View {
    private View() {
    }

    public interface Public {
    }

    public static class Internal extends Private {
    }

    public static class Private extends Protected {
    }

    public static class Protected implements Public {
    }
}
