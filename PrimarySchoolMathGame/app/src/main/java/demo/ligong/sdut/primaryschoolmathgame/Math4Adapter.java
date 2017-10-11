package demo.ligong.sdut.primaryschoolmathgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2017/9/28.
 */
public class Math4Adapter extends BaseAdapter {
    ArrayList<ApkEntity> apk_list;
    LayoutInflater inflater;

    public Math4Adapter(Context context, ArrayList<ApkEntity> apk_list) {
        this.apk_list = apk_list;
        this.inflater = LayoutInflater.from(context);
    }

    public void onDateChange(ArrayList<ApkEntity> apk_list) {
        this.apk_list = apk_list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return apk_list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return apk_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ApkEntity entity = apk_list.get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item9, null);
            holder.name_tv = (TextView) convertView
                    .findViewById(R.id.view01);
            holder.des_tv = (TextView) convertView
                    .findViewById(R.id.view11);
            holder.info_tv = (TextView) convertView
                    .findViewById(R.id.view12);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name_tv.setText(entity.getName());
        holder.des_tv.setText(entity.getDes());
        holder.info_tv.setText(entity.getInfo());
        return convertView;
    }

    class ViewHolder {
        TextView name_tv;
        TextView des_tv;
        TextView info_tv;
    }
}
