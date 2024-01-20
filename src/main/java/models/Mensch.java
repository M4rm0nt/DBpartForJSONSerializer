package models;

import enums.Geschlecht;
import interfaces.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Mensch extends Lebewesen {
    @JsonProperty
    private final int alter;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final LocalDateTime geburtstag;
    @JsonProperty
    private final LocalDateTime heuteTag;
    @JsonProperty
    private final List<String> hobbies;
    @JsonProperty
    private final Map<String, List<String>> haustiere;
    @JsonProperty
    private final Geschlecht geschlecht;

    public Mensch(int alter, String name, LocalDateTime geburtstag, LocalDateTime heuteTag, List<String> hobbies, Map<String, List<String>> haustiere, Geschlecht geschlecht) {
        super("Homo sapiens");
        this.alter = alter;
        this.name = name;
        this.geburtstag = geburtstag;
        this.heuteTag = heuteTag;
        this.hobbies = hobbies;
        this.haustiere = haustiere;
        this.geschlecht = geschlecht;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mensch mensch = (Mensch) o;
        return alter == mensch.alter && Objects.equals(name, mensch.name) && Objects.equals(geburtstag, mensch.geburtstag) && Objects.equals(heuteTag, mensch.heuteTag) && Objects.equals(hobbies, mensch.hobbies) && Objects.equals(haustiere, mensch.haustiere) && geschlecht == mensch.geschlecht;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alter, name, geburtstag, heuteTag, hobbies, haustiere, geschlecht);
    }

    @Override
    public String toString() {
        return "Mensch{" +
                "alter=" + alter +
                ", name='" + name + '\'' +
                ", geburtstag=" + geburtstag +
                ", heuteTag=" + heuteTag +
                ", hobbies=" + hobbies +
                ", haustiere=" + haustiere +
                ", geschlecht=" + geschlecht +
                ", art='" + art + '\'' +
                '}';
    }
}