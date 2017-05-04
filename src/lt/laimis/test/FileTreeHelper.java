package lt.laimis.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

/**
 * This class provides the content for the tree in FileTree
 */
class FileTreeContentProvider implements ITreeContentProvider {
	
	public FileTreeContentProvider(String rootDir){
		this.rootDir = rootDir.trim();
	}
	
	String rootDir = "";
	
	/**
	 * Gets the children of the specified object
	 * 
	 * @param arg0
	 *            the parent object
	 * @return Object[]
	 */
	public Object[] getChildren(Object arg0) {
		// Return the files and subdirectories in this directory
		return ((File) arg0).listFiles();
	}

	/**
	 * Gets the parent of the specified object
	 * 
	 * @param arg0
	 *            the object
	 * @return Object
	 */
	public Object getParent(Object arg0) {
		// Return this file's parent file
		return ((File) arg0).getParentFile();
	}

	/**
	 * Returns whether the passed object has children
	 * 
	 * @param arg0
	 *            the parent object
	 * @return boolean
	 */
	public boolean hasChildren(Object arg0) {
		// Get the children
		Object[] obj = getChildren(arg0);

		// Return whether the parent has children
		return obj == null ? false : obj.length > 0;
	}

	/**
	 * Gets the root element(s) of the tree
	 * 
	 * @param arg0
	 *            the input data
	 * @return Object[]
	 */
	public Object[] getElements(Object arg0) {
		// These are the root elements of the tree

		File[] disks = File.listRoots();

		if (!rootDir.trim().isEmpty()) {

			disks = new File[1];

			disks[0] = new File(rootDir.trim());
		}

		return disks;
	}

	/**
	 * Disposes any created resources
	 */
	public void dispose() {
		// Nothing to dispose
	}
	
	/**
	 * Called when the input changes
	 * 
	 * @param arg0
	 *            the viewer
	 * @param arg1
	 *            the old input
	 * @param arg2
	 *            the new input
	 */
	public void inputChanged(Viewer arg0, Object beforeChange,
			Object afterChange) {
		rootDir = String.valueOf(afterChange);
	}
}

/**
 * This class provides the labels for the file tree
 */

class FileTreeLabelProvider implements ILabelProvider {
	// The listeners
	private List listeners;
	// Images for tree nodes
	private Image file;
	private Image dir;

	// Label provider state: preserve case of file names/directories
	boolean preserveCase;

	/**
	 * Constructs a FileTreeLabelProvider
	 */
	public FileTreeLabelProvider() {
		// Create the list to hold the listeners
		listeners = new ArrayList();

		// Create the images
		try {
			file = new Image(null, new FileInputStream("icons/file.gif"));
			dir = new Image(null, new FileInputStream("icons/folder.gif"));
		} catch (FileNotFoundException e) {
			// Swallow it; we'll do without images
		}
	}

	/**
	 * Sets the preserve case attribute
	 * 
	 * @param preserveCase
	 *            the preserve case attribute
	 */
	public void setPreserveCase(boolean preserveCase) {
		this.preserveCase = preserveCase;

		// Since this attribute affects how the labels are computed,
		// notify all the listeners of the change.
		LabelProviderChangedEvent event = new LabelProviderChangedEvent(this);
		for (int i = 0, n = listeners.size(); i < n; i++) {
			ILabelProviderListener ilpl = (ILabelProviderListener) listeners
					.get(i);
			ilpl.labelProviderChanged(event);
		}
	}

	/**
	 * Gets the image to display for a node in the tree
	 * 
	 * @param arg0
	 *            the node
	 * @return Image
	 */
	public Image getImage(Object arg0) {
		// If the node represents a directory, return the directory image.
		// Otherwise, return the file image.
		return ((File) arg0).isDirectory() ? dir : file;
	}

	/**
	 * Gets the text to display for a node in the tree
	 * 
	 * @param arg0
	 *            the node
	 * @return String
	 */
	public String getText(Object arg0) {
		// Get the name of the file
		String text = ((File) arg0).getName();

		// If name is blank, get the path
		if (text.length() == 0) {
			text = ((File) arg0).getPath();
		}

		return text;
	}

	/**
	 * Adds a listener to this label provider
	 * 
	 * @param arg0
	 *            the listener
	 */
	public void addListener(ILabelProviderListener arg0) {
		listeners.add(arg0);
	}

	/**
	 * Called when this LabelProvider is being disposed
	 */
	public void dispose() {
		// Dispose the images
		if (dir != null)
			dir.dispose();
		if (file != null)
			file.dispose();
	}

	/**
	 * Returns whether changes to the specified property on the specified
	 * element would affect the label for the element
	 * 
	 * @param arg0
	 *            the element
	 * @param arg1
	 *            the property
	 * @return boolean
	 */
	public boolean isLabelProperty(Object arg0, String arg1) {
		return false;
	}

	/**
	 * Removes the listener
	 * 
	 * @param arg0
	 *            the listener to remove
	 */
	public void removeListener(ILabelProviderListener arg0) {
		listeners.remove(arg0);
	}
}

class DataEditorComboLabelProvider extends LabelProvider {
	@Override
	public String getText(Object element) {
		LookupItem listItem = (LookupItem) element;
		return listItem.getValue();
	}

	@Override
	public Image getImage(Object element) {
		return null;
	}
}

class LookupItem {

	private Object key;

	private String value;

	public LookupItem(Object key, String value) {
		this.key = key;
		this.value = value;
	}

	public Object getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}

class DataEditorComboContentProvider implements IStructuredContentProvider {
	@SuppressWarnings("rawtypes")
	@Override
	public Object[] getElements(Object inputElement) {
		List array = null;
		if (inputElement != null) {
			array = (List) inputElement;
		} else {
			array = new ArrayList();
		}
		Object[] result = new Object[array.size()];
		for (int i = 0; i < array.size(); ++i) {
			result[i] = array.get(i);
		}
		return result;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
	}
}