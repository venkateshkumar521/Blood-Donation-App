package blood.venky.com.bloodyef;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;



public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.MyHolder>{

    List<Userdetails> listdata;

    public RecyclerviewAdapter(List<Userdetails> listdata)
    {
        this.listdata = listdata;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myview,parent,false);

        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }


    public void onBindViewHolder(MyHolder holder, int position) {
        Userdetails data = listdata.get(position);
        holder.vDesc.setText(data.getDesc());
        holder.vHosp.setText(data.getHosp());
        holder.vAddress.setText(data.getAddress());
        holder.vMobile.setText(data.getMobile());
        holder.vBlood.setText(data.getBlood());
    }

    @Override
    public int getItemCount()

    {
        return listdata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        protected TextView vDesc,vAddress,vHosp,vMobile,vBlood;

        public MyHolder(View itemView) {
            super(itemView);
            vDesc = (TextView) itemView.findViewById(R.id.vDesc);
            vHosp = (TextView) itemView.findViewById(R.id.vHosp);
            vAddress = (TextView) itemView.findViewById(R.id.vAddress);
            vBlood=(TextView)itemView.findViewById(R.id.vBlood);
            vMobile=(TextView)itemView.findViewById(R.id.vMobile);

        }
    }


}