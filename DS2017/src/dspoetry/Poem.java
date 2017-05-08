package dspoetry;

/*
 * Base class for our poetry project
 */

abstract class Poem{
    // The poem field will be the actual poem
    String poem;

    /**
     * This is an abstract method that all Poem subclasses can
     * implement, but this class kicks the implementation down
     * the road to the subclasses.
     */
    abstract String generatePoem();
}
