package dspoetry;

/*
 * Base class for all Metered Poems
 */

abstract class MeteredPoem extends Poem{
    // ' = stressed, - = unstressed
    // E.g.: "-'-'-'-'-'" would be iambic pentameter
    String meter;

    // Leave generatePoem() unimplemented here, by doing nothing.

}
