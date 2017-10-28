package worldData;

import components.Visitable;

public interface Entity extends Updateable, Visitable {

	public Updateable getMyParent();

	public void setMyParent(Updateable parent);

}
