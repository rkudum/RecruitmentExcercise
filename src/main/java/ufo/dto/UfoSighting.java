package ufo.dto;

/**
 * This is the ValueObject which carries the UfoSightings details as parsed from feed file.
 *
 * TODO: Few improvements that I would like to make:
 * <ul>
 *     <li>The data type of dateSeen and dateReported should be LocalDate rather than generic String</li>
 *     <li>The data type of shape should be of a strict allowable type -- like a pre-defined enum</li>
 *     <li>A builder pattern could be used to construct the object rather than using a constructor with several arguements</li>
 *     <li>Implementation of hashCode() and equals() is recommended -- to distinguish if sightings are unique or
 * 		not (specially when adding them to the data structure or used for comparison).</li> *
 * </ul>
 *
 */
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
}
