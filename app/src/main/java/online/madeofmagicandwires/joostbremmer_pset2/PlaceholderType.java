package online.madeofmagicandwires.joostbremmer_pset2;

/**
 * Represents the type of word a placeholder is to be replaced by
 *
 * @note switch-case hell.
 */
public enum PlaceholderType {
    ADJECTIVE,
    ADJECTIVE_INTERESTING,
    ADJECTIVE_EXCITING,
    ADJECTIVE_UNUSUAL,
    ADVERB,
    BODY_PART,
    CITY,
    COLOR,
    FUNNY_NOISE,
    JOB,
    JOB_TITLE,
    MALE_NAME,
    NOUN,
    NO_NEXT_PLACEHOLDER,
    NUMBER,
    PERSON_NAME,
    PLACE,
    PLURAL,
    VERB,
    UNKNOWN;

    /**
     * "Parses" a placeholder string and returns its type
     *
     * @param placeHolderInput a word or words to be replaced
     * @return the type of word to be replaced by the user
     *
     *
     */
    public static PlaceholderType parsePlaceHolderType(String placeHolderInput) {
        switch (placeHolderInput.toLowerCase()) {
            case "adjective":
                return PlaceholderType.ADJECTIVE;

            case "interesting adjective":
                return PlaceholderType.ADJECTIVE_INTERESTING;

            case "exciting adjective":
                return PlaceholderType.ADJECTIVE_EXCITING;

            case "unusual adjective":
                return PlaceholderType.ADJECTIVE_UNUSUAL;

            case "adverb":
                return PlaceholderType.ADVERB;

            case "body part":
                return PlaceholderType.BODY_PART;

            case "city":
                return PlaceholderType.CITY;

            case "color!":
                return PlaceholderType.COLOR;

            case "funny noise":
                return PlaceholderType.FUNNY_NOISE;

            case "job":
                return PlaceholderType.JOB;

            case "job title":
                return PlaceholderType.JOB_TITLE;

            case "male name":
                return PlaceholderType.MALE_NAME;

            case "number":
                return PlaceholderType.NUMBER;

            case "noun":
                return PlaceholderType.NOUN;

            case "person's name":
                return PlaceholderType.PERSON_NAME;

            case "place":
                return PlaceholderType.PLACE;

            case "plural noun":
                return PlaceholderType.PLURAL;

            case "verb":
                return PlaceholderType.VERB;

            default:
                return PlaceholderType.UNKNOWN;

        }
    }


    /**
     * Returns a String representation of a placeholder's type
     *
     * @return A human readable representation of the PlaceholderType enum
     */
    @Override
    public String toString() {
        switch (this) {
            case ADJECTIVE:
                return "An adjective";
            case ADJECTIVE_EXCITING:
                return "An exciting adjective";
            case ADJECTIVE_INTERESTING:
                return "An interesting adjective";
            case ADJECTIVE_UNUSUAL:
                return "An unusual adjective";
            case ADVERB:
                return "An adverb";
            case BODY_PART:
                return "A body part";
            case CITY:
                return "A city";
            case COLOR:
                return "A color";
            case FUNNY_NOISE:
                return "A funny noise";
            case JOB:
                return "A job";
            case JOB_TITLE:
                return "A job title";
            case MALE_NAME:
                return "A male name";
            case NOUN:
                return "A noun";
            case NUMBER:
                return "A number";
            case PERSON_NAME:
                return "A person's name";
            case PLACE:
                return "A place";
            case PLURAL:
                return "A plural noun";
            case VERB:
                return "A verb";
            case UNKNOWN:
                return "An unknown type";
            case NO_NEXT_PLACEHOLDER:
                return "There aren't any placeholder's left!";

            default:
                return "Something went wrong!";
        }
    }
}
