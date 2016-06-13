package model;

public enum Style {

	A, F;

	public String toString() {
		switch (this) {
		case A:
			return "A style";
		case F:
			return "F style";
		default:
			return "Unspecified";
		}
	}

	public static Style fromString(String name) {
		for (Style e : Style.values()) {
			if (e.toString().equals(name)) {
				return e;
			}
		}
		return null;
	}
}
