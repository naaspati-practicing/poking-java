package index.manager;
import static index.manager.EntrieMeta.DESCRIPTION;
import static index.manager.EntrieMeta.ENTRIE_TABLE_NAME;
import static index.manager.EntrieMeta.ENTRY_DATE;
import static index.manager.EntrieMeta.ID;
import static index.manager.EntrieMeta.IMPL_FULL_PATH;
import static index.manager.EntrieMeta.IMPL_NAME;
import static index.manager.EntrieMeta.NAME;
import static index.manager.EntrieMeta.SITE;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import sam.sql.JDBCHelper;
import sam.sql.sqlite.SQLiteDB;


public class Entry {
    private final int id;
    private final String name;
    private final String description;
    private final String site;
    private final String impl_name;
    private final String impl_full_path;
    private final long entry_date;

    public Entry(ResultSet rs) throws SQLException {
        this.id = rs.getInt(ID);
        this.name = rs.getString(NAME);
        this.description = rs.getString(DESCRIPTION);
        this.site = rs.getString(SITE);
        this.impl_name = rs.getString(IMPL_NAME);
        this.impl_full_path = rs.getString(IMPL_FULL_PATH);
        this.entry_date = rs.getLong(ENTRY_DATE);
    }
    
    public Entry(String name, String description, String site, String impl_name, String impl_full_path){
        this.id = -1;
        this.name = name;
        this.description = description;
        this.site = site;
        this.impl_name = impl_name;
        this.impl_full_path = impl_full_path;
        this.entry_date = -1;
    }

    public int getId(){ return this.id; }
    public String getName(){ return this.name; }
    public String getDescription(){ return this.description; }
    public String getSite(){ return this.site; }
    public String getImplName(){ return this.impl_name; }
    public String getImplFullPath(){ return this.impl_full_path; }
    public long getEntryDate(){ return this.entry_date; }

    private static final String SELECT_SQL_ALL = "SELECT * FROM ".concat(ENTRIE_TABLE_NAME);
    
    public static List<Entry> getAll(SQLiteDB db) throws SQLException{
    	return db.collectToList(SELECT_SQL_ALL, Entry::new);
    }
    
    private static final String SELECT_BY_ID_ALL = "SELECT * FROM " + ENTRIE_TABLE_NAME+ " WHERE "+ID+"=";
    public static Entry getById(SQLiteDB db, int id) throws SQLException {
    	return db.findFirst(SELECT_BY_ID_ALL.concat(String.valueOf(id)), Entry::new);
    }
    
    private static final String insert_sql = JDBCHelper.insertSQL(ENTRIE_TABLE_NAME,
			NAME,
			DESCRIPTION,
			SITE,
			IMPL_NAME,
			IMPL_FULL_PATH,
			ENTRY_DATE
			);
    
    public void insert(SQLiteDB db)  throws SQLException {
    	if(id != -1)
    		throw new SQLException("already persisted: "+this);
    	
    	try(PreparedStatement p = db.prepareStatement(insert_sql)) {
    		int n = 1;
            p.setString(n++,name);
            p.setString(n++,description);
            p.setString(n++,site);
            p.setString(n++,impl_name);
            p.setString(n++,impl_full_path);
            p.setLong(n++,System.currentTimeMillis());
            p.execute();
    	}
	} 
}
