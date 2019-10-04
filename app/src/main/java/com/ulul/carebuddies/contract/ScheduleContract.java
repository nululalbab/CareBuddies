package com.ulul.carebuddies.contract;

import com.ulul.carebuddies.base.BasePresenter;
import com.ulul.carebuddies.base.BaseView;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;

import java.util.Date;
import java.util.List;

public class ScheduleContract {
    public interface View extends BaseView{
        void listSchedule(List<Schedule> list);
        void informationStatusAll(List<Schedule> finish, List<Schedule> unfinish);
        void informatinoStatusOne(List<Schedule> finish, List<Schedule> unfinish);
        void listMedicine(List<Medicine> list);
    }

    public interface Presenter extends BasePresenter {
        void setData(Date start, Date end, String jam, int status, String keterangan,
                     String patient, String medicine);
        void submitData();

        void approvalSchedule(String id, String keterangan);

        void listScheduleById(String id);
        void listScheduleByDate(String date);

        void getInformationStatusAll();
        void getInformationStatusone(String id);

        void getListMedicine();

        void setPatient(String id);
        void setMedicine(String id);
    }
}
