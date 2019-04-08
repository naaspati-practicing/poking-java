package index.manager;


public interface EntrieMeta {
    String ENTRIE_TABLE_NAME = "Entries";

    String ID = "_id";    // _id 	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE
    String NAME = "name";    // name 	TEXT NOT NULL UNIQUE
    String DESCRIPTION = "description";    // description 	TEXT
    String SITE = "site";    // site 	TEXT UNIQUE
    String IMPL_NAME = "impl_name";    // impl_name 	TEXT NOT NULL UNIQUE
    String IMPL_FULL_PATH = "impl_full_path";    // impl_full_path 	TEXT NOT NULL UNIQUE
    String ENTRY_DATE = "entry_date";    // entry_date 	TEXT NOT NULL


}