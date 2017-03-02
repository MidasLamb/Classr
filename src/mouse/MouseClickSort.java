package mouse;

public enum MouseClickSort {
	CLICK{

		@Override
		public String toString() {
			return "Click";
		}
		
	},
	DOUBLE_CLICK{

		@Override
		public String toString() {
			return "Double click";
		}
		
	},
	DRAG{
		@Override
		public String toString() {
			return "Drag";
		}
		
		
		
	};
	
	public abstract String toString();
	
}
