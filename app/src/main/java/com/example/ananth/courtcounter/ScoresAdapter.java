package com.example.ananth.courtcounter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Ananth on 2/11/2018.
 */

public class ScoresAdapter extends RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>{

    private static final String TAG = ScoresAdapter.class.getSimpleName();


    private final Context mContext;

    private Cursor mCursor;

//    private ScoresAdapterOnClickHandler mClickHandler;

    public ScoresAdapter(Context context){//, //ScoresAdapterOnClickHandler clickHandler){
        mContext = context;
//        mClickHandler = clickHandler;
    }

    @Override
    public ScoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.score_list_item, parent, false);
        view.setFocusable(true);
        return new ScoresViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoresViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        int nameTeamA_int = mCursor.getInt(ScoresActivity.INDEX_TEAM_A_NAME);
        String nameTeamA = getTeamName(nameTeamA_int);
        holder.nameTeamA.setText(nameTeamA);

        int nameTeamB_int = mCursor.getInt(ScoresActivity.INDEX_TEAM_B_NAME);
        String nameTeamB = getTeamName(nameTeamB_int);
        holder.nameTeamB.setText(nameTeamB);

        int scoreTeamA = mCursor.getInt(ScoresActivity.INDEX_TEAM_A_SCORE);
        holder.scoreTeamA.setText(Integer.toString(scoreTeamA));

        int scoreTeamB = mCursor.getInt(ScoresActivity.INDEX_TEAM_B_SCORE);
        holder.scoreTeamB.setText(Integer.toString(scoreTeamB));

    }

    private String getTeamName(int teamInt){
        String retString;
        if (teamInt == 1){
            retString = mContext.getString(R.string.team1);
        }
        else if (teamInt == 2){
            retString = mContext.getString(R.string.team2);
        }
        else if (teamInt == 3){
            retString = mContext.getString(R.string.team3);
        }
        else if (teamInt == 4){
            retString = mContext.getString(R.string.team4);
        }
        else
            retString = "Error";
        return retString;
    }

    @Override
    public int getItemCount() {
        if(mCursor == null) return 0;
        else
            return mCursor.getCount();
    }

    public void swap(Cursor newCursor){
        mCursor = newCursor;
        notifyDataSetChanged();
    }


    public class ScoresViewHolder extends RecyclerView.ViewHolder {

        TextView nameTeamA;
        TextView nameTeamB;
        TextView scoreTeamA;
        TextView scoreTeamB;



        public ScoresViewHolder(View itemView){
            super(itemView);
            nameTeamA = (TextView) itemView.findViewById(R.id.tv_teamA_listitem);
            nameTeamB = (TextView) itemView.findViewById(R.id.tv_teamB_listitem);
            scoreTeamA = (TextView) itemView.findViewById(R.id.tv_scoreA_listitem);
            scoreTeamB = (TextView) itemView.findViewById(R.id.tv_scoreB_listitem);
        }
    }

//    public interface ScoresAdapterOnClickHandler {
//        void onClick();
//    }

}
