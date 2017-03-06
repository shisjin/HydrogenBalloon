package com.dream.will.hydrogenballoon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.will.hydrogenballoon.R;
import com.dream.will.hydrogenballoon.bean.PersonalHomePageGroupedBean;
import com.dream.will.hydrogenballoon.customview.PersonalHomePage_GroupedItemView;

import java.util.List;


public class PersonalHomepage_GroupedAdapter extends RecyclerView.Adapter<PersonalHomepage_GroupedAdapter.HolderView> {

    private List<PersonalHomePageGroupedBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;

    public PersonalHomepage_GroupedAdapter(List<PersonalHomePageGroupedBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public HolderView onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.fragment_personalhomepage_grouped_item,parent,false);
        HolderView holder = new HolderView(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderView holder, int position) {


        holder.groupedDiqu.setText(list.get(position).getDistrict().getName());

        holder.groupedYears.setText(list.get(position).getDistrict().getDuring());
        LinearLayout layout = holder.groupedLayout;
        layout.removeAllViews();

        for (int i = 0; i < list.get(position).getActivities().size(); i++) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
              params.setMargins(10,10,10,10);

             PersonalHomePage_GroupedItemView item = new PersonalHomePage_GroupedItemView(context);
             item.setOrientation(LinearLayout.HORIZONTAL);
             String made_at = list.get(position).getActivities().get(i).getMade_at();
            String cityname;
            if (list.get(position).getActivities().get(i).getDistricts().size()<2){
                cityname = list.get(position).getActivities().get(i).getDistricts().get(0).getName();
            }else {
                cityname = list.get(position).getActivities().get(i).getDistricts().get(1).getName();
            }

             String title=  list.get(position).getActivities().get(i).getTopic();
             List<PersonalHomePageGroupedBean.DataBean.ActivitiesBean.ContentsBean> contentsBeen =
                    list.get(position).getActivities().get(i).getContents();
             item.setGroupedItemView(made_at,cityname,title,contentsBeen );
             layout.addView(item,params);






        }










    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  HolderView extends RecyclerView.ViewHolder {


        TextView groupedDiqu;

        TextView groupedYears;

        LinearLayout groupedLayout;


        public HolderView(View itemView) {
            super(itemView);
            groupedDiqu= (TextView) itemView.findViewById(R.id.grouped_diqu);
            groupedYears= (TextView) itemView.findViewById(R.id.grouped_years);
            groupedLayout= (LinearLayout) itemView.findViewById(R.id.grouped_layout);





        }
    }
}
