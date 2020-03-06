package com.example.cibertecproject.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cibertecproject.Modelo.Attendance;
import com.example.cibertecproject.R;

import java.util.List;

public class AdapterAttendanceDetalle extends RecyclerView.Adapter<AdapterAttendanceDetalle.ViewHolder> {

    private List<Attendance> lstAttendance;
    private Context context;

    public AdapterAttendanceDetalle(List<Attendance> lstAttendance, Context context) {
        this.lstAttendance = lstAttendance;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.item_list_attendance_detalle,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Attendance attendance=lstAttendance.get(position);
        holder.txtv_attendance_det_item.setText(String.valueOf(position+1)+".- ");
        holder.txtv_attendance_det_nombres.setText(attendance.getApePaterno()+" "+attendance.getApeMaterno()+", "+attendance.getNombres() );
        //holder.chk_attendance_asistio.setChecked(true);

    }

    @Override
    public int getItemCount() {
        return lstAttendance.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtv_attendance_det_item,txtv_attendance_det_nombres;
        CheckBox chk_attendance_asistio,chk_attendance_noasistio;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtv_attendance_det_item=itemView.findViewById(R.id.txtv_attendance_det_item);
            txtv_attendance_det_nombres=itemView.findViewById(R.id.txtv_attendance_det_nombres);
            chk_attendance_asistio=itemView.findViewById(R.id.chk_attendance_asistio);
            chk_attendance_noasistio=itemView.findViewById(R.id.chk_attendance_noasistio);

            chk_attendance_asistio.setOnClickListener(this);
            chk_attendance_noasistio.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.chk_attendance_asistio:
                    chk_attendance_noasistio.setChecked(false);

                    break;
                case R.id.chk_attendance_noasistio:
                    chk_attendance_asistio.setChecked(false);
                    break;
            }
        }
    }
}
