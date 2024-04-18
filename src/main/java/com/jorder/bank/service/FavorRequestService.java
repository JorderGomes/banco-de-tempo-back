package com.jorder.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorder.bank.model.FavorRequest;
import com.jorder.bank.model.Schedule;
import com.jorder.bank.model.StatusFavor;
import com.jorder.bank.repository.FavorRequestRepository;

@Service
public class FavorRequestService {

    @Autowired
    private FavorRequestRepository favorRepository;

    @Autowired
    private ScheduleService scheduleService;

    public List<FavorRequest> getFavorRequests() {
        return favorRepository.findAll();
    }

    public Optional<FavorRequest> getFavorRequestById(Long id) {
        return favorRepository.findById(id);
    }

    public FavorRequest createFavorRequest(FavorRequest favorRequest) {
        favorRequest.setStatusFavor(StatusFavor.SOLICITADA);

        Schedule currentSchedule = scheduleService.createSchedule(favorRequest.getSchedule());
        favorRequest.setSchedule(currentSchedule);

        return favorRepository.save(favorRequest);
    }

    public FavorRequest editFavorRequest(Long id, FavorRequest newFavorRequest) {
        if (!favorRepository.existsById(id)) {
            return null;
        }
        newFavorRequest.setId(id);

        newFavorRequest = favorRepository.save(newFavorRequest);
        return newFavorRequest;
    }

    public void deleteFavorRequest(Long id) {
        favorRepository.deleteById(id);
    }

    public List<FavorRequest> getFavorRequestsByApplicant(Long id_applicant) {
        return favorRepository.findAllFavorRequestByApplicant(id_applicant);
    }

    public FavorRequest updateStatusFavor(StatusFavor newStatus, Long id_favor) throws Exception {
        var optFavorRequest = favorRepository.findById(id_favor);
        
        if (optFavorRequest.isPresent()) {
            var favorRequest = optFavorRequest.get();
            favorRequest.updateStatus(newStatus);
            favorRepository.save(favorRequest);
            return favorRequest;
        }
        
        return null;
    }

    public FavorRequest updateSchedule(Schedule schedule, Long id_favor) throws Exception {
        var optFavorRequest = favorRepository.findById(id_favor);
        if (optFavorRequest.isPresent()) {
            var favorRequest = optFavorRequest.get();
            schedule = this.scheduleService.editSchedule(schedule.getId(), schedule);
            favorRequest.setSchedule(schedule);
            favorRepository.save(favorRequest);
            return favorRequest;
        }
        throw new Exception("Solicitação de favor não encontrado.");
    }
}
