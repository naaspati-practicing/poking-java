package index.manager;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Paths;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sam.fx.helpers.FxButton;
import sam.fx.helpers.FxHBox;
import sam.myutils.Checker;
import sam.sql.sqlite.SQLiteDB;
public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}
	
	
	private final EntryView insertEntry = new EntryView(false);
	private final BorderPane left = new BorderPane(insertEntry);
	private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		left.setBottom(FxHBox.buttonBox(
				FxButton.button("CLEAR", e -> insertEntry.clear()),
				FxButton.button("SAVE", e -> save())
				));
		
		stage.setScene(new Scene(left));
		stage.show();
	}
	
	private SQLiteDB db;
	private StringBuilder sb;

	private void save() {
		Entry en = insertEntry.build();
		if(en == null)
			return;
		
		try {
			if(db == null) 
				db = new SQLiteDB(Paths.get("../index.db"));
		} catch (Exception e) {
			setError("failed to open db", e);
			return;
		}
		
		try {
			en.insert(db);
			db.commit();
			if(sb == null)
				sb = new StringBuilder();
			
			sb.setLength(0);
			
			sb.append("/**\n")
			.append("* <a href=\"").append(en.getSite()).append("\">").append(en.getSite()).append("</a>")
			.append("\n*\n* <pre>")
			.append("\n* ").append(en.getName());
			
			if(Checker.isNotEmptyTrimmed(en.getDescription())) {
				String s = en.getDescription();
				sb.append("\n*\n* ");
				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if(c != '\r')
						sb.append(c);
					
					if(c == '\n')
						sb.append("* ");
				}
			}
			sb.append("\n* </pre>\n*/\n");
			System.out.println(sb);
		} catch (SQLException e) {
			setError("failed to insert", e);
		}
	}
	
	private void setError(String string, Exception e) {
		String s;
		
		if(e == null)
			s = string;
		else  {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			if(string != null)
				pw.println(string);
			
			e.printStackTrace(pw);
			s = sw.toString();	
		}
		
		stage.getScene().setRoot(new TextArea(s));
	}

	@Override
	public void stop() throws Exception {
		if(db != null)
			db.close();
	}
}
