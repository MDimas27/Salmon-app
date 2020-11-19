package com.master.salmonapp.repository;


import com.master.salmonapp.entity.TargetTeam;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetTeamRepository extends JpaRepository<TargetTeam, String>{
    
    // TargetTeam findByUserId(String userId);
    // TargetTeam findByUsername(String username);

    // @Query("FROM Target detail WHERE detail.team.user.id = ?1 AND detail.target.id = ?2")
	// List<TargetTeam> findByUserIdAndTargetId(String userId, String targetId);

}
