package com.example.docbook;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>
{

    private final ArrayList<Doctor> doctorList;

    public DoctorAdapter(ArrayList<Doctor> doctors)
    {
        this.doctorList = doctors;
    }

    @Override
    @NonNull
    public DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // inflate the cards for the doctors.
        //
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DoctorViewHolder holder, int position)
    {
        //  bind data from the doctor list using a position to the view holder class.
        //
        Doctor currentDoctor = doctorList.get(position);

        holder.nameTextView.setText(currentDoctor.getName());
        holder.specialtyTextView.setText(currentDoctor.getSpecialty());
        holder.locationTextView.setText(currentDoctor.getHospitalLocation());
        // Glide.with(holder.itemView).load(currentUser.getImageUrl()).into(holder.doctorImageView);
    }

    @Override
    public int getItemCount()
    {
        return doctorList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder
    {
        TextView nameTextView, specialtyTextView, locationTextView;
        Button bookAppointment;
        ImageView doctorImageView;

        public DoctorViewHolder(View itemView)
        {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txtDoctorName);
            specialtyTextView = itemView.findViewById(R.id.txtSpecialty);
            locationTextView = itemView.findViewById(R.id.txtLocation);
            doctorImageView = itemView.findViewById(R.id.imgDoctorProfile);
            bookAppointment = itemView.findViewById(R.id.btnBookAppointment);
            bookAppointment.setOnClickListener(v ->
            {
                int position = getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION)
                {
                    Intent intent = new Intent (v.getContext(), BookingActivity.class);

                    intent.putExtra("DOCTOR_NAME", nameTextView.getText());
                    intent.putExtra("SPECIALTY", specialtyTextView.getText());
                    intent.putExtra("HOSPITAL_LOCATION", locationTextView.getText());

                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
