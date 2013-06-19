import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;


public class Test1 {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Test1 window = new Test1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(6, false));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("New Button");
		
		text = new Text(shell, SWT.BORDER);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		btnRadioButton.setText("Radio Button");
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setText("New Button");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setText("New Button");
		new Label(shell, SWT.NONE);
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		btnNewButton_1.setText("New Button");
		
		Button btnNewButton_5 = new Button(shell, SWT.NONE);
		btnNewButton_5.setText("New Button");
		
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setText("New Button");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

	}

}
