package uwi.dcit.AgriExpenseTT.helpers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import uwi.dcit.AgriExpenseTT.models.CycleContract.CycleEntry;
import uwi.dcit.AgriExpenseTT.models.CycleResourceContract.CycleResourceEntry;
import uwi.dcit.AgriExpenseTT.models.ResourcePurchaseContract.ResourcePurchaseEntry;
import uwi.dcit.AgriExpenseTT.models.TransactionLogContract.TransactionLogEntry;
import uwi.dcit.AgriExpenseTT.models.UpdateAccountContract.UpdateAccountEntry;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import uwi.dcit.agriexpensett.cycleendpoint.Cycleendpoint;
import uwi.dcit.agriexpensett.cycleendpoint.model.Cycle;
import uwi.dcit.agriexpensett.cycleendpoint.model.CycleCollection;
import uwi.dcit.agriexpensett.cycleuseendpoint.Cycleuseendpoint;
import uwi.dcit.agriexpensett.cycleuseendpoint.model.CycleUse;
import uwi.dcit.agriexpensett.cycleuseendpoint.model.CycleUseCollection;
import uwi.dcit.agriexpensett.rpurchaseendpoint.Rpurchaseendpoint;
import uwi.dcit.agriexpensett.rpurchaseendpoint.model.RPurchase;
import uwi.dcit.agriexpensett.rpurchaseendpoint.model.RPurchaseCollection;
import uwi.dcit.agriexpensett.translogendpoint.Translogendpoint;
import uwi.dcit.agriexpensett.translogendpoint.model.TransLog;
import uwi.dcit.agriexpensett.translogendpoint.model.TransLogCollection;
import uwi.dcit.agriexpensett.upaccendpoint.model.UpAcc;
//import uwi.dcit.AgriExpenseTT.translogendpoint.model.TransLogCollection;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.jackson2.JacksonFactory;

public class TransactionLog {
	SQLiteDatabase db;
	DbHelper dbh;
	Context context;
	public static final String TL_BEGIN="begin";
	public static final String TL_END="end";
	public static final String TL_INS="ins";
	public static final String TL_DEL="del";
	public static final String TL_UPDATE="updt";
	//a variation of a transaction log, this records the operation and the row that was affected
	//based on the operation associated different tables are associated
	
