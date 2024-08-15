package com.jorder.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jorder.bank.model.Talent;
import com.jorder.bank.model.User;
import com.jorder.bank.model.dto.TalentsAvaliableProjection;

@Repository
public interface TalentRepository extends JpaRepository<Talent, Long>{
    
    List<Talent> findByUser(User user);


    /*
     select 
        talents."name", 
        talents.description, 
        talents.user_id, 
        schedules.week_day, 
        schedules.time_beguin, 
        schedules.time_end, 
        schedules.qtd_hours 
    from 
        talents 
    inner join 
        schedules 
        on talents.user_id = schedules.user_id 
    where 
        talents.category = 'BELEZA_E_CUIDADOS_PESSOAIS' and 
        schedules.week_day = 'TERÇA' and 
        schedules.time_beguin >= '14:00:00';
    */
    @Query("select t.name as name, t.description as description, t.user as user, s.weekDay as weekDay, s.timeBeguin as timeBeguin, s.timeEnd as timeEnd, s.qtdHours as qtdHours from Talent t inner join Schedule s on t.user = s.user  where t.user.id = 3")
    List<TalentsAvaliableProjection> findTalentsAvaliable();

}
