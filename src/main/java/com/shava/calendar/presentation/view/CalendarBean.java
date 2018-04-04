/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shava.calendar.presentation.view;

import com.shava.calendar.appointment.boundary.ScheduleResource;
import com.shava.calendar.appointment.entity.Schedule;
import com.shava.calendar.converter.DateConverter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author raul
 */
@Named
@ViewScoped
public class CalendarBean implements Serializable {

    @Inject
    ScheduleResource schedulerResource;

    @Inject
    UserInfo userInfo;

    private ScheduleModel model;

    private DefaultScheduleEvent event = new DefaultScheduleEvent();

    private List<Schedule> schedules;

    public void loadSchedule() {
        schedules = schedulerResource.getAllSchedules(userInfo.getUserId());
        model = createSchedule(schedules);
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (DefaultScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    private ScheduleModel createSchedule(List<Schedule> schedules) {
        DefaultScheduleModel task = new DefaultScheduleModel();
        if (schedules != null && !schedules.isEmpty()) {
            schedules.stream().map((taskDay) -> {
                DefaultScheduleEvent defaultTask = new DefaultScheduleEvent(taskDay.getDescription(),
                         DateConverter.converToDate(taskDay.getBeginDate()), DateConverter.converToDate(taskDay.getEndDate()));
                ScheduleDetail scheduleDetail = new ScheduleDetail();
                scheduleDetail.setScheduleId(taskDay.getScheduleId());
                scheduleDetail.setContactId(taskDay.getContactId());
                scheduleDetail.setActivedNotification(taskDay.getIndNotification().toString());
                scheduleDetail.setNotificationType(taskDay.getNotificationType() != null ? taskDay.getNotificationType().toString() : null);
                defaultTask.setData(scheduleDetail);
                defaultTask.setDescription(taskDay.getDescription());
                return defaultTask;
            }).forEachOrdered((defaultTask) -> {
                task.addEvent(defaultTask);
            });
        }
        return task;
    }

    /**
     * @return the model
     */
    public ScheduleModel getModel() {
        return model;
    }

    /**
     * @return the event
     */
    public DefaultScheduleEvent getEvent() {
        return event;
    }
}
