package ufo.dto;

public class UfoSighting {
    private String dateSeen;
    private String dateReported;
    private String placeSeen;
    private String shape;
    private String duration;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UfoSighting that = (UfoSighting) o;

        if (!dateSeen.equals(that.dateSeen)) return false;
        if (!dateReported.equals(that.dateReported)) return false;
        if (!placeSeen.equals(that.placeSeen)) return false;
        if (shape != null ? !shape.equals(that.shape) : that.shape != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = dateSeen.hashCode();
        result = 31 * result + dateReported.hashCode();
        result = 31 * result + placeSeen.hashCode();
        result = 31 * result + (shape != null ? shape.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + description.hashCode();
        return result;
    }

    public UfoSighting(String dateSeen, String dateReported, String placeSeen, String shape, String duration, String description) {
        this.dateSeen = dateSeen;
        this.dateReported = dateReported;
        this.placeSeen = placeSeen;
        this.shape = shape;
        this.duration = duration;
        this.description = description;
    }

    @Override
    public String toString() {
        return "UfoSighting{" +
                "dateSeen='" + dateSeen + '\'' +
                ", dateReported='" + dateReported + '\'' +
                ", placeSeen='" + placeSeen + '\'' +
                ", shape='" + shape + '\'' +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDateSeen() {
        return dateSeen;
    }

    public String getDateReported() {
        return dateReported;
    }

    public String getPlaceSeen() {
        return placeSeen;
    }

    public String getShape() {
        return shape;
    }

    public String getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }
}
