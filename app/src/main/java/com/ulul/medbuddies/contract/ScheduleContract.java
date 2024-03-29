package com.ulul.medbuddies.contract;

import com.ulul.medbuddies.base.BasePresenter;
import com.ulul.medbuddies.base.BaseView;
import com.ulul.medbuddies.model.Medicine;
import com.ulul.medbuddies.model.Schedule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ScheduleContract {
    public interface View extends BaseView{
        void listSchedule(List<Schedule> list);
        void informationStatusAll(List<Schedule> finish, List<Schedule> unfinish);
        void informatinoStatusOne(List<Schedule> finish, List<Schedule> unfinish);
        void listMedicine(List<Medicine> list);
        void listScheduleByPatient(HashMap<String, List<Schedule>> list);
        void listScheduleSuccess(List<Schedule> list);
        void listScheduleFailure(List<Schedule> list);
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

        void getListScheduleDone();
        void getListScheduleSuccess();
        void getListScheduleFailure();
    }
}