	public TransactionLog(DbHelper dbh,SQLiteDatabase db,Context context){
		this.dbh=dbh;
		this.db=db;
		this.context=context;
	}
	public boolean pullAllFromCloud(UpAcc cloudAcc){
		System.out.println("----"+cloudAcc.getAcc());
		
		CycleCollection responseCycles = null; 
		Cycleendpoint.Builder builder = new Cycleendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Cycleendpoint endpoint = builder.build();
		try {
			responseCycles = endpoint.getAllCycles(cloudAcc.getAcc()).execute();
		} catch (IOException e) {e.printStackTrace();
			return false;}
		List<Cycle> cycleList=responseCycles.getItems();
			
			
		CycleUseCollection responseCycleUse = null;
		Cycleuseendpoint.Builder builderUse = new Cycleuseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),			         null);         
		builderUse = CloudEndpointUtils.updateBuilder(builderUse);
		Cycleuseendpoint endpointUse = builderUse.build();
		try {
			responseCycleUse = endpointUse.getAllCycleUse(cloudAcc.getAcc()).execute();
		} catch (IOException e) {e.printStackTrace();
			return false;}
		List<CycleUse> cycleUseList=responseCycleUse.getItems();
		
		
		RPurchaseCollection responsePurchase = null;
		Rpurchaseendpoint.Builder builderPurchase = new Rpurchaseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
		Rpurchaseendpoint endpointPurchase = builderPurchase.build();
		try {
			responsePurchase = endpointPurchase.getAllPurchases(cloudAcc.getAcc()).execute();
		} catch (IOException e) {e.printStackTrace();
			return false;}
		List<RPurchase> purchaseList=responsePurchase.getItems();
		
		TransLogCollection responseTranslog = null;
		Translogendpoint.Builder builderTransLog = new Translogendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderTransLog = CloudEndpointUtils.updateBuilder(builderTransLog);
		Translogendpoint endpointTranslog = builderTransLog.build();
		try {
			responseTranslog = endpointTranslog.getAllTranslog(cloudAcc.getAcc()).execute();
		} catch (IOException e) {e.printStackTrace();
			return false;}
		List<TransLog> translogList=responseTranslog.getItems();
		
		dbh.dropTables(db);
		dbh.onCreate(db);
		
		ContentValues cv;
		System.out.println("Cycles now");
		for(Cycle c:cycleList){
			System.out.println("***");
			cv=new ContentValues();
			cv.put(CycleEntry._ID, c.getId());
			cv.put(CycleEntry.CROPCYCLE_CROPID, c.getCropId());
			cv.put(CycleEntry.CROPCYCLE_LAND_TYPE, c.getLandType());
			cv.put(CycleEntry.CROPCYCLE_LAND_AMOUNT, c.getLandQty());
			cv.put(CycleEntry.CROPCYCLE_TOTALSPENT, c.getTotalSpent());
			//cv.put(DbHelper.CROPCYCLE_DATE, c.get);
			db.insert(CycleEntry.TABLE_NAME, null, cv);
			DbQuery.insertCloudKey(db, dbh, CycleEntry.TABLE_NAME,c.getKeyrep(), c.getId());
		}
		
		System.out.println("CycleUses now");
		for(CycleUse c: cycleUseList){
			System.out.println("***");
			//try {System.out.println(c.toPrettyString());} catch (IOException e) {e.printStackTrace();}
			cv=new ContentValues();
			cv.put(CycleResourceEntry._ID, c.getId());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID, c.getCycleid());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID, c.getPurchaseId());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_QTY, c.getAmount());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_TYPE, c.getResource());
			//cv.put(DbHelper.CYCLE_RESOURCE_QUANTIFIER, c.g);
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_USECOST, c.getCost());
			db.insert(CycleResourceEntry.TABLE_NAME, null, cv);
			DbQuery.insertCloudKey(db, dbh, CycleResourceEntry.TABLE_NAME, c.getKeyrep(), c.getId());
		}

		System.out.println("Purchases");
		for(RPurchase p: purchaseList){
			System.out.println("***");
			cv=new ContentValues();
			cv.put(ResourcePurchaseEntry._ID, p.getPId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, p.getType());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, p.getResourceId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, p.getQty());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, p.getQuantifier());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, p.getCost());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, p.getQtyRemaining());
			db.insert(ResourcePurchaseEntry.TABLE_NAME, null, cv);
			DbQuery.insertCloudKey(db, dbh, ResourcePurchaseEntry.TABLE_NAME, p.getKeyrep(), p.getPId());
		}
		
		System.out.println("transactions");
		for(TransLog t:translogList){
			System.out.println("*** Id:"+t.getId());
			
			cv=new ContentValues();
			cv.put(TransactionLogEntry._ID, t.getId());
			cv.put(TransactionLogEntry.TRANSACTION_LOG_TABLE, t.getTableKind());
			cv.put(TransactionLogEntry.TRANSACTION_LOG_ROWID, t.getRowId());
			cv.put(TransactionLogEntry.TRANSACTION_LOG_OPERATION, t.getOperation());
			cv.put(TransactionLogEntry.TRANSACTION_LOG_TRANSTIME, t.getTransTime());
			db.insert(TransactionLogEntry.TABLE_NAME, null, cv);
			DbQuery.updateAccount(db, t.getTransTime());
		}
		cv=new ContentValues();
		cv.put(UpdateAccountEntry.UPDATE_ACCOUNT_ACC, cloudAcc.getAcc());
		cv.put(UpdateAccountEntry.UPDATE_ACCOUNT_CLOUD_KEY, cloudAcc.getKeyrep());
		db.update(UpdateAccountEntry.TABLE_NAME, cv, UpdateAccountEntry._ID+"=1", null);
		
		return true;
		
	}
	
	
	public int insertTransLog(String table,int rowId,String operation){
		ContentValues cv=new ContentValues();
		long unixTime = System.currentTimeMillis() / 1000L;
		cv.put(TransactionLogEntry.TRANSACTION_LOG_OPERATION, operation);
		cv.put(TransactionLogEntry.TRANSACTION_LOG_TABLE, table);
		cv.put(TransactionLogEntry.TRANSACTION_LOG_ROWID, rowId);
		cv.put(TransactionLogEntry.TRANSACTION_LOG_TRANSTIME, unixTime);
		db.insert(TransactionLogEntry.TABLE_NAME, null, cv);
		int row=DbQuery.getLast(db, dbh, TransactionLogEntry.TABLE_NAME);
		DbQuery.updateAccount(db, unixTime);
		return row;//returns the row number of the record just inserted
	}
	//given a time the cloud was last updated will try to update the cloud based on the transactions that happened after that point
	public void updateCloud(long lastUpdate){
		//get all the transactions that happened after the cloud's last update
		System.out.println("starting Qquery");
		String code="select * from "+TransactionLogEntry.TABLE_NAME+
				" where "+TransactionLogEntry.TRANSACTION_LOG_TRANSTIME+">="+lastUpdate+";";
		Cursor cursor=db.rawQuery(code, null);
		
		//will now attempt to recreate such operations on the cloud
		while(cursor.moveToNext()){
			System.out.println("records");
			String operation=cursor.getString(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_OPERATION));
			String table=cursor.getString(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_TABLE));
			int rowId=cursor.getInt(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_ROWID));
			//now that i have the table, row and operation i can insert a record into the redo log
			//by inserting it into the redo log I can be assured it will be inserted when possible
			DbQuery.insertRedoLog(db, dbh, table, rowId, operation);
		}
		CloudInterface c=new CloudInterface(context, db, dbh);
		c.flushToCloud();
	}
	public boolean createCloud(String namespace){
		
		Cycleendpoint.Builder builder = new Cycleendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Cycleendpoint endpointCyc = builder.build();	
			
		Cycleuseendpoint.Builder builderUse = new Cycleuseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),			         null);         
		builderUse = CloudEndpointUtils.updateBuilder(builderUse);
		Cycleuseendpoint endpointUse = builderUse.build();
		
		
		Rpurchaseendpoint.Builder builderPurchase = new Rpurchaseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
		Rpurchaseendpoint endpointPurchase = builderPurchase.build();
		
		Translogendpoint.Builder builderTranslog = new Translogendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Translogendpoint endpointTranslog = builderTranslog.build();
		
		String code="select * from "+CycleEntry.TABLE_NAME;//TODO where something (constraints unknown yet 
		Cursor cursor=db.rawQuery(code, null);
		while(cursor.moveToNext()){
			Cycle c=new Cycle();
			c.setId(cursor.getInt(cursor.getColumnIndex(CycleEntry._ID)));
			c.setCropId(cursor.getInt(cursor.getColumnIndex(CycleEntry.CROPCYCLE_CROPID)));
			c.setLandQty(cursor.getDouble(cursor.getColumnIndex(CycleEntry.CROPCYCLE_LAND_AMOUNT)));
			c.setLandType(cursor.getString(cursor.getColumnIndex(CycleEntry.CROPCYCLE_LAND_TYPE)));
			c.setTotalSpent(cursor.getDouble(cursor.getColumnIndex(CycleEntry.CROPCYCLE_TOTALSPENT)));
			c.setHarvestType(cursor.getString(cursor.getColumnIndex(CycleEntry.CROPCYCLE_HARVEST_TYPE)));
			c.setHarvestAmt(cursor.getDouble(cursor.getColumnIndex(CycleEntry.CROPCYCLE_HARVEST_AMT)));
			c.setCostPer(cursor.getDouble(cursor.getColumnIndex(CycleEntry.CROPCYCLE_COSTPER)));
			c.setAccount(namespace);
			try {
				c=endpointCyc.insertCycle(c).execute();
			} catch (IOException e) {e.printStackTrace();
				return false;}
			System.out.println(c.getKeyrep()+"  "+c.getId());
			DbQuery.insertCloudKey(db, dbh, CycleEntry.TABLE_NAME, c.getKeyrep(), c.getId());
		}
		code="select * from "+CycleResourceEntry.TABLE_NAME;
		cursor=db.rawQuery(code, null);
		while(cursor.moveToNext()){
			CycleUse c=new CycleUse();
			c.setId(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry._ID)));
			c.setAmount(cursor.getDouble(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_QTY)));
			c.setCycleid(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID)));
			c.setPurchaseId(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID)));
			c.setResource(cursor.getString(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_TYPE)));
			c.setCost(cursor.getDouble(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_USECOST)));
			//c.setQuantifier(cursor.getString(cursor.getColumnIndex(DbHelper.CYCLE_RESOURCE_QUANTIFIER)));
			c.setAccount(namespace);
			try {
				c=endpointUse.insertCycleUse(c).execute();
			} catch (IOException e) {e.printStackTrace();
				return false;}
			DbQuery.insertCloudKey(db, dbh, CycleResourceEntry.TABLE_NAME, c.getKeyrep(), c.getId());
		}
		code="select * from "+ResourcePurchaseEntry.TABLE_NAME;
		cursor=db.rawQuery(code, null);
		while(cursor.moveToNext()){
			RPurchase p=new RPurchase();
			p.setPId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseEntry._ID)));
			p.setResourceId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID)));
			p.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
			p.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
			p.setQty(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
			p.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
			p.setQtyRemaining(cursor.getDouble(cursor.getColumnIndex( ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
			p.setAccount(namespace);
			try {
				p=endpointPurchase.insertRPurchase(p).execute();
			} catch (IOException e) {e.printStackTrace();
				return false;}
			DbQuery.insertCloudKey(db, dbh, ResourcePurchaseEntry.TABLE_NAME, p.getKeyrep(), p.getPId());
		}
		
		ContentValues cv=new ContentValues();
		cv.put(UpdateAccountEntry.UPDATE_ACCOUNT_ACC, namespace);
		db.update(UpdateAccountEntry.TABLE_NAME, cv, UpdateAccountEntry._ID+"=1", null);
		
		code="select * from "+TransactionLogEntry.TABLE_NAME;
		CloudInterface cloudIF=new CloudInterface(context, db, dbh);
		cursor=db.rawQuery(code, null);
		while(cursor.moveToNext()){
			TransLog t=new TransLog();
			t.setId(cursor.getInt(cursor.getColumnIndex(TransactionLogEntry._ID)));
			t.setAccount(namespace);
			t.setOperation(cursor.getString(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_OPERATION)));
			t.setTableKind(cursor.getString(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_TABLE)));
			t.setRowId(cursor.getInt(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_ROWID)));
			t.setTransTime(cursor.getLong(cursor.getColumnIndex(TransactionLogEntry.TRANSACTION_LOG_TRANSTIME)));
			t.setKeyrep(DbQuery.getKey(db, dbh, t.getTableKind(), t.getRowId()));
			try {
				System.out.println(t.toPrettyString());
				t=endpointTranslog.insertTransLog(t).execute();
				cloudIF.updateUpAccC(t.getTransTime());
			} catch (IOException e) {e.printStackTrace();
				return false;}
		}
		cv=new ContentValues();
		cv.put(UpdateAccountEntry.UPDATE_ACCOUNT_SIGNEDIN, 1);
		db.update(UpdateAccountEntry.TABLE_NAME, cv, UpdateAccountEntry._ID+"=1", null);
		return true;
	}
	
	
	public void logsUpdateLocal(String namespace,long lastLocalUpdate){
		new UpdateLocal(namespace,lastLocalUpdate).execute();
	}
	public class UpdateLocal extends AsyncTask<Void,Void,Void>{
		String namespace;
		long lastLocalUpdate;
		public UpdateLocal(String namespace,long lastLocalUpdate){
			this.namespace=namespace;
			this.lastLocalUpdate=lastLocalUpdate;
		}
		@Override
		protected Void doInBackground(Void... params) {
			Translogendpoint.Builder builder = new Translogendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
			builder = CloudEndpointUtils.updateBuilder(builder);
			Translogendpoint endpoint = builder.build();
			TransLogCollection tlist = null;
			
			try {
				tlist=endpoint.logs(lastLocalUpdate, namespace).execute();
			} catch (IOException e) {e.printStackTrace();}
			List<TransLog> transList=tlist.getItems();
			Iterator<TransLog> i=transList.iterator();
			while(i.hasNext()){
				TransLog tLog=i.next();
				if(tLog.getOperation().equals(TransactionLog.TL_INS))
					logInsertLocal(tLog,namespace);
				else if(tLog.getOperation().equals(TransactionLog.TL_UPDATE))
					logUpdateLocal(tLog,namespace);
				else if(tLog.getOperation().equals(TransactionLog.TL_DEL)){
					logDeleteLocal(tLog,namespace);
				}
					
			}
			return null;
		}
	}
	public void logInsertLocal (TransLog t,String namespace){
		ContentValues cv=new ContentValues();
		if(t.getTableKind().equals(CycleEntry.TABLE_NAME)){
			Cycle c=getCycle(namespace,t.getKeyrep());
			cv.put(CycleEntry._ID, t.getRowId());
			cv.put(CycleEntry.CROPCYCLE_CROPID, c.getCropId());
			cv.put(CycleEntry.CROPCYCLE_LAND_TYPE, c.getLandType());
			cv.put(CycleEntry.CROPCYCLE_LAND_AMOUNT, c.getLandQty());
			cv.put(CycleEntry.CROPCYCLE_TOTALSPENT, c.getTotalSpent());
			//cv.put(DbHelper.CROPCYCLE_DATE, c.get);
			cv.put(CycleEntry.CROPCYCLE_HARVEST_TYPE, c.getHarvestType());
			cv.put(CycleEntry.CROPCYCLE_HARVEST_AMT, c.getHarvestAmt());
			cv.put(CycleEntry.CROPCYCLE_COSTPER, c.getCostPer());
			db.insert(CycleEntry.TABLE_NAME, null, cv);
		}else if(t.getTableKind().equals(ResourcePurchaseEntry.TABLE_NAME)){
			RPurchase p=getPurchase(namespace,t.getKeyrep());
			cv.put(ResourcePurchaseEntry._ID, t.getRowId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, p.getType());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, p.getResourceId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, p.getQty());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, p.getQuantifier());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, p.getCost());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, p.getQtyRemaining());
			db.insert(ResourcePurchaseEntry.TABLE_NAME, null, cv);
		}else if(t.getTableKind().equals(CycleResourceEntry.TABLE_NAME)){
			CycleUse c=getCycleUse(namespace,t.getKeyrep());
			cv.put(CycleResourceEntry._ID, t.getRowId());
			//cv.put(DbHelper.CYCLE_RESOURCE_TYPE, );
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID, c.getCycleid());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID, c.getPurchaseId());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_QTY, c.getAmount());
			//cv.put(DbHelper.CYCLE_RESOURCE_QUANTIFIER, c.get);
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_USECOST, c.getCost());
			db.insert(CycleResourceEntry.TABLE_NAME, null, cv);
		}
	}
	public void logUpdateLocal(TransLog t,String namespace){
		ContentValues cv=new ContentValues();
		if(t.getTableKind().equals(CycleEntry.TABLE_NAME)){
			Cycle c=getCycle(namespace,t.getKeyrep());
			cv.put(CycleEntry._ID, t.getRowId());
			cv.put(CycleEntry.CROPCYCLE_CROPID, c.getCropId());
			cv.put(CycleEntry.CROPCYCLE_LAND_TYPE, c.getLandType());
			cv.put(CycleEntry.CROPCYCLE_LAND_AMOUNT, c.getLandQty());
			cv.put(CycleEntry.CROPCYCLE_TOTALSPENT, c.getTotalSpent());
			//cv.put(DbHelper.CROPCYCLE_DATE, c.ge);
			cv.put(CycleEntry.CROPCYCLE_HARVEST_TYPE, c.getHarvestType());
			cv.put(CycleEntry.CROPCYCLE_HARVEST_AMT, c.getHarvestAmt());
			cv.put(CycleEntry.CROPCYCLE_COSTPER, c.getCostPer());
			db.update(CycleEntry.TABLE_NAME, cv, CycleEntry._ID+"="+t.getRowId(), null);
		}else if(t.getTableKind().equals(ResourcePurchaseEntry.TABLE_NAME)){
			RPurchase p=getPurchase(namespace,t.getKeyrep());
			cv.put(ResourcePurchaseEntry._ID, t.getRowId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, p.getType());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, p.getResourceId());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, p.getQty());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, p.getQuantifier());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, p.getCost());
			cv.put(ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, p.getQtyRemaining());
			db.update(ResourcePurchaseEntry.TABLE_NAME, cv, ResourcePurchaseEntry._ID+"="+t.getRowId(), null);
		}else if(t.getTableKind().equals(CycleResourceEntry.TABLE_NAME)){
			CycleUse c=getCycleUse(namespace,t.getKeyrep());
			cv.put(CycleResourceEntry._ID, t.getRowId());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_TYPE,c.getResource());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID, c.getCycleid());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID, c.getPurchaseId());
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_QTY, c.getAmount());
			//cv.put(DbHelper.CYCLE_RESOURCE_QUANTIFIER, c.get);
			cv.put(CycleResourceEntry.CYCLE_RESOURCE_USECOST, c.getCost());
			db.update(CycleResourceEntry.TABLE_NAME, cv, CycleResourceEntry._ID+"="+t.getRowId(), null);
		}
	}
	private void logDeleteLocal(TransLog tLog, String namespace2) {
		DbQuery.deleteRecord(db, dbh, tLog.getTableKind(), tLog.getRowId());
	}
	private Cycle getCycle(String namespace, String keyrep){
		Cycleendpoint.Builder builder = new Cycleendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Cycleendpoint endpoint = builder.build();
		Cycle c = null;
		try {
			c=endpoint.getCycle(namespace,keyrep).execute();
		} catch (IOException e) {e.printStackTrace();}
		return c;
	}
	private RPurchase getPurchase(String namespace,String keyrep){
		Rpurchaseendpoint.Builder builder = new Rpurchaseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Rpurchaseendpoint endpoint = builder.build();
		RPurchase p = null;
		try {
			p=endpoint.getRPurchase(namespace, keyrep).execute();
		} catch (IOException e) {e.printStackTrace();}
		return p;
	}
	private CycleUse getCycleUse(String namespace, String keyrep){
		Cycleuseendpoint.Builder builder = new Cycleuseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Cycleuseendpoint endpoint = builder.build();
		CycleUse c=null;
		try {
			c=endpoint.getCycleUse(namespace, keyrep).execute();
		} catch (IOException e) {e.printStackTrace();}
		return c;
	}
	
	
	
	
	public void updateCloud_(TransLog t){
		//get obj from cloud
		if(t.getTableKind().equals(CycleEntry.TABLE_NAME)){
			Cycleendpoint.Builder builder = new Cycleendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
			builder = CloudEndpointUtils.updateBuilder(builder);
			builder.build();
			
			//Cycle c=endpoint.getCycle(t.getKeyrep());
		}
		if(t.getTableKind().equals(CycleResourceEntry.TABLE_NAME)){
			Cycleuseendpoint.Builder builderUse = new Cycleuseendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
			builderUse = CloudEndpointUtils.updateBuilder(builderUse);
			builderUse.build();
		}
		if(t.getTableKind().equals(ResourcePurchaseEntry.TABLE_NAME)){
			Rpurchaseendpoint.Builder builderPurchase = new Rpurchaseendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
			builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
			builderPurchase.build();
			
		}
	}
	
	//removes all entries of kinds Cycle,CycleUse,RPurchase,TransLog But UpAcc remains
	public void removeNamespace(String namespace){
		Cycleendpoint.Builder builder = new Cycleendpoint.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
		builder = CloudEndpointUtils.updateBuilder(builder);
		Cycleendpoint endpoint = builder.build();
		try {
			endpoint.deleteAll(namespace).execute();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Cycleuseendpoint.Builder builderUse = new Cycleuseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),			         null);         
		builderUse = CloudEndpointUtils.updateBuilder(builderUse);
		Cycleuseendpoint endpointUse = builderUse.build();
		try {
			
			endpointUse.deleteAll(namespace).execute();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Rpurchaseendpoint.Builder builderPurchase = new Rpurchaseendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
		Rpurchaseendpoint endpointPurchase = builderPurchase.build();
		try {
			endpointPurchase.deleteAll(namespace).execute();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		Translogendpoint.Builder builderTransLog = new Translogendpoint.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderTransLog = CloudEndpointUtils.updateBuilder(builderTransLog);
		Translogendpoint endpointTranslog = builderTransLog.build();
		try {
			endpointTranslog.deleteAll(namespace).execute();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
