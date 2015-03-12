package database;

import java.util.ArrayList;

import patrol.npd.GlobalVariables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class MemberDataBaseAdapter {

	public SQLiteDatabase db;
	DBAdapter instance;
	LoginDataBaseAdapter lgDB;

	public MemberDataBaseAdapter(Context _context) {
		instance = new DBAdapter(_context);
		instance.open();
		db = instance.getDatabaseInstance();
	}

	public void InsertMember(String name, String phone, String email,
			String housenum, int userId) {

		ContentValues newValues = new ContentValues();
		// Assign values for each row.
		newValues.put(DBAdapter.MEMBERS_COLUMN_MEMBERS_NAME, name);
		newValues.put(DBAdapter.MEMBERS_COLUMN_MEMBERS_PHONE, phone);
		newValues.put(DBAdapter.MEMBERS_COLUMN_MEMBERS_EMAIL, email);
		newValues.put(DBAdapter.MEMBERS_COLUMN_MEMBERS_HOUSENUM, housenum);
		newValues.put(DBAdapter.MEMBERS_COLUMN_LOGINID, userId);

		long status = db.insert(DBAdapter.TABLE_MEMBERS, null, newValues);

		if (status == -1) {
			Toast.makeText(instance.context, "Not Inserted members",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(instance.context, "Success", Toast.LENGTH_LONG)
					.show();
		}
	}
	
	public void DeleteMember(String memberName)
	{
			String where = " DOCTOR_NAME=?";
			int numberOFEntriesDeleted = db.delete(DBAdapter.TABLE_MEMBERS, where,
					new String[] { memberName });
			
			Toast.makeText(instance.context, memberName + " Removed", Toast.LENGTH_LONG).show();
	}

	public ArrayList<String> GetAllMembersNames() {
		String where = null;

		ArrayList<String> members = new ArrayList<String>();

		Cursor c = db.query(true, DBAdapter.TABLE_MEMBERS,
				DBAdapter.MEMBERS_ALLCOLUMNS, where, null, null, null, null,
				null);
		if (c != null) {
			c.moveToFirst();
		}
		

		while (c.isAfterLast() == false) {
			members.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_NAME)));
			
			c.moveToNext();
		}

		return members;
	}
	
	public ArrayList<String> GetAllMembersInfo(String memberName) {
		String where = " DOCTOR_NAME=?";

		ArrayList<String> membersInfo = new ArrayList<String>();
		
		Cursor c = db.query(DBAdapter.TABLE_MEMBERS, null, where,
				new String[] { memberName }, null, null, null);
		
		if (c != null) {
			c.moveToFirst();
		}

		while (c.isAfterLast() == false) {
			membersInfo.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_EMAIL)));
			membersInfo.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_PHONE)));
			membersInfo.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_HOUSENUM)));
			
			c.moveToNext();
		}

		return membersInfo;

	}
	
	public ArrayList<String> GetAllMembersEmail() {
		ArrayList<String> membersInfo = new ArrayList<String>();
		
		String where = null;
		Cursor c = db
				.query(true, DBAdapter.TABLE_MEMBERS, DBAdapter.MEMBERS_ALLCOLUMNS,
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		else return null;

		while (c.isAfterLast() == false) {
			membersInfo.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_EMAIL)));
			c.moveToNext();
		}
		return membersInfo;
	}
	
	public ArrayList<String> GetAllMembersPhone() {
		ArrayList<String> membersInfo = new ArrayList<String>();
		
		String where = null;
		Cursor c = db
				.query(true, DBAdapter.TABLE_MEMBERS, DBAdapter.MEMBERS_ALLCOLUMNS,
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		else return null;

		while (c.isAfterLast() == false) {
			membersInfo.add(c.getString(c.getColumnIndex(DBAdapter.MEMBERS_COLUMN_MEMBERS_PHONE)));
			c.moveToNext();
		}
		
		return membersInfo;
	}

}
