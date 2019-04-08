package index.manager;

import java.nio.file.Paths;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import sam.fx.helpers.FxConstants;
import sam.fx.helpers.FxCss;
import sam.myutils.Checker;

public class EntryView extends GridPane {
	private final Text id;
	private final TextField name;
	private final TextField site;
	private final TextField impl_file_name;
	private final TextField impl_full_path;
	private final TextField entry_date;
	private final TextArea description = new TextArea();

	private int row = 0;

	private static final ColumnConstraints C_1 = new ColumnConstraints();
	private static final ColumnConstraints C_2 = new ColumnConstraints();
	private static final Border BORDER = FxCss.border(Color.RED, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2), Insets.EMPTY);
	private final boolean full;

	static {
		C_1.setHalignment(HPos.RIGHT);

		C_2.setFillWidth(true);
		C_2.setHgrow(Priority.ALWAYS);
		C_2.setMaxWidth(Double.MAX_VALUE);
	}

	public EntryView(boolean full) {
		this.full = full;

		setHgap(5);
		setVgap(5);
		setStyle("-fx-font-family:monospace");
		setPadding(FxConstants.INSETS_5);

		id = full ? text() : null;

		if(full)
			addRow(row++, text("id: "), id);

		name = add("Name: ");
		site = add("Site: ");
		impl_file_name = add("Impl Name: ");
		impl_full_path = add("Impl Path: ");
		entry_date = full ? add("Entry Date: ") : null;

		addRow(row++, text("Description: "));
		add(description, 0, row++, GridPane.REMAINING, GridPane.REMAINING);

		getColumnConstraints().addAll(C_1, C_2);
	}
	private TextField add(String col) {
		TextField t = textfield();
		addRow(row++, text(col), t);
		return t;
	}
	private TextField textfield() {
		TextField t = new TextField();
		t.setEditable(!full);
		return t;
	}
	public Entry build() {
		boolean b = true;

		b  = validate(name) &&  b ;
		b  = validate(site) &&  b ;
		b  = validate(impl_full_path) &&  b ;

		if(b && Checker.isEmptyTrimmed(impl_file_name.getText()))
			impl_file_name.setText(Paths.get(impl_full_path.getText()).getFileName().toString());

		if(b)
			return new Entry(name.getText(), description.getText(), site.getText(), impl_file_name.getText(), impl_full_path.getText());
		else
			return null;
	}
	private boolean validate(TextField t) {
		if(Checker.isEmptyTrimmed(t.getText())) {
			t.setBorder(BORDER);
			return false;
		} else  {
			clearBorder(t);
			return true;
		}
	}

	private void clearBorder(Region t) {
		if(t.getBorder() == BORDER)
			t.setBorder(null);
	}
	private Text text() {
		return new Text();
	}
	private Text text(String s) {
		Text t = text();
		t.setText(s);
		return t;
	}
	public void clear() {
		id.setText(null);

		clear(name);
		clear(site);
		clear(impl_file_name);
		clear(impl_full_path);
		clear(entry_date);

		description.clear();
	}
	private void clear(TextField t) {
		t.clear();
		clearBorder(t);
	}
}
