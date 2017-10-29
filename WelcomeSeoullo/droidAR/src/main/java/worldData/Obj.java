package worldData;

import gl.Color;
import gl.HasColor;
import gl.HasPosition;
import gl.ObjectPicker;
import gl.Renderable;
import gl.scenegraph.MeshComponent;

import javax.microedition.khronos.opengles.GL10;

import util.EfficientList;
import util.Vec;

import commands.Command;

public class Obj extends AbstractObj implements HasPosition, HasColor {

	EfficientList<Entity> myComponents = new EfficientList<Entity>();

	public void setMyComponents(EfficientList<Entity> myComponents) {
		this.myComponents = myComponents;
	}

	private MeshComponent myGraphicsComponent;

	@Deprecated
	public MeshComponent getRenderComp() {
		return getGraphicsComponent();
	}

	public MeshComponent getMeshComp() {
		return getGraphicsComponent();
	}

	public MeshComponent getGraphicsComponent() {
		return myGraphicsComponent;
	}

	// public void updateComponents(Component component) {
	// Log.e("Obj.update()", "update not catched from: " + component);
	// }

	@Override
	public boolean update(float timeDelta, Updateable parent) {
		final int lenght = myComponents.myLength;
		for (int i = 0; i < lenght; i++) {
			if (myComponents.get(i) != null)
				if (!myComponents.get(i).update(timeDelta, this)) {
					remove(myComponents.get(i));
				}
		}
		return true;
	}

	public void setComp(Entity comp) {
		// TODO rename to add.. and return boolean if could be added
		// TODO put the String info in the comp itself or remove it, its crap
		if (comp instanceof MeshComponent) {
			setMyGraphicsComponent((MeshComponent) comp);
		}
		if (comp != null && myComponents.contains(comp) == -1)
			myComponents.add(comp);
	}

	@Deprecated
	public void setMyGraphicsComponent(MeshComponent newGraphicsComponent) {
		this.myGraphicsComponent = newGraphicsComponent;
	}

	public EfficientList<Entity> getMyComponents() {
		return myComponents;
	}

	@Override
	public void render(GL10 gl, Renderable parent) {

		if (myGraphicsComponent == null)
			return;

		if (ObjectPicker.readyToDrawWithColor) {
			gl.glColor4f(0, 0, 0, 1);
		} else {
			/*
			 * before drawing a new object, reset the color to white TODO
			 */
			gl.glColor4f(1, 1, 1, 1);
		}

		myGraphicsComponent.render(gl, this);
	}

	@Override
	public void setOnClickCommand(Command c) {
		super.setOnClickCommand(c);
		MeshComponent m = getComp(MeshComponent.class);
		if (m != null) {
			m.enableMeshPicking(this);
		}
	}

	public boolean remove(Entity compToRemove) {
		if (compToRemove instanceof MeshComponent)
			myGraphicsComponent = null;
		return myComponents.remove(compToRemove);
	}

	// public boolean accept(Visitor visitor) {
	// return visitor.default_visit(this);
	// }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean hasComponent(Class componentSubclass) {
		if (getComp(componentSubclass) != null)
			return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public <T> T getComp(Class<T> componentSubclass) {

		if (componentSubclass.isAssignableFrom(MeshComponent.class)) {
			// Log.e(LOG_TAG, "Fast access to obj.meshcomp=" +
			// myGraphicsComponent);
			return (T) getGraphicsComponent();
		}

		for (int i = 0; i < myComponents.myLength; i++) {
			Entity a = myComponents.get(i);
			if (componentSubclass.isAssignableFrom(a.getClass()))
				return (T) a;
		}
		return null;
	}

	@Override
	public Vec getPosition() {
		MeshComponent g = getGraphicsComponent();
		if (g != null)
			return g.getPosition();
		return null;
	}

	@Override
	public void setPosition(Vec position) {
		MeshComponent g = getGraphicsComponent();
		if (g != null)
			g.setPosition(position);
	}

	@Override
	public boolean accept(Visitor visitor) {
		return visitor.default_visit(this);
	}

	@Override
	public Color getColor() {
		MeshComponent g = getGraphicsComponent();
		if (g != null) {
			return g.getColor();
		}
		return null;
	}

	@Override
	public void setColor(Color c) {
		MeshComponent g = getGraphicsComponent();
		if (g != null) {
			g.setColor(c);
		}
	}

	// public String getDebugInfos() {
	// return myGraphicsComponent.toString();
	// }

	// public Component getComponent(String compName) {
	// return myComponents.get(compName);
	// }

	// @Override
	// public void setLongDescr(String info) {
	// getMyInfoObject().setLongDescr(info);
	// }

	// @Override
	// public void setShortDescr(String name) {
	// myInfoObj.setShortDescr(name);
	// }

}
