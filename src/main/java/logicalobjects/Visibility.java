package logicalobjects;

public enum Visibility {
	PUBLIC {

		@Override
		public String toString() {
			return "public";
		}
	},
	PACKAGE {

		@Override
		public String toString() {
			return "package";
		}
	},
	PRIVATE {

		@Override
		public String toString() {
			return "package";
		}
	};

	@Override
	public abstract String toString();
}
