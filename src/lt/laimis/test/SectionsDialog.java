package lt.laimis.test;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.progress.UIJob;

import lt.laimis.test.store.DataLouder;
import lt.laimis.test.store.DeviceIPAddress;
import lt.laimis.test.store.StorageRoot;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class SectionsDialog extends TitleAreaDialog {

	protected UIJob job_load;

	private Text selectedDir;
	private TreeViewer dirTreeView;
	private String selectedDirString = "";
	private ComboViewer ipAddressViewer;
	private Combo ipAddressCombo;
	private StorageRoot storageRoot;

	private TreeViewer dirTree2View;

	public SectionsDialog(Shell parentShell) {
		super(parentShell);
		storageRoot = new DataLouder().initData(parentShell);
		selectedDirString = storageRoot.getiQClientConfig()
				.getSelectedDirAsString().trim();
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		setTitle("Vienas");

		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(3, false));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		{
			Label lblNewLabel = new Label(container, SWT.NONE);
			lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
					false, false, 1, 1));
			lblNewLabel.setText("IP adresas:");

			ipAddressViewer = new ComboViewer(container, SWT.NONE);
			ipAddressViewer
					.setLabelProvider(new DataEditorComboLabelProvider());
			ipAddressViewer
					.setContentProvider(new DataEditorComboContentProvider());
			ipAddressCombo = ipAddressViewer.getCombo();
			ipAddressCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
					true, false, 2, 1));

			ArrayList<LookupItem> ipComboLookup = new ArrayList<LookupItem>();

			for (DeviceIPAddress ip : storageRoot.getDeviceIPAddressArray()
					.getDeviceIPAddresses()) {
				LookupItem item = new LookupItem("", ip.getIpAdress());
				ipComboLookup.add(item);
			}
			ipAddressViewer.setInput(ipComboLookup);
			ipAddressCombo.setText(storageRoot.getiQClientConfig()
					.getSelectedIPAddress());
			// ipAddressCombo.select(0);
		}
		{

			Button buttonSelectDir = new Button(container, SWT.PUSH);
			buttonSelectDir.setText("Parink dir");
			buttonSelectDir.addListener(SWT.Selection, new Listener() {

				public void handleEvent(Event event) {

					DirectoryDialog directoryDialog = new DirectoryDialog(
							event.display.getActiveShell());

					directoryDialog.setFilterPath(selectedDirString);
					String result = directoryDialog.open();

					if (result != null) {
						selectedDirString = result;
						selectedDir.setText(selectedDirString);
						updateDirTree();

					}
				}
			});

			selectedDir = new Text(container, SWT.BORDER);
			selectedDir.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
					false, 2, 1));
			selectedDir.setText(selectedDirString);
		}
		new Label(container, SWT.NONE);
		{
			// Create the tree viewer to display the file tree
			dirTreeView = new TreeViewer(container);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.widthHint = 200;
			gridData.heightHint = 300;
			dirTreeView.getTree().setLayoutData(gridData);
			dirTreeView.setContentProvider(new FileTreeContentProvider(
					selectedDirString));
			dirTreeView.setLabelProvider(new FileTreeLabelProvider());
			dirTreeView.setInput(selectedDirString);
			dirTreeView.addOpenListener(new IOpenListener() {
				@Override
				public void open(OpenEvent ev) {
					String filePath = ev.getSelection().toString().trim().replace("[", "");
					filePath = filePath.replace("]", "");
					
					WinSystemCommandRunner.runSystemCommand("notepad.exe " + filePath);
				}
			});
		}
		{
			// Create the tree viewer to display the file tree
			dirTree2View = new TreeViewer(container);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.widthHint = 200;
			gridData.heightHint = 300;
			dirTree2View.getTree().setLayoutData(gridData);
			dirTree2View.setContentProvider(new FileTreeContentProvider(
					selectedDirString));
			dirTree2View.setLabelProvider(new FileTreeLabelProvider());
			dirTree2View.setInput(selectedDirString);
		}
		{
			Button button = new Button(container, SWT.PUSH);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					updateClientConnectionIpAddress(ipAddressCombo.getText());

					setInformationMessage("IP adresas pakeistas, sekmingai");
				}
			});
			button.setText("Keisti IP");
		}
		{
			Button button = new Button(container, SWT.PUSH);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {

					validateClientStructure();

					setInformationMessage("Validation Done");
				}
			});
			button.setText("Palyginti klienta");
		}
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		/*
		 * job_load = new UIJob("Kraun duomens") {
		 * 
		 * @Override public IStatus runInUIThread(IProgressMonitor monitor) {
		 * init(); return Status.OK_STATUS; } }; job_load.schedule();
		 */
		
		//TODO Galimybe klientui prideti debug konfiguracija.
		//-Xdebug
		//-Xrunjdwp:transport=dt_socket,server=n,suspend=n,address=8787
		return area;

	}

	protected void validateClientStructure() {
		TreeItem[] treeItems = dirTreeView.getTree().getItems();
		treeItems.toString();

	}

	protected void updateDirTree() {
		dirTreeView.setInput(selectedDir.getText());
		dirTreeView.refresh(true);
	}

	@Override
	protected void okPressed() {
		// save storageRoot data.
		String result = new DataLouder().saveData(storageRoot);
		setInformationMessage(result);

	}

	public void updateClientConnectionIpAddress(String newIP) {

		// file as string lines
		// update ip address
		// store in location

		String fileTmp = Constance.iQCLient
				+ storageRoot.getiQClientConfig().getTmpIQClientConfigFile();

		List<String> lines = FileReadWrite.readFileAsLines(fileTmp);

		StringBuffer sb = new StringBuffer();

		for (String line : lines) {

			if (line.contains(storageRoot.getiQClientConfig().getArg().trim())) {

				line = line.replace(storageRoot.getiQClientConfig().getArg(),
						newIP);
			}

			line = line + " \n";

			sb.append(line);
		}

		String writeTo = selectedDirString.trim()
				+ storageRoot.getiQClientConfig().getSaveIQClientConfigFile()
						.trim();

		FileReadWrite.writeFile(writeTo, sb.toString());
	}

	public void setInformationMessage(String str) {
		setMessage(str, IMessageProvider.INFORMATION);
	}

	public void setErrorMessage(String string) {
		setMessage(string, IMessageProvider.ERROR);
	}
}
