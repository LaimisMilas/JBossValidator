package lt.laimis.test;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class SempleAction extends Action implements IWorkbenchWindowActionDelegate{
	
	private IWorkbenchWindow window;
	
	@Override
	public void run(IAction arg0) {
		SectionsDialog dialog = new SectionsDialog(Display.getDefault()
				.getActiveShell());
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		this.window = arg0;
	}

}
