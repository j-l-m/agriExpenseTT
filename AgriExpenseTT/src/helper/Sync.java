package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.agriexpensett.upaccendpoint.model.UpAcc;

public class Sync {
	private UpAcc localAcc;
	private UpAcc cloudAcc;
	SQLiteDatabase db;
	DbHelper dbh;
	Context context;
	CloudInterface cloudIF;
	TransactionLog tL;
	public Sync(SQLiteDatabase db, DbHelper dbh,Context context){
		this.db=db;
		this.dbh=dbh;
		this.context=context;
		cloudIF=new CloudInterface(context, db, dbh);
		tL=new TransactionLog(dbh, db);
	}
	public void start(String namespace){
		System.out.println("gonna sync now");
		localAcc=DbQuery.getUpAcc(db);
		cloudAcc=cloudIF.getUpAcc(namespace);
		//both exist
		if(localAcc!=null&&cloudAcc!=null){
			long localUpdate=localAcc.getLastUpdated();
			long cloudUpdate=cloudAcc.getLastUpdated();
			if(localUpdate<cloudUpdate){//cloud is more updated (higher time integer = more recent)
				//get all translogs from cloud greater than or equal to local update
				//then try to do them
				tL.logsUpdateLocal(namespace,localUpdate);
				System.out.println("updating local");
			}else if(cloudUpdate<=localUpdate){//local is more updated
				//get all transaction records from local
				//then try to do them
				System.out.println("updating cloud");
				tL.updateCloud(localUpdate);
			}
		//only local exist
		}else if(localAcc!=null){
			System.out.println("cloud doesnt exist so pushing all to cloud");
			tL.createCloud(namespace);
			cloudIF.insertUpAccC(namespace);
		//only cloud exist
		}else if(cloudAcc!=null){
			System.out.println("local doesnt exist so pulling all from cloud");
			tL.pullAllFromCloud(namespace);
			DbQuery.insertUpAcc(db, namespace);
			
		}else{//both are null
			System.out.println("neither exist so creating new local and pushing all to cloud");
			tL.createCloud(namespace);
			DbQuery.insertUpAcc(db, namespace);
			cloudIF.insertUpAccC(namespace);
		}
	}
}
