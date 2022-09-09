package com.mo1ty.medcenterapp.entity.prototype;

import com.mo1ty.medcenterapp.entity.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitsPrototype {

    public static Visit makeVisit(Treatment treatment, Client client, Doctor doctor){
        Visit visit = new Visit();

        visit.setTreatmentDone(treatment);
        visit.setClientVisited(client);
        visit.setDoctorAccepted(doctor);
        visit.setDatetime(Date.from(Instant.now()));

        return visit;
    }

    public static Visit makeLaterVisit(Treatment treatment, Client client, Doctor doctor){
        Visit visit = new Visit();

        visit.setTreatmentDone(treatment);
        visit.setClientVisited(client);
        visit.setDoctorAccepted(doctor);
        visit.setDatetime(Date.from(Instant.now().plusSeconds(86401)));

        return visit;
    }

    public static Visit makeEarlierVisit(Treatment treatment, Client client, Doctor doctor){
        Visit visit = new Visit();

        visit.setTreatmentDone(treatment);
        visit.setClientVisited(client);
        visit.setDoctorAccepted(doctor);
        visit.setDatetime(Date.from(Instant.now().minusSeconds(86401)));

        return visit;
    }

    public static List<Visit> makeAllVisits
            (List<Treatment> treatments, List<Client> clients, List<Doctor> doctors){
        ArrayList<Visit> arrayList = new ArrayList<>();
        for(int i = 0; i<treatments.size() && i<clients.size() && i< doctors.size(); i++){
            arrayList.add(makeEarlierVisit(treatments.get(i), clients.get(i), doctors.get(i)));
            arrayList.add(makeLaterVisit(treatments.get(i), clients.get(i), doctors.get(i)));
        }
        return arrayList;
    }
}
