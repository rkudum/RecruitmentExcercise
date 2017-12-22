package ufo.dto;

import java.util.Objects;

public class UfoSighting {
	private String dateSeen;
	private String dateReported;
	private String placeSeen;
	private String shape;
	private String duration;
	private String description;
	
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

	public UfoSighting(String dateSeen, String dateReported, String placeSeen, String shape, String duration, String description) {
		this.dateSeen = dateSeen;
		this.dateReported = dateReported;
		this.placeSeen = placeSeen;
		this.shape = shape;
		this.duration = duration;
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UfoSighting)) return false;
		UfoSighting that = (UfoSighting) o;
		return Objects.equals(getDateSeen(), that.getDateSeen()) &&
				Objects.equals(getDateReported(), that.getDateReported()) &&
				Objects.equals(getPlaceSeen(), that.getPlaceSeen()) &&
				Objects.equals(getShape(), that.getShape()) &&
				Objects.equals(getDuration(), that.getDuration()) &&
				Objects.equals(getDescription(), that.getDescription());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getDateSeen(), getDateReported(), getPlaceSeen(), getShape(), getDuration(), getDescription());
	}
}
