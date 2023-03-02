package sn.esmt.httpclientandroid.tools;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import sn.esmt.httpclientandroid.R;
import sn.esmt.httpclientandroid.httpconfig.UsersResponse;


public class MyAdapterUser extends BaseAdapter {

	private ArrayList<UsersResponse> users;
	private LayoutInflater myInflater;
	
	public MyAdapterUser(Context context, ArrayList<UsersResponse> users)
	{
		this.myInflater = LayoutInflater.from(context);
		this.users = users;
	}
	
	@Override
	public int getCount() {
		return this.users.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.users.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView textid;
		TextView textemail;
		TextView textpwd;

		TextView textfirstname;
		TextView textlastname;
		TextView textbirthdate;
		TextView textgenre;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null)
		{
			convertView = myInflater.inflate(R.layout.listitem_user, null);
			holder = new ViewHolder();
			holder.textid = (TextView) convertView.findViewById(R.id.txtid);
			holder.textemail = (TextView) convertView.findViewById(R.id.txtemail);
			holder.textpwd = (TextView) convertView.findViewById(R.id.txtpwd);
			holder.textfirstname = (TextView) convertView.findViewById(R.id.txtfirstname);
			holder.textlastname = (TextView) convertView.findViewById(R.id.txtlastname);
			holder.textbirthdate = (TextView) convertView.findViewById(R.id.txtbirthdate);
			holder.textgenre = (TextView) convertView.findViewById(R.id.txtgenre);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.textid.setText(users.get(position).getId()+"");
		holder.textemail.setText(users.get(position).getEmail());
		holder.textpwd.setText(users.get(position).getPassword());
		holder.textfirstname.setText(users.get(position).getFirstName());
		holder.textlastname.setText(users.get(position).getLastName());
		holder.textbirthdate.setText(users.get(position).getBirthDate());
		holder.textgenre.setText(users.get(position).getGenre());

		return convertView;
		
	}

}
